package com.employeemanagement.system.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employeemanagement.system.model.Employee;
import com.employeemanagement.system.model.Manager;

@Repository
public class ManagerDAOImpl implements ManagerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Manager> getAllManagers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Manager>  managers = session.createQuery("from Manager", Manager.class).list();
		return managers;
	}

	@Override
	public Manager getManager(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Manager manager = (Manager) session.get(Manager.class, id);
		return manager;
	}

	@Override
	public void addManager(Manager manager) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(manager);
	}

	@Override
	public void updateManager(Manager manager) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(manager);
		session.update(manager);
	}

	@Override
	public void deleteManager(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Manager manager = (Manager) session.load(Manager.class, id);
		if (null != manager) {
			session.delete(manager);
		}
	}
	
	@Override
	public Manager getManagerByEmail(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Manager where emailId=:email";
		Query query = session.createQuery(hql);
        query.setParameter("email", emailId);
        Manager manager = null;
        if(!query.list().isEmpty()) {
        	manager = (Manager) query.list().get(0);
        }
		return manager;
	}

}
