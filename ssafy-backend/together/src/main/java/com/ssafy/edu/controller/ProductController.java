package com.ssafy.edu.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.Product;
import com.ssafy.edu.model.ProductPage;
import com.ssafy.edu.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY 2020")
public class ProductController {
	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService productservice;

int recentPage = 0;
	
	@ApiOperation(value = "카테고리 별 상품 보기", response = List.class)
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public ResponseEntity<ProductPage> categorySearchProduct(@RequestParam(required = false, defaultValue="1")int page,@RequestParam String category) throws Exception {
		logger.info("3-------------categorySearchProduct-----------------------------" + new Date());
		
		recentPage = page;
		System.out.println("recentPage :" + recentPage);
		
		ProductPage productpage = productservice.getProductListWithPage(page,category);
//		List<Product> products = productpage.getProductlist();

		System.out.println("---------검색결과: " + productpage);

		if (productpage==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ProductPage>(productpage, HttpStatus.OK);
	}

	@ApiOperation(value = "카테고리 별 상품 검색", response = ProductPage.class)
	@RequestMapping(value = { "/product/{keyword}" }, method = RequestMethod.GET)
	public ResponseEntity<ProductPage> categoryKeywordSearchProduct(@RequestParam(required = false, defaultValue="1")int page,@RequestParam String category,
			@PathVariable String keyword) throws Exception {
		logger.info("4-------------categoryKeywordSearchProduct-----------------------------" + new Date());

		recentPage = page;
		System.out.println("recentPage :" + recentPage);
		
		ProductPage productpage = productservice.getKeywordProductListWithPage(page,category,keyword);
//		List<Product> products = productpage.getProductlist();
		
		System.out.println("---------검색결과: " + productpage);

		if (productpage==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ProductPage>(productpage, HttpStatus.OK);
	}



}
