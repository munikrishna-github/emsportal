package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ems.domain.Address;
import com.ems.domain.Department;
import com.ems.domain.Employee;
import com.ems.global.EmsConstants;

public class EmployeeDaoImpl implements EmployeeDao {
	public boolean create(Employee employee)  {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			//First get connection object ...
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return false;
			}
			System.out.println("Connection is not established - do not oroceed.");
			String sql 	= 	"INSERT INTO EMPLOYEE(FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, GENDER, ADDRESS_ID, DEPT_ID, ACTIVE_FL, CREATE_DATETIME, UPDATE_DATETIME) "
						+	"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getMiddleName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setDate(4, employee.getDob());
			pstmt.setString(5, employee.getGender());
			pstmt.setLong(6, employee.getAddress().getAddressId());
			pstmt.setLong(7, employee.getDepartment().getDeptId());
			pstmt.setString(8, EmsConstants.ACTIVE_YES);
			pstmt.setDate(9, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setDate(10, new java.sql.Date(System.currentTimeMillis()));
			
			int count =  pstmt.executeUpdate();
			
			if(count > 0){
				return true;
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		return false;
	}
	
	public boolean update(Employee employee) {	
		//StringBuilder sql = new StringBuilder("UPDATE EMPLOYEE SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, DOB = ?, GENDER = ?, DEPT_ID = ? \n");
		String sql 	=  	"UPDATE EMPLOYEE SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, DOB = ?, GENDER = ?, DEPT_ID = ?, UPDATE_DATETIME = ? \n"
					+	"WHERE emp_id = ?";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return false;
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getMiddleName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setDate(4, employee.getDob());
			pstmt.setString(5, employee.getGender());
			pstmt.setLong(6, employee.getDepartment().getDeptId());
			pstmt.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setLong(8, employee.getEmployeeId());
			
			int count =  pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("Employee updation successfulll...");
				return true;
			} else {
				System.out.println("Employee updation failed ...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		return false;
	}
	
	// Search by employee id
	public Employee getEmployeeById(long employeeId) {
		System.out.println("getEmployeeById -- employeeId="+employeeId);
		// Vicky to fill in
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		Employee employee = null;
		try {
			StringBuilder sql = new StringBuilder("select * from employee where emp_id = ? ");
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, employeeId);
			System.out.println("SQL = " + sql.toString());
			rs = pstmt.executeQuery();
			employee = new Employee();
			if (rs != null) {
				while (rs.next()) {
					employee.setEmployeeId(rs.getLong("EMP_ID"));
					employee.setFirstName(rs.getString("FIRST_NAME"));
					employee.setLastName(rs.getString("LAST_NAME"));
					employee.setMiddleName(rs.getString("MIDDLE_NAME"));
					employee.setDob(rs.getDate("DOB"));
					employee.setGender(rs.getString("GENDER"));
					long addressId = rs.getLong("ADDRESS_ID");
					Address address = new Address();
					address.setAddressId(addressId);
					employee.setAddress(address);
					
					long deptId = rs.getLong("DEPT_ID");
					Department dept = new Department();
					dept.setDeptId(deptId);
					employee.setDepartment(dept);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}

		return employee;
	}
	
	public List<Employee> getEmployeeByAge(int age) {
		return null;
	}
	
	public List<Employee> getEmployeeByLastName(String lastName) {
		return null;
	}
	
	public List<Employee> getEmployeeByGender(String gender) {
		return null;
	}
	
	public List<Employee> getEmployeeByDepartment(long deptId) {
		return null;
	}
	
	public List<Employee> searchEmployee(Employee employee){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<Employee> results = null;
		try {
			StringBuilder sql = new StringBuilder("select * from employee where 1 = 1 ");
			
			if(employee != null){
				if(employee.getDepartment() != null && employee.getDepartment().getDeptId()  > 0) {
					sql.append("and dept_id = ? ");
				}
				
				if(employee.getLastName() != null && !"".equals(employee.getLastName().trim())){
					sql.append("and last_name like ? ");
				}
			}
			
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			if(employee != null){
				int i = 1;
				if(employee.getDepartment() != null && employee.getDepartment().getDeptId()  > 0) {
					pstmt.setLong(i, employee.getDepartment().getDeptId());
					i = i + 1;
				}
				
				if(employee.getLastName() != null && !"".equals(employee.getLastName().trim())){
					pstmt.setString(i, "%"+employee.getLastName()+"%");
					i = i + 1;
				}
			}
			
			System.out.println("SQL = " + sql.toString());
			rs = pstmt.executeQuery();
			
			results = new ArrayList<Employee>();
			if(rs != null){
				while(rs.next()){
					//FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, GENDER
					Employee emp = new Employee();
					emp.setEmployeeId(rs.getLong("EMP_ID"));
					emp.setFirstName(rs.getString("FIRST_NAME"));
					emp.setLastName(rs.getString("LAST_NAME"));
					emp.setMiddleName(rs.getString("MIDDLE_NAME"));
					emp.setDob(rs.getDate("DOB"));
					emp.setGender(rs.getString("GENDER"));
					results.add(emp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		
		return results;
	}
	
	public int delete(long employeeId) {
		System.out.println(">>>> DELETE  Employee for ID ="+employeeId);
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			//DELETE FROM ADDRESS where address_id = ?
			String sql = "DELETE FROM employee WHERE EMP_ID=?";
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return 0;
			}
			pstmt = con.prepareStatement(sql);
			System.out.println("SETTING employeeId="+employeeId);
			pstmt.setLong(1, employeeId);
			
			return pstmt.executeUpdate();
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		return 0;
	}
}
