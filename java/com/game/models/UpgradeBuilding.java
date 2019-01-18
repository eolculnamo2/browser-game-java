package com.game.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.game.buildings.LoggingCamps;
import com.game.buildings.SteelFoundry;
import com.game.buildings.TaxableEconomy;

public class UpgradeBuilding {
	Boolean canAfford = true;
	
	public UpgradeBuilding(String username, String building) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(UserProfile.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			UserProfile user = session.get(UserProfile.class, username);
			
			int cost;
			
			switch(building) {
				case "wood":
					cost = new LoggingCamps(user.getWoodLevel()).getUpgradeCost();
					if(user.getWood() >= cost) {
						user.setWood(user.getWood() - cost);
						user.setWoodLevel(user.getWoodLevel()+1);
					} else {
						this.canAfford = false;
					}
			
					break;
					
				case "steel":
					cost = new SteelFoundry(user.getSteelLevel()).getUpgradeCost();
					if(user.getSteel() >= cost) {
						user.setSteel(user.getSteel() - cost);
						user.setSteelLevel(user.getSteelLevel()+1);
					} else {
						this.canAfford = false;
					}
					
					break;
				case "silver":
					cost = new TaxableEconomy(user.getSilverLevel()).getUpgradeCost();
					if(user.getSilver() >= cost) {
						user.setSilver(user.getSilver() - cost);
						user.setSilverLevel(user.getSilverLevel()+1);
					} else {
						this.canAfford = false;
					}
					
					break;
			}
			
			
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			session.close();
			factory.close();
		}
	}
	public Boolean getCanAfford() {
		return canAfford;
	}
}