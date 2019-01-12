package com.game.army;

public class TroopData {

	private String playerToAttack;
	private int spearmen;
	private int archers;
	private int heavySwords;
	
	public TroopData() {
		
	}
	
	public TroopData(String playerToAttack, int spearmen, int archers, int heavySwords) {
		this.playerToAttack = playerToAttack;
		this.spearmen = spearmen;
		this.archers = archers;
		this.heavySwords = heavySwords;
	}
	
	public String getPlayerToAttack() {
		return playerToAttack;
	}
	public void setPlayerToAttack(String playerToAttack) {
		this.playerToAttack = playerToAttack;
	}
	public int getSpearmen() {
		return spearmen;
	}
	public void setSpearmen(int spearmen) {
		this.spearmen = spearmen;
	}
	public int getArchers() {
		return archers;
	}
	public void setArchers(int archers) {
		this.archers = archers;
	}
	public int getHeavySwords() {
		return heavySwords;
	}
	public void setHeavySwords(int heavySwords) {
		this.heavySwords = heavySwords;
	}
	
}