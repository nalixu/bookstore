package cn.edu.lsu.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cn.edu.lsu.bean.Notice;
import cn.edu.lsu.dao.BaseDao;
import cn.edu.lsu.dao.NoticeDAO;
import cn.edu.lsu.util.TypeChange;

public class NoticeDAOImpl extends BaseDao implements NoticeDAO {

	@Override
	public List<Notice> getAllNotices() {
		List<Notice> notices = new ArrayList<Notice>();
		Connection conn = getConnection();
		String sql = "select * from notice";
		//生成语句对象
		PreparedStatement ps =null;
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()){
				Notice n = new Notice();
				n.setN_id(result.getInt("n_id"));
				n.setTitle(result.getString("title"));
				n.setDetails(result.getString("details"));
				n.setN_time(result.getString("n_time"));	
				notices.add(n);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			close(result,ps,conn);
		}
		return notices;
	}

	@Override
	public int addNotice(Notice n) {
		//建立连接
		Connection conn = getConnection();
		String sql = "insert into notice values(null,?,?,?)";
		//生成语句对象
		PreparedStatement ps =null;
		int i=0;
		try {
			ps = conn.prepareStatement(sql );
			ps.setString(1, n.getTitle());
			ps.setString(2, n.getDetails());
			ps.setString(3, n.getN_time());
			
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(null,ps,conn);
		}			
		return i;
	}

	@Override
	public Notice findNoticeById(String n_id) {
		Connection conn = getConnection();
		String sql = "select * from notice where n_id=?";
		//生成语句对象
		PreparedStatement ps =null;
		Notice n = new Notice();
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, n_id);
			result = ps.executeQuery();
			while(result.next()){
				n.setN_id(result.getInt("n_id"));
				n.setTitle(result.getString("title"));
				n.setDetails(result.getString("details"));
				n.setN_time(result.getString("n_time"));				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			close(result,ps,conn);
		}
		return n;
	}

	@Override
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		String sql = "update notice set title=?,details=?,n_time=? where n_id=?";
		//生成语句对象
		PreparedStatement ps =null;
		int i=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, n.getTitle());
			ps.setString(2, n.getDetails());
			ps.setString(3, n.getN_time());
			ps.setInt(4, n.getN_id());
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(null,ps,conn);
		}
		return i;
	}

	@Override
	public int deleteNotice(String n_id) {
		Connection conn = getConnection();
		String sql = "delete from notice where n_id=?";
		//生成语句对象
		PreparedStatement ps =null;
		int i=0;
			try {
				ps = conn.prepareStatement(sql );
				ps.setString(1, n_id);
				i = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				close(null,ps,conn);
			}
		return i;
	}

	
	
	
	
	
	
	
	
	@Override
	public Notice getRecentNotice() {
		Connection conn = getConnection();
		String sql = "select * from notice order by n_time desc limit 0,1";
		//生成语句对象
		PreparedStatement ps =null;
		Notice n = new Notice();
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()){
				n.setN_id(result.getInt("n_id"));
				n.setTitle(result.getString("title"));
				n.setDetails(result.getString("details"));
				n.setN_time(result.getString("n_time"));				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			close(result,ps,conn);
		}
		return n;
	}

}
