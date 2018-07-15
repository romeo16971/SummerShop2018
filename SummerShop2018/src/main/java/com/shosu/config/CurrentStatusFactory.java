package com.shosu.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import javax.transaction.Transactional;

import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.apache.ignite.resources.SpringResource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shosu.orm.CurrentStatus;

@Configuration
public class CurrentStatusFactory implements CacheStore<String, CurrentStatus> {


	static SessionFactory sessionFactory;
	
	@Override
	public CurrentStatus load(String arg0) throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, CurrentStatus> loadAll(Iterable<? extends String> arg0) throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Collection<?> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Entry<? extends String, ? extends CurrentStatus> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeAll(Collection<Entry<? extends String, ? extends CurrentStatus>> arg0)
			throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void loadCache(IgniteBiInClosure<String, CurrentStatus> arg0, Object... arg1) throws CacheLoaderException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(" from CurrentStatus  ");
		List<CurrentStatus> result = (List<CurrentStatus>) query.list();
		for (CurrentStatus temp : result) {
			arg0.apply(temp.getDriverId(), temp);
		}
		
	}

	@Override
	public void sessionEnd(boolean arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public  void setSessionFactory(SessionFactory sessionFactory) {
		CurrentStatusFactory.sessionFactory = sessionFactory;
	}
	

}
