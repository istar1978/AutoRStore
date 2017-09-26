package com.zhaozhy.autorstore.entity;

/**
 * AbstractMaterialId entity provides the base persistence definition of the
 * MaterialId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMaterialId implements java.io.Serializable {

	// Fields

	private String matId;
	private String braId;
	private String matStat;

	// Constructors

	/** default constructor */
	public AbstractMaterialId() {
	}

	/** full constructor */
	public AbstractMaterialId(String matId, String braId, String matStat) {
		this.matId = matId;
		this.braId = braId;
		this.matStat = matStat;
	}

	// Property accessors

	public String getMatId() {
		return this.matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public String getBraId() {
		return this.braId;
	}

	public void setBraId(String braId) {
		this.braId = braId;
	}

	public String getMatStat() {
		return this.matStat;
	}

	public void setMatStat(String matStat) {
		this.matStat = matStat;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractMaterialId))
			return false;
		AbstractMaterialId castOther = (AbstractMaterialId) other;

		return ((this.getMatId() == castOther.getMatId()) || (this.getMatId() != null
				&& castOther.getMatId() != null && this.getMatId().equals(
				castOther.getMatId())))
				&& ((this.getBraId() == castOther.getBraId()) || (this
						.getBraId() != null
						&& castOther.getBraId() != null && this.getBraId()
						.equals(castOther.getBraId())))
				&& ((this.getMatStat() == castOther.getMatStat()) || (this
						.getMatStat() != null
						&& castOther.getMatStat() != null && this.getMatStat()
						.equals(castOther.getMatStat())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMatId() == null ? 0 : this.getMatId().hashCode());
		result = 37 * result
				+ (getBraId() == null ? 0 : this.getBraId().hashCode());
		result = 37 * result
				+ (getMatStat() == null ? 0 : this.getMatStat().hashCode());
		return result;
	}

}