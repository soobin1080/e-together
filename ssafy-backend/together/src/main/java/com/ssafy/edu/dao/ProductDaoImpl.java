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


	public void insertProduct(Product product) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertProduct", product);
	}

	// 현재 페이지에 뿌려줄 상품 12개 가져오기
	public List<Product> getProductListWithPage(int startContent, String category) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startContent", startContent);
		paramMap.put("category", category);
		return sqlSession.selectList(ns + "getProductListWithPage", paramMap);
	}

	public int cntTotFood(String category) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "cntTotFood",category);
	}

	public List<Product> getKeywordProductListWithPage(int startContent, String category, String keyword) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startContent", startContent);
		paramMap.put("category", category);
		paramMap.put("keyword", keyword);
		return sqlSession.selectList(ns + "getKeywordProductListWithPage", paramMap);
	}

}
