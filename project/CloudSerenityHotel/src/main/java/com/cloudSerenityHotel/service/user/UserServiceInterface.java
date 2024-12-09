package com.cloudSerenityHotel.service.user;

import com.cloudSerenityHotel.model.User;

public interface UserServiceInterface {

	User login(String email,String password);
}
