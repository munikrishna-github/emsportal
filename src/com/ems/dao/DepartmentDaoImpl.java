package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ems.domain.Department;
import com.ems.global.EmsConstants;


public class DepartmentDaoImpl implements DepartmentDao {
	public boolean create(Department department) //-----------------------------------------------------------------1
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			//First get connection object ...
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return false;
			}
			
			String sql 	= "INSERT INTO DEPARTMENT VALUES(?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, department.getDeptId());
			pstmt.setString(2, department.getName());
			pstmt.setString(3, department.getActiveFl());
			pstmt.setDate(4, department.getCreateDateTime());
			pstmt.setDate(5, department.getUpdateDateTime());
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
	public boolean update(Department department) //----------------------------------------------------------------------2
	{	
		//StringBuilder sql = new StringBuilder("UPDATE EMPLOYEE SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, DOB = ?, GENDER = ?, DEPT_ID = ? \n");
		String sql 	=  	"UPDATE DEPARTMENT SET NAME=?,ACTIVE_FL=?,CREATE_DATETIME= ?,UPDATE_DATETIME= ? WHERE DEPT_ID = ?";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt=con.prepareStatement(sql);
			//if(con == null)
			//{
			//	System.out.println("Connection is not established - do not oroceed.");
			//	return false;
			 //    }
			
			
			pstmt.setString(1, department.getName());
			pstmt.setString(2, department.getActiveFl());
			pstmt.setDate(3, department.getCreateDateTime());
			pstmt.setDate(4, department.getUpdateDateTime());
			pstmt.setLong(5, department.getDeptId());
			
			int count =  pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("Department updation successfulll...");
				return true;
			} else {
				System.out.println("department updation failed ...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		return false;
	}
	public int delete(long deptId)//-----------------------------------------------------------------3
	{
		System.out.println(">>>> DELETE  department for ID ="+deptId);
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			//DELETE FROM ADDRESS where address_id = ?
			String sql = "DELETE FROM DEPARTMENT WHERE DEPT_ID=?";
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return 0;
			}
			pstmt = con.prepareStatement(sql);
			System.out.println("SETTING department id="+deptId);
			pstmt.setLong(1,deptId);
			
			return pstmt.executeUpdate();
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		return 0;
	}
	public List<Department> searchDepartment(Department department)//-----------------------------------------------------------------4
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<Department> results = null;
		try {
			String sql ="SELECT * FROM DEPARTMENT WHERE DEPT_ID=?";
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			if(department != null){
				if(department.getDeptId()  > 0) {

				}
				}
			
			
			
		if(department != null){
				int i = 1;
				if(department.getDeptId()  > 0) {
					
					i = i + 1;
				}
				
				
			}
			
			pstmt.setLong(1, department.getDeptId());

		
			System.out.println("SQL = " +sql);
			rs = pstmt.executeQuery();

			
			results = new ArrayList<Department>();
		
			if(rs != null){
	
				while(rs.next()){
					
					//FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, GENDER
					Department dept = new Department();
					dept.setDeptId(rs.getLong("DEPT_ID"));
                    dept.setName(rs.getString("NAME"));
					dept.setActiveFl(rs.getString("ACTIVE_FL"));
					dept.setCreateDateTime(rs.getDate("CREATE_DATETIME"));
					dept.setUpdateDateTime(rs.getDate("UPDATE_DATETIME"));
					results.add(dept);
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
	

	// Search by department id--------------------------------------------------------------------------5
		public Department getDepartmentById(long deptId) {
			System.out.println("getDepartmentById -- departmentId="+deptId);
			PreparedStatement pstmt = null;
			Connection con = null;
			ResultSet rs = null;
			Department department = null;
			try {
				StringBuilder sql = new StringBuilder("select * from DEPARTMENT where dept_id=?");
				con = ConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setLong(1,deptId);
				System.out.println("SQL = " + sql.toString());
				rs = pstmt.executeQuery();
				department = new Department();
				if (rs != null) {
					while (rs.next()) {
						department.setDeptId(rs.getLong("DEPT_ID"));
						department.setName(rs.getString("NAME"));
						department.setActiveFl(rs.getString("ACTIVE_FL"));
						department.setCreateDateTime(rs.getDate("CREATE_DATETIME"));
						department.setUpdateDateTime(rs.getDate("UPDATE_DATETIME"));
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionManager.close(rs);
				ConnectionManager.close(pstmt);
				ConnectionManager.close(con);
			}

			return department;
		}
		@Override
		public List<Department> getDepartmentByActiveFl(String activeFl)//---------------------------------------------------6
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Department> loadAllDepartments() throws SQLException, ClassNotFoundException {
			//Get connection object
			Connection con = null;
			PreparedStatement pstmt = null;
			//try {
				String sql = "Select DEPT_ID, NAME from DEPARTMENT WHERE ACTIVE_FL = ? order by NAME";
				con = ConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, EmsConstants.ACTIVE_YES);
				ResultSet rs = pstmt.executeQuery();
				if(rs != null){
					List<Department> departments = new ArrayList<Department>();
					while(rs.next()){
						System.out.println("No of records in the list = "+departments.size());
						Department department = new Department();
						department.setDeptId(rs.getLong("DEPT_ID"));
						department.setName(rs.getString("NAME"));
						departments.add(department);
					}
					
					System.out.println(">> No of departments available = "+departments.size());
					
					return departments;
				}
			/*} catch(SQLException e) {
				System.out.println("INSIDE SQLException BLOCK....");
				//e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.out.println("INSIDE Generic Exception BLOCK....");
			}*/
			
			return null;
		}
	
	//Unit testing.
	public static void main(String args[]) throws Exception{
		
		System.out.println("This is unit tesing scenario .........");
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		List<Department> departments = ((DepartmentDaoImpl) departmentDao).loadAllDepartments();
		
		if(departments != null && departments.size() > 0){
			for(Department department :departments){
			//for(int i = 0; i < departments.size(); i++){
				//Department department = departments.get(i);
				System.out.println("DEPT_ID="+department.getDeptId()+"NAME="+department.getName());
			}
		} else {
			System.out.println("No active departments found in the database.");
		}
	}
	
}
