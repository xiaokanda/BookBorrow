package top.vist.bookborrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import top.vist.bookborrow.entity.Book;
import top.vist.bookborrow.entity.BookType;
import top.vist.bookborrow.utils.JDBCUtils;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public static void main(String[] args) {
		BookDao bookDao = new BookDao();
		bookDao.getArrayData(bookDao.findAll());
	}

	public int save(Book book) {
		String sql = "insert into book values(?,?,?,?,?,?,?,?);";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, book.getISBN());
			st.setInt(2, book.getTypeId());
			st.setString(3, book.getBookName());
			st.setString(4, book.getAuthor());
			st.setString(5, book.getPublish());
			st.setString(6, book.getStrPublishDate());
			st.setInt(7, book.getPublishNum());
			st.setFloat(8, book.getUnitprice());
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

	public List<Book> findAll() {
		List<Book> list = new ArrayList<>();
		Book book = null;
		String sql = "select * from book";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString(1));
				book.setTypeId(rs.getInt(2));
				book.setBookName(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setPublish(rs.getString(5));
				book.setPublishDate(rs.getDate(6));
				book.setPublishNum(rs.getInt(7));
				book.setUnitprice(rs.getFloat(8));
				list.add(book);
			}
			//System.out.println("--------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String[][] getArrayData(List<Book> lists) {
		String[][] data = new String[lists.size()][8];
		BookTypeDao bookTypeDao = new BookTypeDao();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < lists.size(); i++) {
			Book book = lists.get(i);
			data[i][0] = book.getISBN() + "";
			data[i][1] = bookTypeDao.findNameById(book.getTypeId()) + "";
			data[i][2] = book.getBookName() + "";
			data[i][3] = book.getAuthor() + "";
			data[i][4] = book.getPublish() + "";
			data[i][5] = format.format(book.getPublishDate());
			data[i][6] = book.getPublishNum() + "";
			data[i][7] = book.getUnitprice() + "";
		//	System.out.println(data[i][0] + data[0][1]);
		}
		return data;
	}

}
