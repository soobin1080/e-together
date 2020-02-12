package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.ProductDaoImpl;
import com.ssafy.edu.model.Product;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductDaoImpl productdao;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productdao.getAllProduct();
	}

	@Override
	public List<Product> keywordSearchProduct(String keyword) {
		// TODO Auto-generated method stub
		return productdao.keywordSearchProduct(keyword);
	}

	@Override
	public List<Product> categorySearchProduct(String category) {
		// TODO Auto-generated method stub
		return productdao.categorySearchProduct(category);
	}

	@Override
	public List<Product> categoryKeywordSearchProduct(String category, String keyword) {
		// TODO Auto-generated method stub
		return productdao.categoryKeywordSearchProduct(category,keyword);
	}
	

}
