package fa.training.daoimpl;

import java.util.logging.*;


import java.sql.*;

class DBContext {
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "1234";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=SMS;";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException ex) {
			Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
		}
		return conn;
	}
}