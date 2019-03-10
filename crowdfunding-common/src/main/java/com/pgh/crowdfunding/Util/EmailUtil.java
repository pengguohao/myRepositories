package com.pgh.crowdfunding.Util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;
import java.util.Properties;

public class EmailUtil {
    
    //�ʼ�������������
    // QQ����� SMTP ��������ַΪ: smtp.qq.com
    private static String myEmailSMTPHost = "smtp.qq.com";
    
    //����������
    private static String myEmailAccount = "775840588@qq.com";
    
    //�������������루��Ȩ�룩
    //�ڿ���SMTP����ʱ���ȡ��һ����Ȩ�룬����Ȩ����������
    private static String myEmailPassword = "ikjehrbyorgubbjg";
    
    /**
     * �ʼ����������ɱ༭���ţ������ͣ�������˽�ţ�
     *
     * @param toEmailAddress �ռ����ַ
     * @param emailTitle �ʼ�����
     * @param emailContent �ʼ�����
     * @throws Exception
     */
    public static void sendEmail(String toEmailAddress, String emailTitle, String emailContent) throws Exception{
             
        Properties props = new Properties();
         
        // ����debug����
        props.setProperty("mail.debug", "true");
                 
        // ���ͷ�������Ҫ�����֤
        props.setProperty("mail.smtp.auth", "true");
         
        // �˿ں�
        props.put("mail.smtp.port", 465);
         
        // �����ʼ�������������
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
         
        // �����ʼ�Э������
        props.setProperty("mail.transport.protocol", "smtp");
         
        /**SSL��֤��ע����Ѷ�����ǻ���SSL���ܵģ�������Ҫ�����ſ���ʹ��**/
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
         
        //�����Ƿ�ʹ��ssl��ȫ���ӣ�һ�㶼ʹ�ã�
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
         
        //�����Ự
        Session session = Session.getInstance(props);
         
        //��ȡ�ʼ�����
        //���͵���Ϣ�����ڹ۲���ģʽ������Ƶ�
        Message msg = new MimeMessage(session);
         
        //�����ʼ�����
        msg.setSubject(emailTitle);
         
        //�����ʼ�����
        //ʹ��StringBuilder����ΪStringBuilder�����ٶȻ��String�죬�����̰߳�ȫ��Ҳ����
        StringBuilder builder = new StringBuilder();
        builder.append(emailContent);
        //д������
//        builder.append();
         
        //д���ҵĹ���
        builder.append("\n������" + "https://www.baidu.com");
         
        //����Ҫ��������ַ����ĸ�ʽ
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
        //�����ݺ�����ʼ����͵�ʱ��
//        builder.append("\nʱ�䣺" + sdf.format(new Date()));
         
        //������ʾ�ķ���ʱ��
//        msg.setSentDate(new Date());
         
        //�����ʼ�����
        msg.setText(builder.toString());
         
        //���÷���������
        // InternetAddress �����������ֱ�Ϊ: ����������, ��ʾ���ǳ�(ֻ������ʾ, û���ر��Ҫ��), �ǳƵ��ַ�������
        msg.setFrom(new InternetAddress(myEmailAccount,"���黰", "UTF-8"));
         
        //�õ��ʲ����
        Transport transport = session.getTransport();
         
        //�����Լ��������˻�
        //���벻���Լ�QQ��������룬�����ڿ���SMTP����ʱ����ȡ������Ȩ��
        //connect(host, user, password)
        transport.connect( myEmailSMTPHost, myEmailAccount, myEmailPassword);
         
        //�����ʼ�
        transport.sendMessage(msg, new Address[] { new InternetAddress(toEmailAddress) });
         
        //�����ʼ����浽����
		/*
		 * OutputStream out = new FileOutputStream("MyEmail.eml"); msg.writeTo(out);
		 * out.flush(); out.close();
		 */

        transport.close();
    }
}
