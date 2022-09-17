<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
      <%@ page import="java.sql.*"%>
      <%@ page import="com.elearning.servlets.*"%>
<%@ page import="com.elearning.database.*"%>
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

<h3 class="section-title" style="color:white;text-align:center">View Request</h3>	<br>	
<div class="table">
<table border="2" float:"center">
 <thead>
<tr>
<td>Name</td>
<td>LastName</td>
<td>email</td>
<td>Address</td>
<td>status</td>
<td>Status</td>

</tr>

</thead>
     
	
				<div class="row">
				
					<div class="col-md-4">
											
					</div>
					<div class="col-md-4"><br>
					
					
										
						<%

try
{
 String status="pending";
	   Class.forName("com.mysql.jdbc.Driver");
	   System.out.println("Cool");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning","root","");
		Statement stmt=con.createStatement();
	String query="select * from signup where status='"+status+"'";
	ResultSet rs=stmt.executeQuery(query);

	while(rs.next())
	{
		%>
		
		<tr>
		<td><%out.print(rs.getString(2)); %></td>
		<td><%out.print(rs.getString(4)); %></td>
		<td><%out.print(rs.getString(3)); %></td>
		<td><%out.print(rs.getString(5)); %></td>
		<TD><a href="ApproveTeacher.jsp?aid=<%=rs.getInt(1)%>">Approve</a>
		</TD>
		<TD><a href="ApproveTeacher.jsp?did=<%=rs.getInt(1)%>">Disapprove</a>
		</TD>
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