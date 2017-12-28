package cn.edu.lsu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lsu.bean.Order;
import cn.edu.lsu.bean.OrderItem;
import cn.edu.lsu.bean.User;
import cn.edu.lsu.dao.OrderDAO;
import cn.edu.lsu.dao.OrderItemDAO;
import cn.edu.lsu.dao.impl.OrderDAOImpl;
import cn.edu.lsu.dao.impl.OrderItemDAOImpl;

@WebServlet(name="OrdersServlet",urlPatterns={"/findOrders","/findOrderById","/findOrderByManyCondition","/delOrderById"})
public class OrdersServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		String uri = request.getRequestURI();
		
		OrderDAO orderDao = new OrderDAOImpl();
		OrderItemDAO itemDao = new OrderItemDAOImpl();
		
		if(uri.endsWith("findOrders")){

			//调用DAO查找所有订单
			List<Order> orders = orderDao.findAllOrder();
			//订单列表保存到request
			request.setAttribute("orders", orders);
			//将请求转发到list.jsp页面
			request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);	
		}
		else if(uri.endsWith("findOrderById")){
			String id = request.getParameter("id");
			Order order = orderDao.findOrderById(id);
			List<OrderItem> orderItems = itemDao.getOrderItemsById(id);
			request.setAttribute("order", order);
			request.setAttribute("orderItems", orderItems);
			request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);	
		}
		else if(uri.endsWith("findOrderByManyCondition")){
			String id = request.getParameter("id");
			String receiverName = request.getParameter("receiverName");
			List<Order> orders = orderDao.findOrderByManyCondition(id, receiverName);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
		}
		else if(uri.endsWith("delOrderById")){
			
		}

	}
	
	private void delOrderById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		OrderDAO orderDao = new OrderDAOImpl();
		OrderItemDAO itemDao = new OrderItemDAOImpl();
		String id = request.getParameter("id");
		orderDao.delOrderById(id);
		
		response.sendRedirect("findOrders");
		
	}

	private void findOrderByManyCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDao = new OrderDAOImpl();
		OrderItemDAO itemDao = new OrderItemDAOImpl();
		String id = request.getParameter("id");
		String receiverName = request.getParameter("receiverName");
		List<Order> orders = orderDao.findOrderByManyCondition(id, receiverName);
		request.setAttribute("orders",orders);
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);	
	}

	private void findOrderById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDao = new OrderDAOImpl();
		OrderItemDAO itemDao = new OrderItemDAOImpl();
		String id = request.getParameter("id");
		Order order = orderDao.findOrderById(id);
		List<OrderItem> orderItems = itemDao.getOrderItemsById(id);
		request.setAttribute("order",order);
		request.setAttribute("orderItems",orderItems);
		request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);	
	}

	private void findOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDAO orderDao = new OrderDAOImpl();
		OrderItemDAO itemDao = new OrderItemDAOImpl();
		List<Order> orders = orderDao.findAllOrder();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);	
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request,response);
	}

}
