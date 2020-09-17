package com.chahar.indent.dao;

import java.util.List;

import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserStats;

public interface UserMasterDao extends GenericDao<UserMaster, Long> {

	public Long saveUser(UserMaster userMaster);

	public UserMaster findByUsername(String username);

	public UserStats getStats();

	public void saveUserStats(UserStats userStats);

	public List<UserMaster> getUserListByMobileNumber(Long mobileNumber);

	public List<UserMaster> searchUsers(String searchContactNumber, String searchUsername);

}