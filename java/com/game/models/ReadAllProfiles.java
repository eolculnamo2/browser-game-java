package com.game.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadAllProfiles {
	private List<UserProfile> userProfiles;
	public ReadAllProfiles() {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(UserProfile.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			userProfiles = session.createQuery("from UserProfile").getResultList();
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			session.close();
			factory.close();
		}
	}
	
	public List<UserProfile> getUserProfiles() {
		return this.userProfiles;
	}
}