package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @Title:			ValidatorDicData.java
 * @Package:		com.zhaozhy.autorstore.form
 * @Created：	zhaozhy
 * @Date：			2017-5-27   上午08:45:24
 * @Desc:			TODO 参数表form
 * @Version: 		V1.0
 *
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : 		zhongyong@qq.com
 */
public class ValidatorDicDataForm extends ValidatorForm {

	private String dic_large;
	private String dic_value;
	private String dic_name;
	private String dic_text;
	
	private String action;
	
	
	public String getDic_large() {
		return dic_large;
	}
	public void setDic_large(String dic_large) {
		this.dic_large = dic_large;
	}
	public String getDic_value() {
		return dic_value;
	}
	public void setDic_value(String dic_value) {
		this.dic_value = dic_value;
	}
	public String getDic_name() {
		return dic_name;
	}
	public void setDic_name(String dic_name) {
		this.dic_name = dic_name;
	}
	public String getDic_text() {
		return dic_text;
	}
	public void setDic_text(String dic_text) {
		this.dic_text = dic_text;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
