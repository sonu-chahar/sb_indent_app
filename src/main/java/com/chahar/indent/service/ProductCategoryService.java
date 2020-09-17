package com.chahar.indent.service;

import java.util.List;

import com.chahar.indent.model.ProductCategory;

public interface ProductCategoryService extends GenericService<ProductCategory, Long> {

	public ProductCategory findProductCategory(String code);

	public List<ProductCategory> getAllProductCategories();

	public List<ProductCategory> searchProductCategories(String searchName);

}