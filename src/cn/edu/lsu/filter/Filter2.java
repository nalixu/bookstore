package cn.edu.lsu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class Filter2 implements Filter {

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 System.out.println("la la la,i'm filter2,i'm coming");
		chain.doFilter(request, response);
		 System.out.println("la la la,i'm filter2,i'm leaving");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
