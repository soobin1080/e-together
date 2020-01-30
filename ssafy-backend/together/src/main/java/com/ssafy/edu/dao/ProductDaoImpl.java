package com.ssafy.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.Product;

@Repository
public class ProductDaoImpl {

	String ns = "ssafy.product.";
	@Autowired
	private SqlSession sqlSession;


	public List<Product> getProductList() {
		return sqlSession.selectList(ns + "getProductList");
	}


	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "searchProduct",keyword);
	}


	public List<Product> getProductCategory(String category) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getProductCategory",category);
	}


	public List<Product> getProductCategoryKeword(String category, String keyword) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("category", category);
		paramMap.put("keyword", keyword);
		return sqlSession.selectList(ns + "getProductCategoryKeword",paramMap);
	}

}
