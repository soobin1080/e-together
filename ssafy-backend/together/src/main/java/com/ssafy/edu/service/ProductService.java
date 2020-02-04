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
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		return productdao.getProductList();
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return productdao.searchProduct(keyword);
	}

	@Override
	public List<Product> getProductCategory(String category) {
		// TODO Auto-generated method stub
		return productdao.getProductCategory(category);
	}

	@Override
	public List<Product> getProductCategoryKeword(String category, String keyword) {
		// TODO Auto-generated method stub
		return productdao.getProductCategoryKeword(category,keyword);
	}
	

}
