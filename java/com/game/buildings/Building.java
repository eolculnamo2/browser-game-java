package com.game.buildings;

public interface Building {
	public int getProduction();
	public int getUpgradeCost();
	public int getLevel();
	public final int baseSteel = 500;
	public final int baseWood = 1200;
	public final int baseSilver = 900;
}
