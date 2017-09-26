package com.zhaozhy.autorstore.entity;

/**
 * AbstractStatic2 entity provides the base persistence definition of the
 * Static2 entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStatic2 implements java.io.Serializable {

	// Fields

	private String assId;
	private Integer s2Point;

	// Constructors

	/** default constructor */
	public AbstractStatic2() {
	}

	/** full constructor */
	public AbstractStatic2(String assId, Integer s2Point) {
		this.assId = assId;
		this.s2Point = s2Point;
	}

	// Property accessors

	public String getAssId() {
		return this.assId;
	}

	public void setAssId(String assId) {
		this.assId = assId;
	}

	public Integer getS2Point() {
		return this.s2Point;
	}

	public void setS2Point(Integer s2Point) {
		this.s2Point = s2Point;
	}

}