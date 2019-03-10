package com.pgh.crowdfunding.service;

import java.util.List;
import java.util.Map;


import com.pgh.crowdfunding.pojo.Menu;

public interface MenuService {
	
	
	/**
	 * �������еĲ˵���Ϣ
	 */
	List<Menu> selAllMenu();

	/**
	 * �����Ѿ���ȡ�˵���Ϣ
	 */
	List<Menu> selHadMenus(String rid);
	
	/**
	 * ����ɫ�������Ȩ��
	 */
	//int insAssignMenus(Map<String,Object> menuMap);
	
	/**
	 * ȡ����ɫ����Ȩ��
	 */
	//int delAssignMenus(Integer rid);
	
	void updAssignPower(Integer rid,Map<String,Object> menuMap);
	
	/**
	 * ����ĳ�û���ӵ�еĲ˵�������Ϣ
	 * @return
	 */
	List<Menu> selMenuByUid(Integer uid);
	
	/**
	 * ɾ���˵�
	 */
	int delMenu(Integer mid);
	
	/**
	 * �������˵�
	 */
	int updMenuName(Integer mid,String name);

	/**
	 * ��Ӳ˵�
	 */
	int insMenu(String name, Integer pid, String icon);
	
}
