package com.chahar.indent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chahar.indent.model.ProductDto;
import com.chahar.indent.model.ProductMaster;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserType;
import com.chahar.indent.service.ProductMasterService;
import com.chahar.indent.validator.ProductValidator;

/**
 * @author Chahar
 *
 */
@Controller
public class ProductMasterController extends AbstractPageController {

	@Autowired
	private ProductMasterService productMasterService;

	@Autowired
	private ProductValidator productValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		log.debug("Target {}", target);

		if (target.getClass() == ProductDto.class)
			dataBinder.setValidator(productValidator);

	}

	@GetMapping({ "/product" })
	public ModelAndView showProduct(@ModelAttribute("product") ProductDto product, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("Loading Product page request....");
		model.addAttribute(REQUEST_ATTRIBUTE_USER_ID, request.getParameter(REQUEST_ATTRIBUTE_USER_ID));

		boolean isSearch = StringUtils.isNotBlank(request.getParameter(REQUEST_ATTRIBUTE_SEARCH))
				? Boolean.parseBoolean(request.getParameter(REQUEST_ATTRIBUTE_SEARCH))
				: false;

		if (isSearch) {
			String searchCode = StringUtils.isNotBlank(request.getParameter("searchCode"))
					? request.getParameter("searchCode")
					: CONSTANT_BLANK_STRING;
			String searchName = StringUtils.isNotBlank(request.getParameter("searchName"))
					? request.getParameter("searchName")
					: CONSTANT_BLANK_STRING;
			List<ProductMaster> productList = productMasterService.searchProducts(searchCode, searchName);
			if (productList.isEmpty()) {
				model.put("searchMessage", "No Record Found");
			}
			model.put(MODEL_ATTRIBUTE_PRODUCT_LIST, productList);
		} else {
			model.put(MODEL_ATTRIBUTE_PRODUCT_LIST, productMasterService.getAllProducts());
		}

		model.addAttribute(MODEL_ATTRIBUTE_MESSAGE, getMessageAttributeForPage(request, "Product"));

		log.debug("showing Product Master page....");
		return new ModelAndView(VIEW_NAME_PRODUCT, model);
	}

	@PostMapping({ "/product" })
	public ModelAndView saveOrUpdateProducts(@Validated @ModelAttribute("product") ProductDto productDto,
			BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("Processing saveOrUpdate request for ProductMaster {}", productDto);
		boolean isEdit = StringUtils.isNotBlank(request.getParameter("isEdit"))
				? Boolean.parseBoolean(request.getParameter("isEdit"))
				: false;
		if (result.hasErrors()
				&& ((isEdit && result.getFieldError("code") == null || result.getErrorCount() > 1) || !isEdit)) {
			model.addAttribute(MODEL_ATTRIBUTE_PRODUCT_LIST, productMasterService.getAllProducts());
			if (isEdit) {
				model.addAttribute("isEdit", "true");
			}
			return new ModelAndView(VIEW_NAME_PRODUCT, model);

		}
		ProductMaster productMaster = null;

		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);

		String proudctCode = productDto.getCode();
		if (StringUtils.isNotBlank(proudctCode)) {
			productMaster = productMasterService.findProduct(productDto.getCode());
		}

		String status = null;
		UserType allowedUserType = UserType.ADMIN;

		if (allowedUserType.equals(userMaster.getUserType())) {

			if (productMaster == null) {
				productMaster = new ProductMaster();
			}

			BeanUtils.copyProperties(productDto, productMaster, "imageFile");

			setUserAndDateInfo(productMaster, userMaster);

			try {
				if (productMaster.getId() == null) {
					productMasterService.save(productMaster);
					status = "success";
				} else {
					productMasterService.save(productMaster);
					status = "updateSuccess";
				}

			} catch (ConstraintViolationException e) {
				log.error("Exception occured \n Message : {}  \n Cause : {}", e.getMessage(), e.getCause());
				status = "duplicate";
			} catch (Exception e) {
				log.error("Exception occured \n Message : {}  \n Cause : {}", e.getMessage(), e.getCause());
				status = "error";
			}

			log.debug("Product Master saved sucessfully.....");
		} else {
			status = "Product Configuration can't be updated by Standard User..";
		}
		return new ModelAndView(REDIRECT_URL_PRODUCT + status);

	}

	@GetMapping("/editProduct/{id}")
	public ModelAndView editProduct(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
		log.debug("loading Edit request for Product Master ....");

		ProductMaster productMaster = productMasterService.get(id);
		if (productMaster == null) {
			return new ModelAndView(REDIRECT_URL_PRODUCT + "alreadydeleted");
		}
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(productMaster, productDto);

		model.addAttribute(MODEL_ATTRIBUTE_PRODUCT_MASTER, productDto);
		model.addAttribute(MODEL_ATTRIBUTE_PRODUCT_LIST, productMasterService.getAllProducts());
		model.addAttribute("isEdit", "true");
		model.addAttribute("message", "Record Update Requested");

		log.debug("Showing edit page for Product Master.....");
		return new ModelAndView(VIEW_NAME_PRODUCT, model);
	}

	@GetMapping(value = "/deleteProduct/{id}")
	public ModelAndView deleteProduct(@PathVariable Long id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Processing delete request for Product Master.....");
		ProductMaster productMaster = productMasterService.get(id);

		String status = "";
		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);

		UserType allowedUserType = UserType.ADMIN;

		if (allowedUserType.equals(userMaster.getUserType())) {
			try {
				productMasterService.remove(productMaster.getId());
				status = "deleted";
			} catch (Exception e) {
				status = "cannotdelete";
			}
		} else {
			status = "Product can't be deleted by Statndard User..";
		}
		return new ModelAndView(REDIRECT_URL_PRODUCT + status);

	}

	@PostMapping(value = "/searchProduct")
	public ModelAndView searchProduct(@Valid @ModelAttribute("searchProduct") ProductDto productDto,
			BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("search Product Master object.....");
		return new ModelAndView(REDIRECT_URL_PRODUCT + "&isSearch=" + true + "&searchCode=" + productDto.getCode()
				+ "&searchName=" + productDto.getName());
	}
}
