package com.cloudSerenityHotel.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.cloudSerenityHotel.bean.user.UserBean;
import com.cloudSerenityHotel.service.user.UserService;

@WebServlet("/user/addAdmin")
public class AddAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserBean user = new UserBean();
		user.setEmail(email);
		user.setPassword(password);
		user.setUserName(name);
		user.setUserIdentity("admin");
		int addStatus = userService.addAdmin(user);
		if (addStatus > 0) {
			request.getRequestDispatcher("/static/user/protected/queryAdminData.jsp").forward(request, response);
		}else {
            request.setAttribute("errorMessage", "註冊失敗，請檢查輸入的Email是否重複或稍後重試");
            request.getRequestDispatcher("/static/user/protected/addAdmin.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
