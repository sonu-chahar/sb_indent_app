package com.chahar.indent.filters;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chahar.indent.model.UserMaster;
import com.chahar.indent.service.UserMasterService;

public class UserDtlInterceptor implements HandlerInterceptor {

	@Autowired
	UserMasterService userMasterService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = null;
			UserMaster persistedUserMaster = null;
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			if (username != null) {
				persistedUserMaster = userMasterService.findByUsername(username);
			}
			if (persistedUserMaster != null) {

//				if (persistedUserMaster.getUserType().getUserTypeName().equals("ADMIN")) {
//					request.setAttribute("isAuthorizedForViewOrders", "true");
//				}
				request.getSession(false).setAttribute("userDtl", persistedUserMaster);
//				Locale locale = LocaleContextHolder.getLocale();
//				request.setAttribute("currentLocale", locale.getLanguage());
			}
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}

}
