package com.employeemanagement.system.service;

import java.util.List;

import com.employeemanagement.system.model.Employee;


public interface EmployeeService {
	void saveEmployee(Employee employee, Boolean b);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	void updateEmployeeById(Employee employee, long id);
	void deleteEmployeeById(long id);
	Employee getEmployeeByEmail(String emailId);
}
