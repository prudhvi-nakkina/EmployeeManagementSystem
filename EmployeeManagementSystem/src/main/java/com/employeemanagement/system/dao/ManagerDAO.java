package com.employeemanagement.system.dao;

import java.util.List;
import com.employeemanagement.system.model.Manager;

public interface ManagerDAO{
	
	public List<Manager> getAllManagers() ;

	public Manager getManager(long id) ;

	public void addManager(Manager manager);

	public void updateManager(Manager manager) ;

	public void deleteManager(long id) ;

	Manager getManagerByEmail(String emailId);

}
