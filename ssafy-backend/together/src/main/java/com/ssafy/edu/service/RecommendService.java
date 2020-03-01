package com.ssafy.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.RecommendDaoImpl;
import com.ssafy.edu.model.BudgetAverageRate;
import com.ssafy.edu.model.RecommendResult;

@Service
public class RecommendService implements IRecommendService {

	@Autowired
	private RecommendDaoImpl recommenddao;

	@Override
	public List<Integer> getBudgetNumList(int budget) {
		// TODO Auto-generated method stub
		return recommenddao.getBudgetNumList(budget);
	}

	@Override
	public BudgetAverageRate getBudgetAverageRate(int budget, Integer budget_number) {
		// TODO Auto-generated method stub

		BudgetAverageRate budgetaveragerate = new BudgetAverageRate(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0);

		List<RecommendResult> recommendresult = recommenddao.getBudgetAverageRate(budget_number);
		// 스위치문으로!!!
		for (int i = 0; i < recommendresult.size(); i++) {
			RecommendResult rr = recommendresult.get(i);
			
//			System.out.println(rr.getPrice_sum());
			double p=((double)rr.getPrice_sum()/(double)budget)*100;
			double percentage=Math.round(p*100)/100.0;
//			System.out.println(rr.getMain_category()+" 의  비율 :   "+percentage);
			
			
			switch (rr.getMain_category()) {
			
			case "생수/음료":
				budgetaveragerate.setWater(percentage);
				break;
			case "라면":
				budgetaveragerate.setNoodle(percentage);
//				System.out.println(percentage+" %");
				break;
			case "쌀/잡곡":
				budgetaveragerate.setRice(percentage);
				break;
			case "즉석식품":
				budgetaveragerate.setInstant(percentage);
				break;
			case "견과/건해산물":
				budgetaveragerate.setNuts(percentage);
				break;
			case "채소":
				budgetaveragerate.setVegetable(percentage);
				break;
			case "수산물/해산물":
				budgetaveragerate.setSeafood(percentage);
				break;
			case "과일":
				budgetaveragerate.setFruits(percentage);
				break;
			case "스낵":
				budgetaveragerate.setSnack(percentage);
				break;
			case "정육/계란류":
				budgetaveragerate.setMeat(percentage);
				break;

			default:
				break;
			}
		}

		return budgetaveragerate;
	}
}
