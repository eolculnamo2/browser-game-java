package com.game.buildings;

public class SteelFoundry implements Building {
	private int playerLevel;
	public SteelFoundry(int playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	public int getProduction() {
		return baseSteel + (15 << this.playerLevel);
	}
	
	public int getUpgradeCost() {
		return baseSteel/2  + (30 << this.playerLevel);
	}
	
	public int getLevel() {
		return playerLevel;
	}

}
