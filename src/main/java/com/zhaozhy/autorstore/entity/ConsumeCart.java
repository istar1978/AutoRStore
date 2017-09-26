package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * ConsumeCart entity. @author MyEclipse Persistence Tools
 */

public class ConsumeCart implements java.io.Serializable {

	// Fields    

	private Integer	cartNo;
	private String	assId;
	private String	repId;
	private String	matId;
	private Integer	cartNum;
	private BigDecimal	cartPerprice;
	private BigDecimal	cartMoney;

	// Constructors

	/** default constructor */
	public ConsumeCart() {
	}

	/** minimal constructor */
	public ConsumeCart(Integer cartNo, String assId, Integer cartNum, BigDecimal cartPerprice, BigDecimal cartMoney) {
		this.cartNo = cartNo;
		this.assId = assId;
		this.cartNum = cartNum;
		this.cartPerprice = cartPerprice;
		this.cartMoney = cartMoney;
	}

	/** full constructor */
	public ConsumeCart(Integer cartNo, String assId, String repId, String matId, Integer cartNum, BigDecimal cartPerprice, BigDecimal cartMoney) {
		this.cartNo = cartNo;
		this.assId = assId;
		this.repId = repId;
		this.matId = matId;
		this.cartNum = cartNum;
		this.cartPerprice = cartPerprice;
		this.cartMoney = cartMoney;
	}

	// Property accessors

	public Integer getCartNo() {
		return this.cartNo;
	}

	public void setCartNo(Integer cartNo) {
		this.cartNo = cartNo;
	}

	public String getAssId() {
		return this.assId;
	}

	public void setAssId(String assId) {
		this.assId = assId;
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

	public Integer getCartNum() {
		return this.cartNum;
	}

	public void setCartNum(Integer cartNum) {
		this.cartNum = cartNum;
	}

	public BigDecimal getCartPerprice() {
		return this.cartPerprice;
	}

	public void setCartPerprice(BigDecimal cartPerprice) {
		this.cartPerprice = cartPerprice;
	}

	public BigDecimal getCartMoney() {
		return this.cartMoney;
	}

	public void setCartMoney(BigDecimal cartMoney) {
		this.cartMoney = cartMoney;
	}

}