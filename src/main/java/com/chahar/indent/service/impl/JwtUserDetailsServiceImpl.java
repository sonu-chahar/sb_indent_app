package com.chahar.indent.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.UserMasterDao;
import com.chahar.indent.model.UserDto;
import com.chahar.indent.model.UserMaster;

@Service("jwtUserDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMasterDao userMasterDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMaster user = userMasterDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public UserMaster save(UserDto user) {
		UserMaster newUser = new UserMaster();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userMasterDao.save(newUser);
	}
}