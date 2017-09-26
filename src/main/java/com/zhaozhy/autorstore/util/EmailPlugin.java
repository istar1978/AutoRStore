package com.zhaozhy.autorstore.util;

import java.util.Timer;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

public class EmailPlugin implements PlugIn {

	/**
	 * 上下文被Destroy的时候，会调用该方法，释放相应的资源.
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Struts初始化的同时，会自动初始化插件.
	 */
	public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		Timer timer = new Timer();
        //每两小时触发一次，调用EmailSenderTrigger的run方法.
        timer.schedule(new EmailSenderTrigger(), 0,12*60*60*1000);
//		 timer.schedule(new EmailSenderTrigger(), 0,60*1000);
	}

}
