<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "java.util.List, com.ems.domain.Department, com.ems.domain.Employee, com.ems.dao.DepartmentDao, com.ems.dao.DepartmentDaoImpl, org.apache.log4j.Logger"
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
 	System.out.println("OK now departmemnts have been loaded ....");
 	if(departments != null && departments.size() > 0){
 		logger.info("No.of departments loaded in JSP="+departments.size());
 	}
 	
 	Employee employee = (Employee) request.getAttribute("employee");
 	long empId = 0;
 	String firstName = "";
 	String middleName = "";
 	String lastName = "";
 	String dob = "";
 	String gender = "";
 	String street1 = "";
 	String street2 = "";
 	String city = "";
 	String state = "";
 	int zip=0;
 	String country = "";
 	long deptId = 0;
 	long addressId = 0;
 	if(employee != null){
 		empId = employee.getEmployeeId();
 		firstName = employee.getFirstName();
 	 	middleName = employee.getMiddleName();
 	 	lastName = employee.getLastName();
 	 	dob = employee.getDobStr();
 	 	gender = employee.getGender();
 	 	street1 = employee.getAddress().getStreet1();
 	 	street2 = employee.getAddress().getStreet2();
 	 	city = employee.getAddress().getCity();
 	 	state = employee.getAddress().getState();
 	 	zip = employee.getAddress().getZip();
 	 	country = employee.getAddress().getCountry();
 	 	deptId = employee.getDepartment().getDeptId();
 	 	addressId = employee.getAddress().getAddressId();
 	}
 	
 	
 %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Create Employee</title>
	</head>
	<body marginheight="50px;" marginwidth="50px;" bgcolor="#EFF8FB">
		<!-- Define look and feel of the web page -->
		<form name="employee" action="<%= contextRoot %>/ems/processEmployee" method="POST">
		    <input type="hidden" name="empId" value="<%=empId%>"/>
		     <input type="hidden" name="addressId" value="<%=addressId%>"/>
		     <center>
			<table cellpadding="4" cellspacing="0" align="left" border="1" width="40%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name = "firstName" value="<%= firstName %>"/></td>
				</tr>
				<tr>
					<td>Middle Name</td>
					<td><input type="text" name = "middleName" value="<%= middleName %>" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name = "lastName" value="<%= lastName %>" /></td>
				</tr>
				<tr>
					<td>DOB(DD/MM/YYYY)</td>
					<td><input type="text" name = "dob" value="<%=dob%>"/></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>
						<input type="radio" name = "gender" value="M" <%if(gender.equals("M")){%>checked="checked" <%}%>/>&nbsp;Male&nbsp;
						<input type="radio" name = "gender" value="F" <%if(gender.equals("F")){%>checked="checked" <%}%>/>&nbsp;Female
					</td>
				</tr>
				<tr>
					<td>Department</td>
					<td>
						<select name="deptId">
							 <option value="-1">Please Select</option>
							<!--<option value="1">Human Resource</option>
							<option value="2">Business</option>
							<option value="3">Technology</option>
							<option value="4">Finance</option> -->
							<%
								if(departments != null && !departments.isEmpty()){
									for(Department department :departments){
										System.out.println("ID="+department.getDeptId()+" Name="+department.getName());    
										if(department.getDeptId() == deptId) { %>
											<option value="<%=department.getDeptId()%>" selected="selected"><% out.print(department.getName()); %></option>
										<% } else {%>
											<option value="<%=department.getDeptId()%>"><% out.print(department.getName()); %></option>
										<% }%>
								<%	} 
								}
							%>
							
						</select>
					</td>
				</tr>
				<tr><td colspan="2">Employee Address</td></tr>
				<tr><td colspan="2"> 
					<table width="100%">
						<tr>
							<td>Street1</td>
							<td><input type="text" name = "street1" value="<%= street1 %>"/></td>
						</tr>
						<tr>
							<td>Street2</td>
							<td><input type="text" name = "street2" value="<%= street2 %>"/></td>
						</tr>
						<tr>
							<td>City</td>
							<td><input type="text" name = "city" value="<%= city %>"/></td>
						</tr>
						<tr>
							<td>State</td>
							<td><input type="text" name = "state" value="<%= state %>"/></td>
						</tr>
						<tr>
							<td>Zip Code</td>
							<td><input type="text" name = "zip" value="<%= zip %>"/></td>
						</tr>
						<tr>
							<td>Country</td>
							<td><input type="text" name = "country" value="<%= country %>"/></td>
						</tr>
					</table>
				</td></tr>
				<tr><td colspan=2><center><input type="submit" name="source" value="create" /></center></td></tr>
				
			</table>
			</center>
		</form>
	</body>
</html>