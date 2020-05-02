package com.capgemeni.angular.service;

import java.util.List;

import com.capgemeni.angular.beans.ProductInfo;

public interface ProductService {
	List<ProductInfo> getAllProducts();

	boolean createProductInfo(ProductInfo ref);

	boolean updateProductInfo(ProductInfo ref);

	boolean deleteProductInfo(int id);

}
