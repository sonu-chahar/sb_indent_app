package com.chahar.indent.service;

import java.util.List;

import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserStats;

public interface UserMasterService extends GenericService<UserMaster, Long> {

	public Long saveUser(UserMaster user);

	public UserMaster findByUsername(String username);

	public UserStats getStats();

	public void saveUserStats(UserStats userStats);

	public boolean isMobileNubmerExist(Long mobileNumber);

	public List<UserMaster> searchUsers(String searchContactNumber, String searchName);
}