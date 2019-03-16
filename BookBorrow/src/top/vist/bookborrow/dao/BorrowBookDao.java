package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.entity.BorrowBook;
import top.vist.bookborrow.utils.JDBCUtils;;

public class BorrowBookDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 根据读者id查询借书信息
	 */
	public List<BorrowBook> findBorrowByReaderId(String readerId) {
		List<BorrowBook> lists = new ArrayList<BorrowBook>();
		BorrowBook borrowBook = null;
		String sql = "select isbn,borrowdate from borrowbook where readerid = ? and returndate is null ";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerId);
			rs = st.executeQuery();
			while (rs.next()) {
				borrowBook = new BorrowBook();
				borrowBook.setISBN(rs.getString(1));
				borrowBook.setBorrowDate(rs.getString(2));
				lists.add(borrowBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}
		return lists;
	}

	/**
	 * 将 lists 转为二维数组
	 */
	public String[][] getArrayData(List<BorrowBook> lists) {
		String[][] data = new String[lists.size()][3];
		BookDao bookDao = new BookDao();
		for (int i = 0; i < lists.size(); i++) {
			BorrowBook borrowBook = lists.get(i);
			data[i][0] = borrowBook.getISBN();
			data[i][1] = bookDao.findBookByISBN(borrowBook.getISBN()).getBookName();
			data[i][2] = borrowBook.getBorrowDate();
		}
		return data;
	}

	/**
	 * 保存借阅信息
	 */
	public int save(String readerid, String iSBN) {
		String sql = "insert into borrowbook(readerid,isbn,borrowdate) values(?,?,now())";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerid);
			st.setString(2, iSBN);
			int row = st.executeUpdate();
			if (row !=0 )
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}

		return 0;
	}
	/**
	 * 更新借阅记录的归还时间
	 * */
	public int update(String readerid, String iSBN) {
		String sql = "update borrowbook set  returndate = now() where readerid = ? and isbn = ?";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, readerid);
			st.setString(2, iSBN);
			int row = st.executeUpdate();
			if (row != 0)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.colseAll(conn, st, rs);
		}

		return 0;
	}

}
