package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AssCombo entity. @author MyEclipse Persistence Tools
 */
public class AssCombo extends AbstractAssCombo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public AssCombo() {
	}

	/** minimal constructor */
	public AssCombo(String comId,String comName, String repId, String assId, Date comSdate,
			Date comEdate, Date comDate, Integer comTime,BigDecimal comPrice, String comItem,
			String comStat) {
		super(comId,comName, repId, assId, comSdate, comEdate, comDate, comTime,
				comItem, comPrice,comStat);
	}

	/** full constructor */
	public AssCombo(String comId,String comName, String repId, String assId, Date comSdate,
			Date comEdate, Date comDate, Integer comTime, String comDesc,
			String comItem,BigDecimal comPrice, String comStat) {
		super(comId,comName, repId, assId, comSdate, comEdate, comDate, comTime,
				comDesc, comItem,comPrice, comStat);
	}

}
