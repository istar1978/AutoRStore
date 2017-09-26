package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AbstractStatic4 entity provides the base persistence definition of the
 * Static4 entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStatic4 implements java.io.Serializable {

	// Fields

	private Static4Id id;
	private String braName;
	private BigDecimal s4Allprice;
	private BigDecimal s4Realprice;

	// Constructors

	/** default constructor */
	public AbstractStatic4() {
	}

	/** full constructor */
	public AbstractStatic4(Static4Id id, String braName, BigDecimal s4Allprice,
			BigDecimal s4Realprice) {
		this.id = id;
		this.braName = braName;
		this.s4Allprice = s4Allprice;
		this.s4Realprice = s4Realprice;
	}

	// Property accessors

	public Static4Id getId() {
		return this.id;
	}

	public void setId(Static4Id id) {
		this.id = id;
	}

	public String getBraName() {
		return this.braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
	}

	public BigDecimal getS4Allprice() {
		return this.s4Allprice;
	}

	public void setS4Allprice(BigDecimal s4Allprice) {
		this.s4Allprice = s4Allprice;
	}

	public BigDecimal getS4Realprice() {
		return this.s4Realprice;
	}

	public void setS4Realprice(BigDecimal s4Realprice) {
		this.s4Realprice = s4Realprice;
	}

}