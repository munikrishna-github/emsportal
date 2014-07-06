<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "java.util.List, com.ems.domain.Department, org.apache.log4j.Logger"
    errorPage="failure.jsp"
    %>

    
    <%
    	String contextRoot = request.getContextPath();
    	List<Department> result = (List) request.getAttribute("searchResult");
    	//System.out.println("Search results JSP - size of result = "+result.size());
 	%> 
 	
 	<body bgcolor="#EFF8FB">
 	<table border="1" cellpadding="2" cellspacing="2" width="60%">
 		<tr>
 		    <th>&nbsp;</th>
 		     <th>&nbsp;</th>
 			<th>Department Id</th>
 			<th>Department Name</th>
 			<th>Active flag</th>
 			<th>Create Date</th>
 			<th>Update Date</th>
 		</tr>
 		<%
 			if(result != null && result.size() > 0) {
 				for(Department department : result) { 
 		%>
 		<tr>
 			<td><a href="<%= contextRoot %>/ems/processDepartment?source=delete&deptId=<%=department.getDeptId() %>">Delete</a></td>
 			<td><a href="<%= contextRoot %>/ems/processDepartment?source=edit&deptId=<%= department.getDeptId() %>">Edit</a></td>
 			<td><%= department.getDeptId() %></td>
 			<td><%= department.getName() %></td>
 			<td><%= department.getActiveFl() %></td>
 			<td><%= department.getCreateDateTime() %></td>
 			<td><%= department.getUpdateDateTime()%></td>
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