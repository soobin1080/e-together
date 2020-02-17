package com.ssafy.edu.model;

public class BudgetAverageRate {
	private int water;
	private int noodle;
	private int rice;
	private int instant;
	private int nuts;
	private int vegetable;
	private int seafood;
	private int fruits;
	private int snack;
	private int meat;

	public BudgetAverageRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetAverageRate(int water, int noodle, int rice, int instant, int nuts, int vegetable, int seafood,
			int fruits, int snack, int meat) {
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

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getNoodle() {
		return noodle;
	}

	public void setNoodle(int noodle) {
		this.noodle = noodle;
	}

	public int getRice() {
		return rice;
	}

	public void setRice(int rice) {
		this.rice = rice;
	}

	public int getInstant() {
		return instant;
	}

	public void setInstant(int instant) {
		this.instant = instant;
	}

	public int getNuts() {
		return nuts;
	}

	public void setNuts(int nuts) {
		this.nuts = nuts;
	}

	public int getVegetable() {
		return vegetable;
	}

	public void setVegetable(int vegetable) {
		this.vegetable = vegetable;
	}

	public int getSeafood() {
		return seafood;
	}

	public void setSeafood(int seafood) {
		this.seafood = seafood;
	}

	public int getFruits() {
		return fruits;
	}

	public void setFruits(int fruits) {
		this.fruits = fruits;
	}

	public int getSnack() {
		return snack;
	}

	public void setSnack(int snack) {
		this.snack = snack;
	}

	public int getMeat() {
		return meat;
	}

	public void setMeat(int meat) {
		this.meat = meat;
	}

	@Override
	public String toString() {
		return "BudgetAverageRate [water=" + water + ", noodle=" + noodle + ", rice=" + rice + ", instant=" + instant
				+ ", nuts=" + nuts + ", vegetable=" + vegetable + ", seafood=" + seafood + ", fruits=" + fruits
				+ ", snack=" + snack + ", meat=" + meat + "]";
	}

}
