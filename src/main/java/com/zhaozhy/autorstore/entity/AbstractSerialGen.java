package com.zhaozhy.autorstore.entity;

/**
 * AbstractSerialGen entity provides the base persistence definition of the
 * SerialGen entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSerialGen implements java.io.Serializable {

	// Fields

	private SerialGenId id;
	private String serNo;
	private String serRule;

	// Constructors

	/** default constructor */
	public AbstractSerialGen() {
	}

	/** minimal constructor */
	public AbstractSerialGen(SerialGenId id, String serNo) {
		this.id = id;
		this.serNo = serNo;
	}

	/** full constructor */
	public AbstractSerialGen(SerialGenId id, String serNo, String serRule) {
		this.id = id;
		this.serNo = serNo;
		this.serRule = serRule;
	}

	// Property accessors

	public SerialGenId getId() {
		return this.id;
	}

	public void setId(SerialGenId id) {
		this.id = id;
	}

	public String getSerNo() {
		return this.serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public String getSerRule() {
		return this.serRule;
	}

	public void setSerRule(String serRule) {
		this.serRule = serRule;
	}

}