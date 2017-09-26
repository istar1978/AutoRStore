package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @author zhaozy
 *
 */
public class ValidatorDepMenuForm extends ValidatorForm {

	private String busi;
	private String[] code;
	private String action;
	public String getBusi() {
		return busi;
	}
	public void setBusi(String busi) {
		this.busi = busi;
	}
	public String[] getCode() {
		return code;
	}
	public void setCode(String[] code) {
		this.code = code;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
