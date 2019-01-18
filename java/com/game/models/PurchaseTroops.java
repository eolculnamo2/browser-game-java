package com.game.models;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class PurchaseTroops {
	private UserProfile userProfilePurchase;
	public PurchaseTroops(String username, Map<String, Integer> purchasedTroops) {
	SessionFactory factory = new Configuration()
							.configure()
							.addAnnotatedClass(UserProfile.class)
							.buildSessionFactory();
	
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			userProfilePurchase = session.get(UserProfile.class, username);
			
			//Checks to make sure enough resources exist for purchase
			if(userProfilePurchase.getSilver() >= purchasedTroops.get("silverCost") &&
			   userProfilePurchase.getSteel() >= purchasedTroops.get("teelCost") &&
			   userProfilePurchase.getWood() >= purchasedTroops.get("woodCost")) {
				
				userProfilePurchase.setSpearmen(userProfilePurchase.getSpearmen() + purchasedTroops.get("spearmen"));
				userProfilePurchase.setArchers(userProfilePurchase.getArchers() + purchasedTroops.get("archers"));
				userProfilePurchase.setHeavySwords(userProfilePurchase.getHeavySwords() + purchasedTroops.get("heavySwords"));
				
				userProfilePurchase.setSilver(userProfilePurchase.getSilver() - purchasedTroops.get("silverCost"));
				userProfilePurchase.setSteel(userProfilePurchase.getSteel() - purchasedTroops.get("steelCost"));
				userProfilePurchase.setWood(userProfilePurchase.getWood() - purchasedTroops.get("woodCost"));
				
			} else {
				System.out.println("Not enough resources");
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
			factory.close();
		}
	}
	
	public UserProfile getUserProfilePurchase() {
		return this.userProfilePurchase;
	}
}
