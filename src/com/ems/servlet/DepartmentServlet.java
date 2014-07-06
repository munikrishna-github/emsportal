package com.ems.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ems.domain.Department;
import com.ems.domain.Employee;
import com.ems.service.DepartmentService;
import com.ems.util.ApplicationContextLoader;

public class DepartmentServlet extends HttpServlet { 
	long deptId;
	
	private static final long serialVersionUID = 1L;


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		System.out.println("This is GET in DepartmentServlet.... ");
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String source = request.getParameter("source");
		System.out.println("SOURCE IS ="+ source);
	  if(source != null){
			if(source.equalsIgnoreCase("search")){
				searchDepartment(request, response);
			} else if (source.equalsIgnoreCase("edit")){
			
				editDepartment(request, response);
			} else if (source.equalsIgnoreCase("delete")){
		
				deleteDepartment(request, response);
			}
			
		else  if(source.equalsIgnoreCase("create")){
			System.out.println("**********************************************");
			createDepartment(request, response);
		}
	  }
	}
	private void searchDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{//----------------------------------------------------------------------------------------------------------------------------1
		System.out.println("OK - now we are inside SEARCH operation in Servlet.");
		//DO business logic....
		String deptId = request.getParameter("deptId");
		
		System.out.println("deptId="+deptId);
//		Department department = new Department();
		Department department = (Department) ApplicationContextLoader.getBean("department");
		if(deptId != null && !"".equals(deptId.trim())){
			System.out.println("Setting dept id");
			department.setDeptId(Long.parseLong(deptId));
	   
		} else {
			System.out.println("department is not set.");
		}
		
//		DepartmentService departmentService = new DepartmentService();
	    DepartmentService departmentService = (DepartmentService) ApplicationContextLoader.getBean("departmentService");

		
		List<Department> result = departmentService.searchDepartment(department);
		if(result != null && !result.isEmpty()) {
			System.out.println("No of departments found matching search criteria = " + result.size());
			request.setAttribute("searchResult", result);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchDepartmentResult.jsp");
		dispatcher.forward(request, response);
		
	}
	private void editDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{//---------------------------------------------------------------------------------------------------------------------------2
		System.out.println("Edit department ......");
		String deptId =  request.getParameter("deptId");
		if(deptId != null && !"".equals(deptId)){
//			DepartmentService departmentService = new DepartmentService();
		    DepartmentService departmentService = (DepartmentService) ApplicationContextLoader.getBean("departmentService");

			Department department = departmentService.getDepartmentById(Long.parseLong(deptId));
			if(department != null){
				System.out.println("department = "+department);
			} else {
				System.out.println("Department does not found ....");
			}
			
			request.setAttribute("department", department);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/createDepartment.jsp");
		dispatcher.forward(request, response);
	}
	
	
	@SuppressWarnings("unchecked")
	private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{//--------------------------------------------------------------------------------------------------3
		System.out.println("Delete department ......");
		String deptId1 =  request.getParameter("deptId");
		
		if(deptId1 != null && !"".equals(deptId1)){
			System.out.println("**************************************");
			System.out.println("Found department Id for delete.");
//			DepartmentService departmentService = new DepartmentService();
	        DepartmentService departmentService = (DepartmentService) ApplicationContextLoader.getBean("departmentService");

			deptId=Long.parseLong(deptId1);
	        departmentService.deleteDepartment(deptId);
		} else {
			System.out.println("Department id not found in the request .....");
		   }
		
//		DepartmentService departmentService = new DepartmentService();
	      DepartmentService departmentService = (DepartmentService) ApplicationContextLoader.getBean("departmentService");

		//Department department = new Department();
		Department result =departmentService.getDepartmentById(deptId);
		/*if(result != null && !((List<Department>) result).isEmpty()) {
			System.out.println("No of departments found matching search criteria = " + ((List<Department>) result).size());
			request.setAttribute("searchResult", result);
		}*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchDepartmentResult.jsp");
		dispatcher.forward(request, response);
	}
	private void createDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{//----------------------------------------------------------------------------------------------------------------------------4
		System.out.println("this is create department method");
		//Create employee object and populate data from request.
		/*Employee employee = new Employee();
		employee.setFirstName(firstName);*/
		//Employee employee = populateDataIntoEmployeeFromRequest(request);
		
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String activeFl = request.getParameter("activeFl");
		String createDate = request.getParameter("createDate");
		String updateDate= request.getParameter("updateDate");
		//Set it to department
		Department department = new Department();
		department.setDeptId(Long.parseLong(deptId));
		department.setName(deptName);
		department.setActiveFl(activeFl);
		department.setCreateDateTime(convertStringToSqlDate(createDate));
		department.setUpdateDateTime(convertStringToSqlDate(updateDate));
	//EmployeeDao employeeDao = new EmployeeDaoImpl();
//		DepartmentService departmentService = new DepartmentService();
	    DepartmentService departmentService = (DepartmentService) ApplicationContextLoader.getBean("departmentService");

		boolean status = false;
		//if(deptId != null && !"0".equals(deptId)){
			//department.setDeptId(Long.parseLong(deptId)); 
			//status = departmentService.updateDepartment(department);
		//} else {
			System.out.println("Create employee .....");
			status = departmentService.createDepartment(department);
			
			
		//}
		
		if(status){
			List<Department> result = departmentService.searchDepartment(department);
			if(result != null && !result.isEmpty()) {
				System.out.println("No of departments found matching search criteria = " + result.size());
				request.setAttribute("searchResult", result);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchDepartmentResult.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/failure.jsp");
			dispatcher.forward(request, response);
		}
	}
     @SuppressWarnings("unused")
	private Department populateDataIntoDepartmentFromRequest(HttpServletRequest request)
	{
		System.out.println("Inside populateDataIntoEmployeeFromRequest......");
		//Populate data from request.
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String activeFl = request.getParameter("activeFl");
		String createDate = request.getParameter("createDate");
		String updateDate= request.getParameter("updateDate");
		//Set it to department
//		Department department = new Department();
	    Department department = (Department) ApplicationContextLoader.getBean("department");

		department.setDeptId(Long.parseLong(deptId));
		department.setName(deptName);
		department.setActiveFl(activeFl);
		department.setCreateDateTime(convertStringToSqlDate(createDate));
		department.setCreateDateTime(convertStringToSqlDate(updateDate));
		
		if(deptId != null && !"".equals(deptId.trim()))
		{
			department.setDeptId(Long.parseLong(deptId));

		}
		return department;
	}
		
	
	//This takes date as String and returns java.util.Date
	//1. find out what is the incoming format of the date - //28/08/1992 : 
	//2. define formatter.
	private java.util.Date convertStringToUtilDate(String sourceDate) {
		try {
			String format = "dd/MM/yyyy";
			System.out.println("Date in String="+sourceDate+" Format="+format);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date date = sdf.parse(sourceDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	private java.sql.Date convertStringToSqlDate(String sourceDate){
		if(sourceDate == null){
			System.out.println("Source date shold not be null.");
			return null;
		}
		//firt convert to util date
		java.util.Date source = convertStringToUtilDate(sourceDate);
		java.sql.Date sqlDate = new java.sql.Date(source.getTime());
		
		System.out.println("SQL Date="+sqlDate);
		return sqlDate;
	}
}