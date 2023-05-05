package com.employeemanagement.system.service;

import java.util.List;
import com.employeemanagement.system.model.Manager;

public interface ManagerService {
	void saveManager(Manager manager, Boolean b);
	List<Manager> getAllManagers();
	Manager getManagerById(long id);
	void updateManagerById(Manager manager, long id);
	void deleteManagerById(long id);
	Manager getManagerByEmail(String emailId);
}
