package com.chahar.indent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chahar.indent.model.UserDto;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.service.SecurityService;
import com.chahar.indent.service.UserMasterService;
import com.chahar.indent.validator.UserValidator;

@Controller
public class UserMasterController2 {
	@Autowired
	private UserMasterService userMasterService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new UserMaster());

		return "registration";
	}

	@PostMapping("/addUser")
	public String registration(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult) {

//		userValidator.validate(userForm, bindingResult);
//
//		if (bindingResult.hasErrors()) {
//			return "registration";
//		}
//
//		userForm.setPassword(bcryptEncoder.encode(userForm.getPassword()));
//
//		userMasterService.save(userForm);
//
//		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}
}
