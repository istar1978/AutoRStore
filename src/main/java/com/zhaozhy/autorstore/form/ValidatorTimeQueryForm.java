package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * @author zhaozy
 * 
 */
public class ValidatorTimeQueryForm extends ValidatorForm {

	private String fromDate;
	private String endDate;
	private String b_id;
	
	private String d_id;
	
	

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		//String date=fromDate.replace("-", "");
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		//String date=endDate.replace("-", "");
		this.endDate = endDate;
	}

}
