package cn.edu.lsu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.lsu.bean.OrderItem;
import cn.edu.lsu.dao.BaseDao;
import cn.edu.lsu.dao.OrderItemDAO;

public class OrderItemDAOImpl extends BaseDao implements OrderItemDAO {


	@Override
	public List<OrderItem> getOrderItemsById(String id) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orderitem inner join products on orderitem.product_id = products.id where order_id=?";
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				OrderItem o = new OrderItem();
				o.setBuynum(rs.getInt("buynum"));
				o.setCategory(rs.getString("category"));
				o.setDescription(rs.getString("description"));
				o.setImgurl(rs.getString("imgurl"));
				o.setName(rs.getString("name"));
				o.setOrder_id(rs.getString("order_id"));
				o.setPrice(rs.getDouble("price"));
				o.setProduct_id(rs.getString("product_id"));
				orderItems.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

}
