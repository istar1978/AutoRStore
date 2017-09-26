package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Associator entity. @author MyEclipse Persistence Tools
 */
public class Associator extends AbstractAssociator implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Associator() {
	}

	/** minimal constructor */
	public Associator(String assId, String assPhone,
			BigDecimal assBalance, String assStat) {
		super(assId, assPhone, assBalance, assStat);
	}

	/** full constructor */
	public Associator(String assId, String assPhone, String assName,
			String assPassword, String assGender, Date assBirthday,
			String assAddr, Integer assPoint, String assLevel, Date assCreate,
			Date assActive, BigDecimal assBalance, BigDecimal assPbalance,
			String assCarno, String assStat) {
		super(assId, assPhone, assName, assPassword, assGender, assBirthday,
				assAddr, assPoint, assLevel, assCreate, assActive, assBalance,
				assPbalance, assCarno, assStat);
	}

}
