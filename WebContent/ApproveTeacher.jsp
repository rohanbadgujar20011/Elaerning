 <%@ page import="java.sql.*"%>
      <%@ page import="com.elearning.servlets.*"%>
      <%@ page import="com.elearning.database.*"%> 
<%

String approve = request.getParameter("aid");
System.out.println("id"+approve);

String disapprove = request.getParameter("did");
System.out.println(disapprove);


try {
	//Connection con=null;
	   Class.forName("com.mysql.jdbc.Driver");
	   System.out.println("Cool");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning","root","");
		 System.out.println("Cool");
	if (approve != null) {
						PreparedStatement pstmt= con.prepareStatement("update signup set status=? where id='"+approve+"'");
						pstmt.setString(1,"Approve");
						System.out.println("approve");
		
						int n=pstmt.executeUpdate();
						 if(n>0){
							 	out.println("<script type=\"text/javascript\">");
							    out.println("alert('Update comment');");
							    out.println("location='Approve.jsp';");
							    out.println("</script>"); }
						 else{ 
							 out.println("bye");
							 }
	}else	if (disapprove != null) {
						PreparedStatement pstmt= con.prepareStatement("update signup set status=? where id='"+disapprove+"'");
						pstmt.setString(1,"Disapprove");
						System.out.println("disapprove");
	
						int n=pstmt.executeUpdate();
						 if(n>0){
							 	out.println("<script type=\"text/javascript\">");
							    out.println("alert('Update comment');");
							    out.println("location='approve.jsp';");
							    out.println("</script>"); }
						 else{
							 out.println("bye");
							 }

			 
	}
		} catch (SQLException e) {
			
				e.printStackTrace();
			}
	%>