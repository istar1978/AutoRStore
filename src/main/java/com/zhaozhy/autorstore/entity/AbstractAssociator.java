package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AbstractAssociator entity provides the base persistence definition of the
 * Associator entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAssociator implements java.io.Serializable {

	// Fields

	private String assId;
	private String assPhone;
	private String assName;
	private String assPassword;
	private String assGender;
	private Date assBirthday;
	private String assAddr;
	private Integer assPoint;
	private String assLevel;
	private Date assCreate;
	private Date assActive;
	private BigDecimal assBalance;
	private BigDecimal assPbalance;
	private String assCarno;
	private String assStat;

	// Constructors

	/** default constructor */
	public AbstractAssociator() {
	}

	/** minimal constructor */
	public AbstractAssociator(String assId, String assPhone,
			BigDecimal assBalance, String assStat) {
		this.assId = assId;
		this.assPhone = assPhone;
		this.assBalance = assBalance;
		this.assStat = assStat;
	}

	/** full constructor */
	public AbstractAssociator(String assId, String assPhone, String assName,
			String assPassword, String assGender, Date assBirthday,
			String assAddr, Integer assPoint, String assLevel, Date assCreate,
			Date assActive, BigDecimal assBalance, BigDecimal assPbalance,
			String assCarno, String assStat) {
		this.assId = assId;
		this.assPhone = assPhone;
		this.assName = assName;
		this.assPassword = assPassword;
		this.assGender = assGender;
		this.assBirthday = assBirthday;
		this.assAddr = assAddr;
		this.assPoint = assPoint;
		this.assLevel = assLevel;
		this.assCreate = assCreate;
		this.assActive = assActive;
		this.assBalance = assBalance;
		this.assPbalance = assPbalance;
		this.assCarno = assCarno;
		this.assStat = assStat;
	}

	// Property accessors

	public String getAssId() {
		return this.assId;
	}

	public void setAssId(String assId) {
		this.assId = assId;
	}

	public String getAssPhone() {
		return this.assPhone;
	}

	public void setAssPhone(String assPhone) {
		this.assPhone = assPhone;
	}

	public String getAssName() {
		return this.assName;
	}

	public void setAssName(String assName) {
		this.assName = assName;
	}

	public String getAssPassword() {
		return this.assPassword;
	}

	public void setAssPassword(String assPassword) {
		this.assPassword = assPassword;
	}

	public String getAssGender() {
		return this.assGender;
	}

	public void setAssGender(String assGender) {
		this.assGender = assGender;
	}

	public Date getAssBirthday() {
		return this.assBirthday;
	}

	public void setAssBirthday(Date assBirthday) {
		this.assBirthday = assBirthday;
	}

	public String getAssAddr() {
		return this.assAddr;
	}

	public void setAssAddr(String assAddr) {
		this.assAddr = assAddr;
	}

	public Integer getAssPoint() {
		return this.assPoint;
	}

	public void setAssPoint(Integer assPoint) {
		this.assPoint = assPoint;
	}

	public String getAssLevel() {
		return this.assLevel;
	}

	public void setAssLevel(String assLevel) {
		this.assLevel = assLevel;
	}

	public Date getAssCreate() {
		return this.assCreate;
	}

	public void setAssCreate(Date assCreate) {
		this.assCreate = assCreate;
	}

	public Date getAssActive() {
		return this.assActive;
	}

	public void setAssActive(Date assActive) {
		this.assActive = assActive;
	}

	public BigDecimal getAssBalance() {
		return this.assBalance;
	}

	public void setAssBalance(BigDecimal assBalance) {
		this.assBalance = assBalance;
	}

	public BigDecimal getAssPbalance() {
		return this.assPbalance;
	}

	public void setAssPbalance(BigDecimal assPbalance) {
		this.assPbalance = assPbalance;
	}

	public String getAssCarno() {
		return this.assCarno;
	}

	public void setAssCarno(String assCarno) {
		this.assCarno = assCarno;
	}

	public String getAssStat() {
		return this.assStat;
	}

	public void setAssStat(String assStat) {
		this.assStat = assStat;
	}

}