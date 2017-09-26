package com.zhaozhy.autorstore.sysadmin;

import java.io.Serializable;

/**
 * @Title DictionaryView.java
 * @Package com.zhaozhy.autorstore.sysadmin
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-7-2 下午06:57:33
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class DictionaryView implements Serializable{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	String	code;
	String	name;

	public DictionaryView() {
	}

	public DictionaryView(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * Gets the code
	 * 
	 * @return Returns a String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code
	 * 
	 * @param code
	 *            The code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the name
	 * 
	 * @return Returns a String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *            The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
