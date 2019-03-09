package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.utils.JDBCUtils;

public class ReaderTypeDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public List<String> findNameAll() {
		List<String> list = new ArrayList<>();
		String sql = "select typename from readertype";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				String tmp = rs.getString(1);
				list.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 根据读者类型名查询读者编号
	public int getReaderTypeId(String typename) {
		String sql = "select id from readertype where typename = ?";
		int id = 1;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, typename);
			rs = st.executeQuery();
			if(rs.next())
				id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return id;
	}
}
