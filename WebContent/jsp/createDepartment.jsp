<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "java.util.List, com.ems.domain.Department, com.ems.domain.Employee, com.ems.dao.DepartmentDao, com.ems.dao.DepartmentDaoImpl, org.apache.log4j.Logger"
    errorPage="failure.jsp"
    %>
    <%! 	Logger logger = Logger.getLogger(this.getClass()); %>
   <%
 	String contextRoot = request.getContextPath();
 	 	
 	Department department = (Department) request.getAttribute("department");
 	long deptId=0;
 	String deptName = "";
 	String activeFl = "";
 	String createDate = "";
 	String updateDate= "";
 	
 	if(department != null){
 		deptId = department.getDeptId();
 		deptName = department.getName();
 	 	activeFl = department.getActiveFl();
 	 	createDate=department.getCreateDateStr();
 	 	updateDate= department.getUpdateDateStr();
 	 	}
 	
 	
 %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body marginheight="50px;" marginwidth="50px" bgcolor="#EFF8FB">
		<!-- Define look and feel of the web page -->
		<form name="department" action="<%= contextRoot %>/ems/processDepartment" method="POST">
		      <table cellpadding="4" cellspacing="0" align="left" border="2" width="40%">
			        <tr>
					   <td>Department Id</td>
					   <td> <input type="text" name="deptId" value="<%=deptId%>" /></td>
				    </tr>
				  <tr>
					  <td>Department name</td>
					<td> <input type="text" name="deptName" value="<%=deptName%>"/></td>
				</tr>
				<tr>
					<td>Active Flag</td>
					<td><input type="text" name = "activeFl" value="<%= activeFl %>"/></td>
				</tr>
				<tr>
					<td>Create Date</td>
					<td><input type="text" name = "createDate" value="<%= createDate %>" /></td>
				</tr>
				<tr>
					<td>Update Date</td>
					<td><input type="text" name = "updateDate" value="<%= updateDate %>" /></td>
				</tr>
				<tr>
					<td colspan=2><center><input type="submit" name="source" value="create" /></center></td>
				</tr>
				</table>
				</form>
</body>
</html>