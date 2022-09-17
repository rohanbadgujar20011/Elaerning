package com.elearning.servlets;

import java.io.IOException;
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
 * Servlet implementation class teacherlogin
 */
@WebServlet("/teacherlogin")
public class teacherlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherlogin() {
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
		String status="Approve";
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		DatabaseConnection db=new DatabaseConnection();
		String query="select * from signup where email='"+email+"' and password='"+password+"' and status='"+status+"'";
		
		HttpSession session=request.getSession();
		try {
			
			ResultSet rs=db.selectQuery(query);
			if(rs.next())
			{
				
				session.setAttribute("email", email);
				session.setAttribute("uid", rs.getString(1));
				response.sendRedirect("Managing.jsp");
				}
				else{
					session.setAttribute("invalid", "Wrong username and password");
					response.sendRedirect("Teacherlogin.jsp");
				}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

}
