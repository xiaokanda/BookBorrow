package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.entity.Reader;
import top.vist.bookborrow.utils.JDBCUtils;

public class ReaderDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	// 添加读者信息
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

	// 根据读着编号查询读者是否存在
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

	// 查询所有读着信息
	public List<Reader> findAll() {
		String sql = "select * from reader";
		List<Reader> list = new ArrayList<Reader>();
		Reader reader = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setType(rs.getInt(2));
				reader.setName(rs.getString(3));
				reader.setAge(rs.getInt(4));
				reader.setSex(rs.getString(5));
				reader.setPhone(rs.getString(6));
				reader.setDapt(rs.getString(7));
				reader.setRegDate(rs.getString(8));
				list.add(reader);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 将list<Reader> 转为 string 数组
	public String[][] getArrayData(List<Reader> lists) {
		String[][] data = new String[lists.size()][8];
		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
		Reader reader = null;
		for (int i = 0; i < lists.size(); i++) {
			reader = lists.get(i);
			data[i][0] = reader.getReaderId();
			data[i][1] = readerTypeDao.getNameById(reader.getType());
			data[i][2] = reader.getName();
			data[i][3] = reader.getAge() + "";
			data[i][4] = reader.getSex();
			data[i][5] = reader.getPhone();
			data[i][6] = reader.getDapt();
			data[i][7] = reader.getRegDate();
		}
		return data;
	}

	// 根据readerId 删除 reader
	public int delete(String readerId) {
		String sql = "delete from reader where readerid = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
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

	// 根据id模糊查询 读者
	public List<Reader> findReadersById(String str) {
		String sql = "select * from reader where readerid like ?";
		List<Reader> list = new ArrayList<Reader>();
		Reader reader = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			str = "%"+str+"%";
			st.setString(1, str);
			rs = st.executeQuery();
			while (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setType(rs.getInt(2));
				reader.setName(rs.getString(3));
				reader.setAge(rs.getInt(4));
				reader.setSex(rs.getString(5));
				reader.setPhone(rs.getString(6));
				reader.setDapt(rs.getString(7));
				reader.setRegDate(rs.getString(8));
				list.add(reader);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 根据名字模糊查询读者
	public List<Reader> findReaderByName(String str) {
		String sql = "select * from reader where name like ?";
		List<Reader> list = new ArrayList<Reader>();
		Reader reader = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			str = "%"+str+"%";
			st.setString(1, str);
			rs = st.executeQuery();
			while (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setType(rs.getInt(2));
				reader.setName(rs.getString(3));
				reader.setAge(rs.getInt(4));
				reader.setSex(rs.getString(5));
				reader.setPhone(rs.getString(6));
				reader.setDapt(rs.getString(7));
				reader.setRegDate(rs.getString(8));
				list.add(reader);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 根据读者类型模糊查询读者
	public List<Reader> findReaderByType(String str) {
		String sql = "select * from reader where type in (select id from readertype where typename like ?)";
		List<Reader> list = new ArrayList<Reader>();
		Reader reader = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			str = "%"+str+"%";
			st.setString(1, str);
			rs = st.executeQuery();
			while (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setType(rs.getInt(2));
				reader.setName(rs.getString(3));
				reader.setAge(rs.getInt(4));
				reader.setSex(rs.getString(5));
				reader.setPhone(rs.getString(6));
				reader.setDapt(rs.getString(7));
				reader.setRegDate(rs.getString(8));
				list.add(reader);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}

	// 根据系别模糊查询读者
	public List<Reader> findReaderDept(String str) {
		String sql = "select * from reader where dept like ?";
		List<Reader> list = new ArrayList<Reader>();
		Reader reader = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			str = "%"+str+"%";
			st.setString(1, str);
			rs = st.executeQuery();
			while (rs.next()) {
				reader = new Reader();
				reader.setReaderId(rs.getString(1));
				reader.setType(rs.getInt(2));
				reader.setName(rs.getString(3));
				reader.setAge(rs.getInt(4));
				reader.setSex(rs.getString(5));
				reader.setPhone(rs.getString(6));
				reader.setDapt(rs.getString(7));
				reader.setRegDate(rs.getString(8));
				list.add(reader);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return list;
	}
}
