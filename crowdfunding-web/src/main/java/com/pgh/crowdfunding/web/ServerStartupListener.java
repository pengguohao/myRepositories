package com.pgh.crowdfunding.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		// 将web应用名称（路径）保存到application范围中
		ServletContext servletContext = sce.getServletContext();
		String path = servletContext.getContextPath();
		servletContext.setAttribute("APP_PATH", path);
		
	}

}
