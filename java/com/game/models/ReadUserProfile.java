package com.game.models;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadUserProfile {
	
	private UserProfile userProfile;
	public ReadUserProfile(String username) {
	SessionFactory factory = new Configuration()
							.configure()
							.addAnnotatedClass(UserProfile.class)
							.buildSessionFactory();
	
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			List<UserProfile>queryFind = session.createQuery("from UserProfile x where x.username='"+username+"'").getResultList();
			userProfile = queryFind.get(0);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			factory.close();
		}
	}
	
	public UserProfile getUserProfile() {
		return this.userProfile;
	}
}