package com.game.troops;

import java.util.HashMap;
import java.util.Map;

public class Spearmen implements Troops {
	private int attack;
	private int armor;
	private int speed;
	private int loot;
	private double health;
	private int battlePosition;
	private int woodCost;
	private int steelCost;
	private int silverCost;
	
	public Spearmen() {
		this.attack = this.getAttack();
		this.armor = this.getArmor();
		this.speed = this.getSpeed();
		this.loot = this.getLoot();
		this.health = this.getHealth();
		this.battlePosition = this.getBattlePosition();
		this.woodCost = Spearmen.getCost().get("wood");
		this.steelCost = Spearmen.getCost().get("steel");
		this.silverCost = Spearmen.getCost().get("silver");
	}
	
	public int getWoodCost() {
		return woodCost;
	}

	public void setWoodCost(int woodCost) {
		this.woodCost = woodCost;
	}

	public int getSteelCost() {
		return steelCost;
	}

	public void setSteelCost(int steelCost) {
		this.steelCost = steelCost;
	}

	public int getSilverCost() {
		return silverCost;
	}

	public void setSilverCost(int silverCost) {
		this.silverCost = silverCost;
	}

	
	public int getAttack() {
		return 1;
	}
	public int getArmor() {
		return 0;
	}
	public int getSpeed() {
		return 3;
	}
	public int getLoot() {
		return 4;
	}
	public double getHealth() {
		return baseHealth;
	}
	public int getBattlePosition() {
		return 1;
	}
	
	public static Map<String, Integer> getCost() {
		Map<String, Integer> costMap = new HashMap<>();
		
		costMap.put("wood", 100);
		costMap.put("steel", 20);
		costMap.put("silver", 20);
		
		return costMap;
	}
	
}
