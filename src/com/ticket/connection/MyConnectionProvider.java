package com.ticket.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnectionProvider {
	//creating connection object
	private Connection con;
	/*
	 * Constructor 
	 */
	public MyConnectionProvider()
	{
		this.con=null;
		String driverName="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ticket_management";
		String username="root";
		String password="";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.con=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	
	}
	/**
	 *  method to get the connection object
	 * @return connection object
	 */
	public Connection getConnection()
	{
		return this.con;
	}

}
