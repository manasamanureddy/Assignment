package com.capgemini.mvc_assignment.dao;

import java.util.List;

import com.capgemini.mvc_assignment.beans.EmployeeInfoBean;

public interface EmployeeDao {
	
	EmployeeInfoBean authenticate(int empId, String password);
	
	 EmployeeInfoBean getEmployee(int empId);
	boolean addEmployee(EmployeeInfoBean employeeInfoBean);
	boolean updateEmployee(EmployeeInfoBean employeeInfoBean);
	boolean deleteEmployee(int empId);
	List<EmployeeInfoBean>getAllEmployees();
	
}
