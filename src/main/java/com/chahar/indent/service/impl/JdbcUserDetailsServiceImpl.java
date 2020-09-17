package com.chahar.indent.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.UserMasterDao;
import com.chahar.indent.model.RoleMaster;
import com.chahar.indent.model.UserMaster;

@Service("jdbcUserDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JdbcUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserMasterDao userMasterDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserMaster userMaster = userMasterDao.findByUsername(username);
		if (userMaster == null)
			throw new UsernameNotFoundException(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
		if (userMaster.getRoles() != null) {
			for (RoleMaster role : userMaster.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
			}
		}

		return (UserDetails) new User(userMaster.getUsername(), userMaster.getPassword(), grantedAuthorities);
	}
}
