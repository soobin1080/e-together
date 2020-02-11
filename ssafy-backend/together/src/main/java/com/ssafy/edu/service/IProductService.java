package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Product;

public interface IProductService {
	List<Product> getAllProduct();

	List<Product> keywordSearchProduct(String keyword);

	List<Product> categorySearchProduct(String category);

	List<Product> categoryKeywordSearchProduct(String category, String keyword);
}
