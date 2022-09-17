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
      <script type="text/javascript">
function access()
{
	<% Object s=request.getSession().getAttribute("msg");
	if(s!=null){
		%>
		alert('<%=s.toString()%>');
		<% request.getSession().setAttribute("msg",null);
	}%>
	
	}
</script>
</head>

<body onload="access()" style="background-image: url('images/bg.jpg');" background-size:cover
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
                  
                     
                     
         		  </ul>
                  
               </div>
            </nav>
         
      </div>
      </div>
      </header><br><br>
	<!-- /.navbar -->

	
	<!-- container -->
	
	
				<div class="row">
				
					<div class="col-md-4">
											
					</div>
					<div class="col-md-4"><br>
					
						<h3 class="section-title" style="color:white;text-align:center;">Admin Login</h3><br>					
						<form class="form-light mt-20" action="AdminLogin" method="post">
							<div class="form-group">
								
								<input type="text" class="form-control" placeholder="Email" name="email" autofocus required="">
							</div><br>
							<div class="form-group">
								
								<input type="password" class="form-control" placeholder="Password" name="password" data-ng-model="user.password" required>
							</div>	<br>
							 					
							<center><button type="submit" class="btn btn-two">Login</button> <br><br>&nbsp;
							<button type="reset" class="btn btn-two">Clear </button></center>	
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
