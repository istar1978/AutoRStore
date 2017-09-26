package com.zhaozhy.autorstore.entity;

/**
 * AbstractBranch entity provides the base persistence definition of the Branch
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBranch implements java.io.Serializable {

	// Fields

	private String braId;
	private String braName;
	private String braLevel;
	private String braUpid;
	private String braStat;

	// Constructors

	/** default constructor */
	public AbstractBranch() {
	}

	/** minimal constructor */
	public AbstractBranch(String braId, String braName, String braStat) {
		this.braId = braId;
		this.braName = braName;
		this.braStat = braStat;
	}

	/** full constructor */
	public AbstractBranch(String braId, String braName, String braLevel,
			String braUpid, String braStat) {
		this.braId = braId;
		this.braName = braName;
		this.braLevel = braLevel;
		this.braUpid = braUpid;
		this.braStat = braStat;
	}

	// Property accessors

	public String getBraId() {
		return this.braId;
	}

	public void setBraId(String braId) {
		this.braId = braId;
	}

	public String getBraName() {
		return this.braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
	}

	public String getBraLevel() {
		return this.braLevel;
	}

	public void setBraLevel(String braLevel) {
		this.braLevel = braLevel;
	}

	public String getBraUpid() {
		return this.braUpid;
	}

	public void setBraUpid(String braUpid) {
		this.braUpid = braUpid;
	}

	public String getBraStat() {
		return this.braStat;
	}

	public void setBraStat(String braStat) {
		this.braStat = braStat;
	}

}