package com.zhaozhy.autorstore.util;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmailSenderTrigger extends TimerTask {
	private static final Log log = LogFactory.getLog(EmailSenderTrigger.class);
	@Override
	public void run() {
			try {
				boolean ifSend=checkNewEmail();
				if(ifSend){
					sendEmail();
				}
			} catch (Exception e) {
//				e.printStackTrace();
				log.error(e);
			}
	}

	public boolean checkNewEmail() throws Exception {
//		System.out.println("检查新的未发送邮件");
		File file=new File(DataUtil.getDBFilepath4Mail());
		 
		if(file.exists()){
			File[] files=file.listFiles();
			if(files.length==0){
				log.info("没有需要发送邮件的数据库备份文件！");
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	public void sendEmail() throws MessagingException, IOException {
		SendUtil.doSendEmail();
/*
		//发送完邮件后，清空D://autorstore/dbdata文件夹
		File file=new File(DataUtil.getValueByKeyPro(DicDataUtil.MAIL_FILEPATH));
		File[] fs=file.listFiles();
		for(File f:fs){
			f.delete();
		}
		*/
	}
}
