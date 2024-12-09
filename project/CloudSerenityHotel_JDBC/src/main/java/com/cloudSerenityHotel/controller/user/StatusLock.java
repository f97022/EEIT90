package com.cloudSerenityHotel.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.cloudSerenityHotel.bean.user.MemberBean;
import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.service.user.UserService;

@WebServlet("/user/statusLock")
public class StatusLock extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StatusLock() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		String userId = request.getParameter("userId");
		String action = request.getParameter("action");
		String identity = request.getParameter("identity");
		System.out.println(userId+" "+ action + " " +identity);
		if (action.equals("delacc")) {//delete字詞會被過濾掉
			int del = userService.deleteUser(Integer.parseInt(userId));
			if (del > 0) {
				if(identity.equals("admin")) { //admin
					List<UserBean> data = userService.findUserDataIsAdmin(Integer.parseInt(userId));
					request.setAttribute("userData", data);
					request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
				}else { //user
					List<MemberBean> memberData = userService.findMemberData(Integer.parseInt(userId));
					request.setAttribute("memberData", memberData);
					request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("errorMessage", "註銷使用者帳號");
				request.getRequestDispatcher("/static/user/protected/operationFailed.jsp").forward(request, response);
			}
		} else { // recover
			int rec = userService.recoverUser(Integer.parseInt(userId));
			if (rec > 0) {
				if(identity.equals("admin")) { //admin
					List<UserBean> data = userService.findUserDataIsAdmin(Integer.parseInt(userId));
					request.setAttribute("userData", data);
					request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
				}else { //user
					List<MemberBean> memberData = userService.findMemberData(Integer.parseInt(userId));
					request.setAttribute("memberData", memberData);
					request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("errorMessage", "恢復使用者帳號");
				request.getRequestDispatcher("/static/user/protected/operationFailed.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
