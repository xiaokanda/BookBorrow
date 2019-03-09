package top.vist.bookborrow.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	/**
	 * mysql数据库 驱动程序包名：MySQL-connector-Java-x.x.xx-bin.jar
	 * 驱动程序类名: com.mysql.jdbc.Driver JDBC
	 * URL: jdbc:mysql://<host>:<port>/<database_name> 默认端口3306，如果服务器使用默认端口则port可以省略
	 * MySQL Connector/J
	 * Driver 允许在URL中添加额外的连接属性jdbc:mysql://<host>:<port>/<database_name>?property1=value1&property2=value2
	 */
	private static String dbDriver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/bookborrow?useUnicode=true&characterEncoding=utf-8";
	private static String username = "root"; // 数据库用户名
	private static String password = "root"; // 数据库密码

	private JDBCUtils() {
	};

	static {
		/**
		 * 注册驱动
		 */
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取 Connection
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * 释放资源
	 */
	public static void colseAll(Connection conn, Statement st, ResultSet rs) {
		colseResultSet(rs);
		colseStatement(st);
		colseConnection(conn);
	}

	/**
	 * 释放 ResultSet
	 */
	public static void colseResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rs = null;
	}

	/**
	 * 释放 Statement
	 */
	public static void colseStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		st = null;
	}

	/**
	 * 释放 Connection
	 */
	public static void colseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}
}
