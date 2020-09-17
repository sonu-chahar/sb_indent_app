package com.chahar.indent.dao;

import com.chahar.indent.model.RoleMaster;

public interface RoleMasterDao extends GenericDao<RoleMaster, Long> {

	public RoleMaster findRoleByRoleNAme(String role);
}
