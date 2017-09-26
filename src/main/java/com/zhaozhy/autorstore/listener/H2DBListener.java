package com.zhaozhy.autorstore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ecside.common.H2DriverManagerDataSource;
/**
 * 
 * @Title				H2DBLintener.java
 * @Package		com.zhaozhy.autorstore.listener
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017年9月10日   下午6:07:09
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class H2DBListener implements ServletContextListener {

	private static final Log logger = LogFactory.getLog(H2DBListener.class);
	public static String webRootRealPath;
	
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			// SET DB_CLOSE_DELAY 10
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// do nothing
		}
		logger.info("h2db stoped...");
	}

	public void contextInitialized(ServletContextEvent sce) {
		logger.info("h2Listener initialize...");
		webRootRealPath = sce.getServletContext().getRealPath("/").replace('\\', '/');
		H2DriverManagerDataSource.setRoot(webRootRealPath);
	}

}
