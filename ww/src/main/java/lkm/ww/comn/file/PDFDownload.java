package lkm.ww.comn.file;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

/**
 * PDF 다운로드
 * @author lkm
 * @since 2020. 04. 02
 * @see
 * <pre>
 * 제목 : title
 * 타입(다운로드, 뷰) : type [attachment, inline]
 * 내용 : html 리스트 (List<String>)
 * </pre>
 */
public class PDFDownload extends AbstractView {
	
	private final String OS_TYPE = System.getProperty("os.name").toLowerCase();

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.reset();
		
		// 날짜포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 제목
		String title = sdf.format(new Date()) + "_" + model.get("title").toString() + ".pdf";
		// 출력형태(첨부, 인라인)
		String type = model.get("type").toString();
		// HTML 내용 리스트
		List<String> htmls = (List<String>) model.get("htmls");
		
		// Header 셋팅
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "" + type + "; filename=" + java.net.URLEncoder.encode(title, "UTF-8").replaceAll("\\+", "%20") + ";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Expires", "0");
		response.setHeader("Set-Cookie", "fileDownload=true;");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "public");

		ITextRenderer renderer = null;
		OutputStream os = null;
		
        try {
        	renderer = new ITextRenderer();
        	
        	// PDF 출력 시 한글 폰트 지정(기본 : 나눔고딕)
            ITextFontResolver fontResolver = renderer.getFontResolver();
            
            if(OS_TYPE.indexOf("win") >= 0) {
            	fontResolver.addFont("C:/Windows/Fonts/NanumGothic.ttf", BaseFont.IDENTITY_H, true);
            } else if(OS_TYPE.indexOf("nix") >= 0 || OS_TYPE.indexOf("nux") >= 0 || OS_TYPE.indexOf("aix") > 0) {
            	fontResolver.addFont("/usr/share/fonts/NanumGothic.ttf", BaseFont.IDENTITY_H, true);
            }
            
            // HTML 리스트 중 첫 번째 String
            renderer.setDocumentFromString(htmls.get(0).toString());
            renderer.layout();
            os = response.getOutputStream();
            renderer.createPDF(os, false);
            
            // HTML 리스트가 여러 개일 경우 페이지 넘어가면서 PDF 생성
            if(htmls.size() > 1) {
            	for(int i=1; i<htmls.size(); i++) {
            		renderer.setDocumentFromString(htmls.get(i).toString());
                    renderer.layout();
                    renderer.writeNextDocument();
            	}
            }
            
            renderer.finishPDF();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(os != null) os.close();
        }
	}
}