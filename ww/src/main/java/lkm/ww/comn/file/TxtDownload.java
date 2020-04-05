package lkm.ww.comn.file;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * 텍스트 파일 다운로드
 * @author lkm
 * @since 2020. 04. 02
 */
public class TxtDownload extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.reset();
		
		String fileNm = model.get("fileNm").toString() + ".txt";
		String content = model.get("content").toString();
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20") + ";");
			response.setContentType("text/plain");
			
			PrintWriter txtPrinter = response.getWriter();
			txtPrinter.print(content);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
