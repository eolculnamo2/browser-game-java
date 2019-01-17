package com.game.models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.game.army.Army;


public class BattleUpdateProfiles {
	public BattleUpdateProfiles(String attackerUsername, String defenderUsername, Army attackerArmy, Army defenderArmy) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(UserProfile.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			UserProfile attackerProfile = session.get(UserProfile.class, attackerUsername);
			UserProfile defenderProfile= session.get(UserProfile.class, defenderUsername);
			
			attackerProfile.setSpearmen(Math.abs(attackerProfile.getSpearmen() - attackerArmy.getSpears()));
			attackerProfile.setArchers(Math.abs(attackerProfile.getArchers() - attackerArmy.getArchers()));
			attackerProfile.setHeavySwords(Math.abs(attackerProfile.getHeavySwords() - attackerArmy.getHeavySwords()));
			
			defenderProfile.setSpearmen(defenderArmy.getSpears());
			defenderProfile.setArchers(defenderArmy.getArchers());
			defenderProfile.setHeavySwords(defenderArmy.getHeavySwords());
			
			
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}

