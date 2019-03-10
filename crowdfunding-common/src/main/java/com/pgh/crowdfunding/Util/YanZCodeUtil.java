package com.pgh.crowdfunding.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class YanZCodeUtil {
	/**
	 * 验证码画板宽度
	 */
	public static final int YZ_WIDTH=140;
	/**
	 * 验证码高度
	 */
	public static final int YZ_HEIGTH=42;
	
	//字体名
	private List<String> fontName;
	
	// 字体风格
	private List<Integer> fontStyle;
	
	
	// 颜色数组
	private static Color[] ArryColor;

	
	//随机数字(0-9)或者字母(区分大小写)
	private String code;
	
	//验证码集合
	private HashMap<String, BufferedImage> codeIamge;
	
	//生成的验证码
	private String codeKey;
	/**
	 * 生成验证码并返回
	 */
	public HashMap<String,BufferedImage> buildCodeImage(){
		codeIamge=new HashMap<String, BufferedImage>();
		//创建一个画板
		BufferedImage image=new BufferedImage(YanZCodeUtil.YZ_WIDTH, YanZCodeUtil.YZ_HEIGTH, BufferedImage.TYPE_INT_RGB);
		//创建画笔
		Graphics2D g=image.createGraphics();
		//先取到画笔的颜色
		Color iniColor=g.getColor();
		//给画笔上色
		g.setColor(Color.WHITE);
		//给画板上色
		g.fillRect(0, 0, YanZCodeUtil.YZ_WIDTH, YanZCodeUtil.YZ_HEIGTH);
		//设置颜色数组
		Color[] color=YanZCodeUtil.getArryColor();
		//设置随机数生成器
		Random random=new Random();
		//随机生成数字并放入验证码集合中
		StringBuilder codeNum=new StringBuilder();
		for(int i=0;i<4;i++) {
			String code=this.buildCode(random.nextInt(3));
			g.setColor(color[random.nextInt(9)]);
			g.setFont(new Font(this.getFortName().get(random.nextInt(4)), this.getFortStyle().get(random.nextInt(4)),26+random.nextInt(6)));
			g.drawString(code, 30*i+random.nextInt(20), 20+random.nextInt(25));
			codeNum.append(code);
		}
		//随机的线条数
		int code_line=1+random.nextInt(3);
		for(int i=0;i<code_line;i++) {
			g.setColor(color[random.nextInt(9)]);
			g.drawLine(40-random.nextInt(40), random.nextInt(40), 140-random.nextInt(60), random.nextInt(40));
		}
		g.setColor(iniColor);
		this.codeKey= codeNum.toString();
		codeIamge.put(this.codeKey, image);
		return codeIamge;
	}
	
	
	/**
	 * 获取验证码
	 */
	public String getCodeKey() {
		if(null==codeIamge) {
			return null;
		}else {
			return codeKey;
		}
	}



	/**
	 * 返回随机字符
	 * @param 0=返回1-9的数字  1=返回大写字母  2=返回小写字母
	 * @return
	 */
	public String buildCode(int i){
		Random random=new Random();
		if(i==0) {
			//0-9中的任意一个数
			code=new String().valueOf(random.nextInt(10));
		}else if(i==1) {
			code="";
			code+=(char)(random.nextInt(26)+'A');
		}else {
			code="";
			code+=(char)(random.nextInt(26)+'a');
		}
		return code;
	}
	
	/**
	 * 返回随机字体名
	 */
	public List<String> getFortName(){
		fontName=new ArrayList<String>();
		fontName.add("宋体");
		fontName.add("仿宋");
		fontName.add("Times New Roman");
		fontName.add("Courier");
		return fontName;
	}
	/**
	 * 返回颜色数组
	 * 
	 */
	public static Color[] getArryColor() {
		ArryColor = new Color[] {Color.black,Color.blue,Color.DARK_GRAY,Color.green,Color.orange,Color.pink,
								 Color.red,Color.YELLOW,Color.CYAN};
		return ArryColor;
	}
	
	/**
	 * 返回字体风格
	 */
	public List<Integer> getFortStyle(){
		fontStyle=new ArrayList<Integer>();
		fontStyle.add(Font.BOLD);
		fontStyle.add(Font.ITALIC);
		fontStyle.add(Font.LAYOUT_NO_LIMIT_CONTEXT);
		fontStyle.add(Font.PLAIN);
		return fontStyle;
	}
	
	/**
	 * 返回一个随机的四位数字字符串
	 */
	
	public static String build4CodeByRandom() {
		Random random=new Random();
		String code="";
		for(int i=0;i<4;i++) {
			code+=random.nextInt(10);
		}
		return code;
	}
	
}
