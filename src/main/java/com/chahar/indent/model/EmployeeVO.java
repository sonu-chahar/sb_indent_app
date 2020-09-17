package com.chahar.indent.model;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@DataTransferObject
public class EmployeeVO {

	private String employeeCode;
	private String employeeName;
	private String designation;
	private String department;
	private String mobileNumber;

	@JsonProperty("emp_code")
	@RemoteProperty
	public String getEmployeeCode() {
		return employeeCode;
	}

	@Override
	public String toString() {
		return "EmployeeVO [employeeCode=" + employeeCode + ", employeeName=" + employeeName + ", designation="
				+ designation + ", department=" + department + ", mobileNumber=" + mobileNumber + "]";
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	@JsonProperty("emp_name")
	@RemoteProperty
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@JsonProperty("designation")
	@RemoteProperty
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@JsonProperty("department")
	@RemoteProperty
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@JsonProperty("mobile_no")
	@RemoteProperty
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
