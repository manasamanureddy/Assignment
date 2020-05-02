package com.capgemeni.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemeni.angular.beans.ProductInfo;
import com.capgemeni.angular.dao.ProductDAO;
@Service
public class ProductServiceImplements implements ProductService{
	@Autowired
	private ProductDAO dao;

	@Override
	public List<ProductInfo> getAllProducts() {
		
		return dao.getAllProducts();
	}

	@Override
	public boolean createProductInfo(ProductInfo ref) {
		// TODO Auto-generated method stub
		return dao.createProductInfo(ref);
	}

	@Override
	public boolean updateProductInfo(ProductInfo ref) {
		// TODO Auto-generated method stub
		return dao.updateProductInfo(ref);
	}

	@Override
	public boolean deleteProductInfo(int id) {
		// TODO Auto-generated method stub
		return dao.deleteProductInfo(id);
	}

	}
