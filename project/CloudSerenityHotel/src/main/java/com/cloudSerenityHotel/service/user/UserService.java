package com.cloudSerenityHotel.service.user;

import java.util.List;

import com.cloudSerenityHotel.bean.user.MemberBean;
import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.dao.user.MemberDAO;
import com.cloudSerenityHotel.dao.user.UserDAO;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private MemberDAO memberDAO = new MemberDAO();

	// 登入
	public UserBean login(String email, String password) {
		UserBean user = userDAO.findUserByEmail(email);
		// 判斷帳號是否存在
		if (user != null) {// 存在
			String truePassword = user.getPassword();
			if (truePassword.equals(password)) { // 判斷密碼是否正確
				return user;
			} else {
				return null;
			}

		} else { // 不存在就回傳null

			return null;
		}
	}

	// 註冊
	public int register(UserBean user, MemberBean member) {
		// 判斷Email 是否重複
		String email = user.getEmail();
		UserBean checkEmail = userDAO.findUserByEmail(email);
		if (checkEmail == null) {
			int addUser = userDAO.addUser(user);
			if (addUser > 0) {
				UserBean getUser = userDAO.findUserByEmail(user.getEmail());
				int getUserId = getUser.getUserId();
				member.setUserId(getUserId);
				int addmember = memberDAO.addMemberData(member);
				if (addmember > 0) {
					return 1;
				} else {
					// 如果會員資料新增失敗 使用者同時移除資料 避免使用者資料重複 導致註冊失敗
					userDAO.removeUser(getUserId);
					return 0;
				}
			}
		}
		return 0;
	}
	
	//查詢使用者資料(admin)
	//findByUserName()
	public List<UserBean> findUserData(String name) {
		return null;
	}
	//findByUserId
	public UserBean findUserData(Integer userId) {
		return null;
	}
	//查詢會員資料(admin)
	//findByUserName
	public List<MemberBean> findMemberData(String name) {
		return null;
	}
	//findByUserId
	public MemberBean findMemberData(Integer userId) {
		return null;
	}
	
	
	//修改使用者資料(admin,user)
	public int updateUserData(UserBean user) {
		return 0;
	}
	//修改會員資料(admin,user)
	public int updateMemberData(MemberBean member) {
		return 0;
	}
	//註銷帳號(admin,user)
	public int deleteUser() {
		
		return 0;
	}
	//恢復帳號(admin)
	public int recoverUser() {
		
		return 0;
	}
	//新增管理員帳號(admin)
	public int addAdmin() {
		
		return 0;
	}
	
}
