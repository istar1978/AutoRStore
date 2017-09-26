package com.zhaozhy.autorstore.others;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class LangTest {

	@Test
	public void testStringUtils(){
		String str=null;
		boolean b=StringUtils.isNotBlank(str);
		System.out.println(b);
	}
}
