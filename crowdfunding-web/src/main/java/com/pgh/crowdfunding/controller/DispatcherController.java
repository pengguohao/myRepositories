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
	 * 生成验证码
	 */
	@RequestMapping("yanzCode")
	public void yaCode(HttpServletResponse resp,HttpSession session,String dowhat){
		YanZCodeUtil yzCode=new YanZCodeUtil();
		HashMap<String, BufferedImage> codeMap = yzCode.buildCodeImage();
		//获取生成的验证码
		try {
			session.setAttribute(dowhat, yzCode.getCodeKey());
			ImageIO.write(codeMap.get(yzCode.getCodeKey()), "png", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 返回登陆页面
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
	 * 返回权限不足页面
	 */
	
	@RequestMapping("noPower")
	public String noPower(){
		return "error";
	}
	
	/**
	 * 用户退出跳转用户页面
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
		}
	
	/**
	 * 返回登陆页面
	 * @return
	 */
	@RequestMapping("reg")
	public String register(HttpSession session){
		return "register";
	}
	
	/**
	 * 返回修改密码页面
	 * @return
	 */
	@RequestMapping("resetPwd")
	public String resetPwd(HttpSession session){
		
		
		return "resetpwd";
	}

	
	
	
}
