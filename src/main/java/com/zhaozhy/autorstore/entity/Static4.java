package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * Static4 entity. @author MyEclipse Persistence Tools
 */
public class Static4 extends AbstractStatic4 implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Static4() {
	}

	/** full constructor */
	public Static4(Static4Id id, String braName, BigDecimal s4Allprice,
			BigDecimal s4Realprice) {
		super(id, braName, s4Allprice, s4Realprice);
	}

}
