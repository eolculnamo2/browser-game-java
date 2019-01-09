package com.game.army;

public class Army {
	
	private int totalSpears;
	private int totalArchers;
	private int totalAttack;
	private int totalDefense;
	
	public Army(int spears, int archers) {
		System.out.println("Army called.");
		this.totalSpears += spears;
		this.totalArchers += archers;
	}
	
	public int getTotal() {
		int total = 0;
		total += this.getSpears();
		total += this.getArchers();
		
		return total;
	}
	
	public void killSpear() {
		this.totalSpears--;
	}
	
	public int getSpears() {
		return totalSpears;
	}
	
	public void killArcher() {
		this.totalArchers--;
	}
	
	public int getArchers() {
		return totalArchers;
	}
	
	
	public void setTotalAttack(int attack) {
		this.totalAttack = attack;
	}
	
	public int getTotalAttack() {
		return this.totalAttack;
	}
	
	public void setTotalDefense(int defense) {
		this.totalDefense = defense;
	}
	
	public int getTotalDefense() {
		return this.totalDefense;
	}
	

}
