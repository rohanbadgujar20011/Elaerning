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
<table>
<h1>Table</h1>
<table border="2">
<tr>
<td>Year</td>
<td>Branch</td>
<td>Subject</td>
<td>FileName</td>
<td>Download</td>

</tr>


<%
String year=Get.getYear();
String branch=Get.getBranch();
System.out.println(year);
System.out.println(branch);

try
{
	DatabaseConnection db=new DatabaseConnection();
	String query="select * from pdfs where year='"+year+"' and branch='"+branch+"'";
	ResultSet rs=db.selectQuery(query);
	if(rs.next())
	{
		%>
		<tr>
		
		<td><%out.print(rs.getString(2)); %></td>
		<td><%out.print(rs.getString(3)); %></td>
		<td><%out.print(rs.getString(4)); %></td>
		<td><%out.print(rs.getString(6)); %></td>
		
			
			<td>
		<a href="DownloadFile.jsp?id=<%=rs.getInt(1)%>"> <input type="button"value="Download Documents"></a>
		</td>
		</tr>
		<% 
	}
	
	
}catch(Exception e)
{
	e.printStackTrace();
}

%>

