package com.elearning.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String i=request.getParameter("email");
		String s=request.getParameter("password");
		System.out.println(i);
		System.out.println(s);

		//String m="password";
		System.out.println(i.equals("admin@gmail.com") && s.equals("admin"));
		System.out.println("");
		if(i.equals("admin@gmail.com") && s.equals("admin") )
		{
				System.out.println("Right");
			   response.sendRedirect("AdminPanel.jsp");
		}
		else
		{
			System.out.println("Wrong");
			request.getSession().setAttribute("msg", "please enter valid password");  
			 response.sendRedirect("Login.jsp");
			
		}
	
	
	}

}
