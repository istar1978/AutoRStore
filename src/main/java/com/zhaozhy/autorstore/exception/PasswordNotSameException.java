package com.zhaozhy.autorstore.exception;

/**
 * 处理修改密码时，两次输入的新密码不一致的消息提示
 * 
 * @author zhaozy
 * 
 */
public class PasswordNotSameException extends RuntimeException {

	public PasswordNotSameException(String msg) {
		super(msg);
	}
}
