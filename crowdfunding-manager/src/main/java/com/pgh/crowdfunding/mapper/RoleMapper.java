package com.pgh.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.Role;

public interface RoleMapper {
	
	/**
	 * 查找数据库角色信息
	 */
	@Select("select * from role order by rid")
	List<Role> selAllRole();
	
	/**
	 * 查找用户已有的角色信息
	 */
	@Select("select * from role where rid in (select rid from user_role where uid=#{0})")
	List<Role> selHadRoles(String uid);
	
	/**
	 * 给用户设置角色
	 */
	int insAssignRoles(Map<String,Object> roleMap);
	/**
	 * 取消用户角色
	 * @param roleMap
	 * @return
	 */
	int delAssignRoles(Map<String, Object> roleMap);
	
	/**
	 * 分页查询所有角色的信息
	 */
	List<Role> selAllForPage(PageInfo pageInfo);
	
	/**
	 * 查询所有数据的信息
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * 后台管理增加角色
	 */
	@Insert("insert into role values (default,#{name})")
	int insByManager(Role role);
	
	/**
	 * 后台管理单次删除角色
	 */
	@Delete("delete from role where rid=#{0}")
	int delByManager(int rid);
	
	/**
	 * 后台管理批量删除角色
	 */
	int delRoles(Integer[] array);

	/**
	 * 根据id查询角色信息用于修改
	 * @return
	 */
	@Select("select * from role where rid=#{0}")
	Role selByRid(Integer rid);
	
	/**
	 * 修改角色信息
	 */
	@Update("update role set name=#{name} where rid=#{rid}")
	int updRoleInfo(Role role);
	
	/**
	 * 查询角色信息
	 */
	@Select("select * from role where name=#{0}")
	Role selByName(String name);

	/**
	 * 根据用户id删除其具有的角色信息
	 * @param uid
	 * @return
	 */
	@Delete("delete from user_role where uid=#{0}")
	int delUserRolesByUid(int uid);
	
	/**
	 * 批量删除用户角色信息
	 * @param uids
	 * @return
	 */
	int delUserRolesByUids(Integer[] uids);

	
	/**
	 * 根据角色id删除其关联的角色信息
	 * @param uid
	 * @return
	 */
	@Delete("delete from user_role where rid=#{0}")
	int delUsersRolesByRid(int rid);

	
	/**
	 * 批量删除多用户，根据角色id删除他们关联的角色信息
	 * @param uid
	 * @return
	 */
	int delUsersRolesByRids(Integer[] rids);
	
	
}
