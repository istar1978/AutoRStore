package com.zhaozhy.autorstore.entity;

/**
 * AbstractSerialGenId entity provides the base persistence definition of the
 * SerialGenId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSerialGenId implements java.io.Serializable {

	// Fields

	private String serLarge;
	private String serSmall;

	// Constructors

	/** default constructor */
	public AbstractSerialGenId() {
	}

	/** full constructor */
	public AbstractSerialGenId(String serLarge, String serSmall) {
		this.serLarge = serLarge;
		this.serSmall = serSmall;
	}

	// Property accessors

	public String getSerLarge() {
		return this.serLarge;
	}

	public void setSerLarge(String serLarge) {
		this.serLarge = serLarge;
	}

	public String getSerSmall() {
		return this.serSmall;
	}

	public void setSerSmall(String serSmall) {
		this.serSmall = serSmall;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractSerialGenId))
			return false;
		AbstractSerialGenId castOther = (AbstractSerialGenId) other;

		return ((this.getSerLarge() == castOther.getSerLarge()) || (this
				.getSerLarge() != null
				&& castOther.getSerLarge() != null && this.getSerLarge()
				.equals(castOther.getSerLarge())))
				&& ((this.getSerSmall() == castOther.getSerSmall()) || (this
						.getSerSmall() != null
						&& castOther.getSerSmall() != null && this
						.getSerSmall().equals(castOther.getSerSmall())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSerLarge() == null ? 0 : this.getSerLarge().hashCode());
		result = 37 * result
				+ (getSerSmall() == null ? 0 : this.getSerSmall().hashCode());
		return result;
	}

}