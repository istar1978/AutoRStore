package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AbstractConsumeList entity provides the base persistence definition of the
 * ConsumeList entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractConsumeList implements java.io.Serializable {

	// Fields

	private String lisId;
	private String conId;
	private String repId;
	private String matId;
	private Integer lisNum;
	private BigDecimal lisPrice;

	// Constructors

	/** default constructor */
	public AbstractConsumeList() {
	}

	/** minimal constructor */
	public AbstractConsumeList(String lisId, String conId, Integer lisNum,
			BigDecimal lisPrice) {
		this.lisId = lisId;
		this.conId = conId;
		this.lisNum = lisNum;
		this.lisPrice = lisPrice;
	}

	/** full constructor */
	public AbstractConsumeList(String lisId, String conId, String repId,
			String matId, Integer lisNum, BigDecimal lisPrice) {
		this.lisId = lisId;
		this.conId = conId;
		this.repId = repId;
		this.matId = matId;
		this.lisNum = lisNum;
		this.lisPrice = lisPrice;
	}

	// Property accessors

	public String getLisId() {
		return this.lisId;
	}

	public void setLisId(String lisId) {
		this.lisId = lisId;
	}

	public String getConId() {
		return this.conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getRepId() {
		return this.repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getMatId() {
		return this.matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public Integer getLisNum() {
		return this.lisNum;
	}

	public void setLisNum(Integer lisNum) {
		this.lisNum = lisNum;
	}

	public BigDecimal getLisPrice() {
		return this.lisPrice;
	}

	public void setLisPrice(BigDecimal lisPrice) {
		this.lisPrice = lisPrice;
	}

}