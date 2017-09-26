package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AbstractMaterial entity provides the base persistence definition of the
 * Material entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMaterial implements java.io.Serializable {

	// Fields

	private MaterialId id;
	private String matName;
	private String matClassify;
	private BigDecimal matPreprice;
	private BigDecimal matRealprice;
	private String matFactory;
	private Date matProdate;
	private Date matIndate;
	private Integer matNum;

	// Constructors

	/** default constructor */
	public AbstractMaterial() {
	}

	/** minimal constructor */
	public AbstractMaterial(MaterialId id, String matName, String matClassify,
			BigDecimal matPreprice, BigDecimal matRealprice, Date matIndate,
			Integer matNum) {
		this.id = id;
		this.matName = matName;
		this.matClassify = matClassify;
		this.matPreprice = matPreprice;
		this.matRealprice = matRealprice;
		this.matIndate = matIndate;
		this.matNum = matNum;
	}

	/** full constructor */
	public AbstractMaterial(MaterialId id, String matName,
			String matClassify, BigDecimal matPreprice, BigDecimal matRealprice,
			String matFactory, Date matProdate, Date matIndate, Integer matNum) {
		this.id = id;
		this.matName = matName;
		this.matClassify = matClassify;
		this.matPreprice = matPreprice;
		this.matRealprice = matRealprice;
		this.matFactory = matFactory;
		this.matProdate = matProdate;
		this.matIndate = matIndate;
		this.matNum = matNum;
	}

	// Property accessors

	public MaterialId getId() {
		return this.id;
	}

	public void setId(MaterialId id) {
		this.id = id;
	}

	public String getMatName() {
		return this.matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}


	public String getMatClassify() {
		return this.matClassify;
	}

	public void setMatClassify(String matClassify) {
		this.matClassify = matClassify;
	}

	public BigDecimal getMatPreprice() {
		return this.matPreprice;
	}

	public void setMatPreprice(BigDecimal matPreprice) {
		this.matPreprice = matPreprice;
	}

	public BigDecimal getMatRealprice() {
		return this.matRealprice;
	}

	public void setMatRealprice(BigDecimal matRealprice) {
		this.matRealprice = matRealprice;
	}

	public String getMatFactory() {
		return this.matFactory;
	}

	public void setMatFactory(String matFactory) {
		this.matFactory = matFactory;
	}

	public Date getMatProdate() {
		return this.matProdate;
	}

	public void setMatProdate(Date matProdate) {
		this.matProdate = matProdate;
	}

	public Date getMatIndate() {
		return this.matIndate;
	}

	public void setMatIndate(Date matIndate) {
		this.matIndate = matIndate;
	}

	public Integer getMatNum() {
		return this.matNum;
	}

	public void setMatNum(Integer matNum) {
		this.matNum = matNum;
	}

}