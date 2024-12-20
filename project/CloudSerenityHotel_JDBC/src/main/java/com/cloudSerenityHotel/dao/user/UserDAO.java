package com.cloudSerenityHotel.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.utils.JDBCUtils;
import com.cloudSerenityHotel.utils.getTimeUtils;

public class UserDAO {
	private static final String INSERT = "INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES(?,?,?,'In_use',?,?)";
	private static final String DELETE = "UPDATE users SET user_status = 'Logged_out',update_time=? WHERE userid =?";
	private static final String RECOVER = "UPDATE users SET user_status = 'In_use',update_time=? WHERE userid =?";
	private static final String REMOVE = " DELETE FROM users WHERE userid =?";
	private static final String UPDATE_ALL = "UPDATE users SET user_name=?,email=?,password=?,update_time=? WHERE userid =?";
	private static final String UPDATE_NAME = "UPDATE users SET user_name=?,update_time=? WHERE userid =?";
	private static final String UPDATE_EMAIL = "UPDATE users SET email=?,update_time=? WHERE userid =?";
	private static final String UPDATE_PASSWORD = "UPDATE users SET password=?,update_time=? WHERE userid =?";
	private static final String GETONE_ID = "SELECT * FROM users WHERE userid =?";
	private static final String GETONE_ID_ADMIN = "SELECT * FROM users WHERE userid =? AND user_identity = 'admin'";
	private static final String GETONE_ID_USER = "SELECT * FROM users WHERE userid =? AND user_identity = 'user'";
	private static final String GETONE_EMAIL = "SELECT * FROM users WHERE email =?";
	private static final String GETONE_NAME_ADMIN = "SELECT * FROM users WHERE user_name LIKE ? AND user_identity = 'admin'";
	private static final String GETONE_NAME_USER = "SELECT * FROM users WHERE user_name LIKE ? AND user_identity = 'user'";
	private static final String GETALL = "SELECT * FROM users";
	private static final String GETALL_ADMIN = "SELECT * FROM users WHERE user_identity = 'admin'";
	private static final String GETALL_USER = "SELECT * FROM users WHERE user_identity = 'user'";
	getTimeUtils getTime = new getTimeUtils();
	
	public int addUser(UserBean bean) {
		int insertCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1,bean.getUserName());
			stmt.setString(2,bean.getEmail());
			stmt.setString(3,bean.getPassword());
			stmt.setString(4,bean.getUserIdentity());
			stmt.setTimestamp(5,getTime.getNowTime());
			insertCount = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return insertCount;
	};
	
	public int deleteUser(Integer userid) {
		int deleteCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(2,userid);
			stmt.setTimestamp(1,getTime.getNowTime());
			deleteCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return deleteCount;
	}
	public int recoverUser(Integer userid) {
		int recoverCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(RECOVER);
			stmt.setInt(2,userid);
			stmt.setTimestamp(1,getTime.getNowTime());
			recoverCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return recoverCount;
	}
	public int removeUser(Integer userid) {
		int removeCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(REMOVE);
			stmt.setInt(1,userid);
			removeCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return removeCount;
	}
	public int updateUser(Integer userid,String name,String email,String password) {
		int updateCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(UPDATE_ALL);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.setInt(5,userid);
			stmt.setTimestamp(4,getTime.getNowTime());
			updateCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return updateCount;
	}
	public int updateUserName(Integer userid,String name) {
		int updateCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(UPDATE_NAME);
			stmt.setString(1, name);
			stmt.setInt(3,userid);
			stmt.setTimestamp(2,getTime.getNowTime());
			updateCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return updateCount;
	}
	public int updateUserEmail(Integer userid,String email) {
		int updateCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(UPDATE_EMAIL);
			stmt.setString(1, email);
			stmt.setInt(3,userid);
			stmt.setTimestamp(2,getTime.getNowTime());
			updateCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return updateCount;
	}
	public int updateUserPassword(Integer userid,String password) {
		int updateCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(UPDATE_PASSWORD);
			stmt.setString(1, password);
			stmt.setInt(3,userid);
			stmt.setTimestamp(2,getTime.getNowTime());
			updateCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return updateCount;
	}
	public UserBean findUserById(Integer userid) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserBean user = null;
		try {
			stmt = conn.prepareStatement(GETONE_ID);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return user;
	}
	public UserBean findUserByIdIsAdmin(Integer userid) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserBean user = null;
		try {
			stmt = conn.prepareStatement(GETONE_ID_ADMIN);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return user;
	}
	public UserBean findUserByIdIsUser(Integer userid) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserBean user = null;
		try {
			stmt = conn.prepareStatement(GETONE_ID_USER);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return user;
	}
	public UserBean findUserByEmail(String email) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserBean user = null;
		try {
			stmt = conn.prepareStatement(GETONE_EMAIL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return user;
	}
	public List<UserBean> findUserByNameIsAdmin(String userName) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserBean> users = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETONE_NAME_ADMIN);
			stmt.setString(1,"%" + userName + "%");
			rs = stmt.executeQuery();
			UserBean user = null;
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return users;
	}
	public List<UserBean> findUserByNameIsUser(String userName) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserBean> users = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETONE_NAME_USER);
			stmt.setString(1,"%" + userName + "%");
			rs = stmt.executeQuery();
			UserBean user = null;
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return users;
	}
	public List<UserBean> findAllUser() {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserBean> users = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETALL);
			rs = stmt.executeQuery();
			UserBean user = null;
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return users;
	}
	public List<UserBean> findAllUserIsAdmin() {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserBean> users = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETALL_ADMIN);
			rs = stmt.executeQuery();
			UserBean user = null;
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return users;
	}
	public List<UserBean> findAllUserIsUser() {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserBean> users = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETALL_USER);
			rs = stmt.executeQuery();
			UserBean user = null;
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return users;
	}
}
