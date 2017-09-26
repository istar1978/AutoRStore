package com.zhaozhy.autorstore.others;

import org.junit.Test;
import org.springframework.util.StringUtils;

public class SpringUtilTest {

	@Test
	public void testStringUtil(){
		String str=null;
		boolean b=StringUtils.hasText(str);
		System.out.println(b);
	}
}
