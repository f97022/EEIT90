package com.cloudSerenityHotel.service.user;

import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.dao.user.MemberDAO;
import com.cloudSerenityHotel.dao.user.UserDAO;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private MemberDAO memberDAO = new MemberDAO();
	
	//登入
	public UserBean login(String email,String password) {
		UserBean user = userDAO.findUserByEmail(email);
		//判斷帳號是否存在
		if (user != null) {//存在的話 就確認帳號狀態
			String truePassword = user.getPassword();
			if (truePassword.equals(password)) { //判斷密碼是否正確
				return user;
			}else {
				return null;
			}
			
		}else { //不存在就回傳null
			
			return null;
		}
	}
}
