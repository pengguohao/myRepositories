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
	 * ��֤��½����
	 */
	@RequestMapping("toLogin")
	@ResponseBody
	public Object toLogin(String username,String password,HttpSession session,boolean remember,String flag,HttpServletResponse resp) {
		AjaxResult result=new AjaxResult();
		User user=userServiceImpl.selByNamePwd(username, password);
		if(null==user) {
			//��½ʧ��
			result.setNumCode(0);
		}else {
			if(user.getInfo().equals(flag)){
				//��½�ɹ�
				result.setNumCode(1);
				result.setInfoCode(user.getUid());
				Cookie cokie=new Cookie("cloginUserInfo", user.getUsername()+","+user.getPassword()+","+user.getInfo());
				cokie.setPath("/");
				//���������ס����
				if(remember) {
					cokie.setMaxAge(14*24*60*60);
				}else {
					cokie.setMaxAge(0);
				}
				resp.addCookie(cokie);
				session.setAttribute("loginUser", user);
			}else {
				//��½ʧ�ܣ���ɫ��Ϣ����ȷ
				result.setNumCode(2);
			}
		}
		return result;
	}
	
	/**
	 * ajax��ȡ��֤��
	 * У���½����֤��
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
	 * ע��У���˺���Ϣ
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public Object CheckUserName(@RequestParam(value="strName")String username) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-z]\\w{5,15}", username)) {
			User user=new User();
			user=userServiceImpl.selByName(username);
			if(null!=user) {
				//�˺��Ѵ��ڣ�����ʹ��
				result.setNumCode(0);
			}else {
				//�˺Ų����ڣ�����ʹ��
				result.setNumCode(1);
			}
		}else {
			//�˺Ų�����Ҫ��
			result.setNumCode(2);
		}
		return result;
	}
	
	/**
	 * ע��У���û�����Ϣ
	 */
	@RequestMapping("checkPName")
	@ResponseBody
	public Object CheckPersonName(@RequestParam(value="strName")String pname) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[\\u4E00-\\u9FA5]{2,6}", pname)) {
			User user=new User();
			user=userServiceImpl.selByPersonName(pname);
			if(null!=user) {
				//�û����Ѵ��ڣ�����ʹ��
				result.setNumCode(0);
			}else {
				//�û��������ڣ�����ʹ��
				result.setNumCode(1);
			}
		}else {
			//�û���������Ҫ��
			result.setNumCode(2);
		}
		return result;
	}
	
	/**
	 * ע��У������
	 */
	@RequestMapping("checkEmail")
	@ResponseBody
	public Object CheckEmail(@RequestParam(value="strName")String email) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\\.)+(com|cn|net|org)", email)) {
			User user=new User();
			user=userServiceImpl.selByEmail(email);
			if(null!=user) {
				//�����Ѵ��ڣ�����ʹ��
				result.setNumCode(0);
			}else {
				//���䲻���ڣ�����ʹ��
				result.setNumCode(1);
			}
		}else {
			//���䲻����Ҫ��
			result.setNumCode(2);
		}
		return result;
	}
	
	/**
	 * ע���˺�
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
			//Ĭ����ֵ
			user.setSalt("my");
			//ע��ʱ��
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			int index=0;
			index=userServiceImpl.insByRegularUser(user);
			if(index>0) {
				//ע��ɹ�
				result.setNumCode(1);
			}else {
				//ע��ʧ��
				result.setNumCode(0);
			}
			
		}else{
			//У����Ϣδͨ��
			result.setNumCode(2);
			
		}
		return result;
	}
	
	/**
	 * ͨ���ʼ���֤����У���û��Ƿ�����޸�����
	 * ����У����
	 */
	@RequestMapping("buildEmailCode")
	@ResponseBody
	public Object buildEmailCode4ResetPwd(String toAdress,HttpSession session) {
		AjaxResult result=new AjaxResult();
		//4λ���ֵ�У����
		String emailCode=YanZCodeUtil.build4CodeByRandom();
		String emailContent="\n" + "�����黰����"+"\n"+"    ��������ע���룺"+emailCode+"��������Ա����������Ҫ����Ҫ��֤��Ķ���ƭ�ӣ������Ǳ��˲�������ԡ�";
		try {
			EmailUtil.sendEmail(toAdress, "������֤��", emailContent);
			session.setAttribute("emailCode4ResetPwd", emailCode);
			result.setAjaxCode(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setAjaxCode(false);
		}
		return result;
	}
	
	
	
	/**
	 * ͨ���ʼ���֤����У���û��Ƿ�����޸�����
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
	 * У���˺���Ϣ
	 */
	@RequestMapping("checkName4ResetPwd")
	@ResponseBody
	public Object checkName4ResetPwd(@RequestParam(value="strName")String username) {
		AjaxResult result=new AjaxResult();
		if(CheckUtil.checkReg("[a-zA-z]\\w{5,15}", username)) {
			User user=new User();
			user=userServiceImpl.selByName(username);
			if(null!=user) {
				//�˺��Ѵ��ڣ����޸�����
				result.setNumCode(1);
			}else {
				//�˺Ų����ڣ�����ʹ��
				result.setNumCode(0);
			}
		}else {
			//�˺Ų�����Ҫ��
			result.setNumCode(0);
		}
		return result;
	}
	
	/**
	 * У�������Ƿ���ȷ
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
			//���䲻����Ҫ��
			System.out.println("2222");
			result.setAjaxCode(false);
		}
		return result;
	}
	

	/**
	 * ע���˺�
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
				//�����޸ĳɹ�
				result.setNumCode(1);
			}else {
				//�����޸�ʧ��
				result.setNumCode(0);
			}
			
		}else{
			//У����Ϣδͨ��
			result.setNumCode(2);
			
		}
		return result;
	}
	
	/**
	 * ͨ���ʼ���֤����У���û��Ƿ�����޸�����
	 * ����У����
	 */
	@RequestMapping("resetEmailCode")
	@ResponseBody
	public void resetEmailCode(HttpSession session) {
		//4λ���ֵ�У����
		String emailCode=YanZCodeUtil.build4CodeByRandom();
		emailCode+=YanZCodeUtil.build4CodeByRandom();
		session.setAttribute("emailCode4ResetPwd", emailCode);
	}
	
	
	
	
	
}
