package com.pgh.crowdfunding.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pgh.crowdfunding.Util.YanZCodeUtil;
@Controller
public class DispatcherController {
	
	/**
	 * ������֤��
	 */
	@RequestMapping("yanzCode")
	public void yaCode(HttpServletResponse resp,HttpSession session,String dowhat){
		YanZCodeUtil yzCode=new YanZCodeUtil();
		HashMap<String, BufferedImage> codeMap = yzCode.buildCodeImage();
		//��ȡ���ɵ���֤��
		try {
			session.setAttribute(dowhat, yzCode.getCodeKey());
			ImageIO.write(codeMap.get(yzCode.getCodeKey()), "png", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ���ص�½ҳ��
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("index")
	public String main(){
		return "index";
	}
	
	
	/**
	 * ����Ȩ�޲���ҳ��
	 */
	
	@RequestMapping("noPower")
	public String noPower(){
		return "error";
	}
	
	/**
	 * �û��˳���ת�û�ҳ��
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
		}
	
	/**
	 * ���ص�½ҳ��
	 * @return
	 */
	@RequestMapping("reg")
	public String register(HttpSession session){
		return "register";
	}
	
	/**
	 * �����޸�����ҳ��
	 * @return
	 */
	@RequestMapping("resetPwd")
	public String resetPwd(HttpSession session){
		
		
		return "resetpwd";
	}

	
	
	
}
