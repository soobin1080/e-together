package com.ssafy.edu.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;
import com.ssafy.edu.model.BudgetListResult;
import com.ssafy.edu.service.IBudgetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY 2020")
public class BudgetController {
	public static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

	@Autowired
	private IBudgetService budgetservice;

	@ApiOperation(value = "내 예산안 가져오기", response = List.class)
	@RequestMapping(value = "/budget/{user_email}", method = RequestMethod.GET)
	public ResponseEntity<List<BudgetInfo>> getBudgetList(@PathVariable String user_email) throws Exception {
		logger.info("1-------------getBudgetList-----------------------------" + new Date());
		
		List<BudgetInfo> mybudgetinfo = budgetservice.getMyBudgetList(user_email);

		System.out.println(mybudgetinfo);
		if (mybudgetinfo.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BudgetInfo>>(mybudgetinfo, HttpStatus.OK);
	}

	@ApiOperation(value = "내 예산안 품목 상세보기", response = Budget.class)
	@RequestMapping(value = "/budget/{budget_num}", method = RequestMethod.GET)
	public ResponseEntity<Budget> getOneBudget(@PathVariable int budget_num)
			throws Exception {
		logger.info("2-------------getOneBudget-----------------------------" + new Date());

		BudgetInfo budgetinfo = budgetservice.getOneBudgetInfo(budget_num);
		List<BudgetListResult> budgetlist = budgetservice.getOneBudgetList(budget_num);
		
		Budget budget = new Budget();
		
		System.out.println(budget);

		budget.setBudgetinfo(budgetinfo);
		
		budget.setBudgetlist(budgetlist);
		
		if (budget == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Budget>(budget, HttpStatus.OK);
	}

	@ApiOperation(value = "내 예산안 저장하기", response = List.class)
	@RequestMapping(value = "/budget", method = RequestMethod.POST)
	public ResponseEntity<Budget> insertOneBudget(@RequestBody Budget budget) throws Exception {
		logger.info("3-------------insertOneBudget-----------------------------" + new Date());

		budgetservice.insertBudgetInfo(budget.getBudgetinfo());
//		budgetservice.getInsertBudgetNum(budget.getNum());
		List<BudgetListResult> budgetlistresult = budget.getBudgetlist(); // 이메일, 에산안 제목 없이 날아옴.

		for (int i = 0; i < budgetlistresult.size(); i++) {

//			BudgetList budgetlist = new BudgetList();
//			budgetlist.setUser_email(budget.getUser_email());
//			budgetlist.setBudget_title(budget.getBudget_title());
//			budgetlist.setPro_id(budgetlistresult.get(i).getPro_id());
//			budgetlist.setPro_name(budgetlistresult.get(i).getPro_name());
//			budgetlist.setQuantity(budgetlistresult.get(i).getQuantity());
//			budgetlist.setPrice(budgetlistresult.get(i).getPrice());
//			budgetservice.insertBudgetList(budgetlist);
//			System.out.println(i + "번째 상품 : " + budgetlist.toString());
		}

		if (budget == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Budget>(budget, HttpStatus.OK);
	}

	@ApiOperation(value = "내 예산안 지우기", response = Budget.class)
	@RequestMapping(value = "/budget", method = RequestMethod.DELETE)
	public ResponseEntity<Budget> deleteOneBudget(@PathVariable int budget_num) throws Exception {
		logger.info("4-------------deleteOneBudget-----------------------------" + new Date());

		budgetservice.deleteBudgetInfo(budget_num);

//		List<BudgetListResult> budgetlist = budget.getBudgetlist();

		//mysql cas
//		for (int i = 0; i < budgetlist.size(); i++) {
//			budgetservice.deleteBudgetList(budget.getUser_email(), budget.getBudget_title(),
//					budgetlist.get(i).getPro_id());
//			System.out.println(i + "번째 상품 삭제-----------------");
//		}

//		if ( == null) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
		return new ResponseEntity<Budget>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "내 예산안 적합/부적합 표시하기", response = Budget.class)
	@RequestMapping(value = "/budget/{budget_num}", method = RequestMethod.POST)
	public ResponseEntity<BudgetInfo> updateBudgetFitness(@PathVariable int budget_num) throws Exception {
		logger.info("4-------------isBudgetFitness-----------------------------" + new Date());

		budgetservice.updateBudget(budget_num);
		
		return new ResponseEntity<BudgetInfo>(HttpStatus.OK);
	}
	
}
