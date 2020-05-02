package com.capgemeni.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemeni.angular.beans.ProductInfo;
import com.capgemeni.angular.beans.ProductResponse;
import com.capgemeni.angular.service.ProductService;

@RestController
public class ProductRestController {
	@Autowired
	private ProductService service;

	@PostMapping(path = "/addProduct", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ProductResponse addProduct(@RequestBody ProductInfo productInfo) {
		boolean isAdded = service.createProductInfo(productInfo);
		ProductResponse response = new ProductResponse();
		if (isAdded) {
			response.setMessage("Product Inserted successfully");
		} else {
			response.setError(true);
		}
		return response;
	}
	@GetMapping(path="/getAllProducts",produces=MediaType.APPLICATION_JSON_VALUE)
	public ProductResponse getAllEmployees() {
		List<ProductInfo> productList=service.getAllProducts();
		ProductResponse response=new ProductResponse();
		if(productList!=null && !productList.isEmpty()) {
			response.setProductList(productList);
		}else {
			response.setError(true);
			response.setMessage("no record found");
		}
		return response;
	}
	
	@PutMapping(path="/updateProduct",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
		    produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

	public ProductResponse updateEmployee(@RequestBody ProductInfo proInfo) {
		boolean isUpdated=service.updateProductInfo(proInfo);
		ProductResponse response=new ProductResponse();
		if(isUpdated) {
			response.setMessage("Record updated successfully");
		}else {
			response.setError(true);
			response.setMessage("Unable to update employee");
		}

		return response;
	}
	
	@DeleteMapping(path = "/deletePro/{proId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ProductResponse deleteEmployee(@PathVariable(name = "proId")  int productId) {
		ProductResponse response = new ProductResponse();
		boolean isDeleted = service.deleteProductInfo(productId);
		if (isDeleted) {
			response.setMessage("record deleted for id" +productId);
		} else {
			response.setError(true);
			response.setMessage("No Records found to delete/unble to delete"+productId);
		}
		return response;
	}//end of delete
}
