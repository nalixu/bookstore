package cn.edu.lsu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.lsu.dao.ProductsDAO;
import cn.edu.lsu.dao.impl.ProductsDAOImpl;
import cn.edu.lsu.bean.Products;
@WebServlet(name="ProductsServlet",urlPatterns={"/showProducts","/addProduct","/findProductByManyCondition","/findProductById","/editProduct","/deleteProduct"})
@MultipartConfig()
public class ProductsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = request.getRequestURI();
	
		ProductsDAO pdao = new ProductsDAOImpl();
		if(url.endsWith("showProducts")){
			
			List<cn.edu.lsu.bean.Products> productsList = pdao.queryAll();
			request.setAttribute("products",productsList);
			request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
			
		}
		else if(url.endsWith("addProduct")){
			String id = UUID.randomUUID().toString();
			String name = request.getParameter("name");
			Double price = Double.valueOf(request.getParameter("price"));
			String category = request.getParameter("category");
			int pnum = Integer.valueOf(request.getParameter("pnum"));
			Part upload =  request.getPart("upload");
			String description = request.getParameter("description");
			
			String contentdis= upload.getHeader("content-disposition");
			String ext = contentdis.substring(contentdis.lastIndexOf("."), contentdis.length()-1);
			String fname = UUID.randomUUID().toString()+ext;
			System.out.println("fname: "  +fname);
			String fpath = getServletContext().getRealPath("/")+"productImg";
			System.out.println("fpath: "  +fpath);
			String imgurl = fpath+"\\"+fname;
			File file = new File(fpath);
			if(!file.exists()){
				file.mkdirs();
			}
			upload.write(imgurl);
			imgurl = imgurl.substring(imgurl.indexOf("productImg"));
			Products products = new Products(id, name, price, category, pnum, imgurl, description);
			pdao.addProducts(products);
			response.sendRedirect("admin/products/add.jsp");
		}
		//多条件查询
		else if(url.endsWith("findProductByManyCondition")){
			String id = request.getParameter("id");
			String category = request.getParameter("category");
			String name = request.getParameter("name");
			String minprice = request.getParameter("minprice");
			String maxprice = request.getParameter("maxprice");
			List<Products> productsList = pdao.findProductByManyCondition(id, category, name, minprice, maxprice);
			request.setAttribute("products",productsList);
			request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
		}
		
		else if(url.endsWith("findProductById")){
			String id = request.getParameter("id");
			Products products = pdao.queryById(id);
			request.setAttribute("p",products);
			request.getRequestDispatcher("admin/products/edit.jsp").forward(request, response);
		}
		else if(url.endsWith("editProduct")){
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			Double price = Double.valueOf(request.getParameter("price"));
			String category = request.getParameter("category");
			int pnum = Integer.valueOf(request.getParameter("pnum"));
			Part upload =  request.getPart("upload");
			String description = request.getParameter("description");
			String contentdis= upload.getHeader("content-disposition");
			String ext = contentdis.substring(contentdis.lastIndexOf("."), contentdis.length()-1);
			String fname = UUID.randomUUID().toString()+ext;
			System.out.println("fname: "  +fname);
			String fpath = getServletContext().getRealPath("/")+"productImg";
			System.out.println("fpath: "  +fpath);
			String imgurl = fpath+"\\"+fname;
			File file = new File(fpath);
			if(!file.exists()){
				file.mkdirs();
			}
			upload.write(imgurl);
			imgurl = imgurl.substring(imgurl.indexOf("productImg"));
			Products products = new Products(id, name, price, category, pnum, imgurl, description);
			pdao.update(products);
			response.sendRedirect("showProducts");
		}
		
		else if(url.endsWith("deleteProduct")){
			String id = request.getParameter("id");
			
			pdao.delProducts(id);
			response.sendRedirect("showProducts");
			
			
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
