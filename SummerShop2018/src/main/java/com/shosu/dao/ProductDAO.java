package com.shosu.dao;

import java.util.List;

import com.shosu.dto.ProductDTO;

public interface ProductDAO extends BaseDAO{
	public List<ProductDTO> getAllProduct(int index,int pageSize);
	public int countAllProduct();	
}
