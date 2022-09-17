<%@page import="java.util.ArrayList"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="eLearning is a modern and fully responsive Template by WebThemez.">
	<meta name="author" content="webThemez.com">
	<title>E-Learning</title>
	<link href="assets/images/1.jpg" rel="icon">
	<link rel="favicon" href="assets/images/favicon.png">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">
	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen">
	<link rel="stylesheet" type="text/css" href="assets/css/da-slider.css" />
	<link rel="stylesheet" href="assets/css/style.css">
	<link rel="stylesheet" type="text/css" href="assets/css/isotope.css" media="screen" />
	<link rel="stylesheet" href="assets/js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
	
</head>
<body style="background-image: url('images/d.jpg'); background-repeat: no-repeat;
background-size: 100%;color:white;">
  <%
if(session.getAttribute("email")==null)
{
response.sendRedirect("Login.jsp");
}
%>
	<!-- Fixed navbar -->
	<jsp:include page="Navigation.jsp"></jsp:include>
	<!-- /.navbar -->
	 
		<div class="container">
			<div class="row">
				 
				<div class="col-sm-7" ><br>
					<h1 style="margin: auto; font: 300;">Integrity Checking</h1>
				</div>
			</div>
		</div>
	 
		<div class="container" data-ng-app="documentApp" style="margin-left: -100px;">
		<br>
			<div class="row">
				<div class="col-md-4"  data-ng-controller="documentForm">
					
				</div>				
				<div class="col-md-5" >
				 	<div class="panel panel-primary" >
					<table >
					<tr><td>
						<div class="panel-heading" >
							<h2 style="color:black;font-size:50px;">Integrity Failure Result</h2>
						
						<div class="panel-body" style="height: 200px;width: 400px ;">
							<%
      String s="Integrity is not compromised";
							String f="";
      if(session.getAttribute("result")!=null) 
      {ArrayList<String> al=(ArrayList)session.getAttribute("result");
       System.out.println("Using Advanced for loop");
    		   
      for (String filename : al) 
      {  s="";
         out.println(filename+"\n");%><br><%
         f=filename;
      
      %>       <P></P>
						</div></div></td>
						 </tr></table>
						<div  >
						<%}} %>
						<p><%=s %>
							 <form action="TpaAuditServlet" method="post">
							<input type="submit" class="btn btn-primary" value="Scan System" ></form>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- End Footer -->


	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
 	<script src="assets/js/jquery.cslider.js"></script>
	<script src="assets/js/jquery.isotope.min.js"></script>
	<script src="assets/js/fancybox/jquery.fancybox.pack.js" type="text/javascript"></script>
	<script src="assets/js/custom.js"></script>
</body>
</html>
