package com.chahar.indent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.chahar.indent.model.UserDto;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserType;
import com.chahar.indent.service.UserMasterService;
import com.chahar.indent.validator.UserValidator;

/**
 * @author Chahar
 *
 */
@Controller
public class UserMasterController extends AbstractPageController {

	@Autowired
	private UserMasterService userMasterService;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		log.debug("Target {}", target);

		if (target.getClass() == UserDto.class)
			dataBinder.setValidator(userValidator);

	}

	@GetMapping({ "/user" })
	public ModelAndView showUser(@ModelAttribute("user") UserDto userDto, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("Loading User page request....");
		model.addAttribute(REQUEST_ATTRIBUTE_USER_ID, request.getParameter(REQUEST_ATTRIBUTE_USER_ID));

		boolean isSearch = StringUtils.isNotBlank(request.getParameter(REQUEST_ATTRIBUTE_SEARCH))
				? Boolean.parseBoolean(request.getParameter(REQUEST_ATTRIBUTE_SEARCH))
				: false;

		if (isSearch) {
			String searchContactNumber = StringUtils.isNotBlank(request.getParameter("searchContactNumber"))
					? request.getParameter("searchContactNumber")
					: CONSTANT_BLANK_STRING;
			String searchUsername = StringUtils.isNotBlank(request.getParameter("searchUsername"))
					? request.getParameter("searchUsername")
					: CONSTANT_BLANK_STRING;
			List<UserMaster> productList = userMasterService.searchUsers(searchContactNumber, searchUsername);
			if (productList.isEmpty()) {
				model.put("searchMessage", "No Record Found");
			}
			model.put(MODEL_ATTRIBUTE_USER_LIST, productList);
		} else {
			model.put(MODEL_ATTRIBUTE_USER_LIST, userMasterService.getAllNew());
		}

		model.addAttribute(MODEL_ATTRIBUTE_MESSAGE, getMessageAttributeForPage(request, "User"));

		log.debug("showing User Master page....");
		return new ModelAndView("pages/userPage", model);
	}

	@PostMapping({ "/user" })
	public ModelAndView saveOrUpdateUsers(@Validated @ModelAttribute("user") UserDto userDto, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("Processing saveOrUpdate request for UserMaster {}", userDto);
		boolean isEdit = isEditRequest(request);
		if (result.hasErrors()
				&& ((isEdit && result.getFieldError("username") == null || result.getErrorCount() > 1) || !isEdit)) {
			model.addAttribute(MODEL_ATTRIBUTE_USER_LIST, userMasterService.getAllNew());
			if (isEdit) {
				model.addAttribute("isEdit", "true");
			}
			return new ModelAndView("pages/userPage", model);
		}
		UserMaster userMaster = null;

		if (StringUtils.isNotBlank(userDto.getUsername())) {
			userMaster = userMasterService.findByUsername(userDto.getUsername());
		}

		String status = null;
		UserMaster loggedInUser = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);
		if (UserType.ADMIN.equals(loggedInUser.getUserType())) {
			if (userMaster == null) {
				userMaster = new UserMaster();
			}
			BeanUtils.copyProperties(userDto, userMaster, "imageFile");
			userMaster.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			setUserAndDateInfo(userMaster, loggedInUser);

			try {
				if (userMaster.getId() == null) {
					userMasterService.save(userMaster);
					status = "success";
				} else {
					userMasterService.save(userMaster);
					status = "updateSuccess";
				}
			} catch (ConstraintViolationException e) {
				log.error("Exception occured \n Message : {}  \n Cause : {}", e.getMessage(), e.getCause());
				status = "duplicate";
			} catch (Exception e) {
				log.error("Exception occured \n Message : {}  \n Cause : {}", e.getMessage(), e.getCause());
				status = "error";
			}
			log.debug("User Master saved sucessfully.....");
		} else {
			status = "User Configuration can't be updated by Standard User..";
		}
		return new ModelAndView(REDIRECT_URL_USER + status);
	}

	@GetMapping("/editUser/{id}")
	public ModelAndView editUser(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
		log.debug("loading Edit request for User Master ....");

		UserMaster userMaster = userMasterService.get(id);
		if (userMaster == null) {
			return new ModelAndView(REDIRECT_URL_PRODUCT + "alreadydeleted");
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userMaster, userDto);

		model.addAttribute(MODEL_ATTRIBUTE_USER_MASTER, userDto);
		model.addAttribute(MODEL_ATTRIBUTE_USER_LIST, userMasterService.getAllNew());
		model.addAttribute("isEdit", "true");
		model.addAttribute("message", "Record Update Requested");

		log.debug("Showing edit page for User Master.....");
		return new ModelAndView("pages/productPage", model);
	}

	@GetMapping(value = "/deleteUser/{id}")
	public ModelAndView deleteUser(@PathVariable Long id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Processing delete request for User Master.....");
		UserMaster userMaster = userMasterService.get(id);
		UserMaster loggedInUser = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);

		String status = "";

		UserType allowedUserType = UserType.ADMIN;

		if (allowedUserType.equals(loggedInUser.getUserType())) {
			try {
				userMasterService.remove(userMaster.getId());
				status = "deleted";
			} catch (Exception e) {
				status = "cannotdelete";
			}
		} else {
			status = "User can't be deleted by Statndard User..";
		}
		return new ModelAndView(REDIRECT_URL_USER + status);
	}

	@PostMapping(value = "/searchUser")
	public ModelAndView searchProduct(@Valid @ModelAttribute("searchUser") UserDto userDto, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("search User Master object.....");
		return new ModelAndView(REDIRECT_URL_USER + "&isSearch=" + true + "&searchContactNumber="
				+ userDto.getContactNumber() + "&searchUsername=" + userDto.getUsername());
	}
}
