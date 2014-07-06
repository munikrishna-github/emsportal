<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "java.util.List, com.ems.domain.Employee, org.apache.log4j.Logger"
    errorPage="failure.jsp"
    %>

    
    <%
    	String contextRoot = request.getContextPath();
    	List<Employee> result = (List) request.getAttribute("searchResult");
    	//System.out.println("Search results JSP - size of result = "+result.size());
 	%> 
 	<body bgcolor="#EFF8FB">
 	
 	<table border="2" cellpadding="2" cellspacing="2" width="60%">
 		<tr>
 		    <th>&nbsp;</th>
 		     <th>&nbsp;</th>
 			<th>First Name</th>
 			<th>Middle Name</th>
 			<th>Last Name</th>
 			<th>DOB</th>
 			<th>Gender</th>
 		</tr>
 		<%
 			if(result != null && result.size() > 0) {
 				for(Employee employee : result) { 
 		%>
 		<tr>
 			<td><a href="<%= contextRoot %>/ems/processEmployee?source=delete&empId=<%= employee.getEmployeeId() %>">Delete</a></td>
 			<td><a href="<%= contextRoot %>/ems/processEmployee?source=edit&empId=<%= employee.getEmployeeId() %>">Edit</a></td>
 			<td><%= employee.getFirstName() %></td>
 			<td><%= employee.getMiddleName() %></td>
 			<td><%= employee.getLastName() %></td>
 			<td><%= employee.getDob() %></td>
 			<td><%= employee.getGender() %></td>
 		</tr>
 		<% 
 				}
 			} else {
 		%>
 			<tr><td colspan="5">Data not fond for search - please try again!!</td></tr>
 		<% 		
 			}
 		%>
 	</table>
 	</body>