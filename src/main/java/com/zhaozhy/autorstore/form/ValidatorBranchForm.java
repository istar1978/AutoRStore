package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @author zhaozy
 *
 */
public class ValidatorBranchForm extends ValidatorForm {

	private String action;
	
	private String b_id;
	private String b_name;
	private String b_level;
	private String up_branch;
	private String s_stat;
	private String b_stat;
	
	public String getB_stat() {
		return b_stat;
	}
	public void setB_stat(String b_stat) {
		this.b_stat = b_stat;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_level() {
		return b_level;
	}
	public void setB_level(String b_level) {
		this.b_level = b_level;
	}
	public String getUp_branch() {
		return up_branch;
	}
	public void setUp_branch(String up_branch) {
		this.up_branch = up_branch;
	}
	public String getS_stat() {
		return s_stat;
	}
	public void setS_stat(String s_stat) {
		this.s_stat = s_stat;
	}

	
}
