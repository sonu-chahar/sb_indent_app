package com.chahar.indent.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chahar.indent.model.ProductCategoryDto;
import com.chahar.indent.service.ProductCategoryService;

@Component
public class ProductCategoryValidator implements Validator {
	@Autowired
	private ProductCategoryService productCategoryService;

	@Override
	public boolean supports(Class<?> aClass) {
		return ProductCategoryDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ProductCategoryDto productCategoryDto = (ProductCategoryDto) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		if (productCategoryDto.getName().length() < 3 || productCategoryDto.getName().length() > 32) {
			errors.rejectValue("name", "Size.productCategoryForm.name");
		}
		if (productCategoryService.findProductCategory(productCategoryDto.getName()) != null) {
			errors.rejectValue("code", "Duplicate.productCategoryForm.name");
		}
	}
}
