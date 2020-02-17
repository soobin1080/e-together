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

	@ApiOperation(value = "전체 상품 보기", response = List.class)
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProduct() throws Exception {
		logger.info("1-------------getAllProduct-----------------------------" + new Date());
		List<Product> products = productservice.getAllProduct();

		System.out.println(products);
		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@ApiOperation(value = "전체에서 상품 검색", response = List.class)
	@RequestMapping(value = { "/product/category/{keyword}" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> keywordSearchProduct(@PathVariable String keyword) throws Exception {
		logger.info("2-------------keywordSearchProduct-----------------------------" + new Date());

		System.out.println(keyword);
		List<Product> products = productservice.keywordSearchProduct(keyword);

		System.out.println("---------검색결과: " + products);

		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@ApiOperation(value = "카테고리 별 상품 보기", response = List.class)
	@RequestMapping(value = { "/product/{category}" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> categorySearchProduct(@PathVariable String category) throws Exception {
		logger.info("3-------------categorySearchProduct-----------------------------" + new Date());

		List<Product> products = productservice.categorySearchProduct(category);

		System.out.println("---------검색결과: " + products);

		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@ApiOperation(value = "카테고리 별 상품 검색", response = List.class)
	@RequestMapping(value = { "/product/{category}/{keyword}" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> categoryKeywordSearchProduct(@PathVariable String category,
			@PathVariable String keyword) throws Exception {
		logger.info("4-------------categoryKeywordSearchProduct-----------------------------" + new Date());

		List<Product> products = productservice.categoryKeywordSearchProduct(category, keyword);

		System.out.println("---------검색결과: " + products);

		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@ApiOperation(value = "상품 넣기 보기", response = List.class)
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<List<Product>> insertProduct(@RequestBody List<Product> products) throws Exception {
		logger.info("1-------------insertProduct-----------------------------" + new Date());

		for (int i = 0; i < products.size(); i++) {

			productservice.insertProduct(products.get(i));
		}

		System.out.println(products);
		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
