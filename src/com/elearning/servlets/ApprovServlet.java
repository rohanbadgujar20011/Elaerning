package com.elearning.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.database.DatabaseConnection;
import com.elearning.mail.SendMailBySite;
import com.elearning.util.Delete;

/**
 *  THIS PAGE WILL BE REQUESTED BY ADMIN DASH BOARD ON CLICKING APPROVE,DISAPPROVE OR DELETE BUTTON

 * Servlet implementation class ApprovServlet
 */
@WebServlet("/ApprovServlet")
public class ApprovServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *   THIS PAGE WILL BE REQUESTED BY ADMIN DASH BOARD ON CLICKING APPROVE,DISAPPROVE OR DELETE BUTTON

	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//set the path of doc folder in project specific folder
		
		System.out.println("In ApproveServlet");
		
		String relativePath = "/doc/";
		setUPLOAD_DIRECTORY(getServletContext().getRealPath(relativePath));
HttpSession session=request.getSession();
		String approve = request.getParameter("aid");
		String disapprove = request.getParameter("did");
		String userdelete = request.getParameter("uid");
		String uname=request.getParameter("uname");
		System.out.println(uname);
		int i = 0;
		System.out.println("approve :: "+approve);
		//creating database connection
DatabaseConnection db=new DatabaseConnection();
try {
			 
			//to set user statusto approve
			if (approve != null) {
				i = db.updateQuery("update signup set status='" + "Approve"
						+ "' where id='" + approve + "'");
				if(i==1){
					ResultSet rs=db.selectQuery("select * from signup where id='"+approve+"'");
					if(rs.next()){
						String email=rs.getString("email");
						String password=rs.getString("password");
						String privatekey=rs.getString("privatekey");
						String emailMessage="<h3>Your account has been verified..<br>Your Usernsme : "+email+"<br>Your Password :"+password+"<br>Your PrivateKey :"+privatekey+"</h3>";
					    SendMailBySite.sendMail(email,emailMessage);
					}
				}
			}

			//to set user status to disapprove
			else	if (disapprove != null) {
				ResultSet rs=db.selectQuery("select * from signup where id='"+disapprove+"'");
				if(rs.next()){
					String email=rs.getString("email");
					
					String emailMessage="<h3>Your account has been disapproved..</h3>";
				    SendMailBySite.sendMail(email,emailMessage);
				}
				i = db.updateQuery("delete from signup  where id ='" + disapprove + "'");
			}
			//to delete user and related files
			else	if (userdelete != null) {
				ResultSet rs = db.selectQuery("select filename from filelog where uid='"
								+ userdelete + "'");
				String filename = null;
				if (rs != null) {
					while (rs.next()) {
						 
						filename = getUPLOAD_DIRECTORY()
								+ File.separator + uname+ File.separator+rs.getString(1);
						Delete.delete(filename);
 					}
				}
				db.updateQuery(" delete from filelog where uid='"
						+ userdelete + "'");
				i = db.updateQuery("delete from signup where id='"
						+ userdelete + "'");
			}
			if (i == 0) {
				System.out.println("Something wrong");
			}
			
 			/*request.getRequestDispatcher("Admin.jsp").forward(request,
					response);*/
			ResultSet rs1 = db.selectQuery("select * from signup ");
			session.setAttribute("UsersResultSet", rs1);
			response.sendRedirect("Admin.jsp");

		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String UPLOAD_DIRECTORY = "";

	private void setUPLOAD_DIRECTORY(String realPath) {
		// TODO Auto-generated method stub
		this.UPLOAD_DIRECTORY = realPath;
	}

	private String getUPLOAD_DIRECTORY() {
		// TODO Auto-generated method stub
		return UPLOAD_DIRECTORY;
	}
}
