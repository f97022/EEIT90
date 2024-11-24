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
			+ "(member_name,gender,birthday,phone,personal_id_no,country,address,passport_no,register_date,update_time)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE members SET "
			+ "member_name=?,gender=?,birthday=?,phone=?,personal_id_no=?,country=?,address=?,passport_no=?,update_time=?"
			+ " WHERE userid =?";
	private static final String GETONE = "SELECT * FROM members WHERE userid =?";
	private static final String GETALL = "SELECT * FROM members";
	getTimeUtils getTime = new getTimeUtils();
	
	public int addMemberData(MemberBean bean) {
		int insertCount = 0;
		Connection conn =JDBCUtils.getConnection();
		PreparedStatement stmt =null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1,bean.getMemberName());
			stmt.setString(2,bean.getGender());
			stmt.setDate(3,(Date) bean.getBirthday());
			stmt.setString(4,bean.getPhone());
			stmt.setString(5,bean.getPersonalIdNo());
			stmt.setString(6,bean.getCountry());
			stmt.setString(7,bean.getAddress());
			stmt.setString(8,bean.getPassportNo());
			stmt.setTimestamp(9,getTime.getNowTime());
			stmt.setTimestamp(10,getTime.getNowTime());
			insertCount = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			stmt.setDate(3,(Date) bean.getBirthday());
			stmt.setString(4,bean.getPhone());
			stmt.setString(5,bean.getPersonalIdNo());
			stmt.setString(6,bean.getCountry());
			stmt.setString(7,bean.getAddress());
			stmt.setString(8,bean.getPassportNo());
			stmt.setTimestamp(9,getTime.getNowTime());
			stmt.setInt(10,bean.getUserId());
			updateCount = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
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
			stmt = conn.prepareStatement(GETONE);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				memberData = new MemberBean();
				memberData.setUserId(rs.getInt("userid"));
				memberData.setMemberName(rs.getString("member_name"));
				memberData.setGender(rs.getString("gender"));
				memberData.setBirthday(rs.getDate("birthday"));
				memberData.setPhone(rs.getString("phone"));
				memberData.setPersonalIdNo(rs.getString("personal_id_no"));
				memberData.setCountry(rs.getString("country"));
				memberData.setAddress(rs.getString("address"));
				memberData.setPassportNo(rs.getString("passport_no"));
				memberData.setRegisterDate(rs.getTimestamp("register_date"));
				memberData.setUpdateTime(rs.getTimestamp("update_time"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return memberData;
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
				memberData.setGender(rs.getString("gender"));
				memberData.setBirthday(rs.getDate("birthday"));
				memberData.setPhone(rs.getString("phone"));
				memberData.setPersonalIdNo(rs.getString("personal_id_no"));
				memberData.setCountry(rs.getString("country"));
				memberData.setAddress(rs.getString("address"));
				memberData.setPassportNo(rs.getString("passport_no"));
				memberData.setRegisterDate(rs.getTimestamp("register_date"));
				memberData.setUpdateTime(rs.getTimestamp("update_time"));
				memberDatas.add(memberData);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return memberDatas;
	}
}