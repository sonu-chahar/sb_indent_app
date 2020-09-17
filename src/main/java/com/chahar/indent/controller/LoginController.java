package com.chahar.indent.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.chahar.indent.exceptions.AlreadyLoginException;
import com.chahar.indent.model.UserDto;
import com.chahar.indent.service.UserMasterService;

@Controller
public class LoginController extends AbstractPageController {
	@Autowired
	private UserMasterService userMasterService;

	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model, String error, String logout) {
		model.addAttribute("userStats", userMasterService.getStats());
		if (request.getUserPrincipal() != null)
			throw new AlreadyLoginException();
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		model.addAttribute("userDTO", new UserDto());

		return "homePageWithChart";
	}

	@GetMapping(value = "/logout")
	public ModelAndView logout(SessionStatus session) {
		SecurityContextHolder.getContext().setAuthentication(null);
		session.setComplete();
		return new ModelAndView(REDIRECT_URL_FOR_HOMEPAGE_WITH_CHART);
	}

}
