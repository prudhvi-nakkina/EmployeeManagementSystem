package com.employeemanagement.system.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employeemanagement.system.model.Employee;
import com.employeemanagement.system.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	private EmployeeServiceImpl employeeService;
	
	
	@Autowired
	public EmployeeController(EmployeeServiceImpl employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employee-register")
	public String ViewRegisterEmployee() {
		return "view-register-employee";
	}
	
	@GetMapping("/employee-login")
	public String ViewLoginEmployee() {
		return "view-login-employee";
	}
	
	@PostMapping("/login")
	public String loginEmployee(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("empObject") == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("emppassword");
			Employee emp = employeeService.getEmployeeByEmail(username);
			if(emp != null && emp.getUsername() !=null) {
				if(emp.getUsername().equals(username) && emp.getPassword().equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("empObject", emp);
					if(emp.getLeavesAvailable() < 15) {
						model.addAttribute("notification","Mr. "+emp.getManager().getLastName()+" Approved your leaves!");
					}
					if(emp.getLeavesApplied() > 0) {
						String p = "Leaves successfully Applied!" + "Awaiting approval from Mr. " + emp.getManager().getLastName();
						model.addAttribute("notification",p);
					}
					model.addAttribute("employee",emp);
					return "employee-dashboard";
				}
			}
			model.addAttribute("loginError", true);
			return "view-login-employee";
		} else {
			Employee emp = (Employee)request.getSession().getAttribute("empObject");
			if(emp.getLeavesAvailable() < 15) {
				model.addAttribute("notification","Mr. "+emp.getManager().getLastName()+" "+" Approved your leaves!");
			}
			if(emp.getLeavesApplied() > 0) {
				String p = "Leaves successfully Applied!" + " Awaiting approval from Mr. " + emp.getManager().getLastName();
				model.addAttribute("notification",p);
			}
			model.addAttribute("employee",emp);
			return "employee-dashboard";
		}
	}
	
	@PostMapping("/apply-leave")
	public String applyLeaveEmployee(HttpServletRequest request, Model model) {
		Employee emp = (Employee)request.getSession().getAttribute("empObject");
		if(emp.getLeavesApplied()>0) {
			String s = "A leave request already exists!";
			String p = "Leaves successfully Applied!" + "Awaiting approval from Mr. " + emp.getManager().getLastName();
			model.addAttribute("notification",p);
			model.addAttribute("error",s);
			model.addAttribute("employee",emp);
			return "employee-dashboard";
		} else {
			emp.setLeavesApplied(Integer.parseInt(request.getParameter("leavesReq")));
			employeeService.updateEmployeeById(emp, 0);
			model.addAttribute("employee",emp);
			String s = "Leaves successfully Applied!" + " Awaiting approval from Mr. " + emp.getManager().getLastName();
			model.addAttribute("notification",s);
			return "employee-dashboard";
		}
		
	}
	
	
	@PostMapping("/register")
	public String registerEmployee(HttpServletRequest request, Model model) {
		
		Employee emp = new Employee();
		
		Boolean b = false;
		
		List<Employee> el = employeeService.getAllEmployees();
		
		for (Employee e : el) {
			if(e.getEmailId().equalsIgnoreCase(request.getParameter("email"))) {
				b = true;
				break;
			}
		}
		
		if(!b) {
			emp.setAddress(request.getParameter("address"));
			emp.setCity(request.getParameter("city"));
			emp.setEmailId(request.getParameter("email"));
			emp.setFirstName(request.getParameter("firstName"));
			emp.setLastName(request.getParameter("lastName"));
			emp.setPassword(request.getParameter("emppassword"));
			emp.setUsername(request.getParameter("email"));
			
			employeeService.saveEmployee(emp,true);
			
			return "view-login-employee";
		}
		else {
			model.addAttribute("registerError", true);
			return "view-register-employee";
		}
		
	}
	
	@GetMapping("/logout")
	public String logoutEmployee(HttpServletRequest request) {
		HttpSession session= request.getSession(false);
	         session= request.getSession(false);
	        if(session != null) {
	            session.invalidate();
	        }
	        for(Cookie cookie : request.getCookies()) {
	            cookie.setMaxAge(0);
	        }
		return "view-home";
	}

}
