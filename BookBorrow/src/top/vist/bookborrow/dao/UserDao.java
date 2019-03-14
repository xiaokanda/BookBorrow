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

	// 更具账户和密码查询用户是否存在
	public User login(String username, String password) {
		User user = null;
		String sql = "select * from user where username = ? and password = ?";
		// 创建语句执行者
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			// 设置参数
			st.setString(1, username);
			st.setString(2, password);

			rs = st.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return user;
	}

	public User findUserByName(String text) {
		User user = null;
		String sql = "select * from user where username = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, text);
			rs = st.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return user;
	}

	// 插入用户
	public int save(User user) {
		String sql = "insert into `user`(`username`,`password`) values(?,?)";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUserName());
			st.setString(2, user.getPassword());
			int row = st.executeUpdate();
			if (row == 1) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

	// 更新密码
	public int update(String name, String pwd2) {
		String sql = "update user set `password` = ? where `username` =?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, pwd2);
			st.setString(2, name);
			int row = st.executeUpdate();
			if (row == 1)
				return 1;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

	// 删除用户
	public int delete(String name) {
		String sql = "delete user where username = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			int row = st.executeUpdate();
			if (row == 1)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

}
