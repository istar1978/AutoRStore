package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AbstractAssCombo entity provides the base persistence definition of the
 * AssCombo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAssCombo implements java.io.Serializable {

	// Fields

	private String comId;
	private String comName;
	private String repId;
	private String assId;
	private Date comSdate;
	private Date comEdate;
	private Date comDate;
	private Integer comTime;
	private String comDesc;
	private String comItem;
	private BigDecimal comPrice;
	private String comStat;

	// Constructors

	/** default constructor */
	public AbstractAssCombo() {
	}

	/** minimal constructor */
	public AbstractAssCombo(String comId,String comName, String repId, String assId,
			Date comSdate, Date comEdate, Date comDate, Integer comTime,
			String comItem,BigDecimal comPrice, String comStat) {
		this.comId = comId;
		this.comName=comName;
		this.repId = repId;
		this.assId = assId;
		this.comSdate = comSdate;
		this.comEdate = comEdate;
		this.comDate = comDate;
		this.comTime = comTime;
		this.comItem = comItem;
		this.comPrice = comPrice;
		this.comStat = comStat;
	}

	/** full constructor */
	public AbstractAssCombo(String comId,String comName, String repId, String assId,
			Date comSdate, Date comEdate, Date comDate, Integer comTime,
			String comDesc, String comItem,BigDecimal comPrice, String comStat) {
		this.comId = comId;
		this.comName=comName;
		this.repId = repId;
		this.assId = assId;
		this.comSdate = comSdate;
		this.comEdate = comEdate;
		this.comDate = comDate;
		this.comTime = comTime;
		this.comDesc = comDesc;
		this.comItem = comItem;
		this.comPrice = comPrice;
		this.comStat = comStat;
	}

	// Property accessors

	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getRepId() {
		return this.repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getAssId() {
		return this.assId;
	}

	public void setAssId(String assId) {
		this.assId = assId;
	}

	public Date getComSdate() {
		return this.comSdate;
	}

	public void setComSdate(Date comSdate) {
		this.comSdate = comSdate;
	}

	public Date getComEdate() {
		return this.comEdate;
	}

	public void setComEdate(Date comEdate) {
		this.comEdate = comEdate;
	}

	public Date getComDate() {
		return this.comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	public Integer getComTime() {
		return this.comTime;
	}

	public void setComTime(Integer comTime) {
		this.comTime = comTime;
	}

	public String getComDesc() {
		return this.comDesc;
	}

	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}

	public String getComItem() {
		return this.comItem;
	}

	public void setComItem(String comItem) {
		this.comItem = comItem;
	}

	public String getComStat() {
		return this.comStat;
	}

	public void setComStat(String comStat) {
		this.comStat = comStat;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public BigDecimal getComPrice() {
		return comPrice;
	}

	public void setComPrice(BigDecimal comPrice) {
		this.comPrice = comPrice;
	}
	

}