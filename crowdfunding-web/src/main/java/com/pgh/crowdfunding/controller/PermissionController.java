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
		//�����ݿ��в��ҵ����еĲ˵���Ϣ
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
		//�����в˵�
		allMenus=menuServiceImpl.selAllMenu();
		if(!allMenus.isEmpty()){
			//���ѻ�õ�
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
	
	//����Ȩ������data
	@RequestMapping("assignTree4Role")
	@ResponseBody
	public Object assignTree4Role(String rid,boolean disChecked,HttpSession session) {
		List<Menu> allMenus=new ArrayList<Menu>();
		List<Menu> hasMenus=new ArrayList<Menu>();
		//�����в˵�
		allMenus=menuServiceImpl.selAllMenu();
		if(!allMenus.isEmpty()){
			//���ѻ�õ�
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
	 * ���½�ɫ�ķ���Ȩ��
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
	 * �����û��ĵ�½��Ϣ����ѯ����еĲ˵�����Ȩ��
	 */
	@RequestMapping("sellMenu4Uer")
	@ResponseBody
	public void sellMenu4Uer(Integer uid,HttpSession session) {
		List<Menu> menu=new ArrayList<Menu>();
		List<Menu> allMenu=new ArrayList<Menu>();
		//�����ݿ��в��ҵ��û����еĲ˵�����Ȩ����Ϣ
		allMenu=menuServiceImpl.selMenuByUid(uid);
		//�����еĲ˵���Ϣ�ŵ�map������Ч��
		Map<Integer,Menu> menuMap=new HashMap<Integer, Menu>();
		//�����еĲ˵���url�ŵ�Set�У����ڽ���Ȩ��ȷ��
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
		System.out.println("delMenu�ұ�ִ����");
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
		System.out.println("renameMenu�ұ�ִ����");
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
		//Ĭ��û��url
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
