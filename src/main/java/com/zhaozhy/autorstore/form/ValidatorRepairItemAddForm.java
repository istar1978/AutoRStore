package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @Title:			ValidatorRepairItemForm.java
 * @Package:		com.zhaozhy.autorstore.form
 * @Created：	zhaozhy
 * @Date：			2017-6-5   下午03:10:06
 * @Desc:			TODO
 * @Version: 		V1.0
 *
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : 		zhongyong@qq.com
 */
public class ValidatorRepairItemAddForm extends ValidatorForm {

	private String action;
	private String rep_id;
	private String rep_name;
	private String rep_classify;
	private String rep_money;
	private String rep_stat;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRep_id() {
		return rep_id;
	}
	public void setRep_id(String rep_id) {
		this.rep_id = rep_id;
	}
	public String getRep_name() {
		return rep_name;
	}
	public void setRep_name(String rep_name) {
		this.rep_name = rep_name;
	}
	public String getRep_classify() {
		return rep_classify;
	}
	public void setRep_classify(String rep_classify) {
		this.rep_classify = rep_classify;
	}
	public String getRep_money() {
		return rep_money;
	}
	public void setRep_money(String rep_money) {
		this.rep_money = rep_money;
	}
	public String getRep_stat() {
		return rep_stat;
	}
	public void setRep_stat(String rep_stat) {
		this.rep_stat = rep_stat;
	}
	
	
}
