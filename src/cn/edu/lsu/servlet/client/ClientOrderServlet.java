package cn.edu.lsu.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lsu.bean.User;


@WebServlet({ "/createOrder", "/findOrderByUser", "/clientFindOrderById" })
public class ClientOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = request.getRequestURI();
	
		if(url.endsWith("createOrder")){
			User user = (User) request.getSession().getAttribute("user");
			if(user==null){
				
				response.sendRedirect("client/login.jsp");
			}
		}
		if(url.endsWith("findOrderByUser")){
			
		}
		if(url.endsWith("clientFindOrderById")){
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
