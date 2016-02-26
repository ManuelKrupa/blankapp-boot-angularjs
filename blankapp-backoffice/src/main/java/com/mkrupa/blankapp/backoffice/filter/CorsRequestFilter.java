package com.mkrupa.blankapp.backoffice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Filter class to allow Cross-Origin Requests
 * @author mkrupa
 *
 */
@Component
public class CorsRequestFilter implements Filter {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {
			String vUrl = ((HttpServletRequest)request).getRequestURL().toString();
			String vQueryString = ((HttpServletRequest)request).getQueryString();
			logger.info("URL : " + vUrl + "?" + vQueryString );
		}
		
		// Add some headers to allow Cross-Origin Requests
		HttpServletResponse vResponse = (HttpServletResponse) response;
		vResponse.setHeader("Access-Control-Allow-Origin", "*");
		vResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		vResponse.setHeader("Access-Control-Max-Age", "86400");
		vResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept, X-Auth-Token");
		chain.doFilter(request, vResponse);
	}

	@Override
	public void destroy() {
		
	}	
}
