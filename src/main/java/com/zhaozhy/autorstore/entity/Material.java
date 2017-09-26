package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Material entity. @author MyEclipse Persistence Tools
 */
public class Material extends AbstractMaterial implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** minimal constructor */
	public Material(MaterialId id, String matName, String matClassify,
			BigDecimal matPreprice, BigDecimal matRealprice, Date matIndate,
			Integer matNum) {
		super(id, matName, matClassify, matPreprice, matRealprice, matIndate,
				matNum);
	}

	/** full constructor */
	public Material(MaterialId id, String matName,
			String matClassify, BigDecimal matPreprice, BigDecimal matRealprice,
			String matFactory, Date matProdate, Date matIndate, Integer matNum) {
		super(id, matName,  matClassify, matPreprice, matRealprice,
				matFactory, matProdate, matIndate, matNum);
	}

}
