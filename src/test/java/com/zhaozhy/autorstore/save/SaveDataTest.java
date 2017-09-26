package com.zhaozhy.autorstore.save;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.zhaozhy.autorstore.util.MD5Util;

public class SaveDataTest {
	@Test
	public void testSaveStaffer() {

	}
	
	@Test
	public void testMd5(){
		String pwd="123";
		String str=DigestUtils.md5Hex(pwd);
		System.out.println(str);
		str=DigestUtils.shaHex(pwd);
		System.out.println(str);
	}
	
	@Test
	public void testMD5Local() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String str="123";
		System.out.println(MD5Util.getEncryptedPwd(str));
	}
}
