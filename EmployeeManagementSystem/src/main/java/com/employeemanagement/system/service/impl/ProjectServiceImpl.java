package com.employeemanagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeemanagement.system.dao.ProjectDAOImpl;
import com.employeemanagement.system.model.Employee;
import com.employeemanagement.system.model.Project;
import com.employeemanagement.system.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
	
	private ProjectDAOImpl projectDAO;

	@Autowired
	public ProjectServiceImpl(ProjectDAOImpl projectDAO) {
		super();
		this.projectDAO = projectDAO;
	}

	@Override
	public void saveProject(Project project) {
		projectDAO.addProject(project);
	}

	@Override
	public List<Project> getAllProjects() {
		
		return projectDAO.getAllProjects();
	}

	@Override
	public Project getProjectById(long id) {
		
		return projectDAO.getProject(id);
	}

	@Override
	public void updateProjectById(Project project, long id) {
		projectDAO.updateProject(project);
	}

	@Override
	public void deleteProjectById(long id) {
		Project p = projectDAO.getProject(id);
		List<Project> pl = p.getProjectManager().getProjects();
		pl.remove(p);
		projectDAO.deleteProject(id);
	}

}
