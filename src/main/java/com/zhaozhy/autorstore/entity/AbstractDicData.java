package com.zhaozhy.autorstore.entity;

/**
 * AbstractDicData entity provides the base persistence definition of the
 * DicData entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDicData implements java.io.Serializable {

	// Fields

	private DicDataId id;
	private String dicName;
	private String dicText;

	// Constructors

	/** default constructor */
	public AbstractDicData() {
	}

	/** minimal constructor */
	public AbstractDicData(DicDataId id, String dicName) {
		this.id = id;
		this.dicName = dicName;
	}

	/** full constructor */
	public AbstractDicData(DicDataId id, String dicName, String dicText) {
		this.id = id;
		this.dicName = dicName;
		this.dicText = dicText;
	}

	// Property accessors

	public DicDataId getId() {
		return this.id;
	}

	public void setId(DicDataId id) {
		this.id = id;
	}

	public String getDicName() {
		return this.dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDicText() {
		return this.dicText;
	}

	public void setDicText(String dicText) {
		this.dicText = dicText;
	}

}