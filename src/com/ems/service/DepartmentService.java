package com.ems.service;
import java.util.List;

import com.ems.dao.DepartmentDao;
import com.ems.dao.DepartmentDaoImpl;
import com.ems.domain.Department;

public class DepartmentService {
	boolean status;
	public boolean createDepartment(Department department)//---------------------------------------------------------------1
	{
		try {
			   DepartmentDao deptDao = new DepartmentDaoImpl();
				status = deptDao.create(department);
				
				if(status){
					System.out.println("Data has been successfullly inserted into database.");
					return true;
				}
		    else {
				System.out.println("Department did not get created, so not inserting employee.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
 public boolean updateDepartment(Department department)//------------------------------------------------------------------2
	     {
		    System.out.println("Update department....."+department);
	       DepartmentDao deptDao = new DepartmentDaoImpl();
		   boolean deptUpdateStatus = deptDao.update(department);
		     System.out.println("deptUpdateStatus = " + deptUpdateStatus);
		
		   if(deptUpdateStatus)
		    {
			return true;
		}
		
		return false;
	}
 public int deleteDepartment(long deptId)
 //-------------------------------------------------------------------------------3
 {
	 System.out.println("this is delete method");
	 DepartmentDao deptDao = new DepartmentDaoImpl();
      return deptDao.delete(deptId);
		/*if (department != null) {
			System.out.println("Department is loaded .....");
			
		}
	 else {
			System.out.println("Department not foud with ID =" + deptId);
		}*/
		
	
	}
 public List<Department> searchDepartment(Department department)//-------------------------------------------------------------4
 {
		try {
			DepartmentDao deptDao = new DepartmentDaoImpl();
			return deptDao.searchDepartment(department);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public Department getDepartmentById(long deptId)//--------------------------------------------------------------------------5
	{
		System.out.println("getDepartmentById() ......");
		DepartmentDao deptDao = new DepartmentDaoImpl();
		Department department = deptDao.getDepartmentById(deptId);
		return department;
		}
	//m4
 
}
