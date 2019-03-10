package com.pgh.crowdfunding.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgh.crowdfunding.Util.CheckUtil;
import com.pgh.crowdfunding.Util.EmailUtil;
import com.pgh.crowdfunding.Util.YanZCodeUtil;
import com.pgh.crowdfunding.ajax.AjaxResult;
import com.pgh.crowdfunding.pojo.User;
import com.pgh.crowdfunding.service.UserService;

@Controller
@RequestMapping("check")
public class AjaxCheckController {
	@Resource
	private UserService userServiceImpl;
	
	/**
	 * 验证登陆请求
	 */
	@RequestMapping("toLogin")
	@ResponseBody
	public Object toLogin(String username,String password,HttpSession session,boolean remember,String flag,HttpServletResponse resp) {
		AjaxResult result=new AjaxResult();
		User user=userServiceImpl.selByNamePwd(username, password);
		if(null==user) {
			//登陆失败
			result.setNumCode(0);
		}else {
			if(user.getInfo().equals(flag)){
				//登陆成功
				result.setNumCode(1);
				result.setInfoCode(user.getUid());
				Cookie cokie=new Cookie("cloginUserInfo", user.getUsername()+","+user.getPassword()+","+user.getInfo());
				cokie.setPath("/");
				//让浏览器记住密码
				if(remember) {
					cokie.setMaxAge(14*24*60*60);
				}else {
					cokie.setMaxAge(0);
				}
				resp.addCookie(cokie);
				session.setAttribute("loginUser", user);
			}else {
				//登陆失败，角色信息不正确
				result.setNumCode(2);
			}
		}
		return result;
	}
	
	/**
	 * ajax获取验证码
	 * 校验登陆的验证码
	 */
	@RequestMapping("yan_code")
	@ResponseBody
	public Object login_yanCode(String loginCode,HttpSession session,String dowhat){
		AjaxResult result = new AjaxResult();
		if(CheckUtil.checkYanZhengCode(loginCode, session, dowhat)) {
			result.setAjaxCode(true);
		}else {
			result.setAjaxCode(false);
		}
		return result;
	}
	
	
	/**
	 * 注册校验账号信息
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public Object CheckUserName(@RequestParam(value="strName")String username) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-z]\\w{5,15}", username)) {
			User user=new User();
			user=userServiceImpl.selByName(username);
			if(null!=user) {
				//账号已存在，不可使用
				result.setNumCode(0);
			}else {
				//账号不存在，可以使用
				result.setNumCode(1);
			}
		}else {
			//账号不符合要求
			result.setNumCode(2);
		}
		return result;
	}
	
	/**
	 * 注册校验用户名信息
	 */
	@RequestMapping("checkPName")
	@ResponseBody
	public Object CheckPersonName(@RequestParam(value="strName")String pname) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[\\u4E00-\\u9FA5]{2,6}", pname)) {
			User user=new User();
			user=userServiceImpl.selByPersonName(pname);
			if(null!=user) {
				//用户名已存在，不可使用
				result.setNumCode(0);
			}else {
				//用户名不存在，可以使用
				result.setNumCode(1);
			}
		}else {
			//用户名不符合要求
			result.setNumCode(2);
		}
		return result;
	}
	
	/**
	 * 注册校验邮箱
	 */
	@RequestMapping("checkEmail")
	@ResponseBody
	public Object CheckEmail(@RequestParam(value="strName")String email) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\\.)+(com|cn|net|org)", email)) {
			User user=new User();
			user=userServiceImpl.selByEmail(email);
			if(null!=user) {
				//邮箱已存在，不可使用
				result.setNumCode(0);
			}else {
				//邮箱不存在，可以使用
				result.setNumCode(1);
			}
		}else {
			//邮箱不符合要求
			result.setNumCode(2);
		}
		return result;
	}
	
	/**
	 * 注册账号
	 */
	@RequestMapping("doRegister")
	@ResponseBody
	public Object doRegister(User user,String repeatpwd,String yanZhengCode,HttpSession session) {
		AjaxResult result =new AjaxResult();
		if(
		CheckUtil.checkReg("[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\\.)+(com|cn|net|org)", user.getEmail())&&		
		CheckUtil.checkReg("[a-zA-z]\\w{5,15}",user.getUsername())&&
		CheckUtil.checkReg("(\\w){6,16}",user.getPassword())&&
		CheckUtil.checkReg("[\\u4E00-\\u9FA5]{2,6}",user.getPname())&&
		user.getPassword().equals(repeatpwd)&&
		CheckUtil.checkYanZhengCode(yanZhengCode, session, "register")
		){
			//默认盐值
			user.setSalt("my");
			//注册时间
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			int index=0;
			index=userServiceImpl.insByRegularUser(user);
			if(index>0) {
				//注册成功
				result.setNumCode(1);
			}else {
				//注册失败
				result.setNumCode(0);
			}
			
		}else{
			//校验信息未通过
			result.setNumCode(2);
			
		}
		return result;
	}
	
	/**
	 * 通过邮件验证码来校验用户是否可以修改密码
	 * 生成校验码
	 */
	@RequestMapping("buildEmailCode")
	@ResponseBody
	public Object buildEmailCode4ResetPwd(String toAdress,HttpSession session) {
		AjaxResult result=new AjaxResult();
		//4位数字的校验码
		String emailCode=YanZCodeUtil.build4CodeByRandom();
		String emailContent="\n" + "【神话情话】："+"\n"+"    您的邮箱注册码："+emailCode+"。工作人员不会向您索要，索要验证码的都是骗子，若果非本人操作请忽略。";
		try {
			EmailUtil.sendEmail(toAdress, "邮箱验证码", emailContent);
			session.setAttribute("emailCode4ResetPwd", emailCode);
			result.setAjaxCode(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
	
	
	/**
	 * 通过邮件验证码来校验用户是否可以修改密码
	 */
	@RequestMapping("checkEmailCode")
	@ResponseBody
	public Object checkEmailCode4ResetPwd(HttpSession session,String EmailCode) {
		AjaxResult result=new AjaxResult();
		String emailCode4ResetPwd=(String) session.getAttribute("emailCode4ResetPwd");
		if(null!=EmailCode&&!"".equals(EmailCode)&&EmailCode.equals(emailCode4ResetPwd)) {
			result.setAjaxCode(true);
		}else {
			result.setAjaxCode(false);
		}
		return result;
	}
	
	/**
	 * 校验账号信息
	 */
	@RequestMapping("checkName4ResetPwd")
	@ResponseBody
	public Object checkName4ResetPwd(@RequestParam(value="strName")String username) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-z]\\w{5,15}", username)) {
			User user=new User();
			user=userServiceImpl.selByName(username);
			if(null!=user) {
				//账号已存在，可修改密码
				result.setNumCode(1);
			}else {
				//账号不存在，可以使用
				result.setNumCode(0);
			}
		}else {
			//账号不符合要求
			result.setNumCode(0);
		}
		return result;
	}
	
	/**
	 * 校验邮箱是否正确
	 */
	@RequestMapping("checkEmail4ResetPwd")
	@ResponseBody
	public Object checkEmail4ResetPwd(User user) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\\.)+(com|cn|net|org)", user.getEmail())) {
			String uEmail=userServiceImpl.selEmailByUsername(user.getUsername());
			if(null!=uEmail&&!"".equals(uEmail)) {
				result.setAjaxCode(uEmail.equals(user.getEmail()));
			}else {
				System.out.println("11111");
				result.setAjaxCode(false);
			}
		}else {
			//邮箱不符合要求
			System.out.println("2222");
			result.setAjaxCode(false);
		}
		return result;
	}
	

	/**
	 * 注册账号
	 */
	@RequestMapping("resetPassword4User")
	@ResponseBody
	public Object resetPassword4User(User user,String repeatpwd,String yanZhengCode,HttpSession session) {
		AjaxResult result =new AjaxResult();
		if(
		CheckUtil.checkReg("[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\\.)+(com|cn|net|org)", user.getEmail())&&		
		CheckUtil.checkReg("[a-zA-z]\\w{5,15}",user.getUsername())&&
		CheckUtil.checkReg("(\\w){6,16}",user.getPassword())&&
		user.getPassword().equals(repeatpwd)&&
		CheckUtil.checkYanZhengCode(yanZhengCode, session, "emailCode4ResetPwd")
		){
			int index=0;
			index=userServiceImpl.updPassword4User(user);
			if(index>0) {
				//密码修改成功
				result.setNumCode(1);
			}else {
				//密码修改失败
				result.setNumCode(0);
			}
			
		}else{
			//校验信息未通过
			result.setNumCode(2);
			
		}
		return result;
	}
	
	/**
	 * 通过邮件验证码来校验用户是否可以修改密码
	 * 生成校验码
	 */
	@RequestMapping("resetEmailCode")
	@ResponseBody
	public void resetEmailCode(HttpSession session) {
		//4位数字的校验码
		String emailCode=YanZCodeUtil.build4CodeByRandom();
		emailCode+=YanZCodeUtil.build4CodeByRandom();
		session.setAttribute("emailCode4ResetPwd", emailCode);
	}
	
	
	
	
	
}
