package com.game.troops;

import java.util.HashMap;
import java.util.Map;

public class Archers implements Troops {
	
	public int getAttack() {
		return 1;
	};
	public int getArmor() {
		return 0;
	};
	public int getSpeed() {
		return 3;
	};
	public int getLoot() {
		return 1;
	};
	public double getHealth() {
		return baseHealth;
	};
	public int getBattlePosition() {
		return 5;
	}
	public static Map<String, Integer> getCost() {
		Map<String, Integer> costMap = new HashMap<>();
		
		costMap.put("wood", 150);
		costMap.put("steel", 10);
		costMap.put("silver", 20);
		
		return costMap;
	}
}
