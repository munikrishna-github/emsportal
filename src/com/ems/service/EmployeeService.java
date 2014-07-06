package com.ems.service;

import java.util.List;

import com.ems.dao.AddressDao;
import com.ems.dao.AddressDaoImpl;
import com.ems.dao.EmployeeDao;
import com.ems.dao.EmployeeDaoImpl;
import com.ems.domain.Address;
import com.ems.domain.Employee;
import com.ems.util.ApplicationContextLoader;

public class EmployeeService {
    private AddressDao addressDao;
    private EmployeeDao empDao;

    public void setEmpDao(EmployeeDao empDao) {
        this.empDao = empDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    public void initialize(){
        System.out.println("addressDao=" +addressDao);
    }

    public boolean createEmployee(Employee employee) {
        try {

            // AddressDao adressDao = new AddressDaoImpl();
            // AddressDao adressDao =(AddressDao)
            // ApplicationContextLoader.getBean("addressDao");
            System.out.println("addressDao2=" +addressDao);
            System.out.println("Employee2=" +employee);


            int addrCreateCnt = addressDao.create(employee.getAddress());
            if (addrCreateCnt > 0) {
                System.out.println("Address has been successfully inserted ...");
                employee.getAddress().setAddressId(addrCreateCnt); // Set auto
                                                                   // generated
                                                                   // value from
                                                                   // Address
                                                                   // DAO

                // EmployeeDao empDao = new EmployeeDaoImpl();
                // EmployeeDao empDao =(EmployeeDao)
                // ApplicationContextLoader.getBean("employeeDao");

                boolean status = empDao.create(employee);

                if (status) {
                    System.out.println("Data has been successfullly inserted into database.");
                    return true;
                }
            } else {
                System.out.println("Address did not get created, so not inserting employee.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmployee(Employee employee) {
        System.out.println("Update employee....." + employee);

        // EmployeeDao empDao = new EmployeeDaoImpl();
        // EmployeeDao empDao =(EmployeeDao)
        // ApplicationContextLoader.getBean("employeeDao");
        boolean empUpdateStatus = empDao.update(employee);

        // AddressDao adressDao = new AddressDaoImpl();
        boolean addressUpdateStatus = ((AddressDaoImpl) addressDao).update(employee.getAddress());

        System.out.println("empUpdateStatus = " + empUpdateStatus + " addressUpdateStatus = " + addressUpdateStatus);

        /*
         * if(empUpdateStatus == true){
         * 
         * }
         */

        /*
         * if(!empUpdateStatus){
         * 
         * } if(empUpdateStatus){
         * 
         * }
         */

        if (empUpdateStatus && addressUpdateStatus) {
            return true;
        }

        return false;
    }

    public List<Employee> searchEmployee(Employee employee) {
        try {
            // EmployeeDao empDao = new EmployeeDaoImpl();

            return empDao.searchEmployee(employee);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee getEmployeeById(long empId) {
        System.out.println("getEmployeeById() ......");
        // EmployeeDao empDao = new EmployeeDaoImpl();
        Employee employee = empDao.getEmployeeById(empId);
        if (employee != null) {
            // AddressDao addressDao = new AddressDaoImpl();
            Address address = addressDao.getAddressById(employee.getAddress().getAddressId());
            if (address != null) {
                employee.setAddress(address);
            }
        }

        return employee;
    }

    public boolean deleteEmplyee(long empId) {
        Employee employee = getEmployeeById(empId);
        if (employee != null) {
            System.out.println("Employee loaded .....");

            // EmployeeDao empDao = new EmployeeDaoImpl();
            int empDelCnt = empDao.delete(empId);
            if (empDelCnt > 0) {
                System.out.println("Employee also deleted .... ");
                long addressId = employee.getAddress().getAddressId();
                if (addressId > 0) {
                    // AddressDao addressDao = new AddressDaoImpl();
                    int recCnt = ((AddressDaoImpl) addressDao).delete(addressId);
                    if (recCnt > 0) {
                        System.out.println("Address deleted successfully ... now delete employee as well.");
                        return true;
                    }
                }
            } else {
                System.out.println("Employee deletion not sucessful...  ");
            }

        } else {
            System.out.println("Employee not foud with ID =" + empId);
        }

        return false;
    }
}
