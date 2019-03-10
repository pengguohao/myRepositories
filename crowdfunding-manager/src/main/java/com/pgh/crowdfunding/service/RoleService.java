package com.pgh.crowdfunding.service;


import java.util.List;
import java.util.Map;

import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.Role;


public interface RoleService {

	/**
	 * 查找所有角色的信息
	 */
	List<Role> selByAll();
	
	/**
	 * 查找用户已有的角色信息
	 */
	List<Role> selHadRoles(String uid);
	
	/**
	 * 给用户授予角色
	 */
	int insAssignRoles(Map<String,Object> roleMap);
	
	/**
	 * 取消用户角色
	 */
	int delAssignRoles(Map<String,Object> roleMap);
	
	/**
	 * 分页查询
	 */
	List<Role> selForPage(PageInfo pageInfo);
	
	/**
	 * 分页查询,查总数
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * 后台管理增加角色
	 */
	int insByManager(Role role);
	
	/**
	 * 后台管理单次删除角色
	 */
	int delByManager(int rid);
	
	/**
	 * 后台批量删除
	 * @return
	 */
	int delRoles(Integer[] rids);
	
	/**
	 * 根据id查询角色信息用于修改
	 * @return
	 */
	Role selByRid(Integer rid);
	
	/**
	 * 修改角色信息
	 */
	int updRoleInfo(Role role);

	/**
	 * 通过name查询角色信息
	 */
	Role selByName(String name);
}
