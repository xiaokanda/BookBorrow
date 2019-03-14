package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.entity.User;
import top.vist.bookborrow.utils.JDBCUtils;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 更具账户和密码查询用户是否存在
	 */
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

	/**
	 * 根据用户名查用户
	 */
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

	/**
	 * 插入用户
	 */
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

	/**
	 * 更新密码
	 */
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

	/**
	 * 删除用户
	 */
	public int delete(int userid) {
		String sql = "delete from `user` where `id` = ? ";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, userid);
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

	/**
	 * 查询所有用户
	 */
	public List<User> findAll() {
		String sql = "select * from user";
		List<User> lists = new ArrayList<User>();
		User user = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(2));
				lists.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return lists;
	}

	/**
	 * 将List转成数组
	 */
	public String[][] getArrayData(List<User> lists) {
		String[][] data = new String[lists.size()][3];
		for (int i = 0; i < lists.size(); i++) {
			User user = lists.get(i);
			data[i][0] = user.getId() + "";
			data[i][1] = user.getUserName();
			data[i][2] = user.getPassword();
		}
		return data;
	}

}
