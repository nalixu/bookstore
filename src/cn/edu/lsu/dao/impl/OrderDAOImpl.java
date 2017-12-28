package cn.edu.lsu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.lsu.bean.Order;
import cn.edu.lsu.dao.BaseDao;
import cn.edu.lsu.dao.OrderDAO;

public class OrderDAOImpl extends BaseDao implements OrderDAO {

	@Override
	public void addProduct(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findOrderByUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(String id) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orders inner join user on orders.user_id = user.id where orders.id=?;";
		Order o = new Order();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()){
				o.setId(rs.getString("id"));
				o.setMoney(rs.getDouble("money"));
				o.setOrdertime(rs.getDate("ordertime"));
				o.setPaystate(rs.getInt("paystate"));
				o.setReceiverAddress(rs.getString("receiverAddress"));
				o.setReceiverName(rs.getString("receiverName"));
				o.setReceiverPhone(rs.getString("receiverPhone"));
				o.setUserid(rs.getInt("user_id"));
				o.setUsername(rs.getString("username"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
		System.out.println("订单 "+ o.toString());
		return o;
	}

	@Override
	public List<Order> findAllOrder() {
		List<Order> orders = new ArrayList<Order>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orders inner join user on orders.user_id = user.id;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()){
				Order o = new Order();
				o.setId(rs.getString("id"));
				o.setMoney(rs.getDouble("money"));
				o.setOrdertime(rs.getDate("ordertime"));
				o.setPaystate(rs.getInt("paystate"));
				o.setReceiverAddress(rs.getString("receiverAddress"));
				o.setReceiverName(rs.getString("receiverName"));
				o.setReceiverPhone(rs.getString("receiverPhone"));
				o.setUserid(rs.getInt("user_id"));
				o.setUsername(rs.getString("username"));
				orders.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
		
		
		
		return orders;
	}

	@Override
	public void updateOrderState(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findOrderByManyCondition(String id, String receiverName) {
		List<Order> orders = new ArrayList<Order>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orders inner join user on orders.user_id=user.id where 1=1";
		if(id!=null && id.trim().length()>0){
			sql = sql + " and orders.id='"+id+"'";
		}
		if(receiverName!=null && receiverName.trim().length()>0){
			sql = sql + " and orders.receiverName='"+receiverName+"'";
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()){
				Order o = new Order();
				o.setId(rs.getString("id"));
				o.setMoney(rs.getDouble("money"));
				o.setOrdertime(rs.getDate("ordertime"));
				o.setPaystate(rs.getInt("paystate"));
				o.setReceiverAddress(rs.getString("receiverAddress"));
				o.setReceiverName(rs.getString("receiverName"));
				o.setReceiverPhone(rs.getString("receiverPhone"));
				o.setUserid(rs.getInt("user_id"));
				o.setUsername(rs.getString("username"));
				orders.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
		
		
		
		return orders;
	}

	@Override
	public void delOrderById(String id) {
		// TODO Auto-generated method stub
		
	}

}
