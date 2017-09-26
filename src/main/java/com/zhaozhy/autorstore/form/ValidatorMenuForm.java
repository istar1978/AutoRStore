package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * menuInput.jsp页面的数据接收
 * 
 * @author zhaozy
 * 
 */
public class ValidatorMenuForm extends ValidatorForm {

	private String action;
	private String menuId;
	private String menuName;
	private String menuAtt;
	private String urlAddr;
	/** 是否有效*/
	private String menStat;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuAtt() {
		return menuAtt;
	}
	public void setMenuAtt(String menuAtt) {
		this.menuAtt = menuAtt;
	}
	public String getUrlAddr() {
		return urlAddr;
	}
	public void setUrlAddr(String urlAddr) {
		this.urlAddr = urlAddr;
	}
	public String getMenStat() {
		return menStat;
	}
	public void setMenStat(String menStat) {
		this.menStat = menStat;
	}
	
	
	
}
