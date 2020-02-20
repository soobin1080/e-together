package com.ssafy.edu.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.BudgetAverageRate;
import com.ssafy.edu.service.IRecommendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY 2020")
public class RecommendController {
	public static final Logger logger = LoggerFactory.getLogger(RecommendController.class);

	@Autowired
	private IRecommendService recommendservice;
	// 예산을 주면 예산에 맞는 예산안들 중에 통계를 내서 비율을 준다.

	@ApiOperation(value = "내 예산에 맞게 카테고리별 상품 비율 추천해주기", response = BudgetAverageRate.class)
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public ResponseEntity<BudgetAverageRate> recommendProduct(@RequestParam int budget) throws Exception {
		logger.info("1-------------recommendProduct-----------------------------" + new Date());

		List<Integer> budget_number = recommendservice.getBudgetNumList(budget);
		
		BudgetAverageRate budgetaveragerate = new BudgetAverageRate(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0);
		for (int i = 0; i < budget_number.size(); i++) {
			// 저장된 예산안 하나에서 예산가격으로 상품들의 카테고리별 비율 알아내기!
			int budget_num = budget_number.get(i);
			BudgetAverageRate bar = recommendservice.getBudgetAverageRate(budget, budget_num);
			
			budgetaveragerate.setWater((budgetaveragerate.getWater() + bar.getWater()) / 2);
			budgetaveragerate.setNoodle((budgetaveragerate.getNoodle() + bar.getNoodle()) / 2);
			budgetaveragerate.setRice((budgetaveragerate.getRice() + bar.getRice()) / 2);
			budgetaveragerate.setInstant((budgetaveragerate.getInstant() + bar.getInstant()) / 2);
			budgetaveragerate.setNuts((budgetaveragerate.getNuts() + bar.getNuts()) / 2);
			budgetaveragerate.setVegetable((budgetaveragerate.getVegetable() + bar.getVegetable()) / 2);
			budgetaveragerate.setSeafood((budgetaveragerate.getSeafood() + bar.getSeafood()) / 2);
			budgetaveragerate.setFruits((budgetaveragerate.getFruits() + bar.getFruits()) / 2);
			budgetaveragerate.setSnack((budgetaveragerate.getSnack() + bar.getSnack()) / 2);
			budgetaveragerate.setMeat((budgetaveragerate.getMeat() + bar.getMeat()) / 2);
		}

		if (budgetaveragerate == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BudgetAverageRate>(budgetaveragerate, HttpStatus.OK);
	}

}
