package com.zhaozhy.autorstore.others;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.lang.time.DateUtils;

import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DateUtil;

public class OthersTest extends TestCase{
	public void testDate(){
		Date date=new Date();
		
		DateFormat df=DateFormat.getDateInstance();
		
		/*
		try {
			date=df.parse("1988-01-01");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(date.toString());
		
		String s=new Timestamp(System.currentTimeMillis()).toString();
		
		
		String ss=s.substring(0, 11);
		System.out.println(date);*/
		
		String s=df.format(date);
		
		System.out.println(s);
	}
	
	public void testTime(){
		Date date=new Date();
		DateFormat df=DateFormat.getTimeInstance();
		
		System.out.println(df.format(date));
	}
	
	public void testTime2(){
		String s1="2010-01-01";
		String s2="2011-01-01";
		
		String yearStr=s1.substring(0, 4);
		String monthStr=s1.substring(5, 7);
		String dayStr=s1.substring(8, 10);
		
		System.out.println("year:"+yearStr+",month:"+monthStr+",day:"+dayStr);
		
	}
	
	public void testDate2(){
		Date date=new Date();
		
		DateFormat df=DateFormat.getDateInstance();
		
		System.out.println(df.format(date));
	}
	
	public void testDate4() throws ParseException{
		String dataStr=new String("2010-01-01");
		Date date=DateUtils.parseDate(dataStr, new String[]{"yyyy-mm-dd"});
		DateFormat df=DateFormat.getDateInstance();
		System.out.println(df.format(date));
		System.out.println(date.toString());
		
	}
	public void testDate5(){
		System.out.println(DateUtil.getDateString17());
	}
	
	public void testString1(){
		String sNo="20170526133712541234";
		System.out.println(sNo.substring(8,14));
	}
	public void testG(){
//		Class<T> entityClass=GenericsUtils.getSuperClassGenricType(BaseService.class);
//		System.out.println(entityClass.getName());
	}
	public void testProp() throws IOException{
		String str=DataUtil.getValueByKeyPro("point.rule");
		System.out.println(str);
	}
	public void testPoint () throws IOException{
		Integer strInt=DataUtil.calculatePoint(new BigDecimal(28.0));
		System.out.println(strInt.toString());
	}
	
	public void testDate6(){
		System.out.println(DateUtil.format8To10("20100101"));
	}
	
	public void testBigDecimal(){
		BigDecimal b=new BigDecimal(0);
		BigDecimal b1=new BigDecimal("20");
		System.out.println(b.add(b1.multiply(new BigDecimal(new Integer("1")))));
		System.out.println(b);
	}
	
	public void testString(){
		String a="zhao";
		String[] str=a.split(";");
		System.out.println(str.length);
		for(String s:str){
			System.out.println(s);
		}
	}
	
	public void testFile(){
		File file=new File("D:\\\\autorstore\\dbdata");
		File[] files=file.listFiles();
		System.out.println(files[0].getAbsolutePath());
	}
}
