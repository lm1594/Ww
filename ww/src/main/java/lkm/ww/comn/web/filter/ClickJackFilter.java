package lkm.ww.comn.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 클릭 재킹 필터
 * @author lkm
 * @since 2020. 04. 02
 * @see
 * <pre>
 * new ClickJackFilter(); // DENY
 * new ClickJackFilter("DENY"); // 이 홈페이지는 다른 홈페이지에서 표시할 수 없음.
 * new ClickJackFilter("SAMEORIGIN"); // 이 홈페이지는 동일한 도메인의 페이지 내에서만 표시할 수 있음
 * new ClickJackFilter("ALLOW-FROM origin"); // 이 홈페이지는 origin 도메인의 페이지에서 포함하는 것을 허옹함
 * </pre>
 */
public class ClickJackFilter implements Filter {
	
	private String mode = "DENY";											// 이 홈페이지는 다른 홈페이지에서 표시할 수 없음.
	// private String mode = "SAMEORIGIN";									// 이 홈페이지는 동일한 도메인의 페이지 내에서만 표시할 수 있음
	// private String mode = "ALLOW-FROM origin";							// 이 홈페이지는 origin 도메인의 페이지에서 포함하는 것을 허용함
	
	public ClickJackFilter() {
		
	}
	
	public ClickJackFilter(String mode) {
		this.mode = mode;
	}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("X-FRAME-OPTIONS", mode);			
        chain.doFilter(request, response);
    }
    
    public void destroy() {
    }
    
    public void init(FilterConfig filterConfig) {
        /*String configMode = filterConfig.getInitParameter("mode");
        if(configMode != null) {
            mode = configMode;
        }*/
    }
}