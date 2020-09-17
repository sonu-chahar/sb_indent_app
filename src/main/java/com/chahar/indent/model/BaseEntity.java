package com.chahar.indent.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -1475898471375598734L;
	private Date insDate;
	private Date updDate;
	private UserMaster insUser;
	private UserMaster updUser;

	@Column(name = "insDate")
	public Date getInsDate() {
		return insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	@Column(name = "updDate")
	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@ManyToOne(targetEntity = UserMaster.class)
	@JoinColumn(name = "fk_insUser", referencedColumnName = "USER_ID")
	public UserMaster getInsUser() {
		return insUser;
	}

	public void setInsUser(UserMaster insUser) {
		this.insUser = insUser;
	}

	@ManyToOne(targetEntity = UserMaster.class)
	@JoinColumn(name = "fk_updUser", referencedColumnName = "USER_ID")
	public UserMaster getUpdUser() {
		return updUser;
	}

	public void setUpdUser(UserMaster updUser) {
		this.updUser = updUser;
	}

}
