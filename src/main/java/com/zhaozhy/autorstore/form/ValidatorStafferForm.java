package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @author zhaozy
 *
 */
public class ValidatorStafferForm extends ValidatorForm {

	private String action;
	
	private String s_id;
	private String s_name;
	private String s_password;
	private String s_position;
	private String s_level;
	private String dep_id;
	private String branch_id;
	private String s_stat;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_password() {
		return s_password;
	}
	public void setS_password(String s_password) {
		this.s_password = s_password;
	}
	public String getS_position() {
		return s_position;
	}
	public void setS_position(String s_position) {
		this.s_position = s_position;
	}
	public String getS_level() {
		return s_level;
	}
	public void setS_level(String s_level) {
		this.s_level = s_level;
	}
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getS_stat() {
		return s_stat;
	}
	public void setS_stat(String s_stat) {
		this.s_stat = s_stat;
	}
	
	
	
}
