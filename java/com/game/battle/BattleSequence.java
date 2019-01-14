package com.game.battle;

import java.util.Map;

import com.game.army.Army;
import com.game.army.TroopData;
import com.game.helpers.Helpers;
import com.game.troops.Archers;
import com.game.troops.HeavySwords;
import com.game.troops.Spearmen;

public class BattleSequence {
	Army attacker;
	Army defender;

	public BattleSequence(TroopData attackData, TroopData defendData) {
		System.out.println("Start Battle");
		
		attacker = new Army(attackData.getSpearmen(),
							attackData.getArchers(),
							attackData.getHeavySwords());
		
		defender = new Army(defendData.getSpearmen(),
							defendData.getArchers(),
							defendData.getHeavySwords());
		
		int roundNumber = 1;
		
		//Max 3 rounds OR when one army is out of troops;
		while(roundNumber < 3 && (attacker.getTotal() != 0 && defender.getTotal() !=0)) {
			this.round();
			roundNumber++;
		}
		
		this.showResults();
	}
	
	private void round() {
		attacker.getSpears();
		defender.getArchers();
		
		attacker.setTotalAttack(this.makeTotalAttack(attacker));
		defender.setTotalAttack(this.makeTotalAttack(defender));
		
		attacker.setTotalDefense(this.makeTotalDefense(attacker));
		defender.setTotalDefense(this.makeTotalDefense(defender));
		
		int armyDamage1 = this.calculateDamage(attacker, defender);
		this.inflictDamage(armyDamage1, defender);
		
		int armyDamage2 = this.calculateDamage(defender, attacker);
		this.inflictDamage(armyDamage2, attacker);
		
	}
	
	private void showResults() {
		System.out.println("Attacker: " + attacker.getTotal() );
		System.out.println("Defender: " + defender.getTotal() );
		
		if(defender.getTotal() == 0) {
			System.out.println("Attacker wins!");
		} else {
			System.out.println("Defender wins!");
		}
	}
	
	private void inflictDamage(int damage, Army otherArmy) {
		Map<String, Boolean> troopsLeft = otherArmy.getRemainingTroopTypes();
		//There's probably a better way to do this...
		for(int i=0; i < damage; i++) {
			int decision = Helpers.getRandomInt(troopsLeft.size());
			if(decision == 1 && troopsLeft.get("spearmen").equals(true)) {
				otherArmy.killSpear();
			} else if(decision == 2 && troopsLeft.get("archers").equals(true)) {
				otherArmy.killArcher();
			} else if (decision == 3 && troopsLeft.get("heavySwords").equals(true)) {
				otherArmy.killHeavySwords();
			} else {
				i--;
			}
		}
	}
	
	private int calculateDamage(Army armyN1, Army armyN2) {
		int attackDamageArmy =  armyN2.getTotal() * Helpers.getRandomInt(armyN1.getTotalAttack());
		attackDamageArmy = armyN2.getTotal() - Helpers.getRandomInt(armyN2.getTotalDefense()/2);
		
		return attackDamageArmy;
	}
	
	private int makeTotalAttack(Army army) {
		Spearmen spears = new Spearmen();
		Archers archers = new Archers();
		HeavySwords heavySwords = new HeavySwords();
		
		int spearsAttack = spears.getAttack() * army.getSpears();
		int archersAttack = archers.getAttack() * army.getArchers();
		int heavySwordsAttack = heavySwords.getAttack() * army.getHeavySwords();
		
		return spearsAttack + archersAttack;
	}
	
	private int makeTotalDefense(Army army) {
		Spearmen spears = new Spearmen();
		Archers archers = new Archers();
		HeavySwords heavySwords = new HeavySwords();
		
		
		int spearsDefense = spears.getArmor() * army.getSpears();
		int archersDefense = archers.getArmor() * army.getArchers();
		int heavySwordsDefense = heavySwords.getArmor() * army.getHeavySwords();
		
		return spearsDefense + archersDefense + heavySwordsDefense;
	}
	
	public Army getAttacker() {
		return this.attacker;
	}
	
	public Army getDefender() {
		return this.defender;
	}
	
}
