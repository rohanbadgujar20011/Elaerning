package com.elearning.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxFiles.DeleteException;
import com.elearning.AES.AES;
import com.elearning.SplitMerge.Merge;
import com.elearning.database.DatabaseConnection;
import com.elearning.util.Delete;

/**
 * Servlet implementation class DownloadFileServlet
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	String uid =null; 
	int fid = 0;
	String fname1 =null;
	String action=null;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doget");
		  uid = request.getParameter("uid");
		  fid = Integer.parseInt(request.getParameter("id"));
		  fname1 = request.getParameter("filename");
		  action=request.getParameter("action");
		  HttpSession s=request.getSession();
		  String privakey=request.getParameter("pkey");
		  
		  System.out.println(uid);
		  System.out.println(fid);
		  System.out.println(fname1);
		  System.out.println(action);
 
		  DatabaseConnection db=new DatabaseConnection();
		  ResultSet rs=db.selectQuery("select privatekey from signup where id='"+uid+"'");
		  try {
			if(rs.next()){
		
				if(rs.getString(1).equals(privakey))
				{ 
					doPost(request, response);
				}
				else{
					s.setAttribute("keyResult","Invalid Key");
					response.sendRedirect("DownloadDocs2.jsp");
					System.out.println("keyResult :: "+s.getAttribute("keyResult"));
					
				} 
				
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
		 
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/**
		 * returns the file name which is download from url
		 */
		
		//String fname2 = request.getParameter("fname2");
		String fileName = fname1 ;
		String email = "";
		// this section is to get user email from signup table by uid
	 

		String action = request.getParameter("action");

		System.out.println("in Action " + action);
		String relativePath1 = getServletContext().getRealPath("/doc")  + "//" + fileName;

		String relativePath = null;
		if (action.equals("delete")) {
			DatabaseConnection db=new DatabaseConnection();
			ResultSet rs1=db.selectQuery("select * from filelog where filename='"+fname1+"' and id!='"+fid+"'");
			
			try {
				if(!rs1.next()){
				
				String relativePath2 = getServletContext().getRealPath("/doc")
						+ "\\" + fileName;
				String relativePath3 = getServletContext().getRealPath("/temp")
						+ "\\" + fileName;
				System.out.println("backup file :: "+new File(relativePath3).delete());
				System.out.println("temp file delete"
						+ new File(relativePath2).delete());
				String path = Delete.delete(relativePath1);

				if (path != null) {

					String path1 = null, path2 = null, path3 = null;
					path1 = "(1)" + fileName;
					path2 = "(2)" + fileName;
					path3 = "(3)" + fileName;

					System.out.println("PATH1 :: "+path1);
					System.out.println("PATH1 :: "+path2);
					System.out.println("PATH1 :: "+path3);
					String ACCESS_TOKEN = "MqJ3td0XUpYAAAAAAAAAAafUXercEQINUdD8CAXZTAuffZWT_gdnw2PUZyTuXOXC";

					System.out.println(ACCESS_TOKEN + "access-token");
					DbxRequestConfig config = new DbxRequestConfig(
							"dropbox/java-tutorial", "en_US");
					DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
					try {
						client.files.delete("/DriveOne/"+path1);
						client.files.delete("/DriveSecond/"+path2);
						client.files.delete("/DriveThird/"+path3);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				} 
				db.updateQuery("delete from filelog where id='"+fid+"'");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 response.sendRedirect("UploadDocs.jsp");
 		} else {
			try {

				// Code to merge splitted files to download.
				relativePath = Merge.merge(relativePath1);
				System.out.println("Merging done");
				AES.decrypt(relativePath);

				System.out.println(relativePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File downloadFile = new File(relativePath);

			FileInputStream inStream = new FileInputStream(downloadFile);

			// if you want to use a relative path to context root:

			System.out.println("relativePath = " + relativePath);
 
			// obtains ServletContext
			ServletContext context = getServletContext();

			// gets MIME type of the file
			String mimeType = context.getMimeType(fileName);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);

			// modifies response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			if (action != null) {
				// forces download
				String headerKey = "Content-Disposition";
				String headerValue = String.format(
						action + "; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);

				// obtains response's output stream
				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}

				inStream.close();
				outStream.close();
			}
			downloadFile.delete();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	 

	
	
	
	
}
