package com.elearning.servlets;

import java.io.IOException;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubsyso
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		DatabaseConnection db=new DatabaseConnection();
		String query="select * from signup where email='"+email+"' and password='"+password+"'";
		
		HttpSession session=request.getSession();
		try {
			
			ResultSet rs=db.selectQuery(query);
			if(rs.next())
			{
				if(!rs.getString("status").equals("pending"))
				{
				session.setAttribute("email", email);
				session.setAttribute("uid", rs.getString(1));
				response.sendRedirect("Managing.jsp");
				}
				else{
					session.setAttribute("invalid", "Wrong username and password");
				}
			}
			else if(email.equals("admin")&password.equals("admin"))
			{
				session.setAttribute("email", email);
			//	session.setAttribute("uid", rs.getString(1));
				
				response.sendRedirect("Admin.jsp");
			}
			else if(email.equals("tpa")&password.equals("tpa"))
			{
				session.setAttribute("email", email);
			//	session.setAttribute("uid", rs.getString(1));
				
				response.sendRedirect("TpaAudit.jsp");
			}
			
			else
			{
				session.setAttribute("invalid", "Wrong username and password");
				response.sendRedirect("Login.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

}
