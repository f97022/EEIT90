package com.cloudSerenityHotel.service.user;

import java.util.ArrayList;
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
	
	public UserBean findUserById(int userId) {
		UserBean user = userDAO.findUserById(userId);
		return user;
	}
	public MemberBean findMemberDataById(int userId) {
		MemberBean member = memberDAO.findMemberDataById(userId);
		return member;
	}
	
	//查詢使用者資料(admin)
	//findByUserName()
	public List<UserBean> findUserDataIsAdmin(String name) {
		List<UserBean> userList = userDAO.findUserByNameIsAdmin(name);
		return userList;
	}
	public List<UserBean> findUserDataIsUser(String name) {
		List<UserBean> userList = userDAO.findUserByNameIsUser(name);
		return userList;
	}
	//findByUserId
	public List<UserBean> findUserDataIsAdmin(Integer userId) {
		UserBean user = userDAO.findUserByIdIsAdmin(userId);
		List<UserBean> users = new ArrayList<>();
		if (user != null) {
			users.add(user);
		}
		return users;
	}
	public List<UserBean> findUserDataIsUser(Integer userId) {
		UserBean user = userDAO.findUserByIdIsUser(userId);
		List<UserBean> users = new ArrayList<>();
		if (user != null) {
			users.add(user);
		}
		return users;
	}
	//findAllUser()
	public List<UserBean> findAllUserDataIsAdmin() {
		List<UserBean> userList = userDAO.findAllUserIsAdmin();
		return userList;
	}
	public List<UserBean> findAllUserDataIsUser() {
		List<UserBean> userList = userDAO.findAllUserIsUser();
		return userList;
	}
	
	//查詢會員資料(admin)
	//findByUserName
	public List<MemberBean> findMemberData(String name) {
		List<MemberBean> memberList = memberDAO.findMemberDataByName(name);
		return memberList;
	}
	//findByUserId
	public List<MemberBean> findMemberData(Integer userId) {
		MemberBean member = memberDAO.findMemberDataById(userId);
		List<MemberBean> memberDatas = new ArrayList<>();
		if (member != null) {
			memberDatas.add(member);
		}
		return memberDatas;
	}
	//findAllMember
	public List<MemberBean> findAllMemberData() {
		List<MemberBean> memberList = memberDAO.findAllMemberData();
		return memberList;
	}
	
	//修改使用者資料(admin,user)
	public int updateUserData(Integer userid,String name,String email,String password) {
		int updateUser = userDAO.updateUser(userid,name,email,password);
		if (updateUser > 0) {
			return 1;
		}else {
			return 0;
		}
	}
	//修改會員資料(admin,user)
	public int updateMemberData(MemberBean member) {
		int updateUser = userDAO.updateUser(member.getUserId(),member.getMemberName(),member.getEmail(),member.getPassword());
		int updateMember = memberDAO.updateMemberData(member);
		if (updateUser > 0) {
			if(updateMember > 0) {
				
				return 1;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}
	//註銷帳號(admin,user)
	public int deleteUser(int userId) {
		int deleteUser = userDAO.deleteUser(userId);
		if (deleteUser > 0) {
			return 1;
		}else {
			return 0;
		}
	}
	//恢復帳號(admin)
	public int recoverUser(int userId) {
		int recoverUser = userDAO.recoverUser(userId);
		if (recoverUser > 0) {
			return 1;
		}else {
			return 0;
		}
	}
	//新增管理員帳號(admin)
	public int addAdmin() {
		
		return 0;
	}
	
}
