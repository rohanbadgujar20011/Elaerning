package com.elearning.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection con=null;
	public DatabaseConnection(){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e)
		{
 			e.printStackTrace();
		}
	    try 
	    {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning?" +
			                                   "user=root&password");
		} 
	    catch (SQLException e) 
		{
 			e.printStackTrace();
		}
 	}
	
	public ResultSet selectQuery(String query)
	{
		try
		{
		       PreparedStatement psSelect = con.prepareStatement(query);
		       System.out.println("select query : "+query);
		       return psSelect.executeQuery();
		       
		}
		
		
		catch(SQLException e)
		{
			System.out.println("Here is exception");
		    e.printStackTrace();
		    
		}
		
		return null;
	
	}
	
	public int updateQuery(String query)
	{
		try
		{
		       PreparedStatement psUpdate = con.prepareStatement(query);
		       return psUpdate.executeUpdate();
		       
		}
		
		
		catch(SQLException e)
		{
		    e.printStackTrace();
		}
		
		return 0;
	}
	
	public void closeConnection()
	{
		try 
		{
		  if(con!=null)
		  {
			
				con.close();
		  } 
		  
		}
		catch (SQLException e) 
		  {
				
				e.printStackTrace();
		  }
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}


	
	}
	

 