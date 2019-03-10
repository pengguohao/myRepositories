package com.pgh.crowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgh.crowdfunding.Util.LoadUtil;
import com.pgh.crowdfunding.ajax.AjaxResult;
import com.pgh.crowdfunding.pojo.Menu;
import com.pgh.crowdfunding.service.MenuService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	@Resource
	private MenuService menuServiceImpl;
	
	@RequestMapping("permission")
	public String goPermission(){
		return "permission/permission";
	}
	
	
	
	@RequestMapping("sellAllMenu")
	@ResponseBody
	public Object sellAllMenu(HttpSession session) {
		List<Menu> allMenu=new ArrayList<Menu>();
		//从数据库中查找到所有的菜单信息
		allMenu=menuServiceImpl.selAllMenu();
		List<Menu> loadMenu=LoadUtil.loadMap2AMenu(allMenu);
		return LoadUtil.loadMap2AMenu(loadMenu);
	}
	

	
	
	@RequestMapping("doAssign4Role")
	@ResponseBody
	public Object doAssign4Role(String rid,HttpSession session) {
		AjaxResult result =new AjaxResult();
		List<Menu> allMenus=new ArrayList<Menu>();
		List<Menu> hasMenus=new ArrayList<Menu>();
		List<Menu> notMenus=new ArrayList<Menu>();
		
		Map<String,Object> menuMap=new HashMap<String,Object>();
		//查所有菜单
		allMenus=menuServiceImpl.selAllMenu();
		if(!allMenus.isEmpty()){
			//查已获得的
			hasMenus=menuServiceImpl.selHadMenus(rid);
			if(!hasMenus.isEmpty()) {
				for (Menu menu : allMenus) {
					if(!hasMenus.contains(menu)) {
						notMenus.add(menu);
					}
				}
			}else {
				notMenus=allMenus;
			}
			menuMap.put("id", rid);
			menuMap.put("hasMenus", hasMenus);
			menuMap.put("notMenus", notMenus);
			session.setAttribute("menuMap", menuMap);
			result.setAjaxCode(true);
			
		}else {
			result.setAjaxCode(false);
		}
		return result;
	}
	
	//返回权限树的data
	@RequestMapping("assignTree4Role")
	@ResponseBody
	public Object assignTree4Role(String rid,boolean disChecked,HttpSession session) {
		List<Menu> allMenus=new ArrayList<Menu>();
		List<Menu> hasMenus=new ArrayList<Menu>();
		//查所有菜单
		allMenus=menuServiceImpl.selAllMenu();
		if(!allMenus.isEmpty()){
			//查已获得的
			hasMenus=menuServiceImpl.selHadMenus(rid);
			for (Menu menu : allMenus) {
				
				if(hasMenus.contains(menu)){
						menu.setChecked(true);
				}
				
				menu.setChkDisabled(disChecked);
			}

		}
		
		return LoadUtil.loadMap2AMenu(allMenus);
	}
	
	/**
	 * 更新角色的访问权限
	 * @param rid
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("updAssignPower")
	@ResponseBody
	
	public Object updAssignPower(Integer rid,Integer[] menuIds) {
		AjaxResult result=new AjaxResult();
		Map<String,Object> menuMap=new HashMap<String, Object>();
		menuMap.put("rid", rid);
		if(menuIds.length!=0) {
			menuMap.put("menuIds",menuIds);
		}
		try {
				menuServiceImpl.updAssignPower(rid, menuMap);
				result.setAjaxCode(true);
		}catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
	/**
	 * 根据用户的登陆信息来查询其具有的菜单访问权限
	 */
	@RequestMapping("sellMenu4Uer")
	@ResponseBody
	public void sellMenu4Uer(Integer uid,HttpSession session) {
		List<Menu> menu=new ArrayList<Menu>();
		List<Menu> allMenu=new ArrayList<Menu>();
		//从数据库中查找到用户所有的菜单访问权限信息
		allMenu=menuServiceImpl.selMenuByUid(uid);
		//把所有的菜单信息放到map，增加效率
		Map<Integer,Menu> menuMap=new HashMap<Integer, Menu>();
		//把所有的菜单的url放到Set中，便于进行权限确认
		Set<String> authUri=new HashSet<String>();
		String path=session.getServletContext().getContextPath();
		for (Menu m : allMenu) {
			menuMap.put(m.getId(), m);
			if(null!=m.getUrl()||"".equals(m.getUrl())) {
				authUri.add(path+m.getUrl());
			}
		}
		for (Menu m : allMenu) {
			if(m.getPid()==0) {
				menu.add(m);
			}else {
				Menu parent=menuMap.get(m.getPid());
				parent.getChildren().add(m);
			}
		}
		if(menu.size()!=0) {
			session.setAttribute("userMenu", menu.get(0));
		}
		session.setAttribute("authSet", authUri);
	}
	
	@RequestMapping("delMenu")
	@ResponseBody
	public Object delMenu(Integer mid){
		System.out.println("delMenu我被执行了");
		AjaxResult result=new AjaxResult();
		try {
			int index=menuServiceImpl.delMenu(mid);
			if(index>0) {
				result.setAjaxCode(true);
			}else {
				result.setAjaxCode(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
	@RequestMapping("renameMenu")
	@ResponseBody
	public Object renameMenu(Integer mid,String name){
		System.out.println("renameMenu我被执行了");
		AjaxResult result=new AjaxResult();
		try {
			int index=menuServiceImpl.updMenuName(mid,name);
			if(index>0) {
				result.setAjaxCode(true);
			}else {
				result.setAjaxCode(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
	
	@RequestMapping("addMenu4Manager")
	@ResponseBody
	public Object addMenu4Manager(Integer pid,String name,String icon){
		AjaxResult result=new AjaxResult();
		//默认没有url
		try {
			int index=menuServiceImpl.insMenu(name,pid,icon);
			if(index>0) {
				result.setAjaxCode(true);
			}else {
				result.setAjaxCode(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
}
