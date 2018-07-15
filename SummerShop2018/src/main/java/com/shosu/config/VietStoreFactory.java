package com.shosu.config;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
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

import com.shosu.dto.EmployeeDTO;

@Configuration
public class VietStoreFactory implements CacheStore<Integer, EmployeeDTO> {
	

	static SessionFactory sessionFactory;

	@Override
	public EmployeeDTO load(Integer id) throws CacheLoaderException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Employee where id= :id ");
		query.setParameter("id", id);
		EmployeeDTO result = (EmployeeDTO) query.uniqueResult();
		return result;
	}

	@Override
	public Map<Integer, EmployeeDTO> loadAll(Iterable<? extends Integer> arg0) throws CacheLoaderException {
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
	public void write(Entry<? extends Integer, ? extends EmployeeDTO> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeAll(Collection<Entry<? extends Integer, ? extends EmployeeDTO>> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void loadCache(IgniteBiInClosure<Integer, EmployeeDTO> clo, Object... arg1) throws CacheLoaderException {
		
		try {
			System.out.println(">> Loading cache from store...");
			System.out.println(">> Loading cache from store...");
			
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from EmployeeDTO  ");
			List<EmployeeDTO> result = (List<EmployeeDTO>) query.list();
			for (EmployeeDTO temp : result) {
				clo.apply(temp.getId(), temp);
			}
		}catch (Exception e) {
			e.printStackTrace();
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
		VietStoreFactory.sessionFactory = sessionFactory;
	}

}
