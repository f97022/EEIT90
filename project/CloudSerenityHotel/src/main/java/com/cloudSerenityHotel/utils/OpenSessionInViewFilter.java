package com.cloudSerenityHotel.utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebFilter(urlPatterns = "/*")
public class OpenSessionInViewFilter extends HttpFilter implements Filter {
       
	private Session session;
	
	public OpenSessionInViewFilter() {
        super();

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			
			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			System.out.println("begin transaction!!");
			
			chain.doFilter(request, response);
			
			session.getTransaction().commit();
			System.out.println("commit transaction!!!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("rollback transaction!!!");
			e.printStackTrace();
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
