package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @Title				ValidatorSalesForm.java
 * @Package		com.zhaozhy.autorstore.form
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-16   下午04:06:18
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ValidatorSalesForm extends ValidatorForm {

	public String ass_id;
	public String yorn;
	public String com_id;
	public String rep_id;
	public String mat_id;
	public String cart_num;
	
	public String getCart_num() {
		return cart_num;
	}
	public void setCart_num(String cart_num) {
		this.cart_num = cart_num;
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
	public String getAss_id() {
		return ass_id;
	}
	public void setAss_id(String ass_id) {
		this.ass_id = ass_id;
	}
	public String getYorn() {
		return yorn;
	}
	public void setYorn(String yorn) {
		this.yorn = yorn;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	
	
}
