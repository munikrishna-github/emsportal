package com.ems.dao;
import java.sql.SQLException;
import java.util.List;

import com.ems.domain.Department;

public interface DepartmentDao {
	
	//Create department and return if create successful or not.-------------------------------1
		public boolean create(Department department);
		//Update the given department information and return if create successful or not.-----2
		public boolean update(Department department);
		//delete department by id and return no .of records deleted.--------------------------3
		public int delete(long deptId);
		//Search by department-------------------------------------------------------------4
		public List<Department> searchDepartment(Department department);
		//search by dept id------------------------------------------------------------------------------5
		public Department getDepartmentById(long deptId);
		//search by AFL------------------------------------------------------------------------6
		public List<Department> getDepartmentByActiveFl(String activeFl);
		//-------------------------------------------------------------------------------------7
		public List<Department> loadAllDepartments() throws SQLException, ClassNotFoundException;
	
}
