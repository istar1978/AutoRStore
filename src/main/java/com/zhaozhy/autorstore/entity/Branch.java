package com.zhaozhy.autorstore.entity;

/**
 * Branch entity. @author MyEclipse Persistence Tools
 */
public class Branch extends AbstractBranch implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Branch() {
	}

	/** minimal constructor */
	public Branch(String braId, String braName, String braStat) {
		super(braId, braName, braStat);
	}

	/** full constructor */
	public Branch(String braId, String braName, String braLevel,
			String braUpid, String braStat) {
		super(braId, braName, braLevel, braUpid, braStat);
	}

}
