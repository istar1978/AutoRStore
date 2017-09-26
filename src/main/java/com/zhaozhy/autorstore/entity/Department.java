package com.zhaozhy.autorstore.entity;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
public class Department extends AbstractDepartment implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String depId) {
		super(depId);
	}

	/** full constructor */
	public Department(String depId, String depName, String depType,
			String depStat) {
		super(depId, depName, depType, depStat);
	}

}
