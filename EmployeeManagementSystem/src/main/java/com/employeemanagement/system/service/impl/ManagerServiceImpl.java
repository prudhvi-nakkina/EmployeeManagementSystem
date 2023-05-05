package com.employeemanagement.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeemanagement.system.dao.ManagerDAOImpl;
import com.employeemanagement.system.dao.ProjectDAOImpl;
import com.employeemanagement.system.model.Manager;
import com.employeemanagement.system.model.Project;
import com.employeemanagement.system.service.ManagerService;

@Service 
@Transactional
public class ManagerServiceImpl implements ManagerService {
	
	private ManagerDAOImpl managerDAO;
	private ProjectDAOImpl projectDAO;
	

	@Autowired
	public ManagerServiceImpl(ManagerDAOImpl managerDAO, ProjectDAOImpl projectDAO) {
		super();
		this.managerDAO = managerDAO;
		this.projectDAO = projectDAO;
	}

	@Override
	public void saveManager(Manager manager, Boolean b) {
		if (b) {
			String s = manager.getEmailId().substring(manager.getEmailId().indexOf("@") + 1, manager.getEmailId().indexOf("."));
			manager.setDepartment(s);
			List<String> branches = new ArrayList<>();
			
			branches.add("Fenway");
			branches.add("Downtown");
			branches.add("Chinatown");
			branches.add("Brookline");
			
			int x = (int) ((Math.random() * ((branches.size() - 1) - 0)) + 0);
			manager.setBranch(branches.get(x));
			
			int sal = (int) ((Math.random() * ((200) - 100)) + 100);
			manager.setSalaryPerHour(sal);
		}
		
		List<Project> projects = projectDAO.getAllProjects();
		
		List<Project> assignProjects = new ArrayList<>();
		
		for (Project p : projects) {
			if (manager.getDepartment().equalsIgnoreCase(p.getDepartment()) && p.getProjectManager() == null) {
				assignProjects.add(p);
			}
		}
		
		if (!assignProjects.isEmpty()) {
			List<Project> finalList = new ArrayList<>();
			int r = (int) ((Math.random() * ((assignProjects.size() - 1) - 0)) + 0);
			assignProjects.get(r).setProjectManager(manager);
			finalList.add(assignProjects.get(r));
			manager.setProjects(finalList);
		}
		
		managerDAO.addManager(manager);
	}

	@Override
	public List<Manager> getAllManagers() {
		return managerDAO.getAllManagers();
	}

	@Override
	public Manager getManagerById(long id) {
		return managerDAO.getManager(id);
	}

	@Override
	public void updateManagerById(Manager manager, long id) {
		managerDAO.updateManager(manager);
		
	}

	@Override
	public void deleteManagerById(long id) {
		managerDAO.deleteManager(id);
		
	}
	
	@Override
	public Manager getManagerByEmail(String emailId) {
		 return managerDAO.getManagerByEmail(emailId);
	}

}
