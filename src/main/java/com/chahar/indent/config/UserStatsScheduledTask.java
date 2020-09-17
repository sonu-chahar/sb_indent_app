package com.chahar.indent.config;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserStats;
import com.chahar.indent.model.UserType;
import com.chahar.indent.service.UserMasterService;

@Component
public class UserStatsScheduledTask {
	@Autowired
	private UserMasterService userMasterService;

	protected final Logger log = LogManager.getLogger(this.getClass());

	@Scheduled(fixedRate = 900000)
	public void performTask() {
		UserStats userStats = userMasterService.getStats();
		List<UserMaster> userMasterList = userMasterService.getAllNew();
//
//		userStats.setOrganizations(calculateStats(userMasterList, UserType.ORGANIZATION));
//		userStats.setSubOrganizations(calculateStats(userMasterList, UserType.SUB_ORGNIZATION));
//		userStats.setDoctors(calculateStats(userMasterList, UserType.DOCTOR));
//		userStats.setMmus(calculateStats(userMasterList, UserType.MMU));
	}

	private Integer calculateStats(List<UserMaster> userMasterList, UserType userType) {
		List<UserMaster> filteredUsers = userMasterList.stream().filter(p -> p.getUserType().equals(userType))
				.collect(Collectors.toList());
		if (!filteredUsers.isEmpty()) {
			return filteredUsers.size();
		}
		return null;
	}
}
