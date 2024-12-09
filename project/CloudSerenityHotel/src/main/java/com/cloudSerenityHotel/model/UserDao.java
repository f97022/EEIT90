package com.cloudSerenityHotel.model;

import org.hibernate.Session;

public class UserDao {

	private Session session;
	
	public UserDao(Session session) {
		this.session = session;
	}
	
	
	
}
