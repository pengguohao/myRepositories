package com.pgh.crowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pgh.crowdfunding.mapper.MenuMapper;
import com.pgh.crowdfunding.pojo.Menu;
import com.pgh.crowdfunding.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuMapper menuMapper;
	
	public List<Menu> selAllMenu() {
		return menuMapper.selAllMenu();
	}

	public List<Menu> selHadMenus(String rid) {
		return menuMapper.selHadMenus(rid);
	}
	/*
	public int insAssignMenus(Map<String, Object> menuMap) {
		return menuMapper.insAssignPower(menuMap);
	}
	
	public int delAssignMenus(Integer rid) {
		return menuMapper.delAssignPowers(rid);
	}
	*/
	public List<Menu> selMenuByUid(Integer uid) {
		return menuMapper.selMenuByUid(uid);
	}
	
	//@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void updAssignPower(Integer rid, Map<String, Object> menuMap) {
		menuMapper.delAssignPowers(rid);
		if(null!=menuMap.get("menuIds")&&!"".equals(menuMap.get("menuIds"))) {
			menuMapper.insAssignPower(menuMap);
		}
	}

	public int delMenu(Integer mid) {
		//先收回所有角色对该菜单的访问权限
		menuMapper.delPowerByMid(mid);
		return menuMapper.delMenu(mid);
	}

	public int updMenuName(Integer mid,String name) {
		return menuMapper.updMenuName(mid,name);
	}

	public int insMenu(String name, Integer pid, String icon) {
		return menuMapper.insMenu(name,pid,icon);
	}

}
