package com.zhaozhy.autorstore.entity;

/**
 * DicData entity. @author MyEclipse Persistence Tools
 */
public class DicData extends AbstractDicData implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public DicData() {
	}

	/** minimal constructor */
	public DicData(DicDataId id, String dicName) {
		super(id, dicName);
	}

	/** full constructor */
	public DicData(DicDataId id, String dicName, String dicText) {
		super(id, dicName, dicText);
	}

}
