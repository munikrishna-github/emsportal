<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "java.util.List, com.ems.domain.Department, com.ems.dao.DepartmentDao, com.ems.dao.DepartmentDaoImpl, org.apache.log4j.Logger"
    errorPage="failure.jsp"
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
<body bgcolor="#EFF8FB">
	<form name="searchDepartment" action="<%= contextRoot %>/ems/processDepartment" method="POST">
		<table cellpadding="5" cellspacing="5">
			<tr>
				
				<td>Department Id</td>
				<td><input type="text" name="deptId"/></td>
				<td><input type="submit" name ="source" value="Search"/></td>
			</tr>
		</table>
	</form>
</body>
</html>