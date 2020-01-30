package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Product;

public interface IProductService {
	List<Product> getProductList();

	List<Product> searchProduct(String keyword);

	List<Product> getProductCategory(String category);

	List<Product> getProductCategoryKeword(String category, String keyword);
}
