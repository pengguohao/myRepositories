package com.pgh.crowdfunding.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pgh.crowdfunding.pojo.User;

public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session=request.getSession();
		User loginUser=(User) session.getAttribute("loginUser");
		if(null!=loginUser) {
			return true;
		}else {
			String path=session.getServletContext().getContextPath();
			response.sendRedirect(path+"/login");
			return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
