package com.pgh.crowdfunding.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgh.crowdfunding.ajax.AjaxResult;
import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.User;
import com.pgh.crowdfunding.service.UserService;


@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private UserService userServiceImpl;
	
	/**
	 * 跳转增加用户页面
	 * @return
	 */
	@RequestMapping("add")
	public String user_add() {
		return "user/add";
	}
	
	/**
	 * 跳转授权页面
	 * @return
	 */
	@RequestMapping("assignRole")
	public String goAssignRole() {
		return "user/assignRole";
	}
	
	/**
	 * 跳转修改用户页面
	 * @return
	 */
	@RequestMapping("edit")
	public String user_edit(HttpSession session) {
		if(null!=session.getAttribute("editUser")) {
			return "user/edit";
		}else {
			return "user/user";
		}
		
		
	}
	
	@RequestMapping("user")
	public String toUserPage(){
		return "user/user";
	}
	
	/**
	 * 
	 * @param pageInfo
	 * @return
	 */
	@RequestMapping("sel_All_Page")
	@ResponseBody
	public Object sel_All_Page_Users(@RequestParam(required=false,defaultValue="1")Integer pageNum,@RequestParam(required=false,defaultValue="10")Integer pageSize,HttpSession session,@RequestParam(required=false,defaultValue="")String selAsked){
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setSelAsked(selAsked);
		pageInfo.setPageStart((pageNum-1)*pageSize);
		List<User> list=userServiceImpl.selForPage(pageInfo);
		if("[]".equals(list.toString())){
			pageInfo.setSuccess(false);
			System.out.println("false");
		}else {
			pageInfo.setPageDisp(list);
			long total=userServiceImpl.selTotal(pageInfo);
			pageInfo.setTotal(total);
			pageInfo.setPageNums((int) (total%pageSize==0?(total/pageSize):(total/pageSize)+1));
			pageInfo.setSuccess(true);
			session.setAttribute("pageInfo", pageInfo);
		}
		return pageInfo;
	}
	
	
	
	/**
	 * 后台新增用户
	 */
	@RequestMapping("add_insert")
	@ResponseBody
	public Object add_userByManager(User user) {
		AjaxResult result=new AjaxResult();
		//默认盐值my
		String salt="my";
		//默认密码88888
		String password="88888";
		//获取时间
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setBirthday(dateFormat.format(new Date()));
		user.setSalt(salt);
		user.setPassword(password);
		int index=0;
		try {
			index=userServiceImpl.insByManager(user);
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
	 * 后台删除单条数据
	 */
	@RequestMapping("del_ByOne")
	@ResponseBody
	public Object delByManager(int uid) {
		AjaxResult result =new AjaxResult();
		int index=0;
		try {
			index=userServiceImpl.updLifeStatusByUid(uid);
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
	 * 后台删除多条数据
	 */
	@ResponseBody
	@RequestMapping("deletes")
	public Object deletes( Integer[] uids ) {
		AjaxResult result = new AjaxResult();
		try {
			userServiceImpl.updLifeStatusByUids(uids);
			result.setAjaxCode(true);
		}catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
	/**
	 * 跳转修改用户页面
	 * @return
	 */
	@RequestMapping("user_edit")
	@ResponseBody
	public Object user_edit(HttpSession session,Integer uid) {
		AjaxResult result=new AjaxResult();
		User user=new User();
		user=userServiceImpl.selByUid(uid);
		if(null!=user){
			result.setAjaxCode(true);
			session.setAttribute("editUser", user);
		}else {
			result.setAjaxCode(false);
		}
		return result;
	}
	
	@RequestMapping("updUserInfo")
	@ResponseBody
	public Object updUserInfo(User user,HttpSession session){
		AjaxResult result = new AjaxResult();
		User iniUser=(User) session.getAttribute("editUser");
		if(iniUser.getUid()!=user.getUid()){
			result.setNumCode(2);
		}
		if(iniUser.getUsername()==user.getUsername()&&iniUser.getPname()==user.getPname()&&iniUser.getEmail()==user.getEmail()) {
			result.setNumCode(0);
		}else {
			try {
				int index=0;
				index=userServiceImpl.updUserInfo(user);
				if(index>0) {
					iniUser.setUsername(user.getUsername());
					iniUser.setPname(user.getPname());
					iniUser.setEmail(user.getEmail());
					session.setAttribute("editUser", iniUser);
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
	 * 变更用户身份
	 */
	@RequestMapping("upd")
	@ResponseBody
	public Object updUserInfoByinfo(String info,Integer uid) {
		AjaxResult result =new AjaxResult();
		int index=0;
		try {
			index=userServiceImpl.updUserInfoByinfo(info,uid);
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
