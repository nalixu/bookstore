package cn.edu.lsu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lsu.bean.User;


@WebFilter(value="/admin/*",filterName="Filter1")
public class Filter1 implements Filter {

  
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("la la la,i'm filter1,i'm coming");
        
        
       HttpServletRequest req = (HttpServletRequest) request;
       User user = (User) req.getSession().getAttribute("user");
       if(user!=null && user.getRole().equals("超级用户")){
    	   
    	   chain.doFilter(request, response);
       }else{
    	   HttpServletResponse res = (HttpServletResponse) response;
    	   res.sendRedirect(req.getContextPath()+"/client/login.jsp");
       }
       
		System.out.println("la la la,i'm filter1,i'm leaving");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
