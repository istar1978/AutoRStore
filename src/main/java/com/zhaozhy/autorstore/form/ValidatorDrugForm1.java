package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @author zhaozy
 *
 */
public class ValidatorDrugForm1 extends ValidatorForm {

	private String action;
	private String dr_id;
	private String dr_name;
	private String dr_category;
	private String dr_val;
	private String pre_price;
	private String real_price;
	private String dr_factory;
	private String pro_date;
	private String in_date;
	private String dr_num;
	private String dr_stat;
	
	private String branch_id;
	
	
	
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDr_id() {
		return dr_id;
	}
	public void setDr_id(String dr_id) {
		this.dr_id = dr_id;
	}
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
	}
	public String getDr_category() {
		return dr_category;
	}
	public void setDr_category(String dr_category) {
		this.dr_category = dr_category;
	}
	public String getDr_val() {
		return dr_val;
	}
	public void setDr_val(String dr_val) {
		this.dr_val = dr_val;
	}
	public String getPre_price() {
		return pre_price;
	}
	public void setPre_price(String pre_price) {
		this.pre_price = pre_price;
	}
	public String getReal_price() {
		return real_price;
	}
	public void setReal_price(String real_price) {
		this.real_price = real_price;
	}
	public String getDr_factory() {
		return dr_factory;
	}
	public void setDr_factory(String dr_factory) {
		this.dr_factory = dr_factory;
	}
	public String getPro_date() {
		return pro_date;
	}
	public void setPro_date(String pro_date) {
		this.pro_date = pro_date;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getDr_num() {
		return dr_num;
	}
	public void setDr_num(String dr_num) {
		this.dr_num = dr_num;
	}
	public String getDr_stat() {
		return dr_stat;
	}
	public void setDr_stat(String dr_stat) {
		this.dr_stat = dr_stat;
	}
	
	
}
