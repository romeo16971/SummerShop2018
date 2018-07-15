package com.shosu.orm;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 328943295259209041L;
	private String productId;
	private String productName;
	private Double productPrice;
	private Date createdDate;
	private String createdBy;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public Double getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}



	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}



	public String getLastModifiedBy() {
		return lastModifiedBy;
	}



	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Product))
			return false;
		Product castOther = (Product) other;
		if (castOther.getProductId() == null
				&& getProductId()  == null)
			return true;
		if (getProductId()   == null)
			return false;
		return (this.getProductId()   == (castOther
				.getProductId()  ));
	}

	public int hashCode() {
		int result = 17;
		result = 37
				* result
				+ (getProductId()   == null ? 0 : this
						.getProductId().hashCode());
		return result;
	}	
}
