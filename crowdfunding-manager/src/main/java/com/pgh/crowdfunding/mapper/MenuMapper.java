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
	 * 查找所有的菜单信息
	 * @return
	 */
	@Select("select * from menu where manager='1'")
	List<Menu> selAllMenu();

	/**
	 * 查找已经获取菜单信息
	 */
	@Select("select * from menu where manager='1' and id in(select mid from role_menu where rid=#{0})")
	List<Menu> selHadMenus(String rid);
	
	/**
	 * 给角色授权
	 */
	int insAssignPower(Map<String,Object> menuMap);
	/**
	 * 取消角色的访问权
	 * @param roleMap
	 * @return
	 */
	@Delete("delete from role_menu where rid=#{0}")
	int delAssignPowers(Integer rid);
	
	/**
	 * 查找某用户所拥有的菜单访问信息
	 * @return
	 */
	@Select("select * from menu where id in(select mid from role_menu where rid in (select rid from user_role where uid=#{0}))")
	List<Menu> selMenuByUid(Integer uid);

	/**
	 * 删除菜单
	 */
	@Delete("delete from menu where id=#{0}")
	int delMenu(Integer mid);

	/**
	 * 重命名菜单
	 */
	@Update("update menu set name=#{1} where id=#{0}")
	int updMenuName(Integer mid,String name);

	/**
	 * 添加菜单
	 */
	@Insert("insert into menu values(default,#{0},default,#{1},#{2},'1')")
	int insMenu(String name, Integer pid, String icon);

	/**
	 * 批量取消角色的访问权
	 * @param roleMap
	 * @return
	 */
	int delRolesPowers(Integer[] rids);

	/**
	 * 取消所有角色对该菜单的的访问权
	 * @param roleMap
	 * @return
	 */
	@Delete("delete from role_menu where mid=#{0}")
	int delPowerByMid(Integer mid);
}
