package com.chahar.indent.dao;

import java.util.List;

import com.chahar.indent.model.ProductMaster;

public interface ProductMasterDao extends GenericDao<ProductMaster, Long> {

	public ProductMaster findProduct(String code);

	public List<ProductMaster> getAllProducts();

	public List<ProductMaster> searchProducts(String searchCode, String searchName);

}
