package com.game.army;

public interface ArmyInterface {

	public int getTotal();
	
	//SPEARS
	public void killSpear();
	public int getSpears();
	//ARCHERS
	public void killArcher();
	public int getArchers();
	
	//HEAVYSWORDS
	public void killHeavySwords();
	public int getHeavySwords();
	
	public int getRemainingTroopTypes();
	
	public void setTotalAttack(int attack);
	
	public int getTotalAttack();
	
	public void setTotalDefense(int defense);
	
	public int getTotalDefense();
}
