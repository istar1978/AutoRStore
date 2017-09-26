package com.zhaozhy.autorstore.others;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.zhaozhy.autorstore.util.DateUtil;

import junit.framework.TestCase;

public class MailTest extends TestCase{

	public void testSendEmail() throws MessagingException {
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		Session sendMailSession =Session.getDefaultInstance(props);
		Message mailMessage=new MimeMessage(sendMailSession);//根据session创建一个邮件消息   
		Address from=new InternetAddress("longa-114@163.com");//创建邮件发送者地址 
		mailMessage.setFrom(from);//设置邮件消息的发送者  
		mailMessage.setSubject(DateUtil.getDateString17()+"autorstore");//邮件标题
		mailMessage.setContent("发送成功！", "text/html;charset=utf-8");
		Transport transport=sendMailSession.getTransport();
		
		Address to =new InternetAddress("zhongyong@qq.com");//创建邮件的接收者地址
		mailMessage.setRecipient(Message.RecipientType.TO, to);//设置邮件消息的接收者
		 mailMessage.setSubject("zt");//设置邮件消息的主题  
         mailMessage.setSentDate(new Date());//设置邮件消息发送的时间  
         //mailMessage.setText(mailInfo.getContent());//设置邮件消息的主要内容
       //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象  
         Multipart mainPart = new MimeMultipart();  
         MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含附件内容的MimeBodyPart  
         //设置HTML内容  
         messageBodyPart.setContent("cs","text/html; charset=utf-8");  
         mainPart.addBodyPart(messageBodyPart);  
       //存在附件  
         String[] filePaths = new String[]{"D:/autorstore_20170618.sql"};
         if (filePaths != null && filePaths.length > 0) {  
             for(String filePath:filePaths){  
                 messageBodyPart = new MimeBodyPart();  
                 File file = new File(filePath);   
                 if(file.exists()){//附件存在磁盘中  
                     FileDataSource fds = new FileDataSource(file);//得到数据源  
                     messageBodyPart.setDataHandler(new DataHandler(fds));//得到附件本身并至入BodyPart  
                     messageBodyPart.setFileName(file.getName());//得到文件名同样至入BodyPart  
                     mainPart.addBodyPart(messageBodyPart);  
                 }  
             }  
         }  
       //将MimeMultipart对象设置为邮件内容     
         mailMessage.setContent(mainPart);  
         Transport.send(mailMessage);//发送邮件  
	}
}
