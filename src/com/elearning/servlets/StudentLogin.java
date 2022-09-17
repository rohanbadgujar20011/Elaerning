package com.elearning.servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.database.DatabaseConnection;

/**
 * Servlet implementation class StudentLogin
 */
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		DatabaseConnection db=new DatabaseConnection();
		String query="select * from registerstudent where email='"+email+"' and password='"+password+"'";
		
          try {
			
			ResultSet rs=db.selectQuery(query);
			if(rs.next())
			{
				String branch=rs.getString(5);
				
				String year=rs.getString(6);
				System.out.println(year);
				Get.setYear(year);
				System.out.println(branch);
				Get.setBranch(branch);
				response.sendRedirect("DownloadNotes.jsp");
				
				
			}
			else
			{
				request.getSession().setAttribute("msg", "please enter valid password");  
				 response.sendRedirect("Studentlogin.jsp");
			}
		
	}catch(Exception e)
          {
		e.printStackTrace();
          }

}
}