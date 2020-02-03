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

import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;
import com.ssafy.edu.service.IBudgetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class BudgetController {
	public static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

	@Autowired
	private IBudgetService budgetservice;

	@ApiOperation(value = "내 예산안 가져오기", response = List.class)
	@RequestMapping(value = "/budget/{user_email}", method = RequestMethod.GET)
	public ResponseEntity<List<BudgetList>> getBudgetList(@PathVariable String user_email) throws Exception {
		logger.info("1-------------getBudgetList-----------------------------" + new Date());
		List<BudgetList> mybudgetlist = budgetservice.getBudgetList(user_email);
		
		System.out.println(mybudgetlist);
		if (mybudgetlist.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BudgetList>>(mybudgetlist, HttpStatus.OK);
	}
	
	@ApiOperation(value = "내 예산안 품목 상세보기", response = List.class)
	@RequestMapping(value = "/budget/{user_email}/{budget_num}", method = RequestMethod.GET)
	public ResponseEntity<Budget> getOneBudget(@PathVariable String user_email,@PathVariable String budget_num) throws Exception {
		logger.info("2-------------getOneBudget-----------------------------" + new Date());
		Budget budget = budgetservice.getOneBudget(user_email,budget_num);
		
		System.out.println(budget);
		if (budget==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Budget>(budget, HttpStatus.OK);
	}
	
	@ApiOperation(value = "내 예산안 저장하기", response = List.class)
	@RequestMapping(value = "/budget", method = RequestMethod.POST)
	public ResponseEntity<Budget> insertOneBudget(@RequestBody Budget budget) throws Exception {
		logger.info("3-------------insertOneBudget-----------------------------" + new Date());
		
		System.out.println(budget);
		
		BudgetList budgetlist=new BudgetList();
		budgetlist.setPrice(budget.getBudget());
		budgetlist.setPro_id(budget.getPro_id());
		budgetlist.setPro_name(budget.getPro_name());
		budgetlist.setQuantity(budget.getQuantity());
		budgetlist.setUser_email(budget.getUser_email());
		budgetservice.insertBudgetList(budgetlist);
		
		BudgetInfo budgetinfo=new BudgetInfo();
		budgetinfo.setBudget_num(budget.getBudget_num());
		budgetinfo.setUser_email(budget.getUser_email());
		budgetinfo.setBudget_title(budget.getBudget_title());
		budgetinfo.setPersonnel(budget.getPersonnel());
		budgetinfo.setBudget(budget.getBudget());
		budgetinfo.setFitness(budget.getFitness());
		budgetinfo.setLike_count(budget.getLike_count());
		budgetservice.insertBudgetInfo(budgetinfo);
		
		System.out.println("----------insert 성공-----------");
		
		if (budget==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Budget>(budget, HttpStatus.OK);
	}
}
