package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.entity.BookType;
import top.vist.bookborrow.entity.ReaderType;
import top.vist.bookborrow.utils.JDBCUtils;

public class BookTypeDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public List<String> findNameAll() {
		List<String> list = new ArrayList<>();
		String sql = "select typename from booktype";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 查询所有书本类型
	public List<BookType> findAll() {
		List<BookType> list = new ArrayList<>();
		BookType bookType = null;
		String sql = "select * from booktype";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt(1));
				bookType.setTypeName(rs.getString(2));
				list.add(bookType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 讲list集合转成数组
	public String[][] getArrayData(List<BookType> bookType) {
		String[][] data = new String[bookType.size()][2];
		for (int i = 0; i < bookType.size(); i++) {
			BookType r = bookType.get(i);
			data[i][0] = r.getId() + "";
			data[i][1] = r.getTypeName() + "";
			// System.out.println(data[i][0] + " " + data[i][1]);
		}
		return data;
	}

	// 插入书本类别
	public int save(BookType bookType) {
		String sql = "insert into booktype(typename) values(?)";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, bookType.getTypeName());
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

	// 条件查询
	public List<BookType> search(String typeName) {
		List<BookType> list = new ArrayList<>();
		BookType bookType = null;
		String sql = "select * from booktype where typename like ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			typeName = "%" + typeName + "%";
			st.setString(1, typeName);
			rs = st.executeQuery();
			while (rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt(1));
				bookType.setTypeName(rs.getString(2));
				list.add(bookType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	public int update(BookType bookType) {
		String sql = "update booktype set typename = ? where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, bookType.getTypeName());
			st.setInt(2, bookType.getId());
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

	public int delete(BookType bookType) {
		String sql = "delete from booktype where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, bookType.getId());
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

	public Integer findIdByName(String typename) {
		String sql = "select id from booktype where typename = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, typename);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return 0;
	}

	public String findNameById(Integer typeId) {
		String sql = "select typename from booktype where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, typeId);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return "";
	}

}
