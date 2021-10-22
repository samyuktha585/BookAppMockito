package com.bookapp.repository;

import java.sql.*;

public class BookDAO {
	static Connection connection;
	public static Connection openConnection()
	{//link and load driver
		//create a connection object
		String url = "jdbc:mysql://localhost:3306/training";
		try
		{
		connection = DriverManager.getConnection(url,"root","sammuR@585#");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	return connection;
	}
	public static void  closeConnection()
	{
		try {
			if(connection !=null)
			connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}


}
