package com.zhaozhy.autorstore.entity;

/**
 * AbstractMenu entity provides the base persistence definition of the Menu
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMenu implements java.io.Serializable {

	// Fields

	private String menId;
	private String menName;
	private String menAtt;
	private String menUrl;
	private String menStat;

	// Constructors

	/** default constructor */
	public AbstractMenu() {
	}

	/** minimal constructor */
	public AbstractMenu(String menId, String menName, String menAtt,
			String menStat) {
		this.menId = menId;
		this.menName = menName;
		this.menAtt = menAtt;
		this.menStat = menStat;
	}

	/** full constructor */
	public AbstractMenu(String menId, String menName, String menAtt,
			String menUrl, String menStat) {
		this.menId = menId;
		this.menName = menName;
		this.menAtt = menAtt;
		this.menUrl = menUrl;
		this.menStat = menStat;
	}

	// Property accessors

	public String getMenId() {
		return this.menId;
	}

	public void setMenId(String menId) {
		this.menId = menId;
	}

	public String getMenName() {
		return this.menName;
	}

	public void setMenName(String menName) {
		this.menName = menName;
	}

	public String getMenAtt() {
		return this.menAtt;
	}

	public void setMenAtt(String menAtt) {
		this.menAtt = menAtt;
	}

	public String getMenUrl() {
		return this.menUrl;
	}

	public void setMenUrl(String menUrl) {
		this.menUrl = menUrl;
	}

	public String getMenStat() {
		return this.menStat;
	}

	public void setMenStat(String menStat) {
		this.menStat = menStat;
	}

}