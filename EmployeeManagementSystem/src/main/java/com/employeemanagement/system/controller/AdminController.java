package com.employeemanagement.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
import com.employeemanagement.system.model.Manager;
import com.employeemanagement.system.model.Project;
import com.employeemanagement.system.service.impl.EmployeeServiceImpl;
import com.employeemanagement.system.service.impl.ManagerServiceImpl;
import com.employeemanagement.system.service.impl.ProjectServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private ProjectServiceImpl projectService;
	private ManagerServiceImpl managerService;
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	public AdminController(ProjectServiceImpl projectService, ManagerServiceImpl managerService, EmployeeServiceImpl employeeService) {
		super();
		this.projectService = projectService;
		this.managerService = managerService;
		this.employeeService = employeeService;
	}

	@GetMapping("/admin-login")
	public String ViewLoginAdmin() {
		return "view-login-admin";
	}
	
	@PostMapping("/login")
	public String loginAdmin(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("emppassword");
		
		if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			return "admin-dashboard";
		}
		model.addAttribute("loginError", true);
		return "view-login-admin";
	}
	
	@GetMapping("/admin-dashboard")
	public String ViewAdminDashboard() {
		return "admin-dashboard";
	}
	
	@GetMapping("/manage-project")
	public String ViewManageProject(Model model) {
		List<Project> projects = projectService.getAllProjects();
		List<Manager> managers = managerService.getAllManagers();
		
		model.addAttribute("projects", projects);
		model.addAttribute("managers", managers);
		
		return "view-manage-project";
	}
	
	@GetMapping("/manage-manager")
	public String ViewManageManager(Model model) {
		List<Manager> managers = managerService.getAllManagers();
		
		model.addAttribute("managers", managers);
		
		return "view-manage-manager";
	}
	
	@PostMapping("/add-update-manager")
	public String addUpdateManager(HttpServletRequest request, Model model) {
		
		if(request.getParameter("selection") != "" && request.getParameter("selection").equals("add")) {
			Manager e = new Manager();
			e.setAddress(request.getParameter("address"));
			e.setBranch(request.getParameter("branch"));
			e.setCity(request.getParameter("city"));
			e.setDepartment(request.getParameter("department"));
			e.setEmailId(request.getParameter("email"));
			e.setFirstName(request.getParameter("firstName"));
			e.setLastName(request.getParameter("lastName"));
			e.setPassword("123");
			e.setUsername(request.getParameter("email"));
			e.setSalaryPerHour(Integer.parseInt(request.getParameter("salary")));
			managerService.saveManager(e,false);
			model.addAttribute("addManagerSuccess", "Manager successfully created");
		} else {
			Manager e = new Manager();
			e = managerService.getManagerById(Long.parseLong(request.getParameter("managerId")));
			if(e != null && e.getManagerId() > 0) {
				if(request.getParameter("address") != "") e.setAddress(request.getParameter("address"));
				if(request.getParameter("branch") != "") e.setBranch(request.getParameter("branch"));
				if(request.getParameter("city") != "") e.setCity(request.getParameter("city"));
				if(request.getParameter("department") != "") e.setDepartment(request.getParameter("department"));
				if(request.getParameter("email") != "") e.setEmailId(request.getParameter("email"));
				if(request.getParameter("firstName") != "") e.setFirstName(request.getParameter("firstName"));
				if(request.getParameter("lastName") != "") e.setLastName(request.getParameter("lastName"));
				if(request.getParameter("email") != "") e.setUsername(request.getParameter("email"));
				if(request.getParameter("salary") != "") e.setSalaryPerHour(Integer.parseInt(request.getParameter("salary")));
				managerService.updateManagerById(e, 0);
				model.addAttribute("addManagerSuccess", "Manager successfully updated");
			} else {
				model.addAttribute("addManagerError", "Manager with given ManagerID not found!");
			}
			
		}
		
		List<Manager> managers = managerService.getAllManagers();
		
		model.addAttribute("managers", managers);
		
		return "view-manage-manager";
		
	}
	
	@GetMapping("/manage-employee")
	public String ViewManageEmployee(Model model) {
		List<Project> projects = projectService.getAllProjects();
		List<Manager> managers = managerService.getAllManagers();
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("projects", projects);
		model.addAttribute("managers", managers);
		model.addAttribute("employees",employees);
		return "view-manage-employee";
	}
	
	@PostMapping("/add-update-employee")
	public String addUpdateEmployee(HttpServletRequest request, Model model) {
		if(request.getParameter("selection") != null && request.getParameter("selection").equals("add")) {
			if (request.getParameter("address") == "" || request.getParameter("branch")== "" || request.getParameter("city")== "" ||
				request.getParameter("department")== "" || request.getParameter("email")== "" || request.getParameter("firstName")== "" ||
				request.getParameter("lastName")== "" || request.getParameter("leaves")== "" || request.getParameter("salary")== "") {
				model.addAttribute("addEmployeeError", "Please fill all the fields");
			} else {
				Employee e = new Employee();
				e.setAddress(request.getParameter("address"));
				e.setBranch(request.getParameter("branch"));
				e.setCity(request.getParameter("city"));
				e.setDepartment(request.getParameter("department"));
				e.setEmailId(request.getParameter("email"));
				e.setFirstName(request.getParameter("firstName"));
				e.setLastName(request.getParameter("lastName"));
				e.setLeavesAvailable(Integer.parseInt(request.getParameter("leaves")));
				e.setLeavesApplied(0);
				e.setPassword("123");
				e.setUsername(request.getParameter("email"));
				e.setSalaryPerHour(Integer.parseInt(request.getParameter("salary")));
				Manager m = new Manager();
				String id = request.getParameter("setManager");
				m = managerService.getManagerById(Long.parseLong(id));
				if(m.getDepartment().equalsIgnoreCase(request.getParameter("department"))) {
					e.setManager(m);
					List<Project>projs = m.getProjects();
					
					if (!projs.isEmpty()) {
						for (Project p : projs) {
							if (p.getProjectId() == Long.parseLong(request.getParameter("setProject"))) {
								e.setProject(p);
								break;
							}
						}
						if (e.getProject() != null && e.getProject().getProjectId() > 0) {
							employeeService.saveEmployee(e,false);
							model.addAttribute("addEmployeeSuccess", "Employee successfully created");
						} else {
							model.addAttribute("addEmployeeError", "Selected Project is not under the selected manager");
						}
					} else {
						model.addAttribute("addEmployeeError", "Selected Manager is not managing any project currently!");
					}
					
				} else {
					model.addAttribute("addEmployeeError", "Employee Department and manager Department should match");
				}
			}
			
		} else {
			if (request.getParameter("employeeId") == null || request.getParameter("employeeId") == "") {
				model.addAttribute("addEmployeeError", "Please Enter Employee Id to update");
			} else {
				Employee e = new Employee();
				e = employeeService.getEmployeeById(Long.parseLong(request.getParameter("employeeId")));
				
				if(e != null && e.getEmployeeId() > 0) {
					if(request.getParameter("address") != "") e.setAddress(request.getParameter("address"));
					if(request.getParameter("branch") != "") e.setBranch(request.getParameter("branch"));
					if(request.getParameter("city") != "") e.setCity(request.getParameter("city"));
					if(request.getParameter("department") != "") e.setDepartment(request.getParameter("department"));
					if(request.getParameter("email") != "") e.setEmailId(request.getParameter("email"));
					if(request.getParameter("firstName") != "") e.setFirstName(request.getParameter("firstName"));
					if(request.getParameter("lastName") != "") e.setLastName(request.getParameter("lastName"));
					if(request.getParameter("leaves") != "") e.setLeavesAvailable(Integer.parseInt(request.getParameter("leaves")));
					if(request.getParameter("email") != "") e.setUsername(request.getParameter("email"));
					if(request.getParameter("salary") != "") e.setSalaryPerHour(Integer.parseInt(request.getParameter("salary")));
					Manager m = new Manager();
					String id = request.getParameter("setManager");
					m = managerService.getManagerById(Long.parseLong(id));
					if(m.getDepartment().equalsIgnoreCase(e.getDepartment())) {
						e.setManager(m);
						List<Project>projs = m.getProjects();
						
						for (Project p : projs) {
							if (p.getProjectId() == Long.parseLong(request.getParameter("setProject"))) {
								e.setProject(p);
								break;
							}
						}
						if (e.getProject() != null && e.getProject().getProjectId() > 0) {
							employeeService.updateEmployeeById(e, 0);
							model.addAttribute("addEmployeeSuccess", "Employee updated successfully");
						} else {
							model.addAttribute("addEmployeeError", "Selected Project is not under the selected manager");
						}
					} else {
						model.addAttribute("addEmployeeError", "Employee Department and manager Department should match");
					}
				} else {
					model.addAttribute("addEmployeeError", "Employee with given EmployeeID is not found!");
				}
			}
			
		}
		List<Project> projects = projectService.getAllProjects();
		List<Manager> managers = managerService.getAllManagers();
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("projects", projects);
		model.addAttribute("managers", managers);
		model.addAttribute("employees",employees);
		return "view-manage-employee";
	}
	
	@PostMapping("/add-update-project")
	public String addUpdateProject(HttpServletRequest request, Model model) {
		if(request.getParameter("selection") != null && request.getParameter("selection").equals("add")) {
			Project p = new Project();
			p.setProjectName(request.getParameter("projectName"));
			p.setBudget(Double.parseDouble(request.getParameter("budget")));
			p.setClient(request.getParameter("client"));
			p.setDepartment(request.getParameter("department"));
			Manager m = new Manager();
			String id = request.getParameter("setManager");
			m = managerService.getManagerById(Long.parseLong(id));
			if(m.getDepartment().equalsIgnoreCase(request.getParameter("department"))) {
				Date date = new Date();  
			    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			    String strDate = formatter.format(date);  
				p.setStartDate(strDate);
				p.setProjectManager(m);
				projectService.saveProject(p);
				model.addAttribute("addProjectSuccess", "Project successfully created");
			} else {
				model.addAttribute("addProjectError", "Project Department and manager Department should match");
			}
		} else {
			Project p = new Project();
			p = projectService.getProjectById(Long.parseLong(request.getParameter("projectId")));
			
			if(p != null && p.getProjectId() > 0) {
				if(request.getParameter("projectName") != "") p.setProjectName(request.getParameter("projectName"));
				if(request.getParameter("budget") != "") p.setBudget(Double.parseDouble(request.getParameter("budget")));
				if(request.getParameter("client") != "") p.setClient(request.getParameter("client"));
				if(request.getParameter("department") != "") p.setDepartment(request.getParameter("department"));
				Manager m = new Manager();
				String id = request.getParameter("setManager");
				m = managerService.getManagerById(Long.parseLong(id));
				if(m.getDepartment().equalsIgnoreCase(p.getDepartment())) {
					p.setProjectManager(m);
					projectService.updateProjectById(p, 0);
					model.addAttribute("addProjectError", null);
					model.addAttribute("addProjectSuccess", "Project successfully updated");
				} else {
					model.addAttribute("addProjectError", "Project Department and manager Department should match!");
				}
			} else {
				model.addAttribute("addProjectError", "Project with given ProjectID is not found!");
			}
		}
		List<Project> projects = projectService.getAllProjects();
		List<Manager> managers = managerService.getAllManagers();
		model.addAttribute("managers", managers);
		model.addAttribute("projects", projects);
		return "view-manage-project";
	}
	
	@PostMapping("/delete-project")
	public String deleteProject(HttpServletRequest request,Model model) {
		if (projectService.getProjectById(Long.parseLong(request.getParameter("deleteid"))) != null) {
			Project p = projectService.getProjectById(Long.parseLong(request.getParameter("deleteid")));
			List<Employee> employees = employeeService.getAllEmployees();
			for (Employee e : employees) {
				if(e.getProject().getProjectId() == Long.parseLong(request.getParameter("deleteid"))) {
					e.setProject(null);
					employeeService.updateEmployeeById(e, 0);
				}
			}
			p.setEmployees(null);
			projectService.updateProjectById(p, 0);
			projectService.deleteProjectById(Long.parseLong(request.getParameter("deleteid")));
			model.addAttribute("deleteProjectSuccess", "Project successfully deleted!");
		} else {
			model.addAttribute("deleteProjectError", "Project with given ProjectID is not found!");
		}
		List<Project> projects = projectService.getAllProjects();
		model.addAttribute("projects", projects);
		List<Manager> managers = managerService.getAllManagers();
		model.addAttribute("managers", managers);
		return "view-manage-project";
	}
	
	@PostMapping("/delete-manager")
	public String deletemanager(HttpServletRequest request,Model model) {
		if (managerService.getManagerById(Long.parseLong(request.getParameter("deleteid"))) != null) {
			Manager m = managerService.getManagerById(Long.parseLong(request.getParameter("deleteid")));
			List<Project> projects = projectService.getAllProjects();
			List<Employee> employees = employeeService.getAllEmployees();
			
			for (Project p : projects) {
				if(p.getProjectManager().getManagerId() == Long.parseLong(request.getParameter("deleteid"))) {
					p.setProjectManager(null);
					projectService.updateProjectById(p, 0);
				}
			}
			for (Employee e : employees) {
				if(e.getManager().getManagerId() == Long.parseLong(request.getParameter("deleteid"))) {
					e.setManager(null);
					employeeService.updateEmployeeById(e, 0);
				}
			}
			m.setEmployees(null);
			m.setProjects(null);
			managerService.updateManagerById(m, 0);
			managerService.deleteManagerById(Long.parseLong(request.getParameter("deleteid")));
			model.addAttribute("deleteManagerSuccess", "Manager successfully deleted!");
		} else {
			model.addAttribute("deleteManagerError", "Manager with given ManagerID is not found!");
		}
		List<Manager> managers = managerService.getAllManagers();
		model.addAttribute("managers", managers);
		return "view-manage-manager";
	}
	
	@PostMapping("/delete-employee")
	public String deleteEmployee(HttpServletRequest request,Model model) {
		Employee e = employeeService.getEmployeeById(Long.parseLong(request.getParameter("deleteid")));
		if (e != null) {
			employeeService.deleteEmployeeById(e.getEmployeeId());
			model.addAttribute("deleteEmployeeSuccess", "Employee successfully deleted!");
		} else {
			model.addAttribute("deleteEmployeeError", "Employee with given EmployeeID is not found!");
		}
		List<Project> projects = projectService.getAllProjects();
		List<Manager> managers = managerService.getAllManagers();
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("projects", projects);
		model.addAttribute("managers", managers);
		model.addAttribute("employees",employees);
		return "view-manage-employee";
	}
	
	@GetMapping("/perform-analytics")
	public String performAnalytics(HttpServletRequest request, Model model) throws ParseException {
		if (request.getSession().getAttribute("employees") == null) {
			List<Project> projects = projectService.getAllProjects();
			List<Project> pprojects = new ArrayList<>();
			List<Project> nprojects = new ArrayList<>();
			List<Manager> managers = new ArrayList<>();
			managers = managerService.getAllManagers();
			List<Employee> employees = new ArrayList<>();
			employees = employeeService.getAllEmployees();
			
			for (Project p : projects) {
				int sum = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			    Date firstDate = sdf.parse(p.getStartDate());
			    LocalDate dateObj = LocalDate.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		        String date = dateObj.format(formatter);
			    Date secondDate = sdf.parse(date);
			    if (p.getProjectManager() != null) {
			    	sum += p.getProjectManager().getSalaryPerHour()*(TimeUnit.DAYS.convert(Math.abs(secondDate.getTime() - firstDate.getTime()),TimeUnit.MILLISECONDS))*8;
			    }
			    if (p.getEmployees() != null) {
			    	for (Employee e : p.getEmployees()) {
			    		if (e != null) {
			    			sum += e.getSalaryPerHour()*(TimeUnit.DAYS.convert(Math.abs(secondDate.getTime() - firstDate.getTime()),TimeUnit.MILLISECONDS))*8;
			    		}
					}
			    }
				
				p.setExpenditure((double) sum);
				projectService.updateProjectById(p, 0);
				if (sum > p.getBudget()) {
					nprojects.add(p);
				} else {
					pprojects.add(p);
				}
			}
			Collections.sort(managers, 
	                (o1, o2) -> o1.getSalaryPerHour() > o2.getSalaryPerHour() ? -1 : o1.getSalaryPerHour() < o2.getSalaryPerHour() ? 1 : 0);
			Collections.sort(employees, 
	                (o1, o2) -> o1.getSalaryPerHour() > o2.getSalaryPerHour() ? -1 : o1.getSalaryPerHour() < o2.getSalaryPerHour() ? 1 : 0);
			HttpSession session = request.getSession();
			session.setAttribute("employees",employees);
			session.setAttribute("managers",managers);
			session.setAttribute("pprojects",pprojects);
			session.setAttribute("nprojects",nprojects);
			
			model.addAttribute("employees",employees);
			model.addAttribute("managers",managers);
			model.addAttribute("pprojects",pprojects);
			model.addAttribute("nprojects",nprojects);
		} else {
			model.addAttribute("employees",request.getSession().getAttribute("employees"));
			model.addAttribute("managers",request.getSession().getAttribute("managers"));
			model.addAttribute("pprojects",request.getSession().getAttribute("pprojects"));
			model.addAttribute("nprojects",request.getSession().getAttribute("nprojects"));
		}
		
		
		return "analytics";
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
