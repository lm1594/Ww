package lkm.ww.comn.abstractview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public final class NULLView extends AbstractView {	
	
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
	}	
	
	protected void renderMergedOutputModel(Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
	}
}