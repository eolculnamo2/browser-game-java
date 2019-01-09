package com.game.battle;

import com.game.army.Army;
import com.game.helpers.Helpers;
import com.game.troops.Archers;
import com.game.troops.Spearmen;

public class BattleSequenceBase {
	Army army1;
	Army army2;
	final int totalTroopTypes = 2;
	
	BattleSequenceBase() {
		System.out.println("Start Battle");
		
		army1 = new Army(15,5);
		army2 = new Army(10,10);
	}
	
	protected void round() {
		army1.getSpears();
		army2.getArchers();
		
		army1.setTotalAttack(this.makeTotalAttack(army1));
		army2.setTotalAttack(this.makeTotalAttack(army2));
		
		army1.setTotalDefense(this.makeTotalDefense(army1));
		army2.setTotalDefense(this.makeTotalDefense(army2));
		
		int armyDamage1 = this.calculateDamage(army1, army2);
		this.inflictDamage(armyDamage1, army2);
		
		int armyDamage2 = this.calculateDamage(army2, army1);
		this.inflictDamage(armyDamage2, army1);
		
	}
	
	protected void inflictDamage(int damage, Army otherArmy) {
		for(int i=0; i < damage; i++) {
			int decision = Helpers.getRandomInt(this.totalTroopTypes);
			
			if(decision == 1) {
				otherArmy.killSpear();
			} else if(decision == 2) {
				otherArmy.killArcher();
			}
		}
	}
	
	protected int calculateDamage(Army armyN1, Army armyN2) {
		int attackDamageArmy =  armyN2.getTotal() * Helpers.getRandomInt(armyN1.getTotalAttack());
		attackDamageArmy = armyN2.getTotal() * Helpers.getRandomInt(armyN2.getTotalDefense()/2);
		
		return attackDamageArmy;
	}
	
	protected int makeTotalAttack(Army army) {
		Spearmen spears = new Spearmen();
		Archers archers = new Archers();
		
		int spearsAttack = spears.getAttack() * army.getSpears();
		int archersAttack = archers.getAttack() * army.getArchers();
		
		return spearsAttack + archersAttack;
	}
	
	protected int makeTotalDefense(Army army) {
		Spearmen spears = new Spearmen();
		Archers archers = new Archers();
		
		int spearsDefense = spears.getArmor() * army.getSpears();
		int archersDefense = archers.getArmor() * army.getArchers();
		
		return spearsDefense + archersDefense;
	}
	
}
