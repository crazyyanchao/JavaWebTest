package com.onlinetest.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @author 丁 鹏
 *
 */
public class SessionIsAliveFilter implements Filter {
	private static Logger log = Logger.getLogger(SessionIsAliveFilter.class);
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
		String logURL = request.getRequestURI();
//		if( logURL.equals("/service/login")){
//			log.info("User Login : userId="+request.getParameter("loguserId"));
//		}else if( logURL.equals("/service/register") ){
//			log.info("User register : user="+request.getParameter("userId"));
//		}else if( logURL.equals("/service/files/uploadfile") ){
//			log.info("上传文件");
//		}else if(session.getAttribute("userId") == null){	
//			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//			try {
//				rd.forward(request, response);
//				return;
//			} catch (Exception e) {
//				log.error(e, e);
//				e.printStackTrace();
//			}
//		}
		if( logURL.equals("/OnlineTest/service/login")){
			log.info("User Login : user="+request.getParameter("loguserId"));
		}else if( logURL.equals("/OnlineTest/service/register") ){
			log.info("User register : user="+request.getParameter("userId"));
		}else if( logURL.equals("/OnlineTest/service/files/uploadfile") ){
			log.info("上传文件");
		}else if(session.getAttribute("userId") == null){	
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			try {
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				log.error(e, e);
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
