package cn.edu.lsu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.lsu.bean.Notice;
import cn.edu.lsu.dao.NoticeDAO;
import cn.edu.lsu.dao.impl.NoticeDAOImpl;
import cn.edu.lsu.util.TypeChange;

@WebServlet({ "/AddNoticeServlet", "/ListNoticeServlet", "/FindByIdNoticeServlet", "/EditNoticeServlet", "/DeleteNoticeServlet" })
public class NoticeServlet extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String uri = request.getRequestURI();
		NoticeDAO noticeDAO = new NoticeDAOImpl();
		request.setCharacterEncoding("utf-8");
		//插入一条记录
		if(uri.endsWith("AddNoticeServlet")){
			//获取表单中的参数
			String title = request.getParameter("title");
			String details = request.getParameter("details");
			//得到系统日期和时间
			Date date = new Date();
			//对日期进行格式化
			String n_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			System.out.println("Date" + date);
			System.out.println("n_time" + n_time);
			
			//将获取的参数组成Notice对象
			Notice n = new Notice(1,title,details,n_time);	
			
			//调用noticeDAO将notice对象插入到数据库
			int i = noticeDAO.addNotice(n);
			if(i>0){
				System.out.println("记录插入成功");
			}else{
				System.out.println("记录插入失败");
			}
			
			//将响应重定向到添加公告页面
			response.sendRedirect("admin/notices/add.jsp");
		}
		//删除指定记录
		else if(uri.endsWith("DeleteNoticeServlet")){
			//得到页面上传递的参数（要删除公告的ID）
			String id = request.getParameter("id");
			//调用DAO删除指定ID的公告记录
			int i = noticeDAO.deleteNotice(id);
			if(i>0){
				System.out.println("删除记录成功");
			}else{
				System.out.println("删除记录失败");
			}
			
			//将响应重定向到ListNoticeServlet
			response.sendRedirect("ListNoticeServlet");			
			
		}
		else if(uri.endsWith("FindByIdNoticeServlet")){
			//得到页面上传递的参数（要查找公告的ID）
			String id = request.getParameter("id");
			
			//按ID查找公告记录
			Notice n = noticeDAO.findNoticeById(id);
			System.out.println(n.toString());
			
			//将查找到的公告对象保存到request
			request.setAttribute("n",n);
			
			//将请求转发到edit.jsp页面
			request.getRequestDispatcher("admin/notices/edit.jsp").forward(request, response);
		}
		else if(uri.endsWith("ListNoticeServlet")){
			//查找所有公告记录
			List<Notice> notices = noticeDAO.getAllNotices();	
			//打印所有公告记录
			for(int i=0;i<notices.size();i++){
				System.out.println(notices.get(i).toString());
			}
			request.setAttribute("notices", notices);
			request.getRequestDispatcher("admin/notices/list.jsp").forward(request, response);
		}
		//更新公告
		else if(uri.endsWith("EditNoticeServlet")){
			//得到表单中的参数
			String idstr = request.getParameter("id");
			int id = TypeChange.stringToInt(idstr);
			String title = request.getParameter("title");
			String details = request.getParameter("details");
			Date date = new Date();
			//对日期进行格式化
			String n_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			
			//使用参数实例化notice对象
			Notice n = new Notice(id,title,details,n_time);	
			//调用DAO更新公告对象
			int i = noticeDAO.updateNotice(n);
			if(i>0){
				System.out.println("更新记录成功");
			}else{
				System.out.println("更新记录失败");
			}
			
			//将响应重定向到ListNoticeServlet
			response.sendRedirect("ListNoticeServlet");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
