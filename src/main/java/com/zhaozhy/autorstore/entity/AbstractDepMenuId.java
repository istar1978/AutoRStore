package com.zhaozhy.autorstore.entity;

/**
 * AbstractDepMenuId entity provides the base persistence definition of the
 * DepMenuId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDepMenuId implements java.io.Serializable {

	// Fields

	private String depId;
	private String menId;

	// Constructors

	/** default constructor */
	public AbstractDepMenuId() {
	}

	/** full constructor */
	public AbstractDepMenuId(String depId, String menId) {
		this.depId = depId;
		this.menId = menId;
	}

	// Property accessors

	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getMenId() {
		return this.menId;
	}

	public void setMenId(String menId) {
		this.menId = menId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractDepMenuId))
			return false;
		AbstractDepMenuId castOther = (AbstractDepMenuId) other;

		return ((this.getDepId() == castOther.getDepId()) || (this.getDepId() != null
				&& castOther.getDepId() != null && this.getDepId().equals(
				castOther.getDepId())))
				&& ((this.getMenId() == castOther.getMenId()) || (this
						.getMenId() != null
						&& castOther.getMenId() != null && this.getMenId()
						.equals(castOther.getMenId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDepId() == null ? 0 : this.getDepId().hashCode());
		result = 37 * result
				+ (getMenId() == null ? 0 : this.getMenId().hashCode());
		return result;
	}

}