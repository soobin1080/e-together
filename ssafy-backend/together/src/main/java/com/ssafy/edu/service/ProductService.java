package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.ProductDaoImpl;
import com.ssafy.edu.model.Product;
import com.ssafy.edu.model.ProductPage;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductDaoImpl productdao;
	
	@Override
	public void insertProduct(Product product) {
		// TODO Auto-generated method stub
		productdao.insertProduct(product);
	}

	@Override
	public ProductPage getProductListWithPage(int nowPage, String category) {
		//현재 페이지에 뿌려줄 상품 12개 가져오기
		int totPage = (int) Math.ceil(productdao.cntTotCategoryProduct(category) / 12.0);
		System.out.println("totPage : " + totPage);
		System.out.println("nowPage : " + nowPage);
		
		int startContent = (nowPage - 1) * 12;
		System.out.println("startContent : " + startContent);

		List<Product> products = productdao.getProductListWithPage(startContent,category);
		
		return new ProductPage(products, nowPage,1,totPage,totPage);
	}

	@Override
	public ProductPage getKeywordProductListWithPage(int nowPage, String category, String keyword) {
		//현재 페이지에 뿌려줄 상품 12개 가져오기
		int totPage = (int) Math.ceil(productdao.cntTotCategoryProduct(category) / 12.0);
		System.out.println("totPage : " + totPage);
		System.out.println("nowPage : " + nowPage);
		
		int startContent = (nowPage - 1) * 12;
		System.out.println("startContent : " + startContent);
		int endPage = (int) Math.ceil(productdao.cntTotKeywordProduct(category,keyword) / 12.0);
		List<Product> products = productdao.getKeywordProductListWithPage(startContent,category,keyword);
		
		return new ProductPage(products, nowPage,1,endPage,totPage);
	}
	

}
