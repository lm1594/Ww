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
 * HTML Tag Filter
 * @author lkm
 * @since 2020. 04. 02
 */
public class HTMLTagFilter implements Filter {
	
	protected FilterConfig config;
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest) request), response);
	}
}
