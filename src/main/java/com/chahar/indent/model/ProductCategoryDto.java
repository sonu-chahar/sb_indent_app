package com.chahar.indent.model;

public class ProductCategoryDto {
	private String name;
	private String savedCategoryName;

	public String getName() {
		return name;
	}

	public ProductCategoryDto() {
	}

	public ProductCategoryDto(String name, String savedCategoryName) {
		this.name = name;
		this.savedCategoryName = savedCategoryName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSavedCategoryName() {
		return savedCategoryName;
	}

	public void setSavedCategoryName(String savedCategoryName) {
		this.savedCategoryName = savedCategoryName;
	}
}
