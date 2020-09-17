package com.chahar.indent.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chahar.indent.dao.ProductMasterDao;
import com.chahar.indent.model.ProductMaster;

@Repository("productMasterDao")
public class ProductMasterDaoImpl extends GenericDaoImpl<ProductMaster, Long> implements ProductMasterDao {

	public ProductMasterDaoImpl() {
		super(ProductMaster.class);
	}

	@Override
	public ProductMaster findProduct(String code) {
		Criteria crit = getCurrentSession().createCriteria(ProductMaster.class);
		crit.add(Restrictions.eq("code", code));
		return (ProductMaster) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMaster> getAllProducts() {
		Criteria crit = getCurrentSession().createCriteria(ProductMaster.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMaster> searchProducts(String searchCode, String searchName) {
		Criteria crit = getCurrentSession().createCriteria(ProductMaster.class);
		Criterion codeCrit = null;
		Criterion nameCrit = null;
		if (StringUtils.isNotBlank(searchCode)) {
			codeCrit = Restrictions.like("code", searchCode, MatchMode.ANYWHERE);
		}
		if (StringUtils.isNotBlank(searchName)) {
			nameCrit = Restrictions.like("name", searchName, MatchMode.ANYWHERE);
		}
		LogicalExpression orExp = Restrictions.or(codeCrit, nameCrit);
		crit.add(orExp);

		return crit.list();
	}

}
