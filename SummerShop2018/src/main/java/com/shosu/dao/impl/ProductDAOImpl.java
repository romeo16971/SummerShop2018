package com.shosu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shosu.dao.ProductDAO;
import com.shosu.dto.ProductDTO;

public class ProductDAOImpl extends BaseDAOImpl implements ProductDAO{

	
	
	private static String QUERY_GET_ALL_PRODUCT = "from Product ";
	@Override
	public List<ProductDTO> getAllProduct(int index, int pageSize) {
		Session session= getSessionFactory().getCurrentSession();
		Query query = session.createQuery(QUERY_GET_ALL_PRODUCT);
		query.setFirstResult(index);
		query.setMaxResults(pageSize);
		List<ProductDTO> list = query.list();
		return list;
	}
	private static String QUERY_COUNT_ALL_PRODUCT = "SELECT COUNT(*) FROM Product ";
	@Override
	public int countAllProduct() {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(QUERY_COUNT_ALL_PRODUCT);
		int result = (Integer)query.uniqueResult();
		return result;
	}

}
