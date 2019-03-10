package com.pgh.crowdfunding.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgh.crowdfunding.pojo.Menu;

public class LoadUtil {
	/**
	 * �����ݿ������Ĳ˵�װ������ ע�⣺�˷���Ĭ�ϵ�һ�����˵���pid��0�� ����Jason����Ҫ������
	 * 
	 * @return
	 */
	public static List<Menu> loadMap2AMenu(List<Menu> loadMenus) {
		List<Menu> menu = new ArrayList<Menu>();
		if (null == loadMenus || "".equals(loadMenus) || loadMenus.size() == 0) {

		} else {

			// �����еĲ˵���Ϣ�ŵ�map��
			Map<Integer, Menu> menuMap = new HashMap<Integer, Menu>();
			for (Menu m : loadMenus) {
				menuMap.put(m.getId(), m);
			}

			for (Menu m : loadMenus) {
				if (m.getPid() == 0) {
					menu.add(m);
				} else {
					Menu parent = menuMap.get(m.getPid());
					parent.getChildren().add(m);
				}
			}

		}

		return menu;

	}

	/**
	 * �����û���Ϣװ�ط��ر�ɾ�����û���Ϣ ע�⣺�˷���װ�صĽ�ɫ��ϢΪ���ַ���
	 * 
	 * @param user
	 * @return
	 *//*
		 * public static DeletedUserInfo loadDeletedUserInfo(User user) {
		 * DeletedUserInfo duser=new DeletedUserInfo(); duser.setUid(user.getUid());
		 * duser.setBirthday(user.getBirthday()); duser.setEmail(user.getEmail());
		 * duser.setPassword(user.getPassword()); duser.setPname(user.getPname());
		 * duser.setSalt(user.getSalt()); duser.setUsername(user.getUsername());
		 * duser.setRid(""); return duser; }
		 */

	
	
	
	
	
}
