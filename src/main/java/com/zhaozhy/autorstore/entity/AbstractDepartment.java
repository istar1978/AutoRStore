package com.zhaozhy.autorstore.entity;

/**
 * AbstractDepartment entity provides the base persistence definition of the
 * Department entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDepartment implements java.io.Serializable {

	// Fields

	private String depId;
	private String depName;
	private String depType;
	private String depStat;

	// Constructors

	/** default constructor */
	public AbstractDepartment() {
	}

	/** minimal constructor */
	public AbstractDepartment(String depId) {
		this.depId = depId;
	}

	/** full constructor */
	public AbstractDepartment(String depId, String depName, String depType,
			String depStat) {
		this.depId = depId;
		this.depName = depName;
		this.depType = depType;
		this.depStat = depStat;
	}

	// Property accessors

	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepType() {
		return this.depType;
	}

	public void setDepType(String depType) {
		this.depType = depType;
	}

	public String getDepStat() {
		return this.depStat;
	}

	public void setDepStat(String depStat) {
		this.depStat = depStat;
	}

}