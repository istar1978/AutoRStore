package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.zhaozhy.autorstore.dao.Static2DAO;
import com.zhaozhy.autorstore.entity.Static2;

/**
 * @Title Static2DAOImpl.java
 * @Package com.zhaozhy.autorstore.dao.impl
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-17 下午01:43:46
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class Static2DAOImpl extends BaseDaoImpl<Static2> implements Static2DAO {
	private static final Log	log			= LogFactory.getLog(Static2DAOImpl.class);
	// property constants
	public static final String	S2_POINT	= "s2Point";


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static2DAO#findById(java.lang.String)
	 */
	public Static2 findById(String id) {
		log.debug("getting Static2 instance with id: " + id);
//		Static2 instance = (Static2) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.Static2", id);
		Static2 instance=(Static2)this.getSession().get(Static2.class, id);
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static2DAO#findByS2Point(java.lang.Object)
	 */
	public List findByS2Point(Object s2Point) {
		return findByProperty(S2_POINT, s2Point);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static2DAO#findAllOrderByVal()
	 */
	public List findAllOrderByVal() {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Static2.class);

		criteria.addOrder(Order.desc("val"));

		return criteria.list();
	}
}