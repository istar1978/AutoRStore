package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.Static3;
import com.zhaozhy.autorstore.service.Static3Service;
/**
 * 
 * @Title				Static3ServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:59:06
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class Static3ServiceImpl extends BaseServiceImpl<Static3> implements Static3Service {

	public Static3 findById(String id) {
		return this.static3DAO.findById(id);
	}

	public List findByMatName(Object matName) {
		return this.static3DAO.findByMatName(matName);
	}

	public List findByMatPreprice(Object matPreprice) {
		return this.static3DAO.findByMatPreprice(matPreprice);
	}

	public List findByMatRealprice(Object matRealprice) {
		return this.static3DAO.findByMatRealprice(matRealprice);
	}

	public List findByS3Num(Object num) {
		return this.static3DAO.findByS3Num(num);
	}

	public List findByS3Rsumprice(Object rsumprice) {
		return this.static3DAO.findByS3Rsumprice(rsumprice);
	}

	public List findByS3Sumprice(Object sumprice) {
		return this.static3DAO.findByS3Sumprice(sumprice);
	}

}
