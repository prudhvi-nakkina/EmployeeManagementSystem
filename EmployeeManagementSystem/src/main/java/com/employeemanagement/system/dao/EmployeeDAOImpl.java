package com.employeemanagement.system.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employeemanagement.system.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Employee> getAllEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee>  employees = session.createQuery("from Employee", Employee.class).list();
		return employees;
	}

	@Override
	public Employee getEmployee(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee emp = (Employee) session.get(Employee.class, id);
		return emp;	}

	@Override
	public void addEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employee);
		session.update(employee);
	}

	@Override
	public void deleteEmployee(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee emp = (Employee) session.load(Employee.class, id);
		if (null != emp) {
			session.delete(emp);
		}
		
	}

	@Override
	public Employee getEmployeeByEmail(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Employee where emailId=:email";
		Query query = session.createQuery(hql);
        query.setParameter("email", emailId);
        Employee employee = null;
        if(!query.list().isEmpty()) {
        	employee = (Employee) query.list().get(0);
        }
		return employee;
	}

}
