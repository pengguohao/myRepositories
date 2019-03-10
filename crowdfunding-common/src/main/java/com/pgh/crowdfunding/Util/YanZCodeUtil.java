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
	 * ��֤�뻭����
	 */
	public static final int YZ_WIDTH=140;
	/**
	 * ��֤��߶�
	 */
	public static final int YZ_HEIGTH=42;
	
	//������
	private List<String> fontName;
	
	// ������
	private List<Integer> fontStyle;
	
	
	// ��ɫ����
	private static Color[] ArryColor;

	
	//�������(0-9)������ĸ(���ִ�Сд)
	private String code;
	
	//��֤�뼯��
	private HashMap<String, BufferedImage> codeIamge;
	
	//���ɵ���֤��
	private String codeKey;
	/**
	 * ������֤�벢����
	 */
	public HashMap<String,BufferedImage> buildCodeImage(){
		codeIamge=new HashMap<String, BufferedImage>();
		//����һ������
		BufferedImage image=new BufferedImage(YanZCodeUtil.YZ_WIDTH, YanZCodeUtil.YZ_HEIGTH, BufferedImage.TYPE_INT_RGB);
		//��������
		Graphics2D g=image.createGraphics();
		//��ȡ�����ʵ���ɫ
		Color iniColor=g.getColor();
		//��������ɫ
		g.setColor(Color.WHITE);
		//��������ɫ
		g.fillRect(0, 0, YanZCodeUtil.YZ_WIDTH, YanZCodeUtil.YZ_HEIGTH);
		//������ɫ����
		Color[] color=YanZCodeUtil.getArryColor();
		//���������������
		Random random=new Random();
		//����������ֲ�������֤�뼯����
		StringBuilder codeNum=new StringBuilder();
		for(int i=0;i<4;i++) {
			String code=this.buildCode(random.nextInt(3));
			g.setColor(color[random.nextInt(9)]);
			g.setFont(new Font(this.getFortName().get(random.nextInt(4)), this.getFortStyle().get(random.nextInt(4)),26+random.nextInt(6)));
			g.drawString(code, 30*i+random.nextInt(20), 20+random.nextInt(25));
			codeNum.append(code);
		}
		//�����������
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
	 * ��ȡ��֤��
	 */
	public String getCodeKey() {
		if(null==codeIamge) {
			return null;
		}else {
			return codeKey;
		}
	}



	/**
	 * ��������ַ�
	 * @param 0=����1-9������  1=���ش�д��ĸ  2=����Сд��ĸ
	 * @return
	 */
	public String buildCode(int i){
		Random random=new Random();
		if(i==0) {
			//0-9�е�����һ����
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
	 * �������������
	 */
	public List<String> getFortName(){
		fontName=new ArrayList<String>();
		fontName.add("����");
		fontName.add("����");
		fontName.add("Times New Roman");
		fontName.add("Courier");
		return fontName;
	}
	/**
	 * ������ɫ����
	 * 
	 */
	public static Color[] getArryColor() {
		ArryColor = new Color[] {Color.black,Color.blue,Color.DARK_GRAY,Color.green,Color.orange,Color.pink,
								 Color.red,Color.YELLOW,Color.CYAN};
		return ArryColor;
	}
	
	/**
	 * ����������
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
	 * ����һ���������λ�����ַ���
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
