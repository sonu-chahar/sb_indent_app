package com.chahar.indent.model;

public enum UserType {
	ADMIN("ADMIN"), ORGANIZATION("ORGANIZATION"), SUB_ORGNIZATION("SUB_ORGNIZATION"), DOCTOR("DOCTOR"), MMU("MMU");

	private String userTypeName;

	private UserType(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getUserTypeName() {
		return this.userTypeName;
	}

	@Override
	public String toString() {
		return this.userTypeName;
	}
}
