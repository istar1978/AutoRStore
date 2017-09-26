package com.zhaozhy.autorstore.entity;

/**
 * SerialGenId entity. @author MyEclipse Persistence Tools
 */
public class SerialGenId extends AbstractSerialGenId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SerialGenId() {
	}

	/** full constructor */
	public SerialGenId(String serLarge, String serSmall) {
		super(serLarge, serSmall);
	}

}
