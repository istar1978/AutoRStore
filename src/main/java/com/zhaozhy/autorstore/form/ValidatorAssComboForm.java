package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @Title				ValidatorAssComboForm.java
 * @Package		com.zhaozhy.autorstore.form
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-6   下午07:52:47
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ValidatorAssComboForm extends ValidatorForm {

	private String com_id;
	private String com_name;
	private String rep_id;
	private String ass_id;
	private String com_sdate;
	private String com_edate;
	private String com_date;
	private String com_time;
	private String com_desc;
	private String com_item;
	private String com_price;
	private String com_stat;
	
	
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getRep_id() {
		return rep_id;
	}
	public void setRep_id(String rep_id) {
		this.rep_id = rep_id;
	}
	public String getAss_id() {
		return ass_id;
	}
	public void setAss_id(String ass_id) {
		this.ass_id = ass_id;
	}
	public String getCom_sdate() {
		return com_sdate;
	}
	public void setCom_sdate(String com_sdate) {
		this.com_sdate = com_sdate;
	}
	public String getCom_edate() {
		return com_edate;
	}
	public void setCom_edate(String com_edate) {
		this.com_edate = com_edate;
	}
	public String getCom_date() {
		return com_date;
	}
	public void setCom_date(String com_date) {
		this.com_date = com_date;
	}
	public String getCom_time() {
		return com_time;
	}
	public void setCom_time(String com_time) {
		this.com_time = com_time;
	}
	public String getCom_desc() {
		return com_desc;
	}
	public void setCom_desc(String com_desc) {
		this.com_desc = com_desc;
	}
	public String getCom_item() {
		return com_item;
	}
	public void setCom_item(String com_item) {
		this.com_item = com_item;
	}
	public String getCom_stat() {
		return com_stat;
	}
	public void setCom_stat(String com_stat) {
		this.com_stat = com_stat;
	}
	public String getCom_price() {
		return com_price;
	}
	public void setCom_price(String com_price) {
		this.com_price = com_price;
	}
	
	
	
}
