package com.elearning.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.database.DatabaseConnection;
import com.elearning.util.Password;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   doPost(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name=request.getParameter("name");
		String lname=request.getParameter("contact");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		HttpSession session=request.getSession();
		String checkUserExistQuery="select * from signup where email='"+email+"'";
		String password="";
		
		DatabaseConnection db=new DatabaseConnection();
		ResultSet rs=db.selectQuery(checkUserExistQuery);
		String docPath=request.getServletContext().getRealPath("doc");
		System.out.println("docpath::"+docPath);
		UUID sec_key = UUID.randomUUID();
		System.out.println("sec_key :: " + sec_key);
		String privateKey1 = sec_key.toString().substring(0, 4);
		System.out.println("privateKey1 :: " + privateKey1);
		try {
			if(rs.next())
			{
				
				//System.out.println("insertUserDetail already ");
			    session.setAttribute("insertStatus","user already exists");
			    db.closeConnection();
			    response.sendRedirect("Register.jsp");
 			}
			else
			{	
				password=Password.password(email);
				System.out.println("password :: "+password);
 				System.out.println("insertUserDetail ");
				String insertUserDetail="insert into signup(name,email,lname,address,password,privatekey) values('"+name+"','"+email+"','"+lname+"','"+address+"','"+password+"','"+privateKey1+"')";
				int i=db.updateQuery(insertUserDetail);
				db.closeConnection();
				if(i==1){
					session.setAttribute("insertStatus","user successfully registered,Wait for verification");
					 File dir  = new File(docPath+"\\"+email);
					 boolean successful = dir.mkdirs();
					    if (successful)
					    {
					      // created the directories successfully
					      System.out.println("directories were created successfully");
					    }
					    else
					    {
					      // something failed trying to create the directories
					      System.out.println("failed trying to create the directories");
					    }
				}
				else{
					session.setAttribute("insertStatus","user  registration fail");
				}
				response.sendRedirect("Teacherlogin.jsp");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

}
