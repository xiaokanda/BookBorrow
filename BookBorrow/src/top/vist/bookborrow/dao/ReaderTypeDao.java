package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.entity.ReaderType;
import top.vist.bookborrow.utils.JDBCUtils;

public class ReaderTypeDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	// 查询所有读者类型名
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
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 根据读者类型名查询读者编号
	public int findIdByName(String typename) {
		String sql = "select id from readertype where typename = ?";
		int id = 1;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, typename);
			rs = st.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return id;
	}

	// 查询所有读者类型
	public List<ReaderType> findAll() {
		List<ReaderType> list = new ArrayList<>();
		ReaderType readerType = null;
		String sql = "select * from readertype";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				readerType = new ReaderType();
				readerType.setId(rs.getInt(1));
				readerType.setTypeName(rs.getString(2));
				readerType.setMaxBorrowNum(rs.getInt(3));
				readerType.setLimit(rs.getInt(4));
				list.add(readerType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 讲list集合转成数组
	public String[][] getArrayData(List<ReaderType> readerTypes) {
		String[][] data = new String[readerTypes.size()][4];
		for (int i = 0; i < readerTypes.size(); i++) {
			ReaderType r = readerTypes.get(i);
			data[i][0] = r.getId() + "";
			data[i][1] = r.getTypeName() + "";
			data[i][2] = r.getMaxBorrowNum() + "";
			data[i][3] = r.getLimit() + "";
		}

		return data;
	}

	// 条件查询
	public List<ReaderType> search(String readerTypeName) {
		List<ReaderType> list = new ArrayList<>();
		ReaderType readerType = null;
		String sql = "select * from readertype where typename like ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			readerTypeName = "%" + readerTypeName + "%";
			st.setString(1, readerTypeName);
			rs = st.executeQuery();
			while (rs.next()) {
				readerType = new ReaderType();
				readerType.setId(rs.getInt(1));
				readerType.setTypeName(rs.getString(2));
				readerType.setMaxBorrowNum(rs.getInt(3));
				readerType.setLimit(rs.getInt(4));
				list.add(readerType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	public int save(ReaderType readerType) {
		String sql = "insert into readertype(typename,maxborrownum,`limit`) values(?,?,?);";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerType.getTypeName());
			st.setInt(2, readerType.getMaxBorrowNum());
			st.setInt(3, readerType.getLimit());
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
	//修改读者类别
	public int update(ReaderType readerType) {
		String sql = "update readertype set typename = ?, maxborrownum = ?, `limit`= ? where id = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerType.getTypeName());
			st.setInt(2, readerType.getMaxBorrowNum());
			st.setInt(3, readerType.getLimit());
			st.setInt(4, readerType.getId());
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

	public int delete(ReaderType readerType) {
		String sql = "delete from readertype where id=?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, readerType.getId());
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
