package cn.edu.lsu.servlet.client;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lsu.bean.Products;
import cn.edu.lsu.dao.ProductsDAO;
import cn.edu.lsu.dao.impl.ProductsDAOImpl;
@WebServlet(name="clientProductsServlet",urlPatterns={"/showProductByPage","/MenuSearchSerlvet","/ClientfindProductById"})
public class ClientProductsServlet extends HttpServlet {


	ProductsDAO pDao = new ProductsDAOImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String url = request.getRequestURI();
	
		if(url.endsWith("showProductByPage")){
			showProductByPage(request,response);		
		}
		if(url.endsWith("MenuSearchSerlvet")){
			MenuSearchSerlvet(request,response);
		}
		if(url.endsWith("ClientfindProductById")){
			ClientfindProductById(request,response);
		}
	}

	private void ClientfindProductById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Products p = pDao.queryById(id);
		request.setAttribute("p",p);
		request.getRequestDispatcher("/client/info.jsp").forward(request,response);
		
	}



	private void MenuSearchSerlvet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("textfield");
		name = new String(name.getBytes("iso8859-1"),"utf-8");
		if("请输入书名".equals(name)){
			request.getRequestDispatcher("/showProductByPage").forward(request, response);
			return;
		}
		List<Products> products = pDao.findBookByName(name);
		int total = products.size();
		request.setAttribute("products",products);
		request.setAttribute("total",total);
		request.getRequestDispatcher("/client/product_search_list.jsp").forward(request, response);
		
	}



	private void showProductByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		System.out.println("category: " + category);
		if(category!=null){
		//category = new String(category.getBytes("iso8859-1"),"utf-8");
		}
		List<Products> products = pDao.findProductByManyCondition(null, category, null, null, null);
		int total = products.size();
		request.setAttribute("ps",products);
		request.setAttribute("totalCount",total);
		if(category==null){
			category="全部商品";
		}
		request.setAttribute("category",category);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
