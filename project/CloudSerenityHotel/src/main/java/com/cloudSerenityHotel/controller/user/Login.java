package com.cloudSerenityHotel.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.dao.user.UserDAO;
import com.cloudSerenityHotel.service.user.UserService;

@WebServlet("/user/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//service
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		HttpSession session = request.getSession();
 		String email = request.getParameter("email");
 		String password = request.getParameter("password");
 		
 		UserBean user = userService.login(email, password);
 		if (user != null) { //判斷帳號是否存在
 			String status = user.getUserStatus();
 			if (status.equals("Logged_out")) { //判斷帳號是否已註銷
 		 		request.setAttribute("errorMessage", " 該帳號已註銷，有問題請詢問客服!");
 		 		request.getRequestDispatcher("/static/login.jsp")
 				.forward(request, response);
 			}else if (status.equals("In_use")) {
 				session.setAttribute("identity",user.getUserIdentity());
 		 		request.getRequestDispatcher("/static/main.jsp")
 				.forward(request, response);
			}else {
		 		request.setAttribute("errorMessage", " 該帳號出現問題，請詢問客服!");
		 		request.getRequestDispatcher("/static/login.jsp")
				.forward(request, response);
			}
		}else {
	 		request.setAttribute("errorMessage", " 錯誤的Email或密碼");
	 		request.getRequestDispatcher("/static/login.jsp")
			.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
