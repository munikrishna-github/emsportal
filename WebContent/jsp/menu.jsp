<script type="text/javascript">
		
		function updateHeaderTitle(where) {
			//alert(where);
			//alert(document.getElementById("innerTest").innerHTML);
			
			//document.getElementById("innerTest").innerHTML = "Now I have replaced the whole text to a different one.";
			//alert(where);
			//alert(parent.window.frames["h"]);
			//alert(parent.window.frames["h"].document.getElementById('headerTitle').innerHTML);
			//document.getElementById("headerTitle").innerHTML("testing...");
			var text = "<center><h1>" + where + "</h1></center>";
			//alert(text);
			parent.window.frames["h"].document.getElementById('headerTitle').innerHTML = where;
		}
		
	</script>
	<body bgcolor="#81DAF5">.
<table>
	<tr>
		<td>Department</td>
	</tr>
	<tr>
		<td>
			<ol>
				<li><a href="createDepartment.jsp" target="mc" onclick="updateHeaderTitle('Craete Department');">Create</a></li>
				<li><a href="searchDepartment.jsp" target="mc" onclick="updateHeaderTitle('Search Department');">Search</a></li>
			</ol>
		</td>
	</tr>
	<tr>
		<td>Employee</td>
	</tr>
	<tr>
		<td>
			<ol>
				<li><a href="createEmployee.jsp" target="mc" onclick="updateHeaderTitle('Create Employee');">Create</a></li>
				<li><a href="searchEmployee.jsp" target="mc" onclick="updateHeaderTitle('Search Employee');">Search</a></li>
			</ol>
		</td>
	</tr>
</table>
</body>