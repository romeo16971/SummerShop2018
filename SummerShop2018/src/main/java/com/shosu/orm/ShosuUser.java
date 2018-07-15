package com.shosu.orm;

import java.io.Serializable;

public class ShosuUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1235670039511807281L;
	
	private String userId;
	private String userName;
	private String password;
	private String role;
	
	
	
	public ShosuUser() {
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ShosuUser))
			return false;
		ShosuUser castOther = (ShosuUser) other;
		if (castOther.getUserId() == null
				&& getUserId() == null)
			return true;
		if (getUserId()  == null)
			return false;
		return (this.getUserId()  == (castOther
				.getUserId() ));
	}

	public int hashCode() {
		int result = 17;
		result = 37
				* result
				+ (getUserId()  == null ? 0 : this
						.getUserId().hashCode());
		return result;
	}	
	
	
}
