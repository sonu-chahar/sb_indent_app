package com.chahar.indent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.GenDao;
import com.chahar.indent.dao.UserMasterDao;
import com.chahar.indent.model.RoleMaster;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserStats;
import com.chahar.indent.service.UserMasterService;

@Service("userMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserMasterServiceImpl extends GenericServiceImpl<UserMaster, Long> implements UserMasterService {

	@Autowired
	protected GenDao<RoleMaster> genericRoleDao;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	private UserMasterDao userMasterDao;

	@Autowired
	public UserMasterServiceImpl(@Qualifier("userMasterDao") UserMasterDao userMasterDao) {
		super(userMasterDao);
		this.userMasterDao = userMasterDao;

	}

	@Override
	public UserMaster findByUsername(String username) {
		return userMasterDao.findByUsername(username);
	}

	// dont use below method
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public Long saveUser(UserMaster userMaster) {
		userMaster.setPassword(bCryptPasswordEncoder.encode(userMaster.getPassword()));
		// userMaster.setRoles(new
		// HashSet<>(genericRoleDao.getAllNew(RoleMaster.class)));
		return userMasterDao.saveUser(userMaster);
	}

	@Override
	public UserStats getStats() {
		return userMasterDao.getStats();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void saveUserStats(UserStats userStats) {
		userMasterDao.saveUserStats(userStats);
	}

	@Override
	public boolean isMobileNubmerExist(Long mobileNumber) {
		return userMasterDao.getUserListByMobileNumber(mobileNumber).isEmpty() ? false : true;
	}

	@Override
	public List<UserMaster> searchUsers(String searchContactNumber, String searchUsername) {
		return userMasterDao.searchUsers(searchContactNumber, searchUsername);
	}

}
