package lkm.ww.comn.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * XSS Custom Filter
 * @author lkm
 * @since 2020.04.02
 * @see
 * <pre>
 * 해당 필터는 Gwave에서만 사용하기 위해 만듦
 * ※ 제외패턴을 걸어 해당 URI에 해당하는 화면은 작성한 개발자가 직접 제어.
 * ※ 나머지는 필터에서 무조건 치환해서 넘길 것.
 * </pre>
 */
public class XSSFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		if(excludePattern(request)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			filterChain.doFilter(new XSSFilterRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
		}
	}

	@Override
	public void destroy() {

	}

	/**
	 * 필터 제외 패턴 확인
	 * @param HttpServletRequest
	 * @return boolean
	 */
	private boolean excludePattern(HttpServletRequest request) {
		String uri = request.getRequestURI().toString().trim();
		String[] excludeUri = excludeUris();

		for(int i=0; i<excludeUri.length; i++) {
			if(uri.equals(excludeUri[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 제외 URI 배열을 반환한다.
	 * @return String[]
	 * @see 해당 URI는 XSS 필터에서 제외한다.
	 */
	private String[] excludeUris() {
		String[] excludeUriArr = {
				"/test/test/test.do"
		};

		return excludeUriArr;
	}
}
