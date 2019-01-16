package com.game.buildings;

public class TaxableEconomy implements Building {

	public int playerLevel;
	
	public TaxableEconomy(int playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	public int getProduction() {
		return baseSilver + (20 << this.playerLevel);
	}
	
	public int getUpgradeCost() {
		return baseSilver/2  + (40 << this.playerLevel);
	}

	public int getLevel() {
		return playerLevel;
	}

}
