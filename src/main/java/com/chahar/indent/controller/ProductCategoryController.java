package com.chahar.indent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chahar.indent.model.ProductCategory;
import com.chahar.indent.model.ProductCategoryDto;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserType;
import com.chahar.indent.service.ProductCategoryService;
import com.chahar.indent.validator.ProductCategoryValidator;

/**
 * @author Chahar
 *
 */
@Controller
public class ProductCategoryController extends AbstractPageController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductCategoryValidator productCategoryValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		log.debug("Target {}", target);

		if (target.getClass() == ProductCategoryDto.class)
			dataBinder.setValidator(productCategoryValidator);

	}

	@GetMapping({ "/productCategory" })
	public ModelAndView showProductCategory(@ModelAttribute("productCategory") ProductCategoryDto productCategoryDto,
			@RequestParam(value = "searchName", defaultValue = "") String searchName, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("Loading Product Category page request....");
		model.addAttribute(REQUEST_ATTRIBUTE_USER_ID, request.getParameter(REQUEST_ATTRIBUTE_USER_ID));

		if (StringUtils.isNotBlank(searchName)) {
			List<ProductCategory> productCategoryList = productCategoryService.searchProductCategories(searchName);
			if (productCategoryList.isEmpty()) {
				model.put("searchMessage", "No Record Found");
			}
			model.put(MODEL_ATTRIBUTE_PRODUCT_CATEGORY_LIST, productCategoryList);
		} else {
			model.put(MODEL_ATTRIBUTE_PRODUCT_CATEGORY_LIST, productCategoryService.getAllProductCategories());
		}

		model.addAttribute(MODEL_ATTRIBUTE_MESSAGE, getMessageAttributeForPage(request, "Product"));

		log.debug("showing Product Category Master page....");
		return new ModelAndView(VIEW_NAME_PRODUCT_CATEGORY, model);
	}

	@PostMapping({ "/productCategory" })
	public ModelAndView saveOrUpdateProductCategory(
			@ModelAttribute("productCategory") ProductCategoryDto productCategoryDto, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("Processing saveOrUpdate request for ProductCategory {}", productCategoryDto);

		if (result.hasErrors()) {
			model.put(MODEL_ATTRIBUTE_PRODUCT_CATEGORY_LIST, productCategoryService.getAllProductCategories());
			return new ModelAndView(VIEW_NAME_PRODUCT_CATEGORY, model);
		}

		ProductCategory productCategory = null;

		String categoryName = productCategoryDto.getSavedCategoryName();

		if (StringUtils.isNotBlank(categoryName)) {
			productCategory = productCategoryService.findProductCategory(categoryName);
		}

		String status = null;
		UserType allowedUserType = UserType.ADMIN;
		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);
		if (allowedUserType.equals(userMaster.getUserType())) {

			if (productCategory == null) {
				productCategory = new ProductCategory();
			}

			BeanUtils.copyProperties(productCategoryDto, productCategory);

			setUserAndDateInfo(productCategory, userMaster);

			try {
				if (productCategory.getId() == null) {
					productCategoryService.save(productCategory);
					status = "success";
				} else {
					productCategoryService.save(productCategory);
					status = "updateSuccess";
				}

			} catch (ConstraintViolationException e) {
				log.error("Exception occured \n Message : {}  \n Cause : {}", e.getMessage(), e.getCause());
				status = "duplicate";
			} catch (Exception e) {
				log.error("Exception occured \n Message : {}  \n Cause : {}", e.getMessage(), e.getCause());
				status = "error";
			}

			log.debug("Product Category saved sucessfully.....");
		} else {
			status = "Product Category can't be updated by Standard User..";
		}
		return new ModelAndView(REDIRECT_URL_PRODUCT_CATEGORY + status);

	}

	@GetMapping("/editCategory/{id}")
	public ModelAndView editProductCategory(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
		log.debug("loading Edit request for Product Category ....");

		ProductCategory productCategory = productCategoryService.get(id);
		if (productCategory == null) {
			return new ModelAndView(REDIRECT_URL_PRODUCT + "alreadydeleted");
		}
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getName(),
				productCategory.getName());

		model.addAttribute(MODEL_ATTRIBUTE_PRODUCT_MASTER, productCategoryDto);
		model.addAttribute(MODEL_ATTRIBUTE_PRODUCT_LIST, productCategoryService.getAllProductCategories());
		model.addAttribute("message", "Record Update Requested");

		log.debug("Showing edit page for Product Category.....");
		return new ModelAndView(VIEW_NAME_PRODUCT_CATEGORY, model);
	}

	@GetMapping(value = "/deleteProductCategory/{id}")
	public ModelAndView deleteProductCategory(@PathVariable Long id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Processing delete request for Product Category.....");
		ProductCategory productCategory = productCategoryService.get(id);

		String status = null;
		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);
		UserType allowedUserType = UserType.ADMIN;

		if (allowedUserType.equals(userMaster.getUserType())) {
			try {
				productCategoryService.remove(productCategory.getId());
				status = "deleted";
			} catch (Exception e) {
				status = "cannotdelete";
			}
		} else {
			status = "Product can't be deleted by Statndard User..";
		}
		return new ModelAndView(REDIRECT_URL_PRODUCT_CATEGORY + status);
	}
}
