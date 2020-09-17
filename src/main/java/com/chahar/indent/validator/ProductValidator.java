package com.chahar.indent.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chahar.indent.model.ProductDto;
import com.chahar.indent.service.ProductMasterService;

@Component
public class ProductValidator implements Validator {
	@Autowired
	private ProductMasterService productMasterService;

	@Override
	public boolean supports(Class<?> aClass) {
		return ProductDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ProductDto productDto = (ProductDto) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		if (productDto.getName().length() < 3 || productDto.getName().length() > 32) {
			errors.rejectValue("name", "Size.productForm.name");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty");
		if (productMasterService.findProduct(productDto.getCode()) != null) {
			errors.rejectValue("code", "Duplicate.productForm.code");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");

	}
}
