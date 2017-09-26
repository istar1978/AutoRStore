package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * AssConsume entity. @author MyEclipse Persistence Tools
 */
public class AssConsume extends AbstractAssConsume implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AssConsume() {
	}

	/** minimal constructor */
	public AssConsume(String conId, String assId, String conDate,
			String conTime, BigDecimal conAmount, BigDecimal conRamount,
			String conType, Integer conPoint, String staId, String braId,
			BigDecimal conCollect,BigDecimal conChange,String conMark) {
		super(conId, assId, conDate, conTime, conAmount, conRamount, conType,
				conPoint, staId, braId, conCollect,conChange,conMark);
	}

	/** full constructor */
	public AssConsume(String conId, String assId, String conDate,
			String conTime, BigDecimal conAmount, BigDecimal conRamount,
			String conType, Integer conPoint, String staId, String braId,
			String comId, String conDesc,BigDecimal conCollect,BigDecimal conChange,String conMark) {
		super(conId, assId, conDate, conTime, conAmount, conRamount, conType,
				conPoint, staId, braId, comId, conDesc,conCollect,conChange,conMark);
	}

}
