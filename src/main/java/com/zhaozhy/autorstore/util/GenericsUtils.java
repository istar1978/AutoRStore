package com.zhaozhy.autorstore.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils {

	public static Class getSuperClassGenricType(Class clazz) {  
		 return getSuperClassGenricType(clazz, 0);  
	}
	/**
	 * 
	 * @CreateDate	2017-6-19  下午03:46:22
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					通过反射,获得定义Class时声明的父类的范型参数的类型.  
	 *									 如public BookManager extends GenricManager<Book>   
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class getSuperClassGenricType(Class clazz, int index){
		Type genType = clazz.getGenericSuperclass();
		 if (!(genType instanceof ParameterizedType)) {  
			 return Object.class; 
		 }
		 Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
		 if (index >= params.length || index < 0) {  
			 return Object.class;  
		 }
		 if (!(params[index] instanceof Class)) {  
			 return Object.class;
		 }
		 return (Class) params[index];  
	}
}
