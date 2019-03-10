package com.pgh.crowdfunding.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pgh.crowdfunding.pojo.Menu;
import com.pgh.crowdfunding.pojo.User;
import com.pgh.crowdfunding.service.MenuService;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private MenuService menuServiceImpl;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//��ȡ�û�����ĵ�ַ
		String uri=request.getRequestURI();
		String path=request.getServletContext().getContextPath();
		//����·����uri
		String noPath=uri.substring(path.length());
		//�жϵ�ǰ·���Ƿ���Ҫ����Ȩ����֤
		//��ѯ������Ҫ��֤��·��
		List<Menu> menus=menuServiceImpl.selAllMenu();
		Set<String> uriSet=new HashSet<String>();
		
		Map<String,Object> uriMap=new HashMap<String, Object>();
		
		for (Menu menu : menus) {
			if(menu.getUrl()!=null&&!"".equals(menu.getUrl())) {
				uriSet.add(path+menu.getUrl());
				uriMap.put(path+menu.getUrl(), menu.getManager());
			}
		}
		
		if(uriSet.contains(uri)) {
			//Ȩ����֤
			//�жϵ�ǰ�û��Ƿ�ӵ�ж�Ӧ��Ȩ��
			Set<String> authUriSet=(Set<String>) request.getSession().getAttribute("authSet");
			User u=(User) request.getSession().getAttribute("loginUser");
			if(null!=authUriSet&&!"".equals(authUriSet)&&authUriSet.size()!=0) {
				if(authUriSet.contains(uri)) {
					if("1".equals((String) uriMap.get(uri))) {
						if("1".equals(u.getInfo())) {
							request.getSession().setAttribute("currentMenuUrl", noPath);
							return true;
						}else {
							response.sendRedirect(path+"/noPower");
							return false;
						}
					}
					return true;
				}else {
					response.sendRedirect(path+"/noPower");
					return false;
				}
			}else {
				response.sendRedirect(path+"/noPower");
				return false;
			}
			
		}else {
			return true;
		}
	}
}
