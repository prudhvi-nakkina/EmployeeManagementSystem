package com.employeemanagement.system.service;

import java.util.List;

import com.employeemanagement.system.model.Project;

public interface ProjectService {
	void saveProject(Project project);
	List<Project> getAllProjects();
	Project getProjectById(long id);
	void updateProjectById(Project project, long id);
	void deleteProjectById(long id);
}
