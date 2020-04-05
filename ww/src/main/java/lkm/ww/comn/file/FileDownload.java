package lkm.ww.comn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * 파일 다운로드
 * @author lkm
 * @since 2020. 04. 02
 * @see 
 * <pre>
 * 아래의 내용을 Map에 담아서 넘긴다.
 * new ModelAndView("fileDownload", "map", map);
 * 1. 첨부경로 : atPath (String)
 * 2. 원본파일명 : atOrigNm (String)
 * 3. 저장파일명 : atSaveNm (String)
 * </pre>
 */ 
public class FileDownload extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.reset();
		
		InputStream         in  = null;
        ServletOutputStream out = null;
        
        // 파일 저장 경로
        String atPath = model.get("atPath").toString();
        // 원본 파일명
        String atOrigNm = model.get("atOrigNm").toString();
        // 저장된 파일명
        String atSaveNm = model.get("atSaveNm").toString();

        atOrigNm = URLEncoder.encode(atOrigNm, "UTF-8").replaceAll("\\+", "%20");
        
		try {
            File file = new File(atPath + atSaveNm);

            response.setContentType("application/download;charset=utf-8");
            response.setHeader("Set-Cookie", "fileDownload=true;");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + atOrigNm + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            in = new FileInputStream(file);
            out = response.getOutputStream();

            byte[] buffer = new byte[1024 * 2];
            int buff = -1;
            while ((buff = in.read(buffer)) != -1){
                out.write(buffer, 0, buff);
            }
            out.flush();

		} catch (Exception e) {
			e.printStackTrace();
        } finally {
            try { in.close();  } catch (Throwable ignore) {}
            try { out.close(); } catch (Throwable ignore) {}
        }
	}
}