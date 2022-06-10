package DAO;

import java.sql.*;

public class DBConnection {
	 public static Connection getMySQLConnection() throws SQLException,
     ClassNotFoundException {
	 String userName = "root";
	 String password = "p07112001";
	 return getMySQLConnection(userName, password);
	}
	 
	 public static Connection getMySQLConnection(String userName, String password) 
			 throws SQLException, ClassNotFoundException {
	     Class.forName("com.mysql.cj.jdbc.Driver");
	     String connectionURL = "jdbc:mysql://localhost:3306/doan";
	     Connection conn = DriverManager.getConnection(connectionURL, userName,password);
	     return conn;
	 }
}
