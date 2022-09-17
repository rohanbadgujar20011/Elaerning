package com.elearning.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



/**
 * Servlet implementation class Notes
 */
@WebServlet("/UploadNote")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Notes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.sendRedirect("UploadDocs.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Connection con = null;
	        try {
	            // Connection to Database
	            // (See more in JDBC Tutorial).
	        	Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Class execute");
				con=DriverManager.getConnection("jdbc:mySql://localhost:3306/elearning","root","");
				System.out.println("Connection establish");
	 
	 //           String description = request.getParameter("description");
	            
	           // String cid=request.getParameter("cid");
				int id=0;
				
	    		String year=request.getParameter("year");
	    		System.out.println(year);
	    		String branch=request.getParameter("branch");
	    		System.out.println(branch);
	    		String subject=request.getParameter("subject");
	    		System.out.println(subject);
	    		 
	            // Part list (multi files).
	            for (Part part : request.getParts()) {
	                String fileName = extractFileName(part);

	                if (fileName != null && fileName.length() > 0) {
	                    // File data
	                    InputStream is = part.getInputStream();
	                    // Write to file
	                    this.writeToDB(con,id, year,is,branch,subject,fileName);
	                }
	            }
	            con.commit();
	 
	            // Upload successfully!.
	            response.sendRedirect(request.getContextPath() + "/userdashboard.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Error: " + e.getMessage());
	          response.sendRedirect("UploadDocs.jsp");
	        } 
	    }
	 
	    private String extractFileName(Part part) {
	        // form-data; name="file"; filename="C:\Note\file2.zip"
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                
	                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	                clientFileName = clientFileName.replace("\\", "/");
	                int i = clientFileName.lastIndexOf('/');
	                
	                return clientFileName.substring(i + 1);
	            }
	        }
	        return null;
	    }
	 
	   /* private Long getMaxAttachmentId(Connection conn) throws SQLException {
	        String sql = "Select max(a.id) from new a";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        ResultSet rs = pstm.executeQuery();
	        if (rs.next()) {
	            long max = rs.getLong(1);
	            return max;
	        }
	        return 0L;
	    }
	    
	    */
	 
	    private void writeToDB(Connection conn,int id,String year,InputStream is,String branch ,String subject,String fileName  ) throws SQLException 
	    {
	    	
	    	
	    	   
	    	
	    	
	 
	        String sql = "Insert into pdfs values (?,?,?,?,?,?) ";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
			pstmt.setString(2,year );
			pstmt.setString(3, branch);
			pstmt.setString(4, subject);
			pstmt.setBlob(5,is);
			pstmt.setString(6, fileName);
			int n = pstmt.executeUpdate();
	     
	    }
	 
	  /*  private void closeQuietly(Connection conn) {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (Exception e) {
	        }
	}*/

}
