package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AbstractStatic3 entity provides the base persistence definition of the
 * Static3 entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStatic3 implements java.io.Serializable {

	// Fields

	private String matId;
	private String matName;
	private BigDecimal matRealprice;
	private BigDecimal matPreprice;
	private Integer s3Num;
	private BigDecimal s3Sumprice;
	private BigDecimal s3Rsumprice;

	// Constructors

	/** default constructor */
	public AbstractStatic3() {
	}

	/** full constructor */
	public AbstractStatic3(String matId, String matName, BigDecimal matRealprice,
			BigDecimal matPreprice, Integer s3Num, BigDecimal s3Sumprice,
			BigDecimal s3Rsumprice) {
		this.matId = matId;
		this.matName = matName;
		this.matRealprice = matRealprice;
		this.matPreprice = matPreprice;
		this.s3Num = s3Num;
		this.s3Sumprice = s3Sumprice;
		this.s3Rsumprice = s3Rsumprice;
	}

	// Property accessors

	public String getMatId() {
		return this.matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public String getMatName() {
		return this.matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public BigDecimal getMatRealprice() {
		return this.matRealprice;
	}

	public void setMatRealprice(BigDecimal matRealprice) {
		this.matRealprice = matRealprice;
	}

	public BigDecimal getMatPreprice() {
		return this.matPreprice;
	}

	public void setMatPreprice(BigDecimal matPreprice) {
		this.matPreprice = matPreprice;
	}

	public Integer getS3Num() {
		return this.s3Num;
	}

	public void setS3Num(Integer s3Num) {
		this.s3Num = s3Num;
	}

	public BigDecimal getS3Sumprice() {
		return this.s3Sumprice;
	}

	public void setS3Sumprice(BigDecimal s3Sumprice) {
		this.s3Sumprice = s3Sumprice;
	}

	public BigDecimal getS3Rsumprice() {
		return this.s3Rsumprice;
	}

	public void setS3Rsumprice(BigDecimal s3Rsumprice) {
		this.s3Rsumprice = s3Rsumprice;
	}

}