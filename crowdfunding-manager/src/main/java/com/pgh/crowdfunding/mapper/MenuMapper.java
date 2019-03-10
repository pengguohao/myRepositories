package com.pgh.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pgh.crowdfunding.pojo.Menu;

public interface MenuMapper {
	/**
	 * �������еĲ˵���Ϣ
	 * @return
	 */
	@Select("select * from menu where manager='1'")
	List<Menu> selAllMenu();

	/**
	 * �����Ѿ���ȡ�˵���Ϣ
	 */
	@Select("select * from menu where manager='1' and id in(select mid from role_menu where rid=#{0})")
	List<Menu> selHadMenus(String rid);
	
	/**
	 * ����ɫ��Ȩ
	 */
	int insAssignPower(Map<String,Object> menuMap);
	/**
	 * ȡ����ɫ�ķ���Ȩ
	 * @param roleMap
	 * @return
	 */
	@Delete("delete from role_menu where rid=#{0}")
	int delAssignPowers(Integer rid);
	
	/**
	 * ����ĳ�û���ӵ�еĲ˵�������Ϣ
	 * @return
	 */
	@Select("select * from menu where id in(select mid from role_menu where rid in (select rid from user_role where uid=#{0}))")
	List<Menu> selMenuByUid(Integer uid);

	/**
	 * ɾ���˵�
	 */
	@Delete("delete from menu where id=#{0}")
	int delMenu(Integer mid);

	/**
	 * �������˵�
	 */
	@Update("update menu set name=#{1} where id=#{0}")
	int updMenuName(Integer mid,String name);

	/**
	 * ��Ӳ˵�
	 */
	@Insert("insert into menu values(default,#{0},default,#{1},#{2},'1')")
	int insMenu(String name, Integer pid, String icon);

	/**
	 * ����ȡ����ɫ�ķ���Ȩ
	 * @param roleMap
	 * @return
	 */
	int delRolesPowers(Integer[] rids);

	/**
	 * ȡ�����н�ɫ�Ըò˵��ĵķ���Ȩ
	 * @param roleMap
	 * @return
	 */
	@Delete("delete from role_menu where mid=#{0}")
	int delPowerByMid(Integer mid);
}
