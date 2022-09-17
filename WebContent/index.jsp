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
<body >
<%
if(session.getAttribute("email")==null)
{
response.sendRedirect("Login.jsp");
}
%>
	<!-- Fixed navbar -->
	<jsp:include page="Navigation.jsp"></jsp:include>
	<!-- /.navbar -->

	 	
 
	<section class="container">
		<div class="heading">
			<!-- Heading -->
			<h2>Our Students</h2>
		</div>
		<div class="row">
			<div class="col-md-4">
				<img src="assets/images/1.jpg" alt="" class="img-responsive">
			</div>
			<div id="courses">
		<div class="container">
			<h2>Activity Stream</h2>
			<div class="row">
				<div class="col-md-4">
					<div class="featured-box">
						<i class="fa fa-cogs fa-2x"></i>
						<div class="text">
							<h3>Best Elearning Experience</h3>
						Elerning is fun.
					It is fastest and robust way to learn.
						</div>
					</div>
				</div>
				 
				<div class="col-md-4">
					<div class="featured-box">
						<i class="fa fa-tachometer fa-2x"></i>
						<div class="text">
							<h3>File Secrecy</h3>
							FIles uploded by students are remain protected from storage owners. Files are in encrypted format so one can never see content of file if he or she don't have key for that document.
						</div>
					</div>
				</div>
		 	
			
				<div class="col-md-4">
					<div class="featured-box">
						<i class="fa fa-eye fa-2x"></i>
						<div class="text">
							<h3>Storage Maintenance</h3>
							Files are stored in three chunks to reduce load of server
						</div>
					</div>
				</div></div></div>
				 
		</div>
	</div>
		 
	</section>
	
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
