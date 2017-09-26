package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @Title				ValidatorConsumeCartForm.java
 * @Package		com.zhaozhy.autorstore.form
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-21   下午10:13:37
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ValidatorConsumeCartForm extends ValidatorForm {
	private String ass_id;
	private String rep_id;
	private String mat_id;
	private String cart_num;
	private String cart_perprice;
	private String cart_money;
	public String getAss_id() {
		return ass_id;
	}
	public void setAss_id(String ass_id) {
		this.ass_id = ass_id;
	}
	public String getRep_id() {
		return rep_id;
	}
	public void setRep_id(String rep_id) {
		this.rep_id = rep_id;
	}
	public String getMat_id() {
		return mat_id;
	}
	public void setMat_id(String mat_id) {
		this.mat_id = mat_id;
	}
	public String getCart_num() {
		return cart_num;
	}
	public void setCart_num(String cart_num) {
		this.cart_num = cart_num;
	}
	public String getCart_perprice() {
		return cart_perprice;
	}
	public void setCart_perprice(String cart_perprice) {
		this.cart_perprice = cart_perprice;
	}
	public String getCart_money() {
		return cart_money;
	}
	public void setCart_money(String cart_money) {
		this.cart_money = cart_money;
	}
	
	
}
