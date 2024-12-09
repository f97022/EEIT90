package com.cloudSerenityHotel.dao.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cloudSerenityHotel.bean.user.MemberBean;
import com.cloudSerenityHotel.utils.JDBCUtils;
import com.cloudSerenityHotel.utils.getTimeUtils;

public class MemberDAO {
	private static final String INSERT = "INSERT INTO members"
			+ "(userid,member_name,gender,birthday,phone,personal_id_no,country,address,passport_no,register_date,update_time)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE members SET "
			+ "member_name=?,gender=?,birthday=?,phone=?,personal_id_no=?,country=?,address=?,passport_no=?,update_time=?"
			+ " WHERE userid =?";
	private static final String GETONE_ID = "SELECT "
			+"m.userid, u.email,u.password,u.update_time AS acc_update_time, u.user_status,m.member_name,m.gender,m.birthday,m.phone,m.personal_id_no,m.country,m.address,m.passport_no,m.register_date,m.update_time AS data_update_time"+
			" FROM users u JOIN members m ON u.userid = m.userid WHERE u.userid =?";
	private static final String GETALL_NAME = "SELECT "
			+"m.userid, u.email,u.password,u.update_time AS acc_update_time, u.user_status,m.member_name,m.gender,m.birthday,m.phone,m.personal_id_no,m.country,m.address,m.passport_no,m.register_date,m.update_time AS data_update_time"+ 
			" FROM users u JOIN members m ON u.userid = m.userid WHERE m.member_name LIKE ?";
	private static final String GETALL = "SELECT "
			+"m.userid, u.email,u.password,u.update_time AS acc_update_time, u.user_status,m.member_name,m.gender,m.birthday,m.phone,m.personal_id_no,m.country,m.address,m.passport_no,m.register_date,m.update_time AS data_update_time"+ 
			" FROM users u JOIN members m ON u.userid = m.userid";
	getTimeUtils getTime = new getTimeUtils();
	
	public int addMemberData(MemberBean bean) {
		int insertCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1,bean.getUserId());
			stmt.setString(2,bean.getMemberName());
			stmt.setString(3,bean.getGender());
			stmt.setDate(4,java.sql.Date.valueOf(bean.getBirthday()));
			stmt.setString(5,bean.getPhone());
			stmt.setString(6,bean.getPersonalIdNo());
			stmt.setString(7,bean.getCountry());
			stmt.setString(8,bean.getAddress());
			stmt.setString(9,bean.getPassportNo());
			stmt.setTimestamp(10,getTime.getNowTime());
			stmt.setTimestamp(11,getTime.getNowTime());
			insertCount = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return insertCount;
	};
	
	public int updateMemberData(MemberBean bean) {
		int updateCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1,bean.getMemberName());
			stmt.setString(2,bean.getGender());
			stmt.setDate(3,java.sql.Date.valueOf(bean.getBirthday()));
			stmt.setString(4,bean.getPhone());
			stmt.setString(5,bean.getPersonalIdNo());
			stmt.setString(6,bean.getCountry());
			stmt.setString(7,bean.getAddress());
			stmt.setString(8,bean.getPassportNo());
			stmt.setTimestamp(9,getTime.getNowTime());
			stmt.setInt(10,bean.getUserId());
			updateCount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCUtils.closeResource(conn, stmt, null);
		}
		return updateCount;
	}
	
	public MemberBean findMemberDataById(Integer userid) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MemberBean memberData = null;
		try {
			stmt = conn.prepareStatement(GETONE_ID);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				memberData = new MemberBean();
				memberData.setUserId(rs.getInt("userid"));
				memberData.setMemberName(rs.getString("member_name"));
				memberData.setEmail(rs.getString("email"));
				memberData.setPassword(rs.getString("password"));
				memberData.setAccountUpdateTime(rs.getTimestamp("acc_update_time").toLocalDateTime());
				memberData.setUserStatus(rs.getString("user_status"));
				memberData.setGender(rs.getString("gender"));
				memberData.setBirthday(rs.getDate("birthday").toLocalDate());
				memberData.setPhone(rs.getString("phone"));
				memberData.setPersonalIdNo(rs.getString("personal_id_no"));
				memberData.setCountry(rs.getString("country"));
				memberData.setAddress(rs.getString("address"));
				memberData.setPassportNo(rs.getString("passport_no"));
				memberData.setRegisterDate(rs.getTimestamp("register_date").toLocalDateTime());
				memberData.setDataUpdateTime(rs.getTimestamp("data_update_time").toLocalDateTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return memberData;
	}
	public List<MemberBean> findMemberDataByName(String memberName) {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MemberBean> memberDatas = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETALL_NAME);
			stmt.setString(1,"%" + memberName + "%");
			rs = stmt.executeQuery();
			MemberBean memberData = null;
			while(rs.next()) {
				memberData = new MemberBean();
				memberData.setUserId(rs.getInt("userid"));
				memberData.setMemberName(rs.getString("member_name"));
				memberData.setEmail(rs.getString("email"));
				memberData.setPassword(rs.getString("password"));
				memberData.setAccountUpdateTime(rs.getTimestamp("acc_update_time").toLocalDateTime());
				memberData.setUserStatus(rs.getString("user_status"));
				memberData.setGender(rs.getString("gender"));
				memberData.setBirthday(rs.getDate("birthday").toLocalDate());
				memberData.setPhone(rs.getString("phone"));
				memberData.setPersonalIdNo(rs.getString("personal_id_no"));
				memberData.setCountry(rs.getString("country"));
				memberData.setAddress(rs.getString("address"));
				memberData.setPassportNo(rs.getString("passport_no"));
				memberData.setRegisterDate(rs.getTimestamp("register_date").toLocalDateTime());
				memberData.setDataUpdateTime(rs.getTimestamp("data_update_time").toLocalDateTime());
				memberDatas.add(memberData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return memberDatas;
	}
	public List<MemberBean> findAllMemberData() {
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MemberBean> memberDatas = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(GETALL);
			rs = stmt.executeQuery();
			MemberBean memberData = null;
			while(rs.next()) {
				memberData = new MemberBean();
				memberData.setUserId(rs.getInt("userid"));
				memberData.setMemberName(rs.getString("member_name"));
				memberData.setEmail(rs.getString("email"));
				memberData.setPassword(rs.getString("password"));
				memberData.setAccountUpdateTime(rs.getTimestamp("acc_update_time").toLocalDateTime());
				memberData.setUserStatus(rs.getString("user_status"));
				memberData.setGender(rs.getString("gender"));
				memberData.setBirthday(rs.getDate("birthday").toLocalDate());
				memberData.setPhone(rs.getString("phone"));
				memberData.setPersonalIdNo(rs.getString("personal_id_no"));
				memberData.setCountry(rs.getString("country"));
				memberData.setAddress(rs.getString("address"));
				memberData.setPassportNo(rs.getString("passport_no"));
				memberData.setRegisterDate(rs.getTimestamp("register_date").toLocalDateTime());
				memberData.setDataUpdateTime(rs.getTimestamp("data_update_time").toLocalDateTime());
				memberDatas.add(memberData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return memberDatas;
	}
}
