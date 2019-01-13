package com.game.troops;

import java.util.HashMap;
import java.util.Map;

public class HeavySwords implements Troops{
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
