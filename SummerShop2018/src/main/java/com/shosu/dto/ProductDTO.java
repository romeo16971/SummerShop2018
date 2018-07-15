package com.shosu.dto;

import java.util.Date;

public class ProductDTO extends BaseDTO{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 328943295259209041L;
	private String productName;
	private Double productPrice;
	private Date createdDate;
	private String createdBy;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	
	public ProductDTO() {

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
		if (!(other instanceof ProductDTO))
			return false;
		ProductDTO castOther = (ProductDTO) other;
		if (castOther.getSid() == null
				&& getSid()  == null)
			return true;
		if (getSid()   == null)
			return false;
		return (this.getSid()   == (castOther
				.getSid()  ));
	}

	public int hashCode() {
		int result = 17;
		result = 37
				* result
				+ (getSid()   == null ? 0 : this
						.getSid().hashCode());
		return result;
	}	
}
