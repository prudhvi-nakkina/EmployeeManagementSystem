package com.employeemanagement.system.dao;

import java.util.List;

import com.employeemanagement.system.model.Project;

public interface ProjectDAO {

	public List<Project> getAllProjects() ;

	public Project getProject(long id) ;

	public void addProject(Project project);

	public void updateProject(Project project) ;

	public void deleteProject(long id) ;
}
