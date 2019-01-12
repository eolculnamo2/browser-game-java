package com.game.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateUserProfile {
	
		public CreateUserProfile(int id, String username, String password, String lastLogin, int powerRating, String email,
				   int spearmen, int archers, int heavySwords, int silver, int wood, int steel) {

			SessionFactory factory = new Configuration()
			.configure()
			.addAnnotatedClass(UserProfile.class)
			.buildSessionFactory();
			
			Session session = factory.getCurrentSession();
	
		try {
			UserProfile profile = new UserProfile(id, username, password, 
					                               lastLogin, powerRating, email,
					                               spearmen, archers, heavySwords, 
					                               silver, wood, steel);
			session.beginTransaction();
			session.save(profile);
			session.getTransaction().commit();
			System.out.println(profile.getUsername());
			System.out.println("SAVED");
		}
		finally {
			factory.close();
		}
	}
}
