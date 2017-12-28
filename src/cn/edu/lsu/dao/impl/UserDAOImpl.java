package cn.edu.lsu.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.lsu.bean.User;
import cn.edu.lsu.dao.BaseDao;
import cn.edu.lsu.dao.UserDAO;


public class UserDAOImpl extends BaseDao implements UserDAO {

	@Override
	public User login(String username, String password) {
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		Connection conn = getConnection();
		String sql = "select * from User where username=? and password=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user= null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2,password);
			rs = ps.executeQuery();
			while(rs.next()){
				user= new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setActiveCode(rs.getString("activeCode"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setIntroduce(rs.getString("introduce"));
				user.setRegistTime(rs.getDate("registTime"));
				user.setRole(rs.getString("role1"));
				user.setState(rs.getInt("state"));
				user.setTelephone(rs.getString("telephone"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs,ps,conn);
		return user;
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}

}
