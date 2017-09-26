package com.zhaozhy.autorstore.dao.impl;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.zhaozhy.autorstore.dao.Static4DAO;
import com.zhaozhy.autorstore.entity.Static4;
import com.zhaozhy.autorstore.entity.Static4Id;
/**
 * 
 * @Title				Static4DAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:48:45
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class Static4DAOImpl extends BaseDaoImpl<Static4> implements Static4DAO{
	private static final Log log = LogFactory.getLog(Static4DAOImpl.class);
	// property constants
	public static final String BRA_NAME = "braName";
	public static final String S4_ALLPRICE = "s4Allprice";
	public static final String S4_REALPRICE = "s4Realprice";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findById(com.zhaozhy.autorstore.entity.Static4Id)
	 */
	public Static4 findById(Static4Id id) {
		log.debug("getting Static4 instance with id: " + id);
//			Static4 instance = (Static4) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.Static4", id);
		Static4 instance =(Static4)this.getSession().get(Static4.class, id);
			return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findByBraName(java.lang.Object)
	 */
	public List findByBraName(Object braName) {
		return findByProperty(BRA_NAME, braName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findByS4Allprice(java.lang.Object)
	 */
	public List findByS4Allprice(Object s4Allprice) {
		return findByProperty(S4_ALLPRICE, s4Allprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findByS4Realprice(java.lang.Object)
	 */
	public List findByS4Realprice(Object s4Realprice) {
		return findByProperty(S4_REALPRICE, s4Realprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findStatic4Earlise()
	 */
	public Date findStatic4Earlise() {
		List st4List = this.findAll();

		Date date = null;
		DateFormat df = DateFormat.getDateInstance();
		Iterator it = st4List.iterator();
		while (it.hasNext()) {
			Static4 st4 = (Static4) it.next();

			if (date == null) {
				date = st4.getId().getS4Date();
			}

			String date1 = df.format(date).replace("-", "");
			String date4 = df.format(st4.getId().getS4Date()).replace("-",
					"");

			Integer date1Int = Integer.parseInt(date1);
			Integer date4Int = Integer.parseInt(date4);

			if (date1Int > date4Int) {
				date = st4.getId().getS4Date();
			}
		}
		return date;

	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findStatic4Earlist()
	 */
	public Static4 findStatic4Earlist() {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Static4.class);

		criteria.addOrder(Order.asc("id.staticDate"));

		if (criteria.list().size() > 0) {
			return (Static4) criteria.list().get(0);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.Static4DAO#findStatic4Last()
	 */
	public Static4 findStatic4Last() {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Static4.class);

		criteria.addOrder(Order.desc("id.staticDate"));

		if (criteria.list().size() > 1) {
			return (Static4) criteria.list().get(0);
		} else {
			return null;
		}
	}
}