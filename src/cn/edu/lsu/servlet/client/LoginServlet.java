package cn.edu.lsu.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lsu.bean.User;
import cn.edu.lsu.dao.UserDAO;
import cn.edu.lsu.dao.impl.UserDAOImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO uDao = new UserDAOImpl();
		User user = uDao.login(username, password);
		if(user==null){
			response.sendRedirect("client/login.jsp");
		}else{
			request.getSession().setAttribute("user", user);
			if(user.getRole().equals("超级用户")){
				response.sendRedirect("admin/login/home.jsp");
			}else{
				response.sendRedirect("client/myAccount.jsp");
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
