package com.chahar.indent.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chahar.indent.dao.ProductCategoryDao;
import com.chahar.indent.model.ProductCategory;

@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends GenericDaoImpl<ProductCategory, Long> implements ProductCategoryDao {

	public ProductCategoryDaoImpl() {
		super(ProductCategory.class);
	}

	@Override
	public ProductCategory findProductCategory(String name) {
		Criteria crit = getCurrentSession().createCriteria(ProductCategory.class);
		crit.add(Restrictions.eq("name", name));
		return (ProductCategory) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> getAllProductCategories() {
		Criteria crit = getCurrentSession().createCriteria(ProductCategory.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> searchProductCategories(String searchName) {
		Criteria crit = getCurrentSession().createCriteria(ProductCategory.class);
		if (StringUtils.isNotBlank(searchName)) {
			crit.add(Restrictions.like("name", searchName, MatchMode.ANYWHERE));
		}
		return crit.list();
	}
}
