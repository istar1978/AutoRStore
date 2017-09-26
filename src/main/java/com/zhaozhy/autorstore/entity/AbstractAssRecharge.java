package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AbstractAssRecharge entity provides the base persistence definition of the
 * AssRecharge entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAssRecharge implements java.io.Serializable {

	// Fields

	private String recId;
	private String assId;
	private BigDecimal recAmount;
	private String recDate;
	private String recTime;
	private BigDecimal recPresent;
	private String recText;
	private String staId;

	// Constructors

	/** default constructor */
	public AbstractAssRecharge() {
	}

	/** minimal constructor */
	public AbstractAssRecharge(String recId, String assId, BigDecimal recAmount,
			String recDate, String recTime,String staId) {
		this.recId = recId;
		this.assId = assId;
		this.recAmount = recAmount;
		this.recDate = recDate;
		this.recTime = recTime;
		this.staId=staId;
	}

	/** full constructor */
	public AbstractAssRecharge(String recId, String assId, BigDecimal recAmount,
			String recDate, String recTime, BigDecimal recPresent, String recText,String staId) {
		this.recId = recId;
		this.assId = assId;
		this.recAmount = recAmount;
		this.recDate = recDate;
		this.recTime = recTime;
		this.recPresent = recPresent;
		this.recText = recText;
		this.staId=staId;
	}

	// Property accessors

	public String getRecId() {
		return this.recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public String getAssId() {
		return this.assId;
	}

	public void setAssId(String assId) {
		this.assId = assId;
	}

	public BigDecimal getRecAmount() {
		return this.recAmount;
	}

	public void setRecAmount(BigDecimal recAmount) {
		this.recAmount = recAmount;
	}

	public String getRecDate() {
		return this.recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public String getRecTime() {
		return this.recTime;
	}

	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}

	public BigDecimal getRecPresent() {
		return this.recPresent;
	}

	public void setRecPresent(BigDecimal recPresent) {
		this.recPresent = recPresent;
	}

	public String getRecText() {
		return this.recText;
	}

	public void setRecText(String recText) {
		this.recText = recText;
	}

	public String getStaId() {
		return staId;
	}

	public void setStaId(String staId) {
		this.staId = staId;
	}

}