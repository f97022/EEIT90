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
	private static final String INSERT = "INSERT INTO users(email,password,user_status,user_identity,update_time) VALUES(?,?,'In_use',?,?)";
	private static final String DELETE = "UPDATE users SET user_status = 'Logged_out',update_time=? WHERE userid =?";
	private static final String RECOVER = "UPDATE users SET user_status = 'In_use',update_time=? WHERE userid =?";
	private static final String UPDATE_EMAIL = "UPDATE users SET email=?,update_time=? WHERE userid =?";
	private static final String UPDATE_PASSWORD = "UPDATE users SET password=?,update_time=? WHERE userid =?";
	private static final String GETONE_ID = "SELECT * FROM users WHERE userid =?";
	private static final String GETONE_EMAIL = "SELECT * FROM users WHERE email =?";
	private static final String GETALL = "SELECT * FROM users";
	getTimeUtils getTime = new getTimeUtils();
	
	public int addUser(UserBean bean) {
		int insertCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1,bean.getEmail());
			stmt.setString(2,bean.getPassword());
			stmt.setString(3,bean.getUserIdentity());
			stmt.setTimestamp(4,getTime.getNowTime());
			insertCount = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return deleteCount;
	}
	public int recoverUser(Integer userid) {
		int deleteCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(RECOVER);
			stmt.setInt(2,userid);
			stmt.setTimestamp(1,getTime.getNowTime());
			deleteCount = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return deleteCount;
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
			// TODO: handle exception
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
			// TODO: handle exception
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
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time"));
			}
		} catch (Exception e) {
			// TODO: handle exception
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
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return user;
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
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserStatus(rs.getString("user_status"));
				user.setUserIdentity(rs.getString("user_identity"));
				user.setUpdateTime(rs.getTimestamp("update_time"));
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
