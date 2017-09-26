package com.zhaozhy.autorstore.others;

import java.math.BigDecimal;

import org.junit.Test;

public class MathTest {
	@Test
	public void testBigDecimal() {
		BigDecimal b1=new BigDecimal(100);
		BigDecimal b2=new BigDecimal(200);
		System.out.println(b1.equals(b2));
	}
}
