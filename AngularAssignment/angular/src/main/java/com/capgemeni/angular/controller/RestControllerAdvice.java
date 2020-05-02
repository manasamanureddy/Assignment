package com.capgemeni.angular.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.capgemeni.angular.beans.ProductResponse;
import com.capgemeni.angular.exception.ProductException;
@RestController
public class RestControllerAdvice {
	@ExceptionHandler
	public ProductResponse myExceptionHandler(ProductException empException) {
		ProductResponse response = new ProductResponse();
		response.setError(true);
		response.setMessage(empException.getMessage());
		return response;

	}

}
