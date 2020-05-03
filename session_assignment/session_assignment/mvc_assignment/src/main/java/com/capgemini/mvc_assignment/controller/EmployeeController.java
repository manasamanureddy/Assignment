package com.capgemini.mvc_assignment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.mvc_assignment.beans.EmployeeInfoBean;
import com.capgemini.mvc_assignment.service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/loginForm")
	public String getloginForm() {
		return "loginForm";
	}

	@PostMapping("/login")
	public String authenticate(int empId, String password, HttpServletRequest request) {
		EmployeeInfoBean employeeInfoBean = service.authenticate(empId, password);
		if(employeeInfoBean != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedEmployeeInfo", employeeInfoBean);
			return "home";
		}else {
			request.setAttribute("errMsg", "Invalid Credentials");
			return "loginForm";
		}
	}

	@GetMapping("/home")
	public String home(HttpSession session, ModelMap modelMap) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			return "home";
		}else {

			modelMap.addAttribute("errMsg", "Please Login to Access this Page!!");
			return "loginForm";
		}

	}

	@GetMapping("/getEmployeeForm")
	public String getEmployeeForm(HttpSession session, ModelMap modelMap) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			return "searchEmployee";
		}else {
			
			modelMap.addAttribute("errMsg", "Please First Login!!");
			return "loginForm";
		}
	}
	@GetMapping("/getEmployee")
	public String getEmployee(int empId,ModelMap map, HttpSession session) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			EmployeeInfoBean employeeInfoBean = service.getEmployee(empId);
			if(employeeInfoBean != null) {
				map.addAttribute("employeeInfo", employeeInfoBean);
			}else {
				map.addAttribute("errmsg","Employee Id Not Found");
			}
			return "searchEmployee";
		}else {
			map.addAttribute("errMsg", "Please login First!!");
			return "loginForm";
		}
	}

	@GetMapping("/addEmployeeForm")
	public String addEmployeeForm(HttpSession session, ModelMap modelMap) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			return "addEmployeeForm";
		}else {
			
			modelMap.addAttribute("errMsg", "Please First Login!!");
			return "loginForm";
		}
	}

	@PostMapping("/addEmployee")
	public String addEmployee(EmployeeInfoBean employeeInfoBean,ModelMap map, HttpSession session) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			System.out.println(employeeInfoBean.getEmpId());
			boolean isAdded = service.addEmployee(employeeInfoBean);
			if(isAdded) {
				map.addAttribute("done","SucessFully added to the table");
			}else {
				map.addAttribute("notDone", "Unscessfull add to the table");
			}
			return "status";
		}else {
			map.addAttribute("errMsg", "Please login First!!");
			return "loginForm";
		}
	}

	@GetMapping("/deleteEmployeeForm")
	public String deleteEmployeeForm(HttpSession session, ModelMap modelMap) {

		if(session.getAttribute("loggedEmployeeInfo") != null) {
			return "deleteEmployeeForm";
		}else {
			
			modelMap.addAttribute("errMsg", "Please First Login!!");
			return "loginForm";
		}
	}
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(int empId,ModelMap map,HttpSession session) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			boolean isDeleted = service.deleteEmployee(empId);
			if(isDeleted) {
				map.addAttribute("done", "Successfully Deleted ");
			}else {
				map.addAttribute("Not Done", "Not Deleted");
			}
			return "status";
		}
		else {
			map.addAttribute("errMsg", "Please login First!!");
			return "loginForm";
		}

	}

	@GetMapping("/updateEmployeeForm")
	public String updateEmployeeForm(HttpSession session,ModelMap modelMap) {
		if(session.getAttribute("loggedEmployeeInfo")!= null) {
			return "updateEmployeeForm";
		}
	
		modelMap.addAttribute("errMsg", "Please Login First!!");
		return "loginForm";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(EmployeeInfoBean employeeInfoBean,ModelMap map, HttpSession session) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
			boolean isUpdated = service.updateEmployee(employeeInfoBean);
			if(isUpdated) {
				map.addAttribute("done", "Successfully Updated");
			}else {
				map.addAttribute("Not Done", "Not Updated");
			}
			return "status";
		}else {
			map.addAttribute("errMsg", "Please Login First!!!");
			return "loginForm";
		}
	}

	@GetMapping("/allEmployeeDetails")
	public String allProductDetails(ModelMap map,HttpSession session) {
		if(session.getAttribute("loggedEmployeeInfo") != null) {
		List<EmployeeInfoBean> beanList = service.getAllEmployees();
		if(beanList.size()!=0) {
			map.addAttribute("beanList", beanList);
		}else {
			map.addAttribute("zero", "No Employees are There");
		}
		return "allEmployeeDetails";
		}else {
			map.addAttribute("errMsg", "Please Login First!!");
			return "loginForm";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap modelMap) {
		session.invalidate();
		modelMap.addAttribute("errMsg", "You are logged out Successfully");
		return "loginForm";
	}
}
