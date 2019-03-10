package com.pgh.crowdfunding.Util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

public class CheckUtil {

	/**
	 * 
	 * @param reg 正则表达式
	 * @param checkInfo 待检验的信息
	 * @return
	 */
	public static boolean checkReg(String reg,String checkInfo) {
	 return Pattern.compile(reg).matcher(checkInfo).matches();
	}
	
	/**
	 * 
	 * @param code 输入的验证码
	 * @param session 系统生成的验证码
	 * @param dowhat 验证码的用途
	 * @return
	 */
	public static boolean checkYanZhengCode(String code,HttpSession session,String dowhat) {
		if(null!=code){
			String strCode=(String) session.getAttribute(dowhat);
			if(null!=strCode&&!"".equals(strCode)) {
				if(code.toUpperCase().equals(strCode.toUpperCase())){
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

}
