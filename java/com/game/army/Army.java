package com.game.army;

import java.util.HashMap;
import java.util.Map;

public class Army implements ArmyInterface {
	
	private int totalSpears;
	private int totalArchers;
	private int totalHeavySwords;
	
	private int totalAttack;
	private int totalDefense;
	
	public Army(int spears, int archers, int heavySwords) {
		this.totalSpears += spears;
		this.totalArchers += archers;
		this.totalHeavySwords += heavySwords;
	}
	
	public int getTotal() {
		int total = 0;
		total += this.getSpears();
		total += this.getArchers();
		total += this.getHeavySwords();
		
		return total;
	}
	
	public void killHeavySwords() {
		if(this.totalHeavySwords != 0) {
			this.totalHeavySwords--;
		}
	}
	
	public int getHeavySwords() {
		return this.totalHeavySwords;
	}
	
	public void killSpear() {
		if(this.totalSpears != 0) {
			this.totalSpears--;
		}
	}
	
	public int getSpears() {
		return totalSpears;
	}
	
	public void killArcher() {
		if(this.totalArchers != 0) {
			this.totalArchers--;
		}
	}
	
	public int getArchers() {
		return totalArchers;
	}
	
	public Map<String,Boolean> getRemainingTroopTypes() {
		Map<String, Boolean> troopMap = new HashMap<>();
		
		troopMap.put("archers", this.totalArchers > 0);
		troopMap.put("spearmen", this.totalSpears > 0);
		troopMap.put("heavySwords", this.totalHeavySwords > 0);
		
		return troopMap;
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
