<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII" isErrorPage="true"
	import="org.apache.log4j.Logger"
	%>

<%!
	Logger logger = Logger.getLogger(this.getClass());
%>	

<%
 	String contextRoot = request.getContextPath();
	logger.info("Context Root ="+contextRoot);
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
 <% 
 	System.out.println(">>>>>> Exception from JSP >>>>>>");
 	exception.printStackTrace();
 	System.out.println(exception.getMessage() + " was occured at " + new java.util.Date()); %>
	Processing was not successful, please try again.
	<a href="<%=contextRoot %>/jsp/createEmployee.jsp">Click</a>&nbsp;Here to process your request again.
</body>
</html>