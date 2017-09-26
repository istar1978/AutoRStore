package com.zhaozhy.autorstore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

public class H2DriverManagerDataSource extends DriverManagerDataSource
		implements InitializingBean, DisposableBean {
	private static String rootRealPath = "";

	private static final String C_RootRealPath = "\\{root\\}";

	private String defaultDB;

	private boolean autoReset = true;

	private Connection conn;

	public static void setRoot(String root) {
		rootRealPath = root;
	}

	private static String getDBPathFromUrl(String url) {
		if (url == null || url.length() < 1 || url.indexOf("file:") < 0)
			return null;
		url = url.substring(url.indexOf("file:") + 5);
		return url;
	}

	public void destroy() throws Exception {
		if (conn != null)
			conn.close();
	}

	public void afterPropertiesSet() throws Exception {
		if (autoReset) {
			String dbPath = getDBPathFromUrl(getUrl());
			try {

//				File dataFile = new File(dbPath + ".data.db");
				File dataFile=new File(dbPath+".h2.db");
//				File indexFile = new File(dbPath + ".index.db");
				File traceFile = new File(dbPath + ".trace.db");

				FileCopyUtils.copy(new FileInputStream(defaultDB + ".h2.db"),
						new FileOutputStream(dataFile));
//				FileCopyUtils.copy(new FileInputStream(defaultDB + ".index.db"),new FileOutputStream(indexFile));
				FileCopyUtils.copy(
						new FileInputStream(defaultDB + ".trace.db"),
						new FileOutputStream(traceFile));

			} catch (IOException e) {
				logger.error("Copy Default db file error");
			}
		}

		conn = getConnection();
		int i = 0;
		while (conn == null && i < 5) {
			conn = getConnection();
			i++;
		}

	}

	public void setUrl(String url) {
		if (!StringUtils.hasText(url)) {
			throw new IllegalArgumentException("url must not be empty");
		}
		url = url.replaceFirst(C_RootRealPath, rootRealPath);
		super.setUrl(url.trim());
	}

	public void setDefaultDB(String defaultDB) {
		if (!StringUtils.hasText(defaultDB)) {
			throw new IllegalArgumentException("url must not be empty");
		}
		this.defaultDB = defaultDB.replaceFirst(C_RootRealPath, rootRealPath)
				.trim();
	}

	public boolean isAutoReset() {
		return autoReset;
	}

	public void setAutoReset(boolean autoReset) {
		this.autoReset = autoReset;
	}

	public String getDefaultDB() {
		return defaultDB;
	}
}
