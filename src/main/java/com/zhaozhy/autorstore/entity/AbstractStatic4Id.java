package com.zhaozhy.autorstore.entity;

import java.util.Date;

/**
 * AbstractStatic4Id entity provides the base persistence definition of the
 * Static4Id entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStatic4Id implements java.io.Serializable {

	// Fields

	private Date s4Date;
	private String braId;

	// Constructors

	/** default constructor */
	public AbstractStatic4Id() {
	}

	/** full constructor */
	public AbstractStatic4Id(Date s4Date, String braId) {
		this.s4Date = s4Date;
		this.braId = braId;
	}

	// Property accessors

	public Date getS4Date() {
		return this.s4Date;
	}

	public void setS4Date(Date s4Date) {
		this.s4Date = s4Date;
	}

	public String getBraId() {
		return this.braId;
	}

	public void setBraId(String braId) {
		this.braId = braId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractStatic4Id))
			return false;
		AbstractStatic4Id castOther = (AbstractStatic4Id) other;

		return ((this.getS4Date() == castOther.getS4Date()) || (this
				.getS4Date() != null
				&& castOther.getS4Date() != null && this.getS4Date().equals(
				castOther.getS4Date())))
				&& ((this.getBraId() == castOther.getBraId()) || (this
						.getBraId() != null
						&& castOther.getBraId() != null && this.getBraId()
						.equals(castOther.getBraId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getS4Date() == null ? 0 : this.getS4Date().hashCode());
		result = 37 * result
				+ (getBraId() == null ? 0 : this.getBraId().hashCode());
		return result;
	}

}