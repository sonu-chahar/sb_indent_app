package com.chahar.indent.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chahar.indent.model.BaseEntity;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.model.UserType;

public abstract class AbstractPageController {
	public static final String SESSION_ATTRIBTE_FOR_USER_MASTER = "userDtl";

	public static final String REQUEST_ATTRIBUTE_STATUS = "status";
	public static final String REQUEST_ATTRIBUTE_SEARCH = "isSearch";
	public static final String REQUEST_ATTRIBUTE_USER_ID = "userId";
	public static final String REQUEST_ATTRIBUTE_ID = "id";

	public static final String MODEL_ATTRIBUTE_MESSAGE = "message";

	public static final String MODEL_ATTRIBUTE_USER_MASTER = "user";
	public static final String MODEL_ATTRIBUTE_PRODUCT_MASTER = "product";
	public static final String MODEL_ATTRIBUTE_PRODUCT_LIST = "productList";
	public static final String MODEL_ATTRIBUTE_USER_LIST = "userList";
	public static final String MODEL_ATTRIBUTE_PRODUCT_CATEGORY_LIST = "productCategoryList";

	public static final String REDIRECT_URL_PRODUCT = "redirect:/product?status=";
	public static final String REDIRECT_URL_PRODUCT_CATEGORY = "redirect:/productCategory?status=";

	public static final String REDIRECT_URL_USER = "redirect:/user?status=";

	public static final String REDIRECT_URL_FOR_REGISTRATION = "redirect:/registration/showRegistrationPage?status=";
	public static final String REDIRECT_URL_FOR_HOMEPAGE = "redirect:/viewHomePage?status=";
	public static final String REDIRECT_URL_FOR_HOMEPAGE_WITH_CHART = "redirect:/login";
	public static final String REDIRECT_URL_FOR_PROFILE = "redirect:/myProfile/showProfile?status=";
	public static final String REDIRECT_URL_FOR_UPDATE_PASSWORD = "redirect:/myProfile/showChangePwdPage?status=";

	public static final String USER_CLASSNAME_FOR_MESSAGE = "User";

	public static final String STATUS_FOR_SUCCESS = "success";
	public static final String STATUS_FOR_UPDATE = "updateSuccess";
	public static final String STATUS_FOR_DUPLICATE = "duplicate";
	public static final String STATUS_FOR_ERROR = "error";
	public static final String STATUS_FOR_DELETED = "deleted";
	public static final String STATUS_FOR_NOT_DELETED = "cannotdelete";
	public static final String STATUS_ALREADY_DELETED = "alreadydeleted";

	public static final String CONSTANT_FOR_IMAGE_PATH = "fileDir";
	public static final String CONSTANT_FOR_IMAGE_UPLOAD_STATUS = "imageStatus";
	public static final String CONSTANT_FOR_SLASH = "/";
	public static final String CONSTANT_FOR_DOT = ".";
	public static final String CONSTANT_BLANK_STRING = "";
	public static final String CONSTANT_FOR_TRUE_FLAG = "true";
	public static final String CONSTANT_FOR_SPAN_OPENNING_TAG = "<span>";
	public static final String CONSTANT_FOR_SPAN_CLOSING_TAG = "</span>";

	public static final String CONSTANT_FOR_LOCALHOST = "127.0.0.1";

	public static final String VIEW_NAME_USER = "userPage";
	public static final String VIEW_NAME_HOME_PAGE = "homePage";
	public static final String VIEW_NAME_PRODUCT = "pages/productPage";
	public static final String VIEW_NAME_PRODUCT_CATEGORY = "pages/productCategoryPage";
	public static final String VIEW_NAME_PROFILE = "profilePage";
	public static final String VIEW_NAME_WEBSITE_POLICY = "websitePolicyPage";
	public static final String VIEW_NAME_UPDATE_PASSWORD = "changePasswordPage";

	public final Logger log = LogManager.getLogger(this.getClass());

	public String getMessageAttributeForPage(HttpServletRequest request, String className) {
		String status = StringUtils.isNotBlank(request.getParameter(REQUEST_ATTRIBUTE_STATUS))
				? request.getParameter(REQUEST_ATTRIBUTE_STATUS)
				: CONSTANT_BLANK_STRING;
		String message = CONSTANT_BLANK_STRING;

		/* set success/error message if request is coming after add/edit */
		if (StringUtils.isNotBlank(status)) {
			if (StringUtils.equals(status, STATUS_FOR_SUCCESS))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + className + " Saved Successfully !"
						+ CONSTANT_FOR_SPAN_CLOSING_TAG;
			else if (StringUtils.equals(status, STATUS_FOR_UPDATE))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + className + " Updated Successfully !"
						+ CONSTANT_FOR_SPAN_CLOSING_TAG;
			else if (StringUtils.equals(status, STATUS_FOR_ERROR))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + "Some error occured!</span>";
			else if (StringUtils.equals(status, STATUS_FOR_DELETED))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + className + " Deleted Successfully !"
						+ CONSTANT_FOR_SPAN_CLOSING_TAG;
			else if (StringUtils.equals(status, STATUS_FOR_NOT_DELETED))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + "Can't delete this record it is being used"
						+ CONSTANT_FOR_SPAN_CLOSING_TAG;
			else if (StringUtils.equals(status, STATUS_ALREADY_DELETED))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + "<img src='" + request.getContextPath()
						+ "/images/iconWarning.gif'>" + className + " is Already Deleted !"
						+ CONSTANT_FOR_SPAN_CLOSING_TAG;
			else if (StringUtils.equals(status, STATUS_FOR_DUPLICATE))
				message = CONSTANT_FOR_SPAN_OPENNING_TAG + "Duplicate Record found" + CONSTANT_FOR_SPAN_CLOSING_TAG;
			else
				message = status;
		}
		return message;
	}

	public static String getClientIp(HttpServletRequest request) {

		String remoteAddr = CONSTANT_BLANK_STRING;

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || CONSTANT_BLANK_STRING.equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

	public void setUserAndDateInfo(BaseEntity baseEntity, UserMaster userMaster) {
		if (baseEntity.getInsUser() == null) {

			baseEntity.setInsUser(userMaster);
			baseEntity.setUpdUser(userMaster);
		} else {
			baseEntity.setUpdUser(userMaster);
		}

		if (baseEntity.getInsDate() == null) {
			baseEntity.setUpdDate(new Date());
			baseEntity.setInsDate(new Date());
		} else {
			baseEntity.setUpdDate(new Date());
		}
	}

	public boolean isEditRequest(HttpServletRequest request) {
		return StringUtils.isNotBlank(request.getParameter("isEdit"));
	}

	public boolean isRequestAllowed(HttpServletRequest request) {
		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);
		UserType allowedUserType = UserType.ADMIN;
		return allowedUserType.equals(userMaster.getUserType());
	}
}