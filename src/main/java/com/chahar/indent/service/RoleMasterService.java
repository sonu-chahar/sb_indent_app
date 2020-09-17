package com.chahar.indent.service;

import com.chahar.indent.model.RoleMaster;

public interface RoleMasterService extends GenericService<RoleMaster, Long> {

	public RoleMaster findRoleByRoleNAme(String role);
}