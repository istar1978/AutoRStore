package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * Static3 entity. @author MyEclipse Persistence Tools
 */
public class Static3 extends AbstractStatic3 implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Static3() {
	}

	/** full constructor */
	public Static3(String matId, String matName, BigDecimal matRealprice,
			BigDecimal matPreprice, Integer s3Num, BigDecimal s3Sumprice,
			BigDecimal s3Rsumprice) {
		super(matId, matName, matRealprice, matPreprice, s3Num, s3Sumprice,
				s3Rsumprice);
	}

}
