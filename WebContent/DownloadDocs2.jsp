<%@page import="com.elearning.database.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
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
	<script type="text/javascript" src="angularjs/angular.min.js"></script>
	<script>
	
		var app = angular.module('documentApp', []);
		app.controller('documentList', function($scope,$http) {
			$scope.getDocs=function()
    		{
    			$http({
				    method: 'POST',
				    url: 'GetDocumentListByOwener',
				    
				})
				.then(function(response) {
		            // success
		            $scope.documents=response.data.data;
			    },
			    function(response) { 
			    	
		    });	            
    		};
    		$scope.getDocs();
		});
		
		
		
		
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
</head>

<body style="background-image: url('image/storage.jpg'); color:green;" >
 <%
if(session.getAttribute("email")==null)
{
response.sendRedirect("Login.jsp");
}
%>
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
							<input type="hidden" class="hid" name="id"><br>
							<input type="hidden" class="hid1" name="action"><br>
							<input type="hidden" class="hid2" name="filename"><br>
							<input type="hidden" class="hid3" name="uid"><br>
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

 
	 
		<div class="container" data-ng-app="documentApp"
					data-ng-controller="documentList" >
					<div class="row">
					<%
							 DatabaseConnection db=new DatabaseConnection();
							ResultSet rs= db.selectQuery("select * from filelog");
							%>
					<h2>Download documents</h2>
							<h3>Wrong key, try again</h3>
						<%
						int i=0;
							while (rs.next()) {
								
								String fileName=rs.getString("filename");
								String fname1=fileName;
								String  fname2=rs.getString("displayname");
								
						%>

						<div class="col-md-3" 
							style="margin: auto;" >
							<div class="featured-box"
								style=" background-image: url('image/divbg2.jpeg')  ;  margin: auto; color: black;">

								<h3>Description :</h3>
								<label style="color: black;">File Name: <%
									 
								%> <%=fname2%> <br>
									File Size: <%=rs.getFloat("filesize") + "mb"%> 
									</label><hr> 
									<a style="color: black;"	onclick="downloadDialog(<%=rs.getString("id")%>,'attachment','<%=fileName%>',<%=rs.getInt("uid")%>)"
								href="javascript:void(0);" class="actionLinks">Download</a>
									<a style="color: black;"onclick="downloadDialog(<%=rs.getString("id")%>,'inline','<%=fileName%>',<%=rs.getInt("uid")%>)"
								href="javascript:void(0);" class="actionLinks">View</a>
								 
							</div>
						</div>	
					 		<%
							i++;
							System.out.println("File : "+i);
							}
							//rs.close();
 						%>
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
