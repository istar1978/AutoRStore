package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.StafferDAO;
import com.zhaozhy.autorstore.entity.Staffer;

/**
 * @Title StafferDAOImpl.java
 * @Package com.zhaozhy.autorstore.dao
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-17 下午01:30:07
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class StafferDAOImpl extends BaseDaoImpl<Staffer> implements StafferDAO {
	private static final Log	log				= LogFactory.getLog(StafferDAOImpl.class);
	//property constants
	public static final String	STA_NAME		= "staName";
	public static final String	STA_PWD			= "staPwd";
	public static final String	STA_POSITION	= "staPosition";
	public static final String	STA_LEVEL		= "staLevel";
	public static final String	DEP_ID			= "depId";
	public static final String	BRA_ID			= "braId";
	public static final String	STA_STAT		= "staStat";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findById(java.lang.String)
	 */
	public Staffer findById(String id) {
		log.debug("getting Staffer instance with id: " + id);
//		Staffer instance = (Staffer) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.Staffer", id);
		Staffer instance=(Staffer)this.getSession().get(Staffer.class, id);
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByStaName(java.lang.Object)
	 */
	public List findByStaName(Object staName) {
		return findByProperty(STA_NAME, staName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByStaPwd(java.lang.Object)
	 */
	public List findByStaPwd(Object staPwd) {
		return findByProperty(STA_PWD, staPwd);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByStaPosition(java.lang.Object)
	 */
	public List findByStaPosition(Object staPosition) {
		return findByProperty(STA_POSITION, staPosition);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByStaLevel(java.lang.Object)
	 */
	public List findByStaLevel(Object staLevel) {
		return findByProperty(STA_LEVEL, staLevel);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByDepId(java.lang.Object)
	 */
	public List findByDepId(Object depId) {
		return findByProperty(DEP_ID, depId);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByBraId(java.lang.Object)
	 */
	public List findByBraId(Object braId) {
		return findByProperty(BRA_ID, braId);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findByStaStat(java.lang.Object)
	 */
	public List findByStaStat(Object staStat) {
		return findByProperty(STA_STAT, staStat);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#findAllByIdLike(java.lang.String)
	 */
	public List findAllByIdLike(String id) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Staffer.class);

		criteria.add(Restrictions.like("staId", id.replace("*", "%") + "%"));

		criteria.addOrder(Order.asc("staId"));
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.StafferDAO#queryPageByIdLike(java.lang.String, int, int)
	 */
	public List queryPageByIdLike(String id, int intPage, int intPageSize) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Staffer.class);
		criteria.add(Restrictions.like("id", id.replace("*", "%") + "%"));
		criteria.addOrder(Order.asc("id"));

		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);

		return criteria.list();
	}
}