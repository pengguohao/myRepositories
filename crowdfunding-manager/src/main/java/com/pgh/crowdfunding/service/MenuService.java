package com.pgh.crowdfunding.service;

import java.util.List;
import java.util.Map;


import com.pgh.crowdfunding.pojo.Menu;

public interface MenuService {
	
	
	/**
	 * 查找所有的菜单信息
	 */
	List<Menu> selAllMenu();

	/**
	 * 查找已经获取菜单信息
	 */
	List<Menu> selHadMenus(String rid);
	
	/**
	 * 给角色授予访问权限
	 */
	//int insAssignMenus(Map<String,Object> menuMap);
	
	/**
	 * 取消角色访问权限
	 */
	//int delAssignMenus(Integer rid);
	
	void updAssignPower(Integer rid,Map<String,Object> menuMap);
	
	/**
	 * 查找某用户所拥有的菜单访问信息
	 * @return
	 */
	List<Menu> selMenuByUid(Integer uid);
	
	/**
	 * 删除菜单
	 */
	int delMenu(Integer mid);
	
	/**
	 * 重命名菜单
	 */
	int updMenuName(Integer mid,String name);

	/**
	 * 添加菜单
	 */
	int insMenu(String name, Integer pid, String icon);
	
}
