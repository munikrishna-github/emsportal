<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "java.util.List, com.ems.domain.Department, com.ems.dao.DepartmentDao, com.ems.dao.DepartmentDaoImpl, org.apache.log4j.Logger"
    errorPage="failure.jsp"
    %>
 
 <%!
 	List<Department> departments = null;
 	Logger logger = Logger.getLogger(this.getClass());
 %> 
 
 <%
 	String contextRoot = request.getContextPath();
 	logger.info("Context Root ="+contextRoot);
 	//First create department dao impl object.
 	DepartmentDao departmentDao = new DepartmentDaoImpl();
 	departments = departmentDao.loadAllDepartments();
 %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body bgcolor="#EFF8FB">
	<form name="searchEmployee" action="<%= contextRoot %>/ems/processEmployee" method="POST">
		<table cellpadding="5" cellspacing="5">
			<tr>
				<td>Department</td>
				<td>
					<select name="deptId">
						<option value="-1">Please Select</option>
						<%
							if(departments != null && !departments.isEmpty()){
								for(Department department :departments){
									System.out.println("ID="+department.getDeptId()+" Name="+department.getName());    %>
									<option value="<%=department.getDeptId()%>"><% out.print(department.getName()); %></option>
							<%	} 
							}
						%>
					</select>
				</td>
				<td>Employee last name</td>
				<td><input type="text" name="lastName"/></td>
				<td><input type="submit" name ="source" value="Search"/></td>
			</tr>
		</table>
	</form>
</body>
</html>