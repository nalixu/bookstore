package cn.edu.lsu.servlet.client;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lsu.bean.Products;
import cn.edu.lsu.dao.ProductsDAO;
import cn.edu.lsu.dao.impl.ProductsDAOImpl;
import cn.edu.lsu.util.TypeChange;
@WebServlet(name="cartSerlvet",urlPatterns={"/addCart","/changeCart"})
public class CartServlet extends HttpServlet {

	ProductsDAO pDao = new ProductsDAOImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String url = request.getRequestURI();
	
		if(url.endsWith("addCart")){
			String id = request.getParameter("id");
			Map<Products,Integer> cart =   (Map<Products, Integer>) request.getSession().getAttribute("cart");
			if(cart==null){
				cart = new HashMap<Products,Integer>();
			}
			Products p = pDao.queryById(id);
			Integer count = cart.put(p, 1);
			if(count!=null){
				cart.put(p, count+1);
			}
			
			request.getSession().setAttribute("cart", cart);
			
			request.getRequestDispatcher("client/cart.jsp").forward(request, response);
			
		}
		else if(url.endsWith("changeCart")){
			String id = request.getParameter("id");
			int count = TypeChange.stringToInt(request.getParameter("count"));
			Map<Products,Integer> cart = (Map<Products, Integer>) request.getSession().getAttribute("cart");
			Iterator i = cart.entrySet().iterator();
			while(i.hasNext()){
				Map.Entry<Products, Integer> entry = (Entry<Products, Integer>) i.next();
				if(entry.getKey().getId().equals(id)){
					if(count!=0){
						entry.setValue(count);
					}else{
						cart.remove(entry.getKey());
					}
					break;
				}
			}
			response.sendRedirect("client/cart.jsp");
		}
	}


	private void changeCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// 1.�õ���Ʒid
				String id = request.getParameter("id");
				// 2.�õ�Ҫ�޸ĵ�����
				int count = Integer.parseInt(request.getParameter("count").trim());

				// 3.��session�л�ȡ���ﳵ.
				HttpSession session = request.getSession();
				Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");

				//Products p = new Products();
				//p.setId(id);

				Iterator i = cart.entrySet().iterator();
				while(i.hasNext()){
					Map.Entry<Products,Integer> entry = (Entry<Products, Integer>) i.next();
					if(entry.getKey().getId().equals(id)){
						if (count != 0) {
						entry.setValue(count);
						}else{
							cart.remove(entry.getKey());
						}
						break;
					}
					
				}
			
				response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
				return;
	}


	private void addCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		// 2.����service�㷽��������id������Ʒ
	
		
			Products p = pDao.queryById(id);
			// ����Ʒ��ӵ����ﳵ
			HttpSession session = request.getSession();
			//��session�л�ȡ���ﳵ����
			Map<Products, Integer> cart = (Map<Products, Integer>)session.getAttribute("cart");
			//������ﳵΪnull,˵��û����Ʒ�洢�ڹ��ﳵ�У����������ﳵ
			if (cart == null) {
				cart = new HashMap<Products, Integer>();
			}
			//���count���ز�Ϊnull,˵����Ʒ�ڹ��ﳵ�д��ڡ�
			Integer count = cart.put(p, 1);
			
//			if(cart.containsKey(p)){
//				int count = cart.put(p, cart.get(p)+1);
//				System.out.println("count" + count);
//			}else{
//				int count = cart.put(p, 1);
//				System.out.println("count" + count);
//			}
			if (count != null) {
				cart.put(p, count + 1);
			}
			System.out.println("count" + count);
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
			return;
		
	}
		


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
