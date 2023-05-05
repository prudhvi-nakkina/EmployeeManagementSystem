package com.employeemanagement.system.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.employeemanagement.system.model.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Project> getAllProjects() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Project>  projects = session.createQuery("from Project", Project.class).list();
		return projects;
	}

	@Override
	public Project getProject(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Project project = (Project) session.get(Project.class, id);
		return project;

	}

	@Override
	public void addProject(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(project);
	}

	@Override
	public void updateProject(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(project);
		session.update(project);
	}

	@Override
	public void deleteProject(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Project project = (Project) session.load(Project.class, id);
		if (null != project) {
			session.delete(project);
		}
		
	}

}
