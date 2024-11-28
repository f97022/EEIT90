package com.cloudSerenityHotel.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.cloudSerenityHotel.bean.user.MemberBean;
import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.service.user.UserService;

@WebServlet("/user/updateData")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String identity = request.getParameter("identity");
		
		if(identity.equals("admin")) { //admin
			int update = userService.updateUserData(userId,name,email,password);
			if (update > 0) {
				List<UserBean> data = userService.findUserDataIsAdmin(userId);
				request.setAttribute("userData", data);
				request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
			}else {
				request.setAttribute("errorMessage", "更新使用者帳號");
				request.getRequestDispatcher("/static/user/protected/operationFailed.jsp").forward(request, response);
			}

		}else { //user
			String gender = request.getParameter("gender");
			String birthdayStr = request.getParameter("birthday");
			String phone = request.getParameter("phone");
			String personalIdNo = request.getParameter("personalIdNo");
			String country = request.getParameter("country");
			String address = request.getParameter("address");
			String passportNo = request.getParameter("passportNo");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthday = LocalDate.parse(birthdayStr , formatter);
			
			MemberBean member = new MemberBean();
			member.setUserId(userId);
			member.setMemberName(name);
			member.setEmail(email);
			member.setPassword(password);
			member.setGender(gender);
			member.setBirthday(birthday);
			member.setPhone(phone);
			member.setPersonalIdNo(personalIdNo);
			member.setCountry(country);
			member.setAddress(address);
			member.setPassportNo(passportNo);
			
			int update = userService.updateMemberData(member);
			if (update > 0) {
				List<MemberBean> memberData = userService.findMemberData(userId);
				request.setAttribute("memberData", memberData);
				request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
			}else {
				request.setAttribute("errorMessage", "更新使用者帳號");
				request.getRequestDispatcher("/static/user/protected/operationFailed.jsp").forward(request, response);
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
