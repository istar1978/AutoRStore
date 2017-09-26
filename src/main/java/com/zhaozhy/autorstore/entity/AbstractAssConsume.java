package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AbstractAssConsume entity provides the base persistence definition of the
 * AssConsume entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAssConsume implements java.io.Serializable {

	// Fields

	private String conId;
	private String assId;
	private String conDate;
	private String conTime;
	private BigDecimal conAmount;
	private BigDecimal conRamount;
	private String conType;
	private Integer conPoint;
	private String staId;
	private String braId;
//	private String conCombo;
	private String comId;
	private String conDesc;
	private BigDecimal conCollect;
	private BigDecimal conChange;
	private String conMark;
	

	// Constructors

	/** default constructor */
	public AbstractAssConsume() {
	}

	/** minimal constructor */
	public AbstractAssConsume(String conId, String assId, String conDate,
			String conTime, BigDecimal conAmount, BigDecimal conRamount,
			String conType, Integer conPoint, String staId, String braId,
			BigDecimal conCollect,BigDecimal conChange,String conMark) {
		this.conId = conId;
		this.assId = assId;
		this.conDate = conDate;
		this.conTime = conTime;
		this.conAmount = conAmount;
		this.conRamount = conRamount;
		this.conType = conType;
		this.conPoint = conPoint;
		this.staId = staId;
		this.braId = braId;
//		this.conCombo = conCombo;
		this.conCollect = conCollect;
		this.conChange = conChange;
		this.conMark=conMark;
	}

	/** full constructor */
	public AbstractAssConsume(String conId, String assId, String conDate,
			String conTime, BigDecimal conAmount, BigDecimal conRamount,
			String conType, Integer conPoint, String staId, String braId,
			String comId, String conDesc,BigDecimal conCollect,BigDecimal conChange,String conMark) {
		this.conId = conId;
		this.assId = assId;
		this.conDate = conDate;
		this.conTime = conTime;
		this.conAmount = conAmount;
		this.conRamount = conRamount;
		this.conType = conType;
		this.conPoint = conPoint;
		this.staId = staId;
		this.braId = braId;
//		this.conCombo = conCombo;
		this.comId = comId;
		this.conDesc = conDesc;
		this.conCollect = conCollect;
		this.conChange = conChange;
		this.conMark=conMark;
	}

	// Property accessors

	public String getConId() {
		return this.conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getAssId() {
		return this.assId;
	}

	public void setAssId(String assId) {
		this.assId = assId;
	}

	public String getConDate() {
		return this.conDate;
	}

	public void setConDate(String conDate) {
		this.conDate = conDate;
	}

	public String getConTime() {
		return this.conTime;
	}

	public void setConTime(String conTime) {
		this.conTime = conTime;
	}

	public BigDecimal getConAmount() {
		return this.conAmount;
	}

	public void setConAmount(BigDecimal conAmount) {
		this.conAmount = conAmount;
	}

	public BigDecimal getConRamount() {
		return this.conRamount;
	}

	public void setConRamount(BigDecimal conRamount) {
		this.conRamount = conRamount;
	}

	public String getConType() {
		return this.conType;
	}

	public void setConType(String conType) {
		this.conType = conType;
	}

	public Integer getConPoint() {
		return this.conPoint;
	}

	public void setConPoint(Integer conPoint) {
		this.conPoint = conPoint;
	}

	public String getStaId() {
		return this.staId;
	}

	public void setStaId(String staId) {
		this.staId = staId;
	}

	public String getBraId() {
		return this.braId;
	}

	public void setBraId(String braId) {
		this.braId = braId;
	}


	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getConDesc() {
		return this.conDesc;
	}

	public void setConDesc(String conDesc) {
		this.conDesc = conDesc;
	}

	public BigDecimal getConCollect() {
		return conCollect;
	}

	public void setConCollect(BigDecimal conCollect) {
		this.conCollect = conCollect;
	}

	public BigDecimal getConChange() {
		return conChange;
	}

	public void setConChange(BigDecimal conChange) {
		this.conChange = conChange;
	}

	public String getConMark() {
		return conMark;
	}

	public void setConMark(String conMark) {
		this.conMark = conMark;
	}
	
	

}