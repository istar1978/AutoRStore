package com.zhaozhy.autorstore.entity;

/**
 * SerialGen entity. @author MyEclipse Persistence Tools
 */
public class SerialGen extends AbstractSerialGen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SerialGen() {
	}

	/** minimal constructor */
	public SerialGen(SerialGenId id, String serNo) {
		super(id, serNo);
	}

	/** full constructor */
	public SerialGen(SerialGenId id, String serNo, String serRule) {
		super(id, serNo, serRule);
	}

}
