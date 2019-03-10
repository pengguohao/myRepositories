package com.pgh.crowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pgh.crowdfunding.mapper.MenuMapper;
import com.pgh.crowdfunding.mapper.RoleMapper;
import com.pgh.crowdfunding.mapper.UserMapper;
import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.Role;
import com.pgh.crowdfunding.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private MenuMapper menuMapper;

	public List<Role> selByAll() {
		return roleMapper.selAllRole();
	}

	public int insAssignRoles(Map<String,Object> roleMap) {
		return roleMapper.insAssignRoles(roleMap);
	}

	public List<Role> selHadRoles(String uid) {
		return roleMapper.selHadRoles(uid);
	}

	public int delAssignRoles(Map<String, Object> roleMap) {
		return roleMapper.delAssignRoles(roleMap);
	}

	public List<Role> selForPage(PageInfo pageInfo) {
		return roleMapper.selAllForPage(pageInfo);
	}

	public long selTotal(PageInfo pageInfo) {
		return roleMapper.selTotal(pageInfo);
	}

	public int insByManager(Role role) {
		return roleMapper.insByManager(role);
	}

	public int delByManager(int rid) {
		//删除关联的信息，用户角色表 和角色菜单表
		menuMapper.delAssignPowers(rid);
		roleMapper.delUsersRolesByRid(rid);
		return roleMapper.delByManager(rid);
	}

	public int delRoles(Integer[] rids) {
		//批量删除关联的信息，用户角色表 和角色菜单表
		menuMapper.delRolesPowers(rids);
		roleMapper.delUsersRolesByRids(rids);
		return roleMapper.delRoles(rids);
	}

	public Role selByRid(Integer rid) {
		return roleMapper.selByRid(rid);
	}

	public int updRoleInfo(Role role) {
		return roleMapper.updRoleInfo(role);
	}

	public Role selByName(String name) {
		return roleMapper.selByName(name);
	}
	
	
}
