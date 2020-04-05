package lkm.ww.comn.abstractview;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import net.sf.json.JSONObject;

/**
 * JSON VIEW
 * @author 유현상
 *
 */
public class JSONView extends AbstractView {

	public JSONView(){ 
		super(); 
		setContentType( "text/x-json; charset=utf-8"); 
	} 

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {    	
		JSONObject jObj = (JSONObject) model.get("jObj");

		PrintWriter out = null;        
		try{
			response.setContentType( "text/x-json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			out = response.getWriter();
			out.write(jObj.toString());
		}catch(Exception e){
			throw e;
		}finally{
			if(out != null){
				out.flush(); out.close(); out = null;
			}
		}
	}

}
