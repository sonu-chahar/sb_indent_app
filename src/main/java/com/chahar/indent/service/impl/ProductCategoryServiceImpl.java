package com.chahar.indent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.ProductCategoryDao;
import com.chahar.indent.model.ProductCategory;
import com.chahar.indent.service.ProductCategoryService;

@Service("productCategoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductCategoryServiceImpl extends GenericServiceImpl<ProductCategory, Long>
		implements ProductCategoryService {

	private ProductCategoryDao productCategoryDao;

	@Autowired
	public ProductCategoryServiceImpl(@Qualifier("productCategoryDao") ProductCategoryDao productCategoryDao) {
		super(productCategoryDao);
		this.productCategoryDao = productCategoryDao;

	}

	@Override
	public ProductCategory findProductCategory(String name) {
		return productCategoryDao.findProductCategory(name);
	}

	@Override
	public List<ProductCategory> getAllProductCategories() {
		return productCategoryDao.getAllProductCategories();
	}

	@Override
	public List<ProductCategory> searchProductCategories(String searchName) {
		return productCategoryDao.searchProductCategories(searchName);
	}

}
