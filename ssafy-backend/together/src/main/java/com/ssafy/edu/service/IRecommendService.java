package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.BudgetAverageRate;
import com.ssafy.edu.model.RecommendResult;

public interface IRecommendService {

	List<Integer> getBudgetNumList(int budget);

	BudgetAverageRate getBudgetAverageRate(int budget, Integer budget_num);

}
