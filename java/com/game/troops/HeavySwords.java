package com.game.troops;

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
}
