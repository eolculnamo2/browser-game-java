package com.game.troops;

import java.util.HashMap;
import java.util.Map;

public class HeavySwords implements Troops{
	private int attack;
	private int armor;
	private int speed;
	private int loot;
	private double health;
	private int battlePosition;
	private int woodCost;
	private int steelCost;
	private int silverCost;
	
	public HeavySwords() {
		this.attack = this.getAttack();
		this.armor = this.getArmor();
		this.speed = this.getSpeed();
		this.loot = this.getLoot();
		this.health = this.getHealth();
		this.battlePosition = this.getBattlePosition();
		this.woodCost = HeavySwords.getCost().get("wood");
		this.steelCost = HeavySwords.getCost().get("steel");
		this.silverCost = HeavySwords.getCost().get("silver");
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
		return 3;
	};
	public int getArmor() {
		return 3;
	};
	public int getSpeed() {
		return 2;
	};
	public int getLoot() {
		return 2;
	};
	public double getHealth() {
		return baseHealth * 1.5;
	};
	public int getBattlePosition() {
		return 2;
	};
	public static Map<String, Integer> getCost() {
		Map<String, Integer> costMap = new HashMap<>();
		
		costMap.put("wood", 100);
		costMap.put("steel", 120);
		costMap.put("silver", 100);
		
		return costMap;
	}
}
