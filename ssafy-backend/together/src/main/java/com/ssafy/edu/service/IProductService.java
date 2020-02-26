package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Product;
import com.ssafy.edu.model.ProductPage;

public interface IProductService {
//	List<Product> getAllProduct();
//
//	List<Product> keywordSearchProduct(String keyword);

//	List<Product> categorySearchProduct(String category);
//
//	List<Product> categoryKeywordSearchProduct(String category, String keyword);

	void insertProduct(Product product);

	ProductPage getProductListWithPage(int nowPage, String category);

	ProductPage getKeywordProductListWithPage(int page, String category, String keyword);
}
