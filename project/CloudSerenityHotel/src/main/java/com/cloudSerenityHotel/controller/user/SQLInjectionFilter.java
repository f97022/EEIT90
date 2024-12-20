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
import java.util.regex.Pattern;

@WebFilter("/*") // 適用於所有請求
public class SQLInjectionFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	// 定義可能的 SQL 注入特徵 正規表達式
	private static final String[] SQL_INJECTION_PATTERNS = { "('.+--)|(--)|(%7C)", // 單引號或注釋
			"(\\bSELECT\\b|\\bINSERT\\b|\\bUPDATE\\b|\\bDELETE\\b)", // SQL 關鍵字
			"(\\bUNION\\b|\\bDROP\\b|\\b--\\b|\\bOR\\b)", // 更多關鍵字
			"(\\b1=1\\b|\\b1=\\b|\\bexec\\b|\\bexec\\b)" // 常見攻擊模式
	};

	public SQLInjectionFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; //取得http相關資料
		 HttpServletResponse httpResponse = (HttpServletResponse) response;
		boolean isSQLInjection = false;
		
        // 獲取所有請求參數
        for (String paramName : httpRequest.getParameterMap().keySet()) {
            String[] paramValues = httpRequest.getParameterValues(paramName);
            for (String value : paramValues) {
                // 檢查是否匹配 SQL 注入特徵
                if (isSQLInjectionPattern(value)) {
                    isSQLInjection = true;
                    break;
                }
            }
        }

        if (isSQLInjection) {
            // 如果檢測到 SQL 注入 設定成404錯誤
        	httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	 		request.getRequestDispatcher("/static/404.html")
			.forward(request, response);
            return; // 終止請求處理
        }
        
		chain.doFilter(request, response);
	}

    //驗證是否有sql注入字串出現 (將請求參數帶入比對是否符合SQL 注入特徵 正規表達式 ,以判斷是否為SQL 注入攻擊)
	//Pattern.compile(正規表達式,旗標) 將給定的正規表示式編譯為具有給定旗標的模式。
	//Pattern.CASE_INSENSITIVE 不分大小寫
	//Pattern.matcher() 建立一個匹配器，將給定的輸入與此模式進行匹配
	private boolean isSQLInjectionPattern(String value) { 
		for (String pattern : SQL_INJECTION_PATTERNS) {
			if (Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(value).find()) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
