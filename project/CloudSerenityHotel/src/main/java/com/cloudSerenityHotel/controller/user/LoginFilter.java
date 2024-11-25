package com.cloudSerenityHotel.controller.user;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/user/*")
public class LoginFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 強制轉換為 HttpServletRequest 和 HttpServletResponse 因為要取session
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 獲取當前請求的 Session
        String user = (String) httpRequest.getSession().getAttribute("identity");

        // 定義需要身份驗證的路徑
        String requestURI = httpRequest.getRequestURI();
        boolean isProtectedResource = requestURI.startsWith("/user/protected");

        if (isProtectedResource && user == null) {
            // 用戶未登入，重定向到登入頁面
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }
        
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
