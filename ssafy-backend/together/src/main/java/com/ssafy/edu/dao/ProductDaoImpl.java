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


	public List<Product> getAllProduct() {
		return sqlSession.selectList(ns + "getAllProduct");
	}


	public List<Product> keywordSearchProduct(String keyword) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "keywordSearchProduct",keyword);
	}


	public List<Product> categorySearchProduct(String category) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "categorySearchProduct",category);
	}


	public List<Product> categoryKeywordSearchProduct(String category, String keyword) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("category", category);
		paramMap.put("keyword", keyword);
		return sqlSession.selectList(ns + "categoryKeywordSearchProduct",paramMap);
	}


	public void insertProduct(Product product) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns+"insertProduct",product);
	}

}
