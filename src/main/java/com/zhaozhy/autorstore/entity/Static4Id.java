package com.zhaozhy.autorstore.entity;

import java.util.Date;

/**
 * Static4Id entity. @author MyEclipse Persistence Tools
 */
public class Static4Id extends AbstractStatic4Id implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Static4Id() {
	}

	/** full constructor */
	public Static4Id(Date s4Date, String braId) {
		super(s4Date, braId);
	}

}
