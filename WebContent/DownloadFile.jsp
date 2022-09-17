<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.elearning.servlets.*"%>
<%@ page import="com.elearning.database.*"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Image"%>

<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>

<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%					 
	int id  = Integer.parseInt(request.getParameter("id"));
 
	         
	        try {

				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Class execute");
				Connection con=DriverManager.getConnection("jdbc:mySql://localhost:3306/elearning","root","");
	        	
                String sql = "SELECT * FROM pdfs  WHERE id = ?";
	            PreparedStatement statement = con.prepareStatement(sql);
	            statement.setInt(1, id);
	 
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {

	            	
	            	String fileName = result.getString("filename");
	                Blob blob = result.getBlob("file");
	                InputStream inputStream = blob.getBinaryStream();
	                int fileLength = inputStream.available();
	                 
	                System.out.println("fileLength = " + fileLength);
	 
	                ServletContext context = getServletContext();
	 
	                // sets MIME type for the file download
	                String mimeType = context.getMimeType(fileName);
	                if (mimeType == null) {        
	                    mimeType = "application/octet-stream";
	                }              
	                 
	                // set content properties and header attributes for the response
	                response.setContentType(mimeType);
	                response.setContentLength(fileLength);
	                String headerKey = "Content-Disposition";
	                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
	                response.setHeader(headerKey, headerValue);
	 
	                // writes the file to the clients
	                OutputStream outStream = response.getOutputStream();
	                 
	                byte[] buffer = new byte[1024];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outStream.write(buffer, 0, bytesRead);
	                }
	                 
	                inputStream.close();
	                outStream.close();             
	            } else {
	                // no file found
	                response.getWriter().print("File not found for the id: " + id);  
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            response.getWriter().print("SQL Error: " + ex.getMessage());
	        }
	       
	           

%>