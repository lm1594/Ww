package lkm.ww.comn.abstractview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import lkm.ww.comn.logger.Logger;

 
public abstract class AbstractExcelView extends AbstractView {

    private static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public AbstractExcelView() {
    }
 
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        Workbook workbook = createWorkbook();
        try {
	        setContentType(CONTENT_TYPE_XLSX);
	 
	        buildExcelDocument(model, workbook, request, response);
	 
	        // Set the content type.
	        response.setContentType(getContentType());
	 
	        // Flush byte array to servlet output stream.
	        //ServletOutputStream out = response.getOutputStream();
	        response.getOutputStream().flush();
	        workbook.write(response.getOutputStream());
	        response.getOutputStream().flush();
	        if (workbook instanceof SXSSFWorkbook) {
	            ((SXSSFWorkbook) workbook).dispose();
	        }
        } catch (Exception e) {
        	Logger.getLogger().error(e.getMessage(), e);
        } finally {
        	if(workbook != null) workbook.close();
        }
        
        //out.close();
        // Don't close the stream - we didn't open it, so let the container
        // handle it.
    }

    protected abstract Workbook createWorkbook();

    protected abstract void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
