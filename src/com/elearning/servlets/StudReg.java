package com.elearning.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.database.DatabaseConnection;

/**
 * Servlet implementation class StudReg
 */
@WebServlet("/StudReg")
public class StudReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
		int id=0;
		String name=request.getParameter("name");
		String lname=request.getParameter("lname");
		String Contact=request.getParameter("Contact");
		String Branch=request.getParameter("Branch");
		String Year=request.getParameter("Year");
		String email=request.getParameter("email");
		String Password=request.getParameter("Password");
		
		
		
		System.out.println(name);
		System.out.println(lname);
		System.out.println(Contact);
		System.out.println(Branch);
		System.out.println(Year);
		System.out.println(email);
		System.out.println(Password);
		
		
		Connection con=DatabaseConnection.getConnection();
		
		PreparedStatement ps1=con.prepareStatement("insert into registerstudent values(?,?,?,?,?,?,?,?)");
		ps1.setInt(1,id);
		ps1.setString(2,name);
		ps1.setString(3,lname);
		ps1.setString(4,Contact);
		ps1.setString(5,Branch);
		ps1.setString(6,Year);
		ps1.setString(7,email);
		ps1.setString(8,Password);
		
		int n= ps1.executeUpdate();
		
		if(n>0)
		{
			System.out.println(n);
		}
		
		

		
			
	}
	
	catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}


