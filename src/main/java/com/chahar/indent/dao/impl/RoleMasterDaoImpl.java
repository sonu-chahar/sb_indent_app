package com.chahar.indent.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.RoleMasterDao;
import com.chahar.indent.model.RoleMaster;

@Transactional
@Repository("roleMasterDao")
public class RoleMasterDaoImpl extends GenericDaoImpl<RoleMaster, Long> implements RoleMasterDao {

	public RoleMasterDaoImpl() {
		super(RoleMaster.class);
	}

	@Override
	public RoleMaster findRoleByRoleNAme(String role) {
		Criteria crit = getCurrentSession().createCriteria(RoleMaster.class);
		crit.add(Restrictions.eq("roleName", role));
		return (RoleMaster) crit.uniqueResult();
	}

}
