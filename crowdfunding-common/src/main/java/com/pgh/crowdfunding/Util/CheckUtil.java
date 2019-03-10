package com.pgh.crowdfunding.Util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

public class CheckUtil {

	/**
	 * 
	 * @param reg ������ʽ
	 * @param checkInfo ���������Ϣ
	 * @return
	 */
	public static boolean checkReg(String reg,String checkInfo) {
	 return Pattern.compile(reg).matcher(checkInfo).matches();
	}
	
	/**
	 * 
	 * @param code �������֤��
	 * @param session ϵͳ���ɵ���֤��
	 * @param dowhat ��֤�����;
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
