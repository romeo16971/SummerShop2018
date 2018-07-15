package com.shosu.orm;

import java.io.Serializable;

public class Driver implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1954800769852302498L;
	private String driverId;
	
	public Driver() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Driver(String driverId) {
		super();
		this.driverId = driverId;
	}


	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	

}
