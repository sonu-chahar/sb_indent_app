package com.chahar.indent.dao;

import java.util.List;

import com.chahar.indent.model.ProductCategory;

public interface ProductCategoryDao extends GenericDao<ProductCategory, Long> {

	public ProductCategory findProductCategory(String code);

	public List<ProductCategory> getAllProductCategories();

	public List<ProductCategory> searchProductCategories(String searchName); 

}
