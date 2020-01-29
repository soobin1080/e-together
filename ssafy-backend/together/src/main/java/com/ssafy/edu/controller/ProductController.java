package com.ssafy.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.Product;
import com.ssafy.edu.service.IProductService;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class ProductController {
	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductService productService;

	// 관리자	
	int recentPage = 0;
	
	@ApiOperation(value = "회원정보 리스트를 반환한다.", response = List.class)
	@RequestMapping(value = "/findAllProduct", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAllProducts(@RequestParam(required=false, defaultValue="0") String nowPage) throws Exception {
		logger.info("1-------------findAllProducts-----------------------------" + new Date());

//		int totPage = (int) Math.ceil(userService.cntTotMember() / 5.0);
//		System.out.println("totPage : " + totPage);
//		if (nowPage == "" || nowPage == null) {
//			recentPage = 1;
//		} else {
//			recentPage = Integer.parseInt(nowPage);
//		}
//		System.out.println("recentPage :" + recentPage);
//		int startContent = (recentPage - 1) * 5;
//		System.out.println("startContent : " + startContent);
//
//		List<Member> memlist = memService.findAllMembers(startContent);	
//		memlist.get(0).setTotPage(totPage);
		
		List<Product> productList = productService.findAllProducts();
		System.out.println(productList);
	

		if (productList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			System.out.println(productList.get(0));
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

}