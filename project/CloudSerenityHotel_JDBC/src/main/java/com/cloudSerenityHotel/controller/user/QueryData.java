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

@WebServlet("/user/QueryData")
public class QueryData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		String queryConditions = request.getParameter("conditions"); //條件(userId,userName)
		String queryTargetIdentity = request.getParameter("targetIdentity"); //身分(admin,user)
		String queryKeyword = request.getParameter("keyword"); //關鍵字
		System.out.println(queryConditions+" "+ queryTargetIdentity + " " +queryKeyword);
		//先判條件 再判身分
		if (queryConditions.equals("userId")) { //userId
			if(queryTargetIdentity.equals("admin")) { //admin
				List<UserBean> data = userService.findUserDataIsAdmin(Integer.parseInt(queryKeyword));
				request.setAttribute("userData", data);
				request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
			}else { //user
				List<MemberBean> memberData = userService.findMemberData(Integer.parseInt(queryKeyword));
				request.setAttribute("memberData", memberData);
				request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
			}
		}else { //userName
			if(queryTargetIdentity.equals("admin")) { //admin
				List<UserBean> dataList = userService.findUserDataIsAdmin(queryKeyword);
				request.setAttribute("userData", dataList);
				request.getRequestDispatcher("/static/user/protected/queryResultsAdmin.jsp").forward(request, response);
			}else { //user
				List<MemberBean> memberDataList = userService.findMemberData(queryKeyword);
				request.setAttribute("memberData", memberDataList);
				request.getRequestDispatcher("/static/user/protected/queryResultsMember.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
