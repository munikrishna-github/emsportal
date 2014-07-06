package com.ems.dao;

import java.util.List;

import com.ems.domain.Employee;

public interface EmployeeDao {
	//Create employee and return if create successful or not.
	public boolean create(Employee employee);
	//Update the given employee information and return if create successful or not.
	public boolean update(Employee employee);
	//delete employee by id and return no .of records deleted.
	public int delete(long employeeId);
	//Search by employee id
	
	public List<Employee> searchEmployee(Employee employee);
	public Employee getEmployeeById(long employeeId);
	public List<Employee> getEmployeeByAge(int age);
	public List<Employee> getEmployeeByLastName(String lastName);
	public List<Employee> getEmployeeByGender(String gender);
	public List<Employee> getEmployeeByDepartment(long deptId);
}