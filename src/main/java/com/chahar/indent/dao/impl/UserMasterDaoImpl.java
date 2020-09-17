package com.chahar.indent.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chahar.indent.dao.UserMasterDao;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserStats;

@Repository("userMasterDao")
public class UserMasterDaoImpl extends GenericDaoImpl<UserMaster, Long> implements UserMasterDao {

	public UserMasterDaoImpl() {
		super(UserMaster.class);
	}

	@Override
	public Long saveUser(UserMaster userMaster) {
		return (Long) getCurrentSession().save(userMaster);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserMaster findByUsername(String username) {
		List<UserMaster> userMasterList = getCurrentSession().createCriteria(UserMaster.class)
				.add(Restrictions.eq("username", username)).list();
		if (userMasterList != null && !userMasterList.isEmpty()) {
			return userMasterList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public UserStats getStats() {
		@SuppressWarnings("unchecked")
		List<UserStats> list = getCurrentSession().createCriteria(UserStats.class).list();
		Integer intialCounter = 0;
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return new UserStats(intialCounter, intialCounter, intialCounter, intialCounter);
		}
	}

	@Override
	public void saveUserStats(UserStats userStats) {
		getCurrentSession().merge(userStats);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMaster> getUserListByMobileNumber(Long mobileNumber) {
		return getCurrentSession().createCriteria(UserMaster.class).add(Restrictions.eq("mobileNumber", mobileNumber))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMaster> searchUsers(String searchContactNumber, String searchUsername) {
		Criteria crit = getCurrentSession().createCriteria(UserMaster.class);
		Criterion contactCrit = null;
		Criterion nameCrit = null;
		if (StringUtils.isNotBlank(searchContactNumber)) {
			contactCrit = Restrictions.like("contactNumber", searchContactNumber, MatchMode.ANYWHERE);
		}
		if (StringUtils.isNotBlank(searchUsername)) {
			nameCrit = Restrictions.like("username", searchUsername, MatchMode.ANYWHERE);
		}
		LogicalExpression orExp = Restrictions.or(contactCrit, nameCrit);
		crit.add(orExp);
		return crit.list();

	}

}
