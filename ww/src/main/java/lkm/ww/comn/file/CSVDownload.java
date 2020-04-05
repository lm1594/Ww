package lkm.ww.comn.file;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * CSV 다운로드
 * @author lkm
 * @since 2020. 04. 02
 * @see 
 * <pre>
 * 아래의 내용을 Map에 담아서 넘긴다.
 * new ModelAndView("csvDownload", "map", map);
 * 1. 파일명 : title (String)
 * 2. 컬럼헤더 : colName (String[])
 * 3. 컬럼아이디 : colId (String[])
 * 4. 컬럼내용 : colValue (List<Map<String, Object>>)
 * </pre>
 */
public class CSVDownload extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.reset();
		
		// 제목
		String title = model.get("title").toString();
		// 날짜포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// YYYYMMDD_CSV제목.csv
		title = sdf.format(new Date()) + "_" + title + ".csv";
		
		// CSV 헤더
		String[] colName = (String[]) model.get("colName");
		
		// CSV 컬럼아이디
		String[] colId = (String[]) model.get("colId");
		
		// CSV 내용부
		List<Map<String, Object>> colValue = (List<Map<String, Object>>) model.get("colValue");
		
		response.setContentType("text/csv; charset=MS949");
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(title, "UTF-8").replaceAll("\\+", "%20") + "\"");
        
        ICsvMapWriter writer = new CsvMapWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        
        try {
	        // 헤더 셋팅
	        writer.writeHeader(colName);
	        
	        // 내용부 셋팅
	    	for(int i=0; i<colValue.size(); i++) {
	    		Map<String, Object> col = colValue.get(i);
	    		writer.write(col, colId);
	    	}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if (writer != null) writer.close();
        }
	}
}
