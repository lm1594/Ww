package lkm.ww.comn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.web.servlet.view.AbstractView;

/**
 * MS Word 문서 다운로드
 * @author lkm
 * @since 2020. 04. 02
 * @see
 * <pre>
 * 1. 제목 : title
 * 2. 내용 : html
 * </pre>
 */
public class WordDownload extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.reset();
		
		InputStream is = null;
		OutputStream out = null;
		File word = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String title = model.get("title").toString();
			// YYYYMMDD_제목.docx
			title = sdf.format(new Date()) + "_" + title + ".docx";
			title = java.net.URLEncoder.encode(title, "UTF-8").replaceAll("\\+", "%20");
			
			String html = model.get("html").toString();
	        
	        WordprocessingMLPackage pack = WordprocessingMLPackage.createPackage();
	        XHTMLImporterImpl importer = new XHTMLImporterImpl(pack);
	
	        pack
	        	.getMainDocumentPart()
	        	.getContent()
	        	.addAll(importer.convert(html, null));
	
	        word = new File(title);
	        pack.save(word);
	        
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + title + "\"");
	        
	        is = new FileInputStream(word);
	        out = response.getOutputStream();
	
	        byte[] buffer = new byte[1024 * 2];
	        int buff = -1;
	        while ((buff = is.read(buffer)) != -1){
	            out.write(buffer, 0, buff);
	        }
	        out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) out.close();
			if(is != null) is.close();
	        word.delete();
		}
	}
}