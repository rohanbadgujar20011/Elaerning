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
	
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">
	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen">
	<link rel="stylesheet" href="assets/css/style.css">
	
	<script type="text/javascript" src="angularjs/angular.min.js"></script>
	<script>
	
	function validate(){
		
		var f = $('.fn').val();
		var l = $('.ln').val();
		var email = $('.email').val();
		var pass = $('.pass').val();
		var hasNumber = /\d/;

		if(hasNumber.test(f)){
			alert('Numbers not allowed in fname');
			$('.fn').focus();
			return false;
		}
		if(hasNumber.test(l)){
			alert('Numbers not allowed in lname');
			$('.ln').focus();
			return false;
		}
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if (!re.test(email)){
	    	alert('Invalid Email Address');
			$('.email').focus();
			return false;
	    }
		if(pass.length<8){
			alert('Password should more than 8 chars');
			$('.ln').focus();
			return false;
		}
	}
	
		var app = angular.module('userApp', []);
		
		app.controller('userForm', function($scope,$http) {
    		$scope.user={};		
    		$scope.message="";
    		$scope.submitForm = function() {			      
			$http({
			    method: 'POST',
			    url: 'AccessUserRegistration',
			    data : $scope.user,
			    headers: {'Content-Type': 'application/json'},
			})
			.then(function(response) {
	            // success
	            $scope.message=response.data.result;   
	               
	        }, 
		    function(response) { 
		    		// optional
		            // failed
		    });
			};	
		});
		
		
</script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
      
<link href="css/fontawesome-all.min.css" rel="stylesheet" type="text/css" media="all">
<link href="css/lsb.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel='stylesheet' type='text/css' media="all">
 <link href="//fonts.googleapis.com/css?family=Felipa" rel="stylesheet">
</head>

<body style="background-image: url('images/bg.jpg');" background-size:cover
background-attachment: fixed;	>

	<!-- Fixed navbar -->
<%-- 	<jsp:include page="Navigation.jsp"></jsp:include>
 --%>	<!-- /.navbar -->
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
                  <li class="nav-item active"><br>
                        <a class="nav-link" href="Teacherlogin.jsp"><b>Teacher Login</b> <span class="sr-only">(current)</span></a>
                      </li>
                     
                    
         		  </ul>
                  
               </div>
            </nav>
         
      </div>
      </div>
      </header>
 		<div class="container">
			<div class="row">
				<div class="col-md-5">
											
				</div>
				<div class="col-sm-7" ><br>
					<h3 class="section-title" style="color:white;">Register Teacher</h3><br>					
				</div>
			</div>
		</div>
	 

	<!-- container -->
	<div class="container" data-ng-app="userApp">
			<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-4">
											
					</div>
					<div class="col-md-4">												
						<form class="form-light mt-20" action="RegisterServlet" method="post"   >
							<div class="form-group">
								
								<input type="text" size="30" class="form-control fn" placeholder="Enter First Name" name="name"  autofocus required="">
							</div><br>
							
							<div class="form-group">
								<input type="text" class="form-control ln" placeholder="Enter Last Name" name="contact" required>
							</div><br>
							<div class="form-group">
								
								<input type="text" class="form-control" placeholder="Address" name="address" required>
							</div><br>
							<div class="form-group">
								
								<input type="email" class="form-control email" placeholder="Email" name="email" required>
							</div><br>
							
								<h3><%if(session.getAttribute("insertStatus")!=null)
								out.print(session.getAttribute("insertStatus"));
								session.setAttribute("insertStatus",null);
								%></h3>
							 <div class="form-group">					
							<center><button type="submit" style="color:white;font-size:17px;" class="btn btn-two" onclick="return validate();">Register</button><p></p></center><br>
 							<center><a href="Teacherlogin.jsp" style="color:white;font-size:17px;" class="btn btn-two">&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;</a></center><br>
 							<center><button type="reset" style="color:white;font-size:17px;" class="btn btn-two">Clear </button></center>
 							</div>
							 <div class="form-group">		
							 	
							</div>
							
						</form>
						
					</div>
					<div class="col-md-3">
																	
					</div>
				</div>
			</div>
	<!-- /container -->
	
	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- End Footer -->
	
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/google-map.js"></script>


</body>
</html>
