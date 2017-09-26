package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.AssociatorDAO;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.util.DicDataUtil;

public class AssociatorDAOImpl extends BaseDaoImpl<Associator> implements AssociatorDAO {
	private static final Log	log				= LogFactory.getLog(AssociatorDAOImpl.class);
	// property constants
	public static final String	ASS_PHONE		= "assPhone";
	public static final String	ASS_NAME		= "assName";
	public static final String	ASS_PASSWORD	= "assPassword";
	public static final String	ASS_GENDER		= "assGender";
	public static final String	ASS_ADDR		= "assAddr";
	public static final String	ASS_POINT		= "assPoint";
	public static final String	ASS_LEVEL		= "assLevel";
	public static final String	ASS_BALANCE		= "assBalance";
	public static final String	ASS_PBALANCE	= "assPbalance";
	public static final String	ASS_CARNO		= "assCarno";
	public static final String	ASS_STAT		= "assStat";

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findById(java.lang.String)
	 */
	public Associator findById(java.lang.String id) {
		log.debug("getting Associator instance with id: " + id);
		//			Associator instance = (Associator) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.Associator", id);
		Associator instance = (Associator) this.getSession().get(Associator.class, id);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssPhone(java.lang.Object)
	 */
	public List findByAssPhone(Object assPhone) {
		return findByProperty(ASS_PHONE, assPhone);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssName(java.lang.Object)
	 */
	public List findByAssName(Object assName) {
		return findByProperty(ASS_NAME, assName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssPassword(java.lang.Object)
	 */
	public List findByAssPassword(Object assPassword) {
		return findByProperty(ASS_PASSWORD, assPassword);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssGender(java.lang.Object)
	 */
	public List findByAssGender(Object assGender) {
		return findByProperty(ASS_GENDER, assGender);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssAddr(java.lang.Object)
	 */
	public List findByAssAddr(Object assAddr) {
		return findByProperty(ASS_ADDR, assAddr);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssPoint(java.lang.Object)
	 */
	public List findByAssPoint(Object assPoint) {
		return findByProperty(ASS_POINT, assPoint);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssLevel(java.lang.Object)
	 */
	public List findByAssLevel(Object assLevel) {
		return findByProperty(ASS_LEVEL, assLevel);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssBalance(java.lang.Object)
	 */
	public List findByAssBalance(Object assBalance) {
		return findByProperty(ASS_BALANCE, assBalance);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssPbalance(java.lang.Object)
	 */
	public List findByAssPbalance(Object assPbalance) {
		return findByProperty(ASS_PBALANCE, assPbalance);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssCarno(java.lang.Object)
	 */
	public List findByAssCarno(Object assCarno) {
		return findByProperty(ASS_CARNO, assCarno);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findByAssStat(java.lang.Object)
	 */
	public List findByAssStat(Object assStat) {
		return findByProperty(ASS_STAT, assStat);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findAllByIdLike(java.lang.String)
	 */
	public List findAllByIdLike(String no) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Associator.class);

		criteria.add(Restrictions.like("assId", no.replace("*", "%") + "%"));
		//20170921 added by zhaozhy 会员管理页面 不再显示系统默认会员
		criteria.add(Restrictions.ne("assId", DicDataUtil.ASSOCIATOR_DEFAULT));
		criteria.addOrder(Order.asc("assId"));
		List rtnList=criteria.list();
		return rtnList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#queryPerPage(java.lang.String, int, int)
	 */
	public List queryPerPage(String no, int intPage, int intPageSize) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Associator.class);

		criteria.add(Restrictions.like("assId", no.replace("*", "%") + "%"));
		//20170921 added by zhaozhy 会员管理页面 不再显示系统默认会员
				criteria.add(Restrictions.ne("assId", DicDataUtil.ASSOCIATOR_DEFAULT));

		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);

		criteria.addOrder(Order.asc("assId"));

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.AssociatorDAOImpl#findAllOrderByVal()
	 */
	public List findAllOrderByVal() {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Associator.class);
		criteria.add(Restrictions.eq("assStat", DicDataUtil.DICDATA_000000));// 只找出已经激活的会员
		criteria.addOrder(Order.desc("assPoint"));

		criteria.addOrder(Order.asc("assId"));

		return criteria.list();
	}
}