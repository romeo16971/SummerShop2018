package com.shosu.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.shosu.dao.ShosuUserDAO;
import com.shosu.orm.ShosuUser;

@ManagedBean(name="loginAdmin",eager=true)
@ViewScoped
public class LoginAdmin {
	private String userName;
	private String password;
	private Persion persion;
	private Boolean fuck;
	@ManagedProperty(value = "#{shosuUserDao}")
	ShosuUserDAO shosuUserDao;
	
	public void init() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			persion = new Persion();
			persion.setName("viet");
		}
	}
	
	public String checkLogin() {
		
		try {
			ShosuUser user = shosuUserDao.checkLoginByUserPw(userName, password);
			if(user!=null)
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("oke"));
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("dm cut"));
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String TestShit() {
		try {
			persion.setName("viet2");
			userName="hempme";
			fuck=true;
			RequestContext.getCurrentInstance().update("form1");
			RequestContext.getCurrentInstance().update("form2");
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	public class Persion{
		private String name;
		public Persion() {
			// TODO Auto-generated constructor stub
		}
		
		

		public Persion(String name) {
			super();
			this.name = name;
		}



		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
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

	public ShosuUserDAO getShosuUserDao() {
		return shosuUserDao;
	}

	public void setShosuUserDao(ShosuUserDAO shosuUserDao) {
		this.shosuUserDao = shosuUserDao;
	}

	public Boolean getFuck() {
		return fuck;
	}

	public void setFuck(Boolean fuck) {
		this.fuck = fuck;
	}

	public Persion getPersion() {
		return persion;
	}

	public void setPersion(Persion persion) {
		this.persion = persion;
	}
	
	
	
	
}
