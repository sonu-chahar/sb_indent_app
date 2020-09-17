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

import com.chahar.indent.dao.UserMasterDao;
import com.chahar.indent.model.RoleMaster;
import com.chahar.indent.model.UserMaster;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

	@Autowired
	private UserMasterDao accountDAO;

	@Override

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMaster account = accountDAO.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (RoleMaster role : account.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		}

		boolean enabled = account.getIsActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(account.getUsername(), account.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuthorities);
	}
}
