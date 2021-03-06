package lkm.ww.comn.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import lkm.ww.comn.util.StringUtil;


/**
 * XSS Filter Wrapper
 * @author lkm
 * @since 2020. 04. 02.
 */
public class XSSFilterRequestWrapper extends HttpServletRequestWrapper {
	
	public XSSFilterRequestWrapper(HttpServletRequest request) {		
		super(request);	
	}	
	
	public XSSFilterRequestWrapper(MultipartHttpServletRequest request) {
		super(request);
	}
	
	public String[] getParameterValues(String parameter) {		
		String values[] = super.getParameterValues(parameter);		
		
		if (values == null) {			
			return null;		
		}		
		
		for(int ii = 0; ii < values.length; ++ii) {			
			values[ii] = StringUtil.removeXSS(values[ii]);		
		}		
		
		return values;	
	}	
	
	public String getParameter(String parameter) {		
		String value = super.getParameter(parameter);		
		return StringUtil.removeXSS(value);	
	}
}
