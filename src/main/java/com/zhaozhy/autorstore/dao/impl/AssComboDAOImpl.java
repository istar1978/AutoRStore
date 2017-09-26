package com.zhaozhy.autorstore.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.AssComboDAO;
import com.zhaozhy.autorstore.entity.AssCombo;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * @Title AssComboDAOImpl.java
 * @Package com.zhaozhy.autorstore.dao.impl
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-19 下午12:15:21
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class AssComboDAOImpl extends BaseDaoImpl<AssCombo> implements AssComboDAO {
	private static final Log	log			= LogFactory.getLog(AssComboDAOImpl.class);
	// property constants
	public static final String	COM_ID		= "comId";
	public static final String	REP_ID		= "repId";
	public static final String	COM_NAME	= "comName";
	public static final String	REP_NAME	= "repName";
	public static final String	ASS_ID		= "assId";
	public static final String	COM_DATE	= "comDate";
	public static final String	COM_SDATE	= "comSdate";
	public static final String	COM_EDATE	= "comEdate";
	public static final String	COM_TIME	= "comTime";
	public static final String	COM_DESC	= "comDesc";
	public static final String	COM_ITEM	= "comItem";
	public static final String	COM_PRICE	= "comPrice";
	public static final String	COM_STAT	= "comStat";

	public AssCombo findById(java.lang.String id) {
		log.debug("getting AssCombo instance with id: " + id);
		//		AssCombo instance = (AssCombo) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.AssCombo", id);
		AssCombo instance = (AssCombo) this.getSession().get(AssCombo.class, id);
		return instance;

	}

	public List findByRepId(Object repId) {
		return findByProperty(REP_ID, repId);
	}

	public List findByRepName(Object repName) {
		return findByProperty(REP_NAME, repName);
	}

	public List findByAssId(Object assId) {
		return findByProperty(ASS_ID, assId);
	}

	public List findByComTime(Object comTime) {
		return findByProperty(COM_TIME, comTime);
	}

	public List findByComDesc(Object comDesc) {
		return findByProperty(COM_DESC, comDesc);
	}

	public List findByComItem(Object comItem) {
		return findByProperty(COM_ITEM, comItem);
	}

	public List findByComPrice(Object comPrice) {
		return this.findByProperty(COM_PRICE, comPrice);
	}

	public List findByComStat(Object comStat) {
		return findByProperty(COM_STAT, comStat);
	}


	public List<AssCombo> queryAssComboInUsing(String assId, Date currDate) {
		//1.ass_id,2.状态con_stat正常，3.剩余次数con_tim>0，4.当前日期在起始日期和结束日期之间
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(AssCombo.class);
		criteria.add(Restrictions.eq(ASS_ID, assId)) //
				.add(Restrictions.le(COM_SDATE, currDate)) //
				.add(Restrictions.gt(COM_EDATE, currDate))//
				.add(Restrictions.eq(COM_STAT, DicDataUtil.DICDATA_000000))//
				.add(Restrictions.gt(COM_TIME, 0))//
				.addOrder(Order.asc(COM_DATE));

		return criteria.list();
	}

}