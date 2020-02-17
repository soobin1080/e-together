package com.ssafy.edu.model;

public class BudgetAverageRate {
	private Double water;
	private Double noodle;
	private Double rice;
	private Double instant;
	private Double nuts;
	private Double vegetable;
	private Double seafood;
	private Double fruits;
	private Double snack;
	private Double meat;

	public BudgetAverageRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetAverageRate(Double water, Double noodle, Double rice, Double instant, Double nuts, Double vegetable,
			Double seafood, Double fruits, Double snack, Double meat) {
		super();
		this.water = water;
		this.noodle = noodle;
		this.rice = rice;
		this.instant = instant;
		this.nuts = nuts;
		this.vegetable = vegetable;
		this.seafood = seafood;
		this.fruits = fruits;
		this.snack = snack;
		this.meat = meat;
	}

	public Double getWater() {
		return water;
	}

	public void setWater(Double water) {
		this.water = water;
	}

	public Double getNoodle() {
		return noodle;
	}

	public void setNoodle(Double noodle) {
		this.noodle = noodle;
	}

	public Double getRice() {
		return rice;
	}

	public void setRice(Double rice) {
		this.rice = rice;
	}

	public Double getInstant() {
		return instant;
	}

	public void setInstant(Double instant) {
		this.instant = instant;
	}

	public Double getNuts() {
		return nuts;
	}

	public void setNuts(Double nuts) {
		this.nuts = nuts;
	}

	public Double getVegetable() {
		return vegetable;
	}

	public void setVegetable(Double vegetable) {
		this.vegetable = vegetable;
	}

	public Double getSeafood() {
		return seafood;
	}

	public void setSeafood(Double seafood) {
		this.seafood = seafood;
	}

	public Double getFruits() {
		return fruits;
	}

	public void setFruits(Double fruits) {
		this.fruits = fruits;
	}

	public Double getSnack() {
		return snack;
	}

	public void setSnack(Double snack) {
		this.snack = snack;
	}

	public Double getMeat() {
		return meat;
	}

	public void setMeat(Double meat) {
		this.meat = meat;
	}

	@Override
	public String toString() {
		return "BudgetAverageRate [water=" + water + ", noodle=" + noodle + ", rice=" + rice + ", instant=" + instant
				+ ", nuts=" + nuts + ", vegetable=" + vegetable + ", seafood=" + seafood + ", fruits=" + fruits
				+ ", snack=" + snack + ", meat=" + meat + "]";
	}

	

}
