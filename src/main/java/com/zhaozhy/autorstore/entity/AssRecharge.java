package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AssRecharge entity. @author MyEclipse Persistence Tools
 */
public class AssRecharge extends AbstractAssRecharge implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AssRecharge() {
	}

	/** minimal constructor */
	public AssRecharge(String recId, String assId, BigDecimal recAmount,
			String recDate, String recTime,String staId) {
		super(recId, assId, recAmount, recDate, recTime,staId);
	}

	/** full constructor */
	public AssRecharge(String recId, String assId, BigDecimal recAmount,
			String recDate, String recTime, BigDecimal recPresent, String recText,String staId) {
		super(recId, assId, recAmount, recDate, recTime, recPresent, recText,staId);
	}

}
