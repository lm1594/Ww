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
 * CORS 필터
 * @author lkm
 * @since 2020. 04. 02
 * @see
 * <pre>
 * 모든 도메인에서 AJAX 요청하여 결과를 가져올 수 있도록
 * Origin 설정되어 있으므로 사용할 경우 수정해서 사용하여야 함.
 * </pre>
 */
public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
         
        filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
	
	/*@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		// 허용 가능 도메인 배열
		String[] allowDomain = {"gwave.childfund.or.kr", "g-wave.childfund.or.kr:8060", "dhsim.childfund.or.kr:8060", "we.childfund.or.kr"};

		for(String domain : allowDomain) {
			String httpDomain = "http://" + domain;
			String httpsDomain = "https://" + domain;
			response.setHeader("Access-Control-Allow-Origin", httpDomain);
			response.setHeader("Access-Control-Allow-Origin", httpsDomain);
		}

        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
         
        filterChain.doFilter(servletRequest, servletResponse);
	}*/
}
