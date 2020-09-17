package com.chahar.indent.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.chahar.indent.exceptions.AlreadyLoginException;
import com.chahar.indent.exceptions.NoOrderFoundException;
import com.chahar.indent.exceptions.ProductOutOfStockException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String pageNotFoundHandler(HttpServletRequest request, Exception ex)
	{
		System.out.println("requested url ="+request.getRequestURL());
		System.out.println("Exception ="+ex.getMessage());
		return "pages/404";
	}
	
	@ExceptionHandler(ProductOutOfStockException.class)
	public String productOutOfStock(ProductOutOfStockException ex)
	{
		return "pages/outOfStock";
	}
	
	@ExceptionHandler(NoOrderFoundException.class)
	public String noOrderFound(NoOrderFoundException ex)
	{
		return "pages/noOrder";
	}
	
	@ExceptionHandler(AlreadyLoginException.class)
	public String AlreadyLogin(AlreadyLoginException ex)
	{
		return "redirect:/products";
	}
	
	@ExceptionHandler(SQLException.class)
	public String internalServerProblem(SQLException ex)
	{
		return "pages/serverError";
	}
}
