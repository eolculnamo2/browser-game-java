package com.game.troops;

public class Archers implements Troops {
	
	public int getAttack() {
		return 1;
	};
	
	public int getArmor() {
		return 0;
	}
	
	public int getSpeed() {
		return 3;
	};
	
	public int getLoot() {
		return 1;
	};
	
	public int getHealth() {
		return baseHealth;
	};
	
	public int getBattlePosition() {
		return 5;
	}
}
