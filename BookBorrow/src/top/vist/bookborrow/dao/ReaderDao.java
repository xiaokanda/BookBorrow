package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import top.vist.bookborrow.entity.Reader;
import top.vist.bookborrow.utils.JDBCUtils;

public class ReaderDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	//添加读者信息
	public int addReader(Reader reader) {
		String sql = "insert into reader values(?,?,?,?,?,?,?,now())";
		int row = 0;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, reader.getReaderId());
			st.setInt(2, reader.getType());
			st.setString(3, reader.getName());
			st.setInt(4, reader.getAge());
			st.setString(5, reader.getSex());
			st.setString(6, reader.getPhone());
			st.setString(7, reader.getDapt());

			row = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return row;
	}
	//根据读着编号查询读者是否存在
	public int findByReaderId(String readerId) {
		String sql = "select * from reader where readerid = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
			rs = st.executeQuery();
			if (rs.next())
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}
}
