package com.zhaozhy.autorstore.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 
 * @Title				SendUtil.java
 * @Package		com.zhaozhy.autorstore.util
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-7-4   下午03:44:45
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class SendUtil {

	public static void doSendEmail() throws IOException, MessagingException{
		Properties pros=new Properties();
		String mailHost=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_SMTP_HOST);
		pros.setProperty(DicDataUtil.MAIL_SMTP_HOST, mailHost);
		String sender_username=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_SENDER_USERNAME);
		pros.setProperty(DicDataUtil.MAIL_SENDER_USERNAME, sender_username);
		String sender_password=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_SENDER_PASSWORD);
		pros.setProperty(DicDataUtil.MAIL_SENDER_PASSWORD, sender_password);
		String sessionDebug=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_SESSION_DEBUG);
		pros.setProperty(DicDataUtil.MAIL_SESSION_DEBUG, sessionDebug);
		String receiver_usernames=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_RECEIVER_USERNAMES);
		
		String split=DataUtil.getValueByKeyPro(DicDataUtil.GLOBAL_STRING_SPLIT);
		
		for(String receiver:receiver_usernames.split(split)){
			sendEmail(pros,receiver);
		}
		
	}
	
	private static void sendEmail(Properties pros,String receiver) throws IOException, MessagingException{
		Session session=Session.getInstance(pros);
		String debug=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_SESSION_DEBUG);
		if(debug.toLowerCase().equals(DicDataUtil.BOOLEAN_TRUE)){
			session.setDebug(true);
		}else{
			session.setDebug(false);
		}
		MimeMessage message=new MimeMessage(session);
		
		//sender
		InternetAddress from=new InternetAddress(pros.getProperty(DicDataUtil.MAIL_SENDER_USERNAME));
		message.setFrom(from);
		//reveiver
		InternetAddress to=new InternetAddress(receiver);
		message.setRecipient(Message.RecipientType.TO, to);
		
		//mail subject
		message.setSubject(DateUtil.getDateString17()+"AutoRStore SQL");
		// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        
     // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent("数据库备份", "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        
//        String filePath=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_FILEPATH);
//        String dbtype=DataUtil.getValueByKeyPro(DicDataUtil.DBTYPE);
        File file=new File(DataUtil.getDBFilepath4Mail());
       
        
        File attachment=new File(file.listFiles()[0].getAbsolutePath());
        
        BodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        
        // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
        // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
        //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
        
        //MimeUtility.encodeWord可以避免文件名乱码
        attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
        multipart.addBodyPart(attachmentBodyPart);
        
        // 将multipart对象放到message中
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();

        Transport transport = session.getTransport("smtp");
        // smtp验证，就是你用来发邮件的邮箱用户名密码
        transport.connect(pros.getProperty(DicDataUtil.MAIL_SMTP_HOST), pros.getProperty(DicDataUtil.MAIL_SENDER_USERNAME), pros.getProperty(DicDataUtil.MAIL_SENDER_PASSWORD));
        // 发送
        transport.sendMessage(message, message.getAllRecipients());

        System.out.println(DateUtil.getDateString17()+"send mail success!");
	}
}
