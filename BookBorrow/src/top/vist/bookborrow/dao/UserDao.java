package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import top.vist.bookborrow.entity.User;
import top.vist.bookborrow.utils.JDBCUtils;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	//更具账户和密码查询用户是否存在
	public User login(String username , String password) {
		User user = null;
		String sql = "select * from user where username = ? and password = ?";
		//创建语句执行者
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			//设置参数
			st.setString(1, username);
			st.setString(2, password);
			
			rs = st.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return user;
	}
}
