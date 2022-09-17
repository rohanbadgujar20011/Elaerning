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
<!DOCTYPE html>
<html lang="en">
<head>
<style>
.table
{
  margin-left:200px;
}

</style>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="eLearning is a modern and fully responsive Template by WebThemez.">
	<meta name="author" content="webThemez.com">
	<title>E-Learning</title>
	<link href="assets/images/1.jpg" rel="icon">
	<meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="keywords" content="Sway Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
      <script>
         addEventListener("load", function () {
         	setTimeout(hideURLbar, 0);
         }, false);
         
         function hideURLbar() {
         	window.scrollTo(0, 1);
         }
      </script>
      
      <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
      
      <link href="css/fontawesome-all.min.css" rel="stylesheet" type="text/css" media="all">
      
      <link href="css/lsb.css" rel="stylesheet" type="text/css">
      
      <link href="css/style.css" rel='stylesheet' type='text/css' media="all">
      
      <link href="//fonts.googleapis.com/css?family=Montserrat:300,400,500" rel="stylesheet">
      
      <link href="//fonts.googleapis.com/css?family=Felipa" rel="stylesheet">
</head>

<body style="background-image: url('images/bg.jpg');" background-size:cover
background-attachment:fixed;>
	<!-- Fixed navbar -->
	   <header>
     
      
         
       
            <nav class="navbar navbar-expand-lg navbar-light">
               <div class="hedder-up">
                  <a href="index.html" ><img src="assets/images/l1.jpg" alt="E-Learning Portal"></a>
               </div>
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                  <ul class="navbar-nav ">
                  
                     
                     <li class="nav-item active">
                        <a class="nav-link" href="index.html"><b>Logout </b> <span class="sr-only">(current)</span></a>
                        
                     </li>
                     
         		  </ul>
                  
               </div>
            </nav>
         
      </div>
      </div>
      </header><br><br>
	<!-- /.navbar -->

	
	<!-- container -->

<h3 class="section-title" style="color:white;text-align:center">View Notes</h3>	<br>	
<div class="table">
<table border="2" float:"center">

<tr>
<td>Year</td>
<td>Branch</td>
<td>Subject</td>
<td>FileName</td>
<td>Download</td>

</tr>


     
	
				<div class="row">
				
					<div class="col-md-4">
											
					</div>
					<div class="col-md-4"><br>
					
					
										
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
	while(rs.next())
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
</div>
		</div>
		</table>

						
															
								<h3><%if(session.getAttribute("insertStatus")!=null)
								out.print(session.getAttribute("insertStatus"));
								session.setAttribute("insertStatus",null);
								%>
								<%
								if(session.getAttribute("invalid")!=null)
								out.print(session.getAttribute("invalid"));
								session.setAttribute("invalid",null);
								%>
								</h3>									
						</form>
					</div>
					<div class="col-md-3">
																	
					</div>
				</div>
			</div>
			</div>
	<!-- /container -->
	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/google-map.js"></script>


</body>
</html>
