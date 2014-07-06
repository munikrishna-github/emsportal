package com.ems.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
    
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		//try {
			//ConnectionManager manager = (ConnectionManager) Class.forName("com.ems.dao.ConnectionManager").newInstance();
			//ConnectionManager manager1  = new ConnectionManager();
		 Class.forName("com.mysql.jdbc.Driver");   // this will load the class Driver
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?","root","");
			System.out.println("CONNECTION="+conn);
		/*} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e){
			//e.printStackTrace();
			throw e;
		}*/
		
		return conn;
	}
	
	public static void close(Connection connection){
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement statement){
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt){
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
}
	public static void main(String args[]) throws Exception{
	    getConnection();
	}
}