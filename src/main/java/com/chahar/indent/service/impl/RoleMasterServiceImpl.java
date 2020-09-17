package com.chahar.indent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.RoleMasterDao;
import com.chahar.indent.model.RoleMaster;
import com.chahar.indent.service.RoleMasterService;

@Service("roleMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleMasterServiceImpl extends GenericServiceImpl<RoleMaster, Long> implements RoleMasterService {

	private RoleMasterDao roleMasterDao;

	@Autowired
	public RoleMasterServiceImpl(@Qualifier("roleMasterDao") RoleMasterDao roleMasterDao) {
		super(roleMasterDao);
		this.roleMasterDao = roleMasterDao;

	}

	@Override
	public RoleMaster findRoleByRoleNAme(String role) {
		return roleMasterDao.findRoleByRoleNAme(role);
	}
}
