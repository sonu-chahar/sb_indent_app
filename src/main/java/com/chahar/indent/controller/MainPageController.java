package com.chahar.indent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chahar.indent.model.UserMaster;

@Controller
public class MainPageController extends AbstractPageController {

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		return "welcome";
	}

//	@Autowired
//	private UserMasterService userMasterService;

//	@GetMapping(value = "/homePage")
//	public ModelAndView getHomePageWithChart(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
//		model.addAttribute("userStats", userMasterService.getStats());
//		model.addAttribute(MODEL_ATTRIBUTE_MESSAGE,
//				getMessageAttributeForPage(request, USER_STATS_CLASSNAME_FOR_MESSAGE));
//		return new ModelAndView(VIEW_NAME_HOME_PAGE_WITH_CHART, model);
//	}

	@GetMapping(value = { "/homePage" })
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute("userDtl");

		if (userMaster == null) {
			model.addAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER, userMaster);
			return new ModelAndView(VIEW_NAME_HOME_PAGE, model);
		}

		if (userMaster.getImageName() == null) {
			return new ModelAndView(REDIRECT_URL_FOR_PROFILE, model);
		}

		model.addAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER, userMaster);
		model.addAttribute(MODEL_ATTRIBUTE_MESSAGE, getMessageAttributeForPage(request, USER_CLASSNAME_FOR_MESSAGE));

		return new ModelAndView(VIEW_NAME_HOME_PAGE, model);
	}

	@GetMapping({ "**/websitePolicy.html" })
	public String showWebsitePolicyPage() {
		return VIEW_NAME_WEBSITE_POLICY;
	}
}
