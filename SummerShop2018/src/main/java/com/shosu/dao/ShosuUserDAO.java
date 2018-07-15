package com.shosu.dao;

import com.shosu.orm.ShosuUser;

public interface ShosuUserDAO extends BaseDAO {
	
	public ShosuUser checkLoginByUserPw(String user, String password);
}
