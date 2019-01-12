package com.game.models;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadUserProfile {
	
	private List<UserProfile> userProfile;
	public ReadUserProfile(String username, int password) {
	SessionFactory factory = new Configuration()
							.configure()
							.addAnnotatedClass(UserProfile.class)
							.buildSessionFactory();
	
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			userProfile = session.createQuery("from Chart x where x.username="+username+"AND x.password="+password).getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
}