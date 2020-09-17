package com.chahar.indent.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProductDto {
	private String code;
	private String name;
	private Double price;
	private Integer quantity;

	private CommonsMultipartFile imageFile;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CommonsMultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(CommonsMultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "ProductDto [code=" + code + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", imageFile=" + imageFile + "]";
	}
	
	
}
