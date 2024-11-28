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
		int update = userService.updateUserData(userId,name,email,password);
		if (update > 0) {
			if(identity.equals("admin")) { //admin
				List<UserBean> data = userService.findUserDataIsAdmin(userId);
				request.setAttribute("userData", data);
				request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
			}else { //user
				List<MemberBean> memberData = userService.findMemberData(userId);
				request.setAttribute("memberData", memberData);
				request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("errorMessage", "更新使用者帳號");
			request.getRequestDispatcher("/static/user/protected/operationFailed.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
