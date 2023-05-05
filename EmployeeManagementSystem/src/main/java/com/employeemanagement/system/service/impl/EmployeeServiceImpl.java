package com.employeemanagement.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.employeemanagement.system.dao.EmployeeDAOImpl;
import com.employeemanagement.system.dao.ManagerDAOImpl;
import com.employeemanagement.system.model.Employee;
import com.employeemanagement.system.model.Manager;
import com.employeemanagement.system.model.Project;
import com.employeemanagement.system.service.EmployeeService;

@Service 
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAOImpl employeeDAO;
	private ManagerDAOImpl managerDAO;
	

	@Autowired
	public EmployeeServiceImpl(EmployeeDAOImpl employeeDAO, ManagerDAOImpl managerDAO) {
		super();
		this.employeeDAO = employeeDAO;
		this.managerDAO = managerDAO;
	}

	@Override
	public void saveEmployee(Employee employee, Boolean b) {
		
		if (b) {
			String s = employee.getEmailId().substring(employee.getEmailId().indexOf("@") + 1, employee.getEmailId().indexOf("."));
			if(s.equalsIgnoreCase("RD")) {
				employee.setDepartment("R&D");
			} else {
				employee.setDepartment(s);
			}
			employee.setLeavesAvailable(15);
			
			List<Manager> mans = new ArrayList<>();
			
			List<String> branches = new ArrayList<>();
			
			branches.add("Fenway");
			branches.add("Downtown");
			branches.add("Chinatown");
			branches.add("Brookline");
			
			int sal = (int) ((Math.random() * ((100) - 40)) + 40);
			employee.setSalaryPerHour(sal);
			
			int x = (int) ((Math.random() * ((branches.size() - 1) - 0)) + 0);
			employee.setBranch(branches.get(x));
			
			
			for (Manager m : managerDAO.getAllManagers()) {
				if(m.getDepartment().equalsIgnoreCase(employee.getDepartment())) {
					mans.add(m);
				}
			}
			
			if (!mans.isEmpty()) {
				int num = (int) ((Math.random() * ((mans.size() - 1) - 0)) + 0);
				employee.setManager(mans.get(num));
				
				List<Project> projs = new ArrayList<>();
				projs = mans.get(num).getProjects();
				
				if (!projs.isEmpty()) {
					int pnum = (int) ((Math.random() * ((projs.size() - 1) - 0)) + 0);
					employee.setProject(projs.get(pnum));
				}
			}
		}
		
		employeeDAO.addEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployee(id);
	}

	@Override
	public void updateEmployeeById(Employee employee, long id) {
		// TODO Auto-generated method stub
		employeeDAO.updateEmployee(employee);
	}

	@Override
	public void deleteEmployeeById(long id) {
		Employee e = employeeDAO.getEmployee(id);
		List<Employee> el = e.getManager().getEmployees();
		el.remove(e);
        List<Employee> pl = e.getProject().getEmployees();
        pl.remove(e);
		employeeDAO.deleteEmployee(id);
		
	}

	@Override
	public Employee getEmployeeByEmail(String emailId) {
		 return employeeDAO.getEmployeeByEmail(emailId);
	}

	

}
