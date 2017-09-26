package com.zhaozhy.autorstore.form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @author zhaozy
 *
 */
public class ValidatorAssociatorForm extends ValidatorForm {

	private String action;
	private String a_id;
	private String a_password;
	
	private String a_no ;
	private String a_name;
	private String a_gender;
	private String a_birthday;
	private String a_addr;
	private String a_tel;
	private String a_level;
	
	private String a_stat;
	
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;
	
	
	private String a_carno;
	private String a_balance;
	private String a_pbalance;
	
	/** 是否 */
	private String yorn;
	
	public String getYorn() {
		return yorn;
	}
	public void setYorn(String yorn) {
		this.yorn = yorn;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword1() {
		return newPassword1;
	}
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	public String getA_stat() {
		return a_stat;
	}
	public void setA_stat(String a_stat) {
		this.a_stat = a_stat;
	}
	public String getA_no() {
		return a_no;
	}
	public void setA_no(String a_no) {
		this.a_no = a_no;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getA_gender() {
		return a_gender;
	}
	public void setA_gender(String a_gender) {
		this.a_gender = a_gender;
	}
	public String getA_birthday() {
		return a_birthday;
	}
	public void setA_birthday(String a_birthday) {
		this.a_birthday = a_birthday;
	}
	public String getA_addr() {
		return a_addr;
	}
	public void setA_addr(String a_addr) {
		this.a_addr = a_addr;
	}
	public String getA_tel() {
		return a_tel;
	}
	public void setA_tel(String a_tel) {
		this.a_tel = a_tel;
	}
	public String getA_level() {
		return a_level;
	}
	public void setA_level(String a_level) {
		this.a_level = a_level;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public String getA_password() {
		return a_password;
	}
	public void setA_password(String a_password) {
		this.a_password = a_password;
	}
	public String getA_carno() {
		return a_carno;
	}
	public void setA_carno(String a_carno) {
		this.a_carno = a_carno;
	}
	public String getA_balance() {
		return a_balance;
	}
	public void setA_balance(String a_balance) {
		this.a_balance = a_balance;
	}
	public String getA_pbalance() {
		return a_pbalance;
	}
	public void setA_pbalance(String a_pbalance) {
		this.a_pbalance = a_pbalance;
	}
	 
}
