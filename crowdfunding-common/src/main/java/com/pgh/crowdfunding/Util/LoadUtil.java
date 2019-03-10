package com.pgh.crowdfunding.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgh.crowdfunding.pojo.Menu;

public class LoadUtil {
	/**
	 * 把数据库查出来的菜单装载起来 注意：此方法默认第一个父菜单的pid是0！ 用于Jason树需要的资料
	 * 
	 * @return
	 */
	public static List<Menu> loadMap2AMenu(List<Menu> loadMenus) {
		List<Menu> menu = new ArrayList<Menu>();
		if (null == loadMenus || "".equals(loadMenus) || loadMenus.size() == 0) {

		} else {

			// 把所有的菜单信息放到map中
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
	 * 根据用户信息装载返回被删除的用户信息 注意：此方法装载的角色信息为空字符串
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
