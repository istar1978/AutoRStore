package com.zhaozhy.autorstore.entity;

import java.math.BigDecimal;

/**
 * ConsumeList entity. @author MyEclipse Persistence Tools
 */
public class ConsumeList extends AbstractConsumeList implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConsumeList() {
	}

	/** minimal constructor */
	public ConsumeList(String lisId, String conId, Integer lisNum,
			BigDecimal listPrice) {
		super(lisId, conId, lisNum, listPrice);
	}

	/** full constructor */
	public ConsumeList(String lisId, String conId, String repId, String matId,
			Integer lisNum, BigDecimal listPrice) {
		super(lisId, conId, repId, matId, lisNum, listPrice);
	}

}
