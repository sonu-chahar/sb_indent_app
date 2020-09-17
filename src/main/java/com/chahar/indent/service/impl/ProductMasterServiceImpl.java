package com.chahar.indent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.ProductMasterDao;
import com.chahar.indent.model.ProductMaster;
import com.chahar.indent.service.ProductMasterService;

@Service("productMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductMasterServiceImpl extends GenericServiceImpl<ProductMaster, Long> implements ProductMasterService {

	private ProductMasterDao productMasterDao;

	@Autowired
	public ProductMasterServiceImpl(@Qualifier("productMasterDao") ProductMasterDao productMasterDao) {
		super(productMasterDao);
		this.productMasterDao = productMasterDao;

	}

	@Override
	public ProductMaster findProduct(String code) {
		return productMasterDao.findProduct(code);
	}

	@Override
	public List<ProductMaster> getAllProducts() {
		return productMasterDao.getAllProducts();
	}

	@Override
	public List<ProductMaster> searchProducts(String searchCode, String searchName) {
		return productMasterDao.searchProducts(searchCode, searchName);
	}

}
