package com.cloudSerenityHotel.service.user;

import org.hibernate.Session;

import com.cloudSerenityHotel.model.User;
import com.cloudSerenityHotel.model.UserDao;

public class UserService implements UserServiceInterface {

	private UserDao userDao;
	
	public UserService(Session session) {
		this.userDao = new UserDao(session);
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
