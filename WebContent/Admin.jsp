<%@page import="com.elearning.database.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="eLearning is a modern and fully responsive Template by WebThemez.">
<meta name="author" content="webThemez.com">
<title>E-Learning</title>
	<link href="assets/images/1.jpg" rel="icon">
<link rel="favicon" href="assets/images/favicon.png">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<!-- Custom styles for our template -->
<link rel="stylesheet" href="assets/css/bootstrap-theme.css"
	media="screen">
<link rel="stylesheet" type="text/css" href="assets/css/da-slider.css" />
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" type="text/css" href="assets/css/isotope.css"
	media="screen" />
<link rel="stylesheet" href="assets/js/fancybox/jquery.fancybox.css"
	type="text/css" media="screen" />
</head>
<body style="background-image: url('image/23.jpg');background-repeat: no-repeat;
background-size: 100%;">
	<%
		if (session.getAttribute("email") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	<nav class="navbar navbar-inverse" style="background:transparent;background-repeat: no-repeat;
background-size: 100% 100%; background-position: center center;">

		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button><br>
				<a class="navbar-brand"> <a href="index.html" ><img src="assets/images/l1.jpg"
					alt="Techro HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right mainNav"><br>
					 
					<li class="dropdown">
					<li><a href="" onclick="TruncateDatabase()" style="color:#6419B3">Truncate Database</a></li>
					<li><a href="LogoutServlet" style="color:#6419B3">Sign Out</a></li>
				</ul>

			</div>
			<script type="text/javascript">
			function TruncateDatabase(){
				var pword = prompt('insert password');
				if(pword=='admin'){
					alert('database truncated');
				window.open("TruncateDatabase","_self");			
				}
				else alert('wrong pword');
			}</script>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<!-- /.navbar -->


	<div class="container" data-ng-app="documentApp"
		style="margin-left: 300px; margin-top: 50px">
		<div class="row">

			<div class="col-md-8">
				<div class="form-group">
					<span class="btn btn-default btn-file"> <a href="#"
						class="btn btn-two">User Details</a>
					</span>
					<!--   <span class="btn btn-default btn-file">
   								     <a href="FileStatus" class="btn btn-two">Files Staus</a></span>	.
   								   <span class="btn btn-default btn-file">
   								     <a href="Admin.jsp" class="btn btn-two">Back</a></span>	 -->
				</div>

				<%
					//getting resultset from LOADSIGNUPINFO servlet of all user details and displaying in table form to the admin
					//on clicking loadSignupInfo button
					DatabaseConnection db = new DatabaseConnection();
					ResultSet rs = db
							.selectQuery("select * from elearning.signup where status='pending'");
					
						System.out.print("in rs");
				%>
				<table class="table">
 					<tr style="color: black;">
						<td>Id</td>
						<td>Name</td>
						<td>Email</td>
 						<td>Address</td>
						<td>Status</td>
						<td>Approve</td>
						<td>Disapprove</td>
					</tr>

					<%
						int i = 1;
					while (rs.next()) {
					%>

					<tr>
						<td><%=i++%></td>
						<td><%=rs.getString("name")+" "+rs.getString("lname")%></td>
						<td><%=rs.getString("email")%></td>
						<td><%=rs.getString("password")%></td>
						<td><%=rs.getString("status")%></td>
					 
						<TD><a href="ApprovServlet?aid=<%=rs.getInt("id")%>">Approve</a>
						</TD>
						<TD><a href="ApprovServlet?did=<%=rs.getInt("id")%>">Disapprove</a>
						</TD>
						<%-- 	 <TD> <a href="ApprovServlet?uid=<%=rs.getInt(1) %>&uname=<%=rs.getString(2) %>">DeleteUser</a> </TD>
 --%>
					</tr>

					<%
					 
						}
					%>
				</table>



				 


		</div>
	</div>


</body>
</html>