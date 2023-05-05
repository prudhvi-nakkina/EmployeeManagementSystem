package com.employeemanagement.system.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employeemanagement.system.model.Employee;
import com.employeemanagement.system.model.Manager;
import com.employeemanagement.system.model.Project;
import com.employeemanagement.system.service.impl.EmployeeServiceImpl;
import com.employeemanagement.system.service.impl.ManagerServiceImpl;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	private ManagerServiceImpl managerService;
	private EmployeeServiceImpl employeeService;

	@Autowired
	public ManagerController(ManagerServiceImpl managerService, EmployeeServiceImpl employeeService) {
		super();
		this.managerService = managerService;
		this.employeeService = employeeService;
	}
	
	@GetMapping("/manager-register")
	public String ViewRegisterManager() {
		return "view-register-manager";
	}
	
	@GetMapping("/manager-login")
	public String ViewLoginManager() {
		return "view-login-manager";
	}
	
	@PostMapping("/register")
	public String registerManager(HttpServletRequest request) {
		
		Manager man = new Manager();
		
		man.setAddress(request.getParameter("address"));
		man.setCity(request.getParameter("city"));
		man.setEmailId(request.getParameter("email"));
		man.setFirstName(request.getParameter("firstName"));
		man.setLastName(request.getParameter("lastName"));
		man.setPassword(request.getParameter("emppassword"));
		man.setUsername(request.getParameter("email"));
		
		managerService.saveManager(man,true);
		
		return "view-login-manager";
	}
	
	@PostMapping("/login")
	public String loginEmployee(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("manObject") == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("emppassword");
			Manager man = managerService.getManagerByEmail(username);
			if(man != null && man.getUsername() !=null) {
				if(man.getUsername().equals(username) && man.getPassword().equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("manObject", man);
					Collection<Employee> employees = man.getEmployees();
					Collection<Project> projects = man.getProjects();
					model.addAttribute("employees",employees);
					model.addAttribute("projects",projects);
					model.addAttribute("manager",man);
					return "manager-dashboard";
				}
			}
			model.addAttribute("loginError", true);
			return "view-login-manager";
		} else {
			Manager man = (Manager)request.getSession().getAttribute("manObject");
			Collection<Employee> employees = man.getEmployees();
			Collection<Project> projects = man.getProjects();
			model.addAttribute("employees",employees);
			model.addAttribute("projects",projects);
			model.addAttribute("manager",man);
			return "manager-dashboard";
		}
	}
	
	@GetMapping("/portal")
	public String openPortal(HttpServletRequest request, Model model) {
		Manager man = (Manager)request.getSession().getAttribute("manObject");
		
			List<Employee> list = new ArrayList<>();
			
			for (Employee emp : man.getEmployees()) {
				if(emp.getLeavesApplied() > 0) {
					list.add(emp);
				}
			}
			model.addAttribute("leaveList",list);
			Collection<Employee> employees = man.getEmployees();
			Collection<Project> projects = man.getProjects();
			System.out.println("!!!!!!!!!!!!!!!!!!!insideportal"+employees.size());
			model.addAttribute("employees",employees);
			model.addAttribute("projects",projects);
			model.addAttribute("manager",man);
			return "portal";
	}
	
	@PostMapping("/approve-leave")
	public String approveLeave(HttpServletRequest request, Model model, @ModelAttribute("approveStatus") String status) {	
		Manager man = (Manager)request.getSession().getAttribute("manObject");
			System.out.println("**************************************1"+status);
			Collection<Employee> employees = man.getEmployees();
			for (Employee emp : employees) {
				if(emp.getEmployeeId() == Long.parseLong(status)) {
					System.out.println("**************************************2");
					emp.setLeavesAvailable(emp.getLeavesAvailable() - emp.getLeavesApplied());
					emp.setLeavesApplied(0);
					employeeService.updateEmployeeById(emp, 0);
					model.addAttribute("employees",employees);
					Collection<Project> projects = man.getProjects();
					model.addAttribute("projects",projects);
					model.addAttribute("manager",man);
				}
			}
		return "portal";
	}
	
	@PostMapping("/transfer-employee")
	public String transferEmployee(HttpServletRequest request, Model model, @ModelAttribute("transferEmp") String empstatus, @ModelAttribute("transferProj") String projstatus) {
		Manager man = (Manager)request.getSession().getAttribute("manObject");
		Collection<Employee> employees = man.getEmployees();
		Collection<Project> projects = man.getProjects();
		Project p = new Project();
		for(Project proj:projects) {
			if(proj.getProjectName().equalsIgnoreCase(projstatus)) {
				p = proj;
			}
		}
		for (Employee emp : employees) {
			if(emp.getEmployeeId() == Long.parseLong(empstatus)) {
				emp.setProject(p);
				employeeService.updateEmployeeById(emp, 0);
				model.addAttribute("success",true);
			}
		}
		Manager manager = managerService.getManagerByEmail(man.getEmailId());
		Collection<Employee> employeesnew = manager.getEmployees();
		Collection<Project> projectsnew = manager.getProjects();
		model.addAttribute("manager",manager);
		model.addAttribute("employees",employeesnew);
		model.addAttribute("projects",projectsnew);
		return "portal";
	}
	
	@GetMapping("/logout")
	public String logoutManager(HttpServletRequest request) {
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
