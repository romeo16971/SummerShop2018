package com.shosu.dao;

import java.io.Serializable;

public interface BaseDAO extends Serializable{
	
	public int update(Object obj);

	/**
	 * 
	 * @param obj
	 * @param session
	 */
	public int insert(Object obj) ;
	/**
	 * 
	 * @param obj
	 */
	public int insertOrUpdate(Object obj) ;

	
	/**
	 * 
	 * @param obj
	 * @param session
	 */
	public int delete(Object obj) ;
}
