package com.elearning.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class registerstudent
 */
@WebServlet("/registerstudent")
public class registerstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerstudent() {
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
		 
		
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Class execute");
			Connection con=DriverManager.getConnection("jdbc:mySql://localhost:3306/elearning","root","");
			System.out.println("Connection establish");
		
		HttpSession session = request.getSession(true);
		
		
		session.setAttribute("registerstudent",Contact);
		
		int i=0;
		
		String sql="select * from registerstudent  where Contact  ='"+Contact+"'";
		

		PrintWriter out=response.getWriter();
		PreparedStatement ps2=con.prepareStatement(sql);
		ResultSet r= ps2.executeQuery();
	
		if(r.next())
		{	
			i=1;
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Existing Contact ....');");
			out.println("location='StudentRegister.jsp';");
			out.println("</script>");
			r.close();
		}

		else if(i==0)
		{
			String sql1="select * from registerstudent where email  ='"+email+"'";
			
			
			PrintWriter out1=response.getWriter();
			PreparedStatement ps3=con.prepareStatement(sql1);
			ResultSet r1= ps3.executeQuery();
		
			if(r1.next())
			{	
 				i=1;
				out1.println("<script type=\"text/javascript\">");
				out1.println("alert('Existing Email ....');");
				out1.println("location='StudentRegister.jsp';");
				out1.println("</script>");
				r1.close();
			}
			else
			{
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
				
				if(n>=1)
				{
					
					response.sendRedirect("StudentRegister.jsp"); 
				}
				else
				{
					
					response.sendRedirect("StudentRegister.jsp"); 
				}
			}
				
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
}
}


