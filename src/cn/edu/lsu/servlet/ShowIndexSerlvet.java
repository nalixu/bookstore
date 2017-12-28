package cn.edu.lsu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.lsu.bean.Notice;
import cn.edu.lsu.bean.Products;
import cn.edu.lsu.dao.*;
import cn.edu.lsu.dao.impl.*;


@WebServlet("/ShowIndexSerlvet")
public class ShowIndexSerlvet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NoticeDAO nDao = new NoticeDAOImpl();
		ProductsDAO pDao = new ProductsDAOImpl();
		Notice n =nDao.getRecentNotice();
		List<Products> pList = pDao.getWeekHotProduct();
		System.out.println("list size:" + pList.size());
		request.setAttribute("n",n);
		request.setAttribute("pList",pList);
		request.getRequestDispatcher("client/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
