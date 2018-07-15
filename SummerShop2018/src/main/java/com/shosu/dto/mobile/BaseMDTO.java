package com.shosu.dto.mobile;

import java.io.Serializable;

public class BaseMDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5857293001165572467L;
	protected double lmd;
	protected Object content;
	protected int key;
	protected int value;
	protected int status;
	public BaseMDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getLmd() {
		return lmd;
	}
	public void setLmd(double lmd) {
		this.lmd = lmd;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	

}
