<%@page import="java.sql.ResultSet"%>
<%@page import="com.elearning.database.DatabaseConnection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
<script type="text/javascript" src="angularjs/angular.min.js"></script>
<script>
	
	    function downloadDialog(id,action,filename,uid){
 	    	$('.hid').val(id);
	    	$('.hid1').val(action);
	    	$('.hid2').val(filename);
	    	$('.hid3').val(uid);
	    	$('.modal').modal('show');
	    	
	    }
	    
	    function submitKey()
	    {
	    	
	      $('.model').modal('hide');
	  	  document.form1.submit();
	  	  $('.hid').val(null);
	  	  $('.hid1').val(null);
	  	  $('.hid2').val(null);
	  	  $('.hid3').val(null);
 	  	  $('.pkey').val('');
	    }
	 
</script>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect("Login.jsp");
	}
%>
</head>
<body style="background-image: url('image/c.jpg');background-repeat: no-repeat;
background-size: 100%;color:black;">



	<div class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Enter Private Key ::</h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
						<form action="DownloadFileServlet" name="form1" method="Get">
							<input type="password" name="pkey" class="form-control pkey">
							<input type="hidden" class="hid" name="id"><br> <input
								type="hidden" class="hid1" name="action"><br> <input
								type="hidden" class="hid2" name="filename"><br> <input
								type="hidden" class="hid3" name="uid"><br>
							<p>
								<button type="button" class="btn btn-success"
									onclick="submitKey()">Submit</button>
							</p>
						</form>

					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Fixed navbar -->
	<jsp:include page="Navigation.jsp"></jsp:include>
	<!-- /.navbar -->

	<div class="container">
		<div class="row">
			<div class="col-md-5"></div>
			<div class="col-sm-7">
				<h1>Upload Documents</h1>
			</div>
		</div>
	</div>
<div class="container" data-ng-app="documentApp"
		style="margin-left: 300px; margin-top: 50px">
		<div class="row">

			<div class="col-md-8">
			
				
					 <form class="form-light mt-20" method="post" action="${pageContext.request.contextPath}/UploadNote"
        enctype="multipart/form-data">
					
					  <label class="col-sm-2 col-sm-2 control-label">Select Year</label> 
					<div class="col-sm-10">			
               <select name="year" class="form-control round-form" required>
                <option value="FY">FY</option>
               <option value="SY">SY</option>
               <option value="TY">TY</option>
               <option value="BE">BE</option>
               </select>
               </div><br><br>
               
                <label class="col-sm-2 col-sm-2 control-label">Select Branch</label> 
					<div class="col-sm-10">			
               <select name="branch" class="form-control round-form" required>
                <option value="Computer">Computer</option>
               <option value="IT">IT</option>
               <option value="Mechanical">Mechanical</option>
               <option value="Electrical">Electrical</option>
               </select>
               </div><br><br>
               
               
                <label class="col-sm-2 col-sm-2 control-label">Select Subject</label> 
					<div class="col-sm-10">			
               <select name="subject" class="form-control round-form" required>
                <option value="M1">M1</option>
               <option value="M2">M2</option>
               <option value="M3">M3</option>
               <option value="SOM">SOM</option>
               <option value="TOM">TOM</option>
               </select>
               </div>
               
					<div class="form-group">
						<label class="header">Attach Documents <span>*(.txt,.pdf,image)</span></label>
										<input type="file" id="email" name="file" placeholder="Mail@example.com" title="Please attached docs" required>
						
					</div>
					<hr>
					<button type="submit" class="btn btn-two">Upload</button>
					<button type="reset" class="btn btn-two">Cancel</button>
					<p>
						<br />
					</p>
				</form>
					<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- End Footer -->


	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<script src="assets/js/jquery.cslider.js"></script>
	<script src="assets/js/jquery.isotope.min.js"></script>
	<script src="assets/js/fancybox/jquery.fancybox.pack.js"
		type="text/javascript"></script>
	<script src="assets/js/custom.js"></script>
</body>
</html>
