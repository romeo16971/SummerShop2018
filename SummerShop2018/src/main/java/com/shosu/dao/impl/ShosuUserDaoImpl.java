package com.shosu.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shosu.dao.ShosuUserDAO;
import com.shosu.orm.ShosuUser;

public class ShosuUserDaoImpl extends BaseDAOImpl implements ShosuUserDAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8913233395126658627L;

	@Transactional
	public ShosuUser checkLoginByUserPw(String user, String password) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder bui = new StringBuilder(" from ShosuUser u where u.userName = :userName and u.password = :password ");
		Query query = session.createQuery(bui.toString());
		query.setParameter("userName", user);
		query.setParameter("password", password);
		Object oject = query.uniqueResult();
		ShosuUser result = (ShosuUser)query.uniqueResult();
		return result;
	}

	
}
