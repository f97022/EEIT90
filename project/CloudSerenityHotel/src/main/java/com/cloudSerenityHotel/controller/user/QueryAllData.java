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

@WebServlet("/user/QueryAllData")
public class QueryAllData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryAllData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		String queryTargetIdentity = request.getParameter("targetIdentity"); //身分(admin,user)
		
		if(queryTargetIdentity.equals("admin")) { //admin
			List<UserBean> dataList = userService.findAllUserDataIsAdmin();
			request.setAttribute("userData", dataList);
			request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
		}else { //user
			List<UserBean> userDataList = userService.findAllUserDataIsUser();
			List<MemberBean> memberDataList = userService.findAllMemberData();
			request.setAttribute("userData", userDataList);
			request.setAttribute("memberData", memberDataList);
			request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
