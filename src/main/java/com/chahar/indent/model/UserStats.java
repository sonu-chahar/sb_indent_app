package com.chahar.indent.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.hibernate.H4BeanConverter;

@Entity
@Table(name = "acc_userStats")
@DataTransferObject(type = "hibernate4", converter = H4BeanConverter.class, params = @Param(name = "assumeSession", value = "true"))
public class UserStats implements Serializable {

	private static final long serialVersionUID = 1537675251768920542L;

	private Integer id;
	private Integer organizations;
	private Integer subOrganizations;
	private Integer doctors;
	private Integer mmus;

	public UserStats() {
	}

	public UserStats(Integer doctors, Integer organizations, Integer subOrganizations, Integer mmus) {
		this.doctors = doctors;
		this.organizations = organizations;
		this.subOrganizations = subOrganizations;
		this.mmus = mmus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_userStats")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "organizations")
	public Integer getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Integer organizations) {
		this.organizations = organizations;
	}

	@Column(name = "subOrganizations")
	public Integer getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(Integer subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	@Column(name = "doctors")
	public Integer getDoctors() {
		return doctors;
	}

	public void setDoctors(Integer doctors) {
		this.doctors = doctors;
	}

	@Column(name = "mmus")
	public Integer getMmus() {
		return mmus;
	}

	public void setMmus(Integer mmus) {
		this.mmus = mmus;
	}

}
