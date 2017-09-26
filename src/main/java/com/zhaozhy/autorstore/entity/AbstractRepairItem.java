package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AbstractRepairItem entity provides the base persistence definition of the
 * RepairItem entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRepairItem implements java.io.Serializable {

	// Fields

	private String repId;
	private String repName;
	private String repClassify;
	private BigDecimal repMoney;
	private String repStat;

	// Constructors

	/** default constructor */
	public AbstractRepairItem() {
	}

	/** minimal constructor */
	public AbstractRepairItem(String repId, String repName, String repStat) {
		this.repId = repId;
		this.repName = repName;
		this.repStat = repStat;
	}

	/** full constructor */
	public AbstractRepairItem(String repId, String repName, String repClassify,
			BigDecimal repMoney, String repStat) {
		this.repId = repId;
		this.repName = repName;
		this.repClassify = repClassify;
		this.repMoney = repMoney;
		this.repStat = repStat;
	}

	// Property accessors

	public String getRepId() {
		return this.repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getRepName() {
		return this.repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getRepClassify() {
		return this.repClassify;
	}

	public void setRepClassify(String repClassify) {
		this.repClassify = repClassify;
	}

	public BigDecimal getRepMoney() {
		return this.repMoney;
	}

	public void setRepMoney(BigDecimal repMoney) {
		this.repMoney = repMoney;
	}

	public String getRepStat() {
		return this.repStat;
	}

	public void setRepStat(String repStat) {
		this.repStat = repStat;
	}

}