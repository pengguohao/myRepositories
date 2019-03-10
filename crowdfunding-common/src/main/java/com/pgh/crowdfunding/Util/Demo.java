package com.pgh.crowdfunding.Util;

public class Demo {

	public static void main(String[] args) throws Exception {
		String emailContent1="\n" + "【神话情话】："+"\n"+"    您的邮箱注册码：11111。工作人员不会向您索要，索要验证码的都是骗子，若果非本人操作请忽略。";
		EmailUtil.sendEmail("923245201@qq.com", "邮箱验证码", emailContent1);
	}

}
