package com.zhaozhy.autorstore.entity;

/**
 * AbstractDicDataId entity provides the base persistence definition of the
 * DicDataId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDicDataId implements java.io.Serializable {

	// Fields

	private String dicLarge;
	private String dicValue;

	// Constructors

	/** default constructor */
	public AbstractDicDataId() {
	}

	/** full constructor */
	public AbstractDicDataId(String dicLarge, String dicValue) {
		this.dicLarge = dicLarge;
		this.dicValue = dicValue;
	}

	// Property accessors

	public String getDicLarge() {
		return this.dicLarge;
	}

	public void setDicLarge(String dicLarge) {
		this.dicLarge = dicLarge;
	}

	public String getDicValue() {
		return this.dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractDicDataId))
			return false;
		AbstractDicDataId castOther = (AbstractDicDataId) other;

		return ((this.getDicLarge() == castOther.getDicLarge()) || (this
				.getDicLarge() != null
				&& castOther.getDicLarge() != null && this.getDicLarge()
				.equals(castOther.getDicLarge())))
				&& ((this.getDicValue() == castOther.getDicValue()) || (this
						.getDicValue() != null
						&& castOther.getDicValue() != null && this
						.getDicValue().equals(castOther.getDicValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDicLarge() == null ? 0 : this.getDicLarge().hashCode());
		result = 37 * result
				+ (getDicValue() == null ? 0 : this.getDicValue().hashCode());
		return result;
	}

}