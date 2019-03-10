package com.pgh.crowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgh.crowdfunding.ajax.AjaxResult;
import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.Role;
import com.pgh.crowdfunding.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	@Resource
	private RoleService roleServiceImpl;
	
	/**
	 * 跳转用户维护页面
	 * @return
	 */
	@RequestMapping("role")
	public String goFixRole() {
		
		return "role/role";
	}
	
	@RequestMapping("assignPower")
	public String goAssignPower(){
		return "role/assignPower";
	}
	
	
	
	
	
	@RequestMapping("userAssignRole")
	@ResponseBody
	public Object userAssignRole(String uid,HttpSession session) {
		AjaxResult result =new AjaxResult();
		List<Role> allRoles=new ArrayList<Role>();
		List<Role> hasRoles=new ArrayList<Role>();
		List<Role> notRoles=new ArrayList<Role>();
		
		Map<String,Object> roleMap=new HashMap<String,Object>();
		//查所有角色
		allRoles=roleServiceImpl.selByAll();
		if(!allRoles.isEmpty()){
			//查已获得的
			hasRoles=roleServiceImpl.selHadRoles(uid);
			if(!hasRoles.isEmpty()) {
				for (Role role : allRoles) {
					if(!hasRoles.contains(role)) {
						notRoles.add(role);
					}
				}
			}else {
				notRoles=allRoles;
			}
			
			roleMap.put("id", uid);
			roleMap.put("hasRoles", hasRoles);
			roleMap.put("notRoles", notRoles);
			session.setAttribute("roleMap", roleMap);
			result.setAjaxCode(true);
			
		}else {
			result.setAjaxCode(false);
		}
		
		return result;
	}
	
	
	@RequestMapping("doAssignRole")
	@ResponseBody
	public Object doAssignRole(Integer uid,Integer[] roleIds) {
		AjaxResult result=new AjaxResult();
		Map<String,Object> roleMap=new HashMap<String, Object>();
		roleMap.put("uid", uid);
		roleMap.put("roleIds",roleIds);
		try {
			int index=roleServiceImpl.insAssignRoles(roleMap);
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
	
	@RequestMapping("delAssignRole")
	@ResponseBody
	public Object delAssignRole(Integer uid,Integer[] roleIds) {
		AjaxResult result=new AjaxResult();
		Map<String,Object> roleMap=new HashMap<String, Object>();
		roleMap.put("uid", uid);
		roleMap.put("roleIds",roleIds);
		try {
			int index=roleServiceImpl.delAssignRoles(roleMap);
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
	
	/**
	 * 
	 * @param pageInfo
	 * @return
	 */
	@RequestMapping("selAllPageRoles")
	@ResponseBody
	public Object selAllPageRoles(@RequestParam(required=false,defaultValue="1")Integer pageNum,@RequestParam(required=false,defaultValue="10")Integer pageSize,HttpSession session,@RequestParam(required=false,defaultValue="")String selAsked){
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setSelAsked(selAsked);
		pageInfo.setPageStart((pageNum-1)*pageSize);
		List<Role> list=roleServiceImpl.selForPage(pageInfo);
		if("[]".equals(list.toString())){
			pageInfo.setSuccess(false);
		}else {
			pageInfo.setPageDispRoles(list);
			long total=roleServiceImpl.selTotal(pageInfo);
			pageInfo.setTotal(total);
			pageInfo.setPageNums((int) (total%pageSize==0?(total/pageSize):(total/pageSize)+1));
			pageInfo.setSuccess(true);
			session.setAttribute("pageInfoRoles", pageInfo);
		}
		return pageInfo;
	}
	
	
	/**
	 * 后台新增角色
	 */
	@RequestMapping("addRole")
	@ResponseBody
	public Object addRoleByManager(Role role) {
		AjaxResult result=new AjaxResult();
		int index=0;
		try {
			index=roleServiceImpl.insByManager(role);
			if(index>0) {
			result.setNumCode(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setNumCode(0);
		}
		return result;
	}
	
	/**
	 * 后台删除数据
	 */
	@RequestMapping("delByOne")
	@ResponseBody
	public Object delByManager(int rid) {
		AjaxResult result =new AjaxResult();
		int index=0;
		try {
			index=roleServiceImpl.delByManager(rid);
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
	
	
	
	@ResponseBody
	@RequestMapping("deleteRoles")
	public Object deleteRoles( Integer[] rids ) {
		AjaxResult result = new AjaxResult();
		try {
			int index=roleServiceImpl.delRoles(rids);
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
	
	
	/**
	 * 查询修改角色页面的信息
	 * @return
	 */
	@RequestMapping("roleEdit")
	@ResponseBody
	public Object roleEdit(HttpSession session,Integer rid) {
		AjaxResult result=new AjaxResult();
		Role role=new Role();
		role=roleServiceImpl.selByRid(rid);
		if(null!=role){
			result.setAjaxCode(true);
			session.setAttribute("editRole", role);
		}else {
			result.setAjaxCode(false);
		}
		return result;
	}
	
	/**
	 * 跳转角色修改页面
	 * @param role
	 * @param session
	 * @return
	 */
	@RequestMapping("role_Edit")
	public String editPageInfo() {
		return "role/edit";
	}
	
	/**
	 * 跳转角色修改页面
	 * @param role
	 * @param session
	 * @return
	 */
	@RequestMapping("role_Add")
	public String addPageInfo() {
		return "role/add";
	}
	
	
	
	@RequestMapping("updRoleInfo")
	@ResponseBody
	public Object updRoleInfo(Role role,HttpSession session){
		AjaxResult result = new AjaxResult();
		Role iniRole=(Role) session.getAttribute("editRole");
		if(iniRole.getRid()!=role.getRid()){
			result.setNumCode(2);
		}
		if(iniRole.getName()==role.getName()) {
			System.out.println("111");
			result.setNumCode(0);
		}else {
			try {
				int index=0;
				index=roleServiceImpl.updRoleInfo(role);
				if(index>0) {
					iniRole.setName(role.getName());;
					session.setAttribute("editRole", iniRole);
					result.setNumCode(1);
				}else {
					result.setNumCode(0);
				}
			}catch (Exception e) {
				e.printStackTrace();
				result.setNumCode(2);
			}
		}
		return result;
		
		
	}
	
	/**
	 * 注册校验账号信息
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public Object CheckUserName(@RequestParam(value="strName")String name) {
		AjaxResult result=new AjaxResult();
			Role role=new Role();
			role=roleServiceImpl.selByName(name);
			if(null!=role) {
				//账号已存在，不可使用
				result.setNumCode(0);
			}else {
				//账号不存在，可以使用
				result.setNumCode(1);
			}
		return result;
	}
	
}
