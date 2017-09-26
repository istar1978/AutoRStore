package com.zhaozhy.autorstore.entity;

/**
 * AbstractDepMenu entity provides the base persistence definition of the
 * DepMenu entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDepMenu implements java.io.Serializable {

	// Fields

	private DepMenuId id;
	private String dmStat;

	// Constructors

	/** default constructor */
	public AbstractDepMenu() {
	}

	/** full constructor */
	public AbstractDepMenu(DepMenuId id, String dmStat) {
		this.id = id;
		this.dmStat = dmStat;
	}

	// Property accessors

	public DepMenuId getId() {
		return this.id;
	}

	public void setId(DepMenuId id) {
		this.id = id;
	}

	public String getDmStat() {
		return this.dmStat;
	}

	public void setDmStat(String dmStat) {
		this.dmStat = dmStat;
	}

}