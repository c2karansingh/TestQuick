package util;

import java.sql.*;

public class Database 
{
	public static Connection getConnection()
	{
		  
		
//		Registering the driver(above line) is optional after JDBC 4.0. Just put the vendor's JAR file
//		In class path
		Connection connect=null;
		
		try 
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false","root","Karan!1610");  
			if(connect == null)
			{
				System.out.println("Database connection failed to establish");
			}

		
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return connect;
		  		
	}

	
	
}
