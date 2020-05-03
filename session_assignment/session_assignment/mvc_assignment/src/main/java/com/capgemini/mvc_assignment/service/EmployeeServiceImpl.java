package com.capgemini.mvc_assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.mvc_assignment.beans.EmployeeInfoBean;
import com.capgemini.mvc_assignment.dao.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao dao;
	
	@Override
	public EmployeeInfoBean getEmployee(int empId) {
		// TODO Auto-generated method stub
		return dao.getEmployee(empId);
	}

	@Override
	public boolean addEmployee(EmployeeInfoBean employeeInfoBean) {
		// TODO Auto-generated method stub
		return dao.addEmployee(employeeInfoBean);
	}

	@Override
	public boolean updateEmployee(EmployeeInfoBean employeeInfoBean) {
		// TODO Auto-generated method stub
		return dao.updateEmployee(employeeInfoBean);
	}

	@Override
	public boolean deleteEmployee(int empId) {
		// TODO Auto-generated method stub
		return dao.deleteEmployee(empId);
	}

	@Override
	public List<EmployeeInfoBean> getAllEmployees() {
		// TODO Auto-generated method stub
		return dao.getAllEmployees();
	}

	@Override
	public EmployeeInfoBean authenticate(int empId, String password) {
		
		if(empId <1 || password == null || password.isEmpty() || password.trim().isEmpty()) {
			return null;
		}
		return dao.authenticate(empId, password);
	}

}
