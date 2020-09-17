package com.chahar.indent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chahar.indent.exceptions.AlreadyLoginException;
import com.chahar.indent.exceptions.NoOrderFoundException;
import com.chahar.indent.exceptions.ProductOutOfStockException;
import com.chahar.indent.model.OrderMaster;
import com.chahar.indent.model.ProductMaster;
import com.chahar.indent.model.UserMaster;
import com.chahar.indent.service.OrderMasterService;
import com.chahar.indent.service.ProductMasterService;
import com.chahar.indent.service.SecurityService;
import com.chahar.indent.service.UserMasterService;
import com.chahar.indent.validator.UserValidator;

@Controller
public class MainController extends AbstractPageController {

	public static final Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	private OrderMasterService orderMasterService;

	@Autowired
	private ProductMasterService productMasterService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserMasterService userMasterService;

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		if (logger.isDebugEnabled())
			logger.debug("Target =" + target);

		if (target.getClass() == UserMaster.class)
			dataBinder.setValidator(userValidator);

	}

	@GetMapping("/403")
	public String accessDenied(HttpServletRequest request) {
		logger.error("Access Denied URL =" + request.getRequestURL());
		return "pages/403";
	}
	
	@GetMapping({"/error"})
	public String errorPage(HttpServletRequest request) {
		logger.error("Error occurred at URL =" + request.getRequestURL());
		return "pages/403";
	}

	// GET: Show Login Page
	/*
	 * @GetMapping(value = { "/loginPage" }) public String login(HttpServletRequest
	 * request) { if (request.getUserPrincipal() != null) throw new
	 * AlreadyLoginException(); return "pages/login"; }
	 */

	// GET: Show Sign Up Page
	@GetMapping(value = { "/signUp" })
	public String signUpForm(Model model, HttpServletRequest request) {
		if (request.getUserPrincipal() != null)
			throw new AlreadyLoginException();
		model.addAttribute("account", new UserMaster());
		return "pages/signUp";
	}

	@PostMapping(value = { "/signUp" })
	public String signUpFormProcess(@Validated UserMaster userMaster, BindingResult bindingResult,
			HttpServletRequest request) {
		if (request.getUserPrincipal() != null)
			throw new AlreadyLoginException();

		if (bindingResult.hasErrors()) {
			return "signUp";
		}
		String password = userMaster.getPassword();
		userMasterService.saveUser(userMaster);
		securityService.autoLogin(userMaster.getUsername(), password);
		return "redirect:products";
	}

	// Product List page.
	@GetMapping({ "/products" })
	public ModelAndView listProductHandler(ModelMap model) {
		model.addAttribute("productList", productMasterService.getAllProducts());
		return new ModelAndView("pages/productList", model);
	}

	@GetMapping({ "/buyProduct" })
	public String buyProduct(HttpServletRequest request, Model model, //
			@RequestParam(value = "code", defaultValue = "") String code) {
		if (code.isEmpty()) {
			return "redirect:products";
		}

		ProductMaster product = productMasterService.findProduct(code);

		if (product != null) {
			if (product.getQuantity() == 0) {
				logger.error("Product out of stock having code " + code);
				throw new ProductOutOfStockException();
			}
			model.addAttribute(MODEL_ATTRIBUTE_PRODUCT_MASTER, product);
		}
		return "pages/confirmation";
	}

	@PostMapping(value = { "/purchase" })
	public String purchaseProduct(HttpServletRequest request, Model model) {
		String code = request.getParameter("code");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		OrderMaster order = orderMasterService.saveOrder(code, quantity, securityService.findLoggedInUsername());
		model.addAttribute("order", order);
		if (logger.isInfoEnabled())
			logger.info("Product purchased having code " + code + ", quantity " + quantity);
		return "pages/orderDetail";
	}

	@GetMapping(value = { "/orders" })
	public String getOrderByUsername(HttpServletRequest request, Model model) {
		List<OrderMaster> list = orderMasterService.getOrdersByUserName(securityService.findLoggedInUsername());
		if (list.isEmpty())
			throw new NoOrderFoundException();
		model.addAttribute("orderList", list);
		return "pages/orderList";
	}

}
