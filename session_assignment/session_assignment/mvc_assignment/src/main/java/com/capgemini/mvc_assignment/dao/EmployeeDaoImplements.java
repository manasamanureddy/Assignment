package com.capgemini.mvc_assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.capgemini.mvc_assignment.beans.EmployeeInfoBean;

@Repository
public class EmployeeDaoImplements implements EmployeeDao{

	
	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public EmployeeInfoBean getEmployee(int empId) {
		EntityManager manager = factory.createEntityManager();
	EmployeeInfoBean employeeInfoBean = manager.find(EmployeeInfoBean.class, empId);
		manager.close();
		return employeeInfoBean;
	}

	@Override
	public boolean addEmployee(EmployeeInfoBean employeeInfoBean) {
		
		boolean isAdded = false;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(employeeInfoBean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return isAdded;
	}

	@Override
	public boolean updateEmployee(EmployeeInfoBean employeeInfoBean) {
		
		EntityManager manager = factory.createEntityManager();
		EmployeeInfoBean bean = manager.find(EmployeeInfoBean.class, employeeInfoBean.getEmpId());
		boolean isUpdated = false;
		if (bean != null) {
			try {
				System.out.println("in update"+employeeInfoBean.getEmpId());
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				manager.remove(bean);
				manager.persist(employeeInfoBean);
				transaction.commit();
				isUpdated = true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				manager.close();
			}

		}
		return isUpdated;
	}

	@Override
	public boolean deleteEmployee(int empId) {
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isDeleted = false;
		try {
			transaction.begin();
			EmployeeInfoBean productInfoBean = manager.find(EmployeeInfoBean.class, empId);
			System.out.println(productInfoBean.getEmpId());
			System.out.println(productInfoBean.getName());
			manager.remove(productInfoBean);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return isDeleted;
	}

	@Override
	public List<EmployeeInfoBean> getAllEmployees() {
		
		EntityManager manager = factory.createEntityManager();
		String jpql = "Select e from EmployeeInfoBean e";
		Query query = manager.createQuery(jpql);
		List<EmployeeInfoBean> list = query.getResultList();
		return list;
	}

	@Override
	public EmployeeInfoBean authenticate(int empId, String password) {
		
		//String ipql = " from EmployeeInfoBean where empId := empId and password := pwd";
		
		EmployeeInfoBean employeeInfoBean = getEmployee(empId);
		if(employeeInfoBean != null && employeeInfoBean.getPassword().equals(password)) {
			return employeeInfoBean;
		}
		return null;
	}

	
}
