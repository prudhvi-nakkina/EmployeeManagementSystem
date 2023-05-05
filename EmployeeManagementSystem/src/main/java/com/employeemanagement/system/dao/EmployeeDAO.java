package com.employeemanagement.system.dao;

import java.util.List;

import com.employeemanagement.system.model.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getAllEmployees() ;

	public Employee getEmployee(long id) ;
	
	public Employee getEmployeeByEmail(String emailId) ;

	public void addEmployee(Employee employee);

	public void updateEmployee(Employee employee) ;

	public void deleteEmployee(long id) ;
	
	

}
