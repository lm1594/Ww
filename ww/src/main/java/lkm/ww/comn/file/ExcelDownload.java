package lkm.ww.comn.file;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import lkm.ww.comn.util.ObjectUtil;
import lkm.ww.comn.util.StringUtil;

/**
 * 엑셀 다운로드
 * @author lkm
 * @since 2020. 04. 02
 * @see 
 * <pre>
 * 아래의 내용을 Map에 담아서 넘긴다.
 * new ModelAndView("excelDownload", "map", map);
 * 1. 파일명/시트명 : title (String)
 * 2. 컬럼명 : colName (List<String>)
 * 3. 내용부 : colValue (List<String[]>)
 * </pre>
 */ 
@Component
public class ExcelDownload extends AbstractXlsxView {
	
	// Number 체크 정규표현식(LONG형)
	private final String NUMBER_REGX = "^[-+]?(0|[1-9][0-9]*)(\\\\.[0-9]+)?([eE][-+]?[0-9]+)?$";

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.reset();
		
		// 엑셀 시트 및 파일명
		String title = model.get("title").toString();
		// 엑셀 셀 타입 배열
		String[] cellTypeArr = (String[]) model.get("cellTypeArr");
		// 엑셀 컬럼 width
		String[] columnWidthArr = (String[]) model.get("columnWidthArr");
		// 엑셀 제목 컬럼 리스트
		List<String[]> colName = (List<String[]>) model.get("colName");	
		// 엑셀 내용 컬럼 리스트
		List<String[]> colValue = (List<String[]>) model.get("colValue");							
		
		String sCurTime = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());
		// 20170101_엑셀파일명.xlsx
        String excelName = sCurTime + "_" + java.net.URLEncoder.encode(title, "UTF-8").replaceAll("\\+", "%20") + ".xlsx";

        // 메모리에 100개 행 유지, 행 수 초과 시 디스크에 기록
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        
        try {
        	// 시트 생성
	        SXSSFSheet sh = wb.createSheet();
	        //sh.trackAllColumnsForAutoSizing();

	        // 제목 로우 생성
	        Row row = sh.createRow(0);
	        Cell cell = null;
	        
	        for(int i = 0; i < colName.size(); i++) {
	        	//sh.setColumnWidth(i, sh.getColumnWidth(i) + (short) 1024);
	        	row = sh.createRow(i);
	        	
	        	String[] data = new String[colName.get(i).length];
	        	data = colName.get(i);
	        	
	        	for(int j = 0; j < data.length; j++) {
	        		cell = row.createCell(j);
	        		cell.setCellValue(data[j]);
	        	}
	        }
	        
	        Pattern pattern = Pattern.compile(NUMBER_REGX);
	        
	        // 정수 포맷 스타일
	        CellStyle numberStyle = wb.createCellStyle();
	        DataFormat numberFormat = wb.createDataFormat();
	        numberStyle.setDataFormat(numberFormat.getFormat("#,##0"));
	        
	        // 실수 포맷 스타일
	        CellStyle doubleStyle = wb.createCellStyle();
	        DataFormat doubleFormat = wb.createDataFormat();
	        doubleStyle.setDataFormat(doubleFormat.getFormat("#,##0.00"));
	        
	        // 날짜 포맷 스타일
	        CellStyle dateStyle = wb.createCellStyle();
	        DataFormat dateFormat = wb.createDataFormat();
	        dateStyle.setDataFormat(dateFormat.getFormat("yyyy/MM/dd HH:mm"));
	        
	        // 내용부 생성
	        for(int i = 0; i < colValue.size(); i++){
	        	// 로우 n개 생성
	            row = sh.createRow(colName.size() + i);
	            
	            // 데이터 Set
	            String[] data = new String[colName.get(0).length];
	            data = colValue.get(i);
	            
	            for(int j = 0; j < colName.get(0).length; j++){	
	            	// 셀 생성
	            	cell = row.createCell(j);
	                
	            	if(StringUtil.isNullOrBlank(data[j])) continue;
	            	
	                Matcher matcher = pattern.matcher(data[j]);
	                
	                if(!ObjectUtil.isNull(cellTypeArr)) {
	                	String cellType = "STRING";
	            		
	            		if(cellTypeArr.length >= j) {
	            			cellType = cellTypeArr[j];
	            		}
	            		
	            		if("STRING".equals(cellType)) {
	            			cell.setCellType(CellType.STRING);
	            			cell.setCellValue(data[j]);
	            		} else if("DOUBLE".equals(cellType)) {
	            			cell.setCellType(CellType.NUMERIC);
	            			cell.setCellStyle(doubleStyle);
	            			cell.setCellValue(Double.parseDouble(data[j]));
	                	} else if("NUMBER".equals(cellType)) {
	            			cell.setCellType(CellType.NUMERIC);
	            			cell.setCellStyle(numberStyle);
	            			cell.setCellValue(Double.parseDouble(data[j]));
	            		} else if("DATE".equals(cellType)) {
	            			cell.setCellStyle(dateStyle);
	            			cell.setCellValue(new SimpleDateFormat("yyyyMMddHHmmss").parse(data[j]));
	            		}
	                } else {
	                	// 데이터가 숫자일 경우 셀 타입을 NUMERIC으로 변경
		                if(matcher.find()) {
		                	cell.setCellType(CellType.NUMERIC);
		                	cell.setCellValue(Double.parseDouble(data[j]));
		                } else {
		                	cell.setCellType(CellType.STRING);
		                	cell.setCellValue(data[j]);
		                }
	                }
	                
	                if(!ObjectUtil.isNull(columnWidthArr)) {
		        		if(columnWidthArr.length >= j) {
		        			sh.setColumnWidth(j, Integer.parseInt(columnWidthArr[j]) * 256);
		        		} else {
		        			sh.setColumnWidth(j, (sh.getColumnWidth(j)) + (short) 1024);
		        		}
		        	}

	                //sh.autoSizeColumn(j);
	            }
	        }
	        
	        response.setHeader("Set-Cookie", "fileDownload=true;");
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    	response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\";charset=\"UTF-8\"");
	    	wb.write(response.getOutputStream());
        } catch (Exception e) {
        	e.printStackTrace();
        } finally { 
        	wb.dispose();
        	if(wb != null) wb.close();
        }
	}
}