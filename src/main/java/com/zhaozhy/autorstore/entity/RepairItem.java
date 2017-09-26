package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * RepairItem entity. @author MyEclipse Persistence Tools
 */
public class RepairItem extends AbstractRepairItem implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RepairItem() {
	}

	/** minimal constructor */
	public RepairItem(String repId, String repName, String repStat) {
		super(repId, repName, repStat);
	}

	/** full constructor */
	public RepairItem(String repId, String repName, String repClassify,
			BigDecimal repMoney, String repStat) {
		super(repId, repName, repClassify, repMoney, repStat);
	}

}
