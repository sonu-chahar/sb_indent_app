package com.chahar.indent.service;

import java.util.List;

import com.chahar.indent.model.ProductMaster;

public interface ProductMasterService extends GenericService<ProductMaster, Long> {

	public ProductMaster findProduct(String code);

	public List<ProductMaster> getAllProducts();

	public List<ProductMaster> searchProducts(String searchCode, String searchName);

}