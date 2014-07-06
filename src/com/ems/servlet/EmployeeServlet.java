package com.ems.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.domain.Address;
import com.ems.domain.Department;
import com.ems.domain.Employee;
import com.ems.service.EmployeeService;
import com.ems.util.ApplicationContextLoader;

public class EmployeeServlet extends HttpServlet {
	long empId;
    EmployeeService employeeService =null;
//            (EmployeeService) ApplicationContextLoader.getBean("employeeService");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
	    System.out.println("Initializing....!");
	    ApplicationContextLoader.getContext();
        employeeService=(EmployeeService) ApplicationContextLoader.getBean("employeeService");

	}


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		System.out.println("This is GET in EmployeeServlet.... ");
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		System.out.println("*******************************************************************");
		String source = request.getParameter("source");
		System.out.println("SOURCE IS ="+ source);
		System.out.println("*******************************************************************");
		  if(source != null){
				if(source.equalsIgnoreCase("search")){
					searchEmployee(request, response);
				} else if (source.equalsIgnoreCase("edit")){
				
					editEmployee(request, response);
				} else if (source.equalsIgnoreCase("delete")){
			
					deleteEmployee(request, response);
				}
				
			else  if(source.equalsIgnoreCase("create")){
				System.out.println("**********************************************");
				createEmployee(request, response);
			}
		  }
		}
	private void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{//---------------------------------------------------------------------------------------------------------------------------------1
		System.out.println("OK - now we are inside SEARCH operation in Servlet.");
		//DO business logic....
		String deptId = request.getParameter("deptId");
		String lastName = request.getParameter("lastName");
		
		System.out.println("deptId="+deptId+" lastName="+lastName);
//		Employee employee = new Employee();
		Employee employee = (Employee) ApplicationContextLoader.getBean("employee");
		if(lastName != null && !"".equals(lastName.trim())){
			System.out.println("Setting last name");
			employee.setLastName(lastName);
		} else {
			System.out.println("Last name is not set.");
		}
		
		if(deptId != null && !"".equals(deptId.trim())){
			System.out.println("Setting dept id");
//			Department department = new Department();
			employee.getDepartment().setDeptId(Long.parseLong(deptId));
//			department.setDeptId(Long.parseLong(deptId));
//			employee.setDepartment(department);
		} else {
			System.out.println("department is not set.");
		}
		
//		EmployeeService employeeService = new EmployeeService();
//		EmployeeService employeeService = (EmployeeService)ApplicationContextLoader.getBean("employeeService");
		
		List<Employee> result = employeeService.searchEmployee(employee);
		if(result != null && !result.isEmpty()) {
			System.out.println("No of employees found matching search criteria = " + result.size());
			request.setAttribute("searchResult", result);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchEmployeeResult.jsp");
		dispatcher.forward(request, response);
		
	}
	private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{//------------------------------------------------------------------------------------------------------------------------------2
		System.out.println("Edit employee ......");
		String empId =  request.getParameter("empId");
		if(empId != null && !"".equals(empId)){
			EmployeeService employeeService = new EmployeeService();
			Employee employee = employeeService.getEmployeeById(Long.parseLong(empId));
			request.setAttribute("employee", employee);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/createEmployee.jsp");
		dispatcher.forward(request, response);
	}
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{//-----------------------------------------------------------------------------------------------------------------------------------3
		System.out.println("Delete Employee ......");
		String empId1 =  request.getParameter("empId");
		
		if(empId1 != null && !"".equals(empId1)){
			System.out.println("**************************************");
			System.out.println("Found Employee Id for delete.");
			EmployeeService employeeService = new EmployeeService();
			empId=Long.parseLong(empId1);
			employeeService.deleteEmplyee(empId);
		} else {
			System.out.println("Department id not found in the request .....");
		   }
		
		EmployeeService employeeService = new EmployeeService();
		//Department department = new Department();
		Employee result =employeeService.getEmployeeById(empId);
		/*if(result != null && !((List<Department>) result).isEmpty()) {
			System.out.println("No of departments found matching search criteria = " + ((List<Department>) result).size());
			request.setAttribute("searchResult", result);
		}*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchDepartmentResult.jsp");
		dispatcher.forward(request, response);
	}
	
	private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{//-----------------------------------------------------------------------------------------------------------------------------------4
		System.out.println("This is POST in  createEmployeeServlet method");
		//Create employee object and populate data from request.
		/*Employee employee = new Employee();
		employee.setFirstName(firstName);*/
		//Employee employee = populateDataIntoEmployeeFromRequest(request);
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String deptId = request.getParameter("deptId");
		String street1 = request.getParameter("street1");
		String street2 = request.getParameter("street2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		//Set it to employee
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setMiddleName(middleName);
		employee.setLastName(lastName);
		employee.setDob(convertStringToSqlDate(dob));
		employee.setGender(gender);
		
		if(deptId != null && !"".equals(deptId.trim())){
			Department department = new Department();
			department.setDeptId(Long.parseLong(deptId));
			employee.setDepartment(department);
		}
		//creeate address object first
		Address address = new Address();
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setCity(city);
		address.setState(state);
		if(zip != null && !"".equals(zip)){
			address.setZip(Integer.parseInt(zip));
		}
		address.setCountry(country);
		//set it to employee
		employee.setAddress(address);
		
		System.out.println("Employee populated from REQUEST="+employee);
		//EmployeeDao employeeDao = new EmployeeDaoImpl();
//		EmployeeService employeeService = new EmployeeService();
//		EmployeeService employeeService =(EmployeeService) ApplicationContextLoader.getBean("employeeService");
		boolean status = employeeService.createEmployee(employee);
		
		if(status){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/success.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/failure.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings("unused")
	private Employee populateDataIntoEmployeeFromRequest(HttpServletRequest request){
		System.out.println("Inside populateDataIntoEmployeeFromRequest......");
		//Populate data from request.
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String deptId = request.getParameter("deptId");
		System.out.println("deptId"+deptId);
		String street1 = request.getParameter("street1");
		System.out.println("street1"+street1);
		String street2 = request.getParameter("street2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		//Set it to employee
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setMiddleName(middleName);
		employee.setLastName(lastName);
		employee.setDob(convertStringToSqlDate(dob));
		employee.setGender(gender);
		
		if(deptId != null && !"".equals(deptId.trim())){
			Department department = new Department();
			department.setDeptId(Long.parseLong(deptId));
			employee.setDepartment(department);
		}
		//creeate address object first
		Address address = new Address();
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setCity(city);
		address.setState(state);
		if(zip != null && !"".equals(zip)){
			address.setZip(Integer.parseInt(zip));
		}
		address.setCountry(country);
		//set it to employee
		employee.setAddress(address);
		
		return employee;
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