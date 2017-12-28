package cn.edu.lsu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.lsu.bean.Products;
import cn.edu.lsu.dao.BaseDao;
import cn.edu.lsu.dao.ProductsDAO;

public class ProductsDAOImpl extends BaseDao implements ProductsDAO{


	Connection connection = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public int addProducts(Products p) {	
		connection = getConnection();
		String sql  = "insert into products values(?,?,?,?,?,?,?)";
		int i=0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getId());
			ps.setString(2,p.getName());
			ps.setDouble(3,p.getPrice());
			ps.setString(4,p.getCategory());
			ps.setInt(5,p.getPnum());
			ps.setString(6, p.getImgurl());
			ps.setString(7,p.getDescription());
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(null, ps, connection);
		
		return i;
	}

	@Override
	public int delProducts(String id) {
		connection = getConnection();
		String sql  = "delete from products where id=?";
		int i=0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(null, ps, connection);
		return i;
	}

	@Override
	public List<Products> queryAll() {
		connection = getConnection();
		String sql  = "select * from products";
		List<Products> productsList =  new ArrayList<Products>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				Double price = rs.getDouble("price");
				String category = rs.getString("category");
				int pnum = rs.getInt("pnum");
				String imgurl = rs.getString("imgurl");
				String description = rs.getString("description");
				Products products = new Products(id, name, price, category, pnum, imgurl, description);
				productsList.add(products);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, ps, connection);
		return productsList;
	}

	@Override
	public Products queryById(String qid) {
		connection = getConnection();
		String sql  = "select * from products where id=?";
		Products products = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,qid);			
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				Double price = rs.getDouble("price");
				String category = rs.getString("category");
				int pnum = rs.getInt("pnum");
				String imgurl = rs.getString("imgurl");
				String description = rs.getString("description");
				 products = new Products(id, name, price, category, pnum, imgurl, description);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, ps, connection);
		return products;
	}

	@Override
	public Products update(Products p) {
		connection = getConnection();
		String sql  = "update products  set name=?,price=?,category=?,pnum=?,imgurl=?,description=? where id=?";
				try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,p.getName());
			ps.setDouble(2, p.getPrice());
			ps.setString(3,p.getCategory());
			ps.setInt(4,p.getPnum());
			ps.setString(5,p.getImgurl());
			ps.setString(6,p.getDescription());
			ps.setString(7,p.getId());
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, ps, connection);
		return p;
	}

	@Override
	public List<Products> findProductByManyCondition(String qid,
			String qcategory, String qname, String minprice, String maxprice) {
		connection = getConnection();
		List<Products> productsList = new ArrayList<Products>();
		String sql = "select * from products where 1=1";
		if(qid!=null&&qid.trim().length()>0){
		sql+= " and id='"+qid.trim()+"'";
		}
		if(qcategory!=null && qcategory.trim().length()>0){
			sql += " and category='"+qcategory.trim()+"'";
			}
		if(qname!=null && qname.trim().length()>0){
			sql += " and name='"+qname+"'";
			
		}
		if(minprice!=null && maxprice!=null && minprice.trim().length()>0 &&maxprice.trim().length()>0){
			sql += " and price between "+minprice+" and "+maxprice;
		}
		System.out.println("sql= "+sql);
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				Double price = rs.getDouble("price");
				String category = rs.getString("category");
				int pnum = rs.getInt("pnum");
				String imgurl = rs.getString("imgurl");
				String description = rs.getString("description");
				Products products = new Products(id, name, price, category, pnum, imgurl, description);
				productsList.add(products);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(rs, ps, connection);
		}
		
		
		return productsList;
	}

	@Override
	public List<Products> getWeekHotProduct() {
		connection = getConnection();
		String sql  = "SELECT products.id,products.name, products.imgurl,SUM(orderitem.buynum) totalsalnum "+ 
         "FROM orderitem,orders,products "+
         "WHERE orderitem.order_id = orders.id "+
                "AND products.id = orderitem.product_id "+
              
                "AND orders.ordertime > DATE_SUB(NOW(), INTERVAL 7 DAY) "+
         "GROUP BY products.id,products.name,products.imgurl "+
         "ORDER BY totalsalnum DESC "+
       " LIMIT 0,2";
		List<Products> productsList =  new ArrayList<Products>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				//Double price = rs.getDouble("price");
				//String category = rs.getString("category");
				//int pnum = rs.getInt("pnum");
				String imgurl = rs.getString("imgurl");
				//String description = rs.getString("description");
				Products products = new Products();
				products.setId(id);
				products.setName(name);
				products.setImgurl(imgurl);
				productsList.add(products);
				System.out.println("product:  "+products.toString());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, ps, connection);
		return productsList;
	}

	@Override
	public List<Products> findBookByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
