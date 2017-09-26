package com.zhaozhy.autorstore.entity;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */
public class Menu extends AbstractMenu implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(String menId, String menName, String menAtt, String menStat) {
		super(menId, menName, menAtt, menStat);
	}

	/** full constructor */
	public Menu(String menId, String menName, String menAtt, String menUrl,
			String menStat) {
		super(menId, menName, menAtt, menUrl, menStat);
	}

}
