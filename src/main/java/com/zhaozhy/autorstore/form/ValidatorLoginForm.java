package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * 接收登陆表单的数据
 * 
 * @author zhaozy
 * 
 */
public class ValidatorLoginForm extends ValidatorForm {

	
	private String action;
	private String password;
	private String username;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
