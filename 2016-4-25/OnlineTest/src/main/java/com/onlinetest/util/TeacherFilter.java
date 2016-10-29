package com.onlinetest.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @author 丁 鹏
 *
 */
public class TeacherFilter implements Filter {
	private static Logger log = Logger.getLogger(TeacherFilter.class);
	FilterConfig filterConfig = null;
	
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		if(session.getAttribute("role") == null){
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			try {
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				log.error(e, e);
				e.printStackTrace();
			}
		}else{
			if( !session.getAttribute("role").equals("教师") ){
				String role = session.getAttribute("role").toString();
				Cookie DenyMsg = new Cookie("DenyMsg", "denied");
				DenyMsg.setPath("/");
				response.addCookie(DenyMsg);
//				if(role.equals("学生")){
//					response.sendRedirect("/service/student/home");
//				}else if(role.equals("管理员") || role.equals("超级管理员")){
//					response.sendRedirect("/service/admin/home");
//				}else{
//					response.sendRedirect("/");
//				}
				if(role.equals("学生")){
					response.sendRedirect("/OnlineTest/service/student/home");
				}else if(role.equals("管理员") || role.equals("超级管理员")){
					response.sendRedirect("/OnlineTest/service/admin/home");
				}else{
					response.sendRedirect("/OnlineTest/");
				}
				log.info("IsTeacherFilter: the user("+session.getAttribute("userId")+") is not teacher ,access denied!");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
