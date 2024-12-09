package com.cloudSerenityHotel.controller.user;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter("/user/*")
public class EncodingFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = 1L;
	
	private String encoding = "UTF-8";
	
	public EncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	    // 確保請求是 HTTP 的請求
	    if (request.getContentType() == null || request.getContentType().contains("text/html")) {
	        // 僅針對 HTML 設置編碼
	        request.setCharacterEncoding(encoding);
	        response.setCharacterEncoding(encoding);
	    }
	    
	    // 將請求傳遞到下一個過濾器或目標資源
	    chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
