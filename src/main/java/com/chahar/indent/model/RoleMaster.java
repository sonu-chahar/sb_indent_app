package com.chahar.indent.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "acc_roleMaster")
public class RoleMaster implements Serializable {

	private static final long serialVersionUID = 5128062071893875704L;

	private Long id;
	private String roleName;
	private Set<UserMaster> users;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "roleName")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	public Set<UserMaster> getUsers() {
		return users;
	}

	public void setUsers(Set<UserMaster> users) {
		this.users = users;
	}

//	@Column(name = "userType")
//	@Enumerated(EnumType.STRING)
//	public UserType getUserType() {
//		return userType;
//	}
//
//	public void setUserType(UserType userType) {
//		this.userType = userType;
//	}
}
