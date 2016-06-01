<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/LabTallySystem/LabTallySystemStyle.css" />
<title>Feedback</title>
</head>
<body>
	<a href="LaborblattServlet">Laborblätter</a> |
	<a href="servlet1">Overview</a> |
	<a href="LaborBlattView.html">Laborblatt Overview</a> |
	<a href="LaborSlotView.html">Laborgruppen Overview</a>

	<div id="rechts">
		<a href="login.html">Login</a> | 
		<a href="LogoutServlet">Logout</a>
	</div>

	<hr>
	
	 <%
    String[] messages= (String[])request.getAttribute("messages");

       if (messages.length>0) {       
       for (String message : messages) {            
    	   		out.println( "<p>" + message + "</p>" );
            }
        }
 	%>
</body>
</html>