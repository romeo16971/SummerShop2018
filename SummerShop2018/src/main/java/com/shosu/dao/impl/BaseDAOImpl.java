package com.shosu.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shosu.dao.BaseDAO;

public class BaseDAOImpl implements BaseDAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1852473335627934893L;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int update(Object obj) {
		sessionFactory.getCurrentSession().update(obj);
		return 0;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int insert(Object obj) {
		sessionFactory.getCurrentSession().persist(obj);
		return 0;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int insertOrUpdate(Object obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		return 0;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int delete(Object obj) {
		sessionFactory.getCurrentSession().delete(obj);;
		return 0;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	

}
