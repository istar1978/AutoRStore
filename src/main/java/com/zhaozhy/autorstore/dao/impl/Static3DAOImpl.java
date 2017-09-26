package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaozhy.autorstore.dao.Static3DAO;
import com.zhaozhy.autorstore.entity.Static3;

/**
 * @Title Static3DAOImpl.java
 * @Package com.zhaozhy.autorstore.dao.impl
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-17 下午01:45:46
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class Static3DAOImpl extends BaseDaoImpl<Static3> implements Static3DAO {
	private static final Log	log				= LogFactory.getLog(Static3DAOImpl.class);
	// property constants
	public static final String	MAT_NAME		= "matName";
	public static final String	MAT_REALPRICE	= "matRealprice";
	public static final String	MAT_PREPRICE	= "matPreprice";
	public static final String	S3_NUM			= "s3Num";
	public static final String	S3_SUMPRICE		= "s3Sumprice";
	public static final String	S3_RSUMPRICE	= "s3Rsumprice";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findById(java.lang.String)
	 */
	public Static3 findById(String id) {
		log.debug("getting Static3 instance with id: " + id);
//		Static3 instance = (Static3) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.Static3", id);
		Static3 instance=(Static3)this.getSession().get(Static3.class, id);
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findByMatName(java.lang.Object)
	 */
	public List findByMatName(Object matName) {
		return findByProperty(MAT_NAME, matName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findByMatRealprice(java.lang.Object)
	 */
	public List findByMatRealprice(Object matRealprice) {
		return findByProperty(MAT_REALPRICE, matRealprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findByMatPreprice(java.lang.Object)
	 */
	public List findByMatPreprice(Object matPreprice) {
		return findByProperty(MAT_PREPRICE, matPreprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findByS3Num(java.lang.Object)
	 */
	public List findByS3Num(Object s3Num) {
		return findByProperty(S3_NUM, s3Num);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findByS3Sumprice(java.lang.Object)
	 */
	public List findByS3Sumprice(Object s3Sumprice) {
		return findByProperty(S3_SUMPRICE, s3Sumprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static3DAO#findByS3Rsumprice(java.lang.Object)
	 */
	public List findByS3Rsumprice(Object s3Rsumprice) {
		return findByProperty(S3_RSUMPRICE, s3Rsumprice);
	}

}