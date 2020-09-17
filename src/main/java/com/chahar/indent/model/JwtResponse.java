package com.chahar.indent.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final UserMaster userMaster;

	public JwtResponse(String jwttoken, UserMaster userMaster) {
		this.jwttoken = jwttoken;
		this.userMaster = userMaster;
	}

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
		this.userMaster = null;
	}

	@JsonIgnore
	public UserMaster getUserMaster() {
		return userMaster;
	}

	public String getToken() {
		return this.jwttoken;
	}
}