package com.game.buildings;

public class LoggingCamps implements Building{
	public int playerLevel;
	
	public LoggingCamps(int playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	public int getProduction() {
		return baseWood + (25 << this.playerLevel) ;
	}
	
	public int getUpgradeCost() {
		return baseWood/2  + (50 << this.playerLevel);
	}

	public int getLevel() {
		return this.playerLevel;
	}

}
