<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    
<%
	String contextRoot = request.getContextPath();
%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Insert title here</title>
	</head>
	
	<frameset rows="13%,75%,12%" border="0">
		<frame src="<%= contextRoot %>/jsp/header.jsp" name="h" id="h"/>	
		<frameset cols="20%,80%">
			<frame src="<%= contextRoot %>/jsp/menu.jsp" name="m" id="m"/>	
			<frame src="<%= contextRoot %>/jsp/mainContent.jsp" name="mc" id="mc"/>	
			<frame src="<%= contextRoot %>/jsp/menu1.jsp" name="m1" id="m1ss"/>
		</frameset>
		<frame src="<%= contextRoot %>/jsp/footer.jsp" name="f" id="f"/>	
	</frameset>
</html>