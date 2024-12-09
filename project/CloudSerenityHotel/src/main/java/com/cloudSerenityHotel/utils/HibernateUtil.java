package com.cloudSerenityHotel.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	//2
	private static SessionFactory factory = createSessionFactory();
	
	//1
	private static SessionFactory createSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		
		return factory;
	}
	
	//3
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	//4
	public static void colseSessionFactory() {
		if (factory != null) {
			factory.close();
		}
	}
	
}
