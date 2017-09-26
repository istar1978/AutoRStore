package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.MenuDAO;
import com.zhaozhy.autorstore.entity.Menu;
/**
 * 
 * @Title				MenuDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:08:12
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class MenuDAOImpl extends BaseDaoImpl<Menu> implements MenuDAO {
	private static final Log log = LogFactory.getLog(MenuDAOImpl.class);
	// property constants
	public static final String MEN_NAME = "menName";
	public static final String MEN_ATT = "menAtt";
	public static final String MEN_URL = "menUrl";
	public static final String MEN_STAT = "menStat";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findById(java.lang.String)
	 */
	public Menu findById(String id) {
		log.debug("getting Menu instance with id: " + id);
//			Menu instance = (Menu) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.Menu", id);
		Menu instance=(Menu)this.getSession().get(Menu.class, id);
			return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findByMenName(java.lang.Object)
	 */
	public List findByMenName(Object menName) {
		return findByProperty(MEN_NAME, menName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findByMenAtt(java.lang.Object)
	 */
	public List findByMenAtt(Object menAtt) {
		return findByProperty(MEN_ATT, menAtt);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findByMenUrl(java.lang.Object)
	 */
	public List findByMenUrl(Object menUrl) {
		return findByProperty(MEN_URL, menUrl);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findByMenStat(java.lang.Object)
	 */
	public List findByMenStat(Object menStat) {
		return findByProperty(MEN_STAT, menStat);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findAllByIdLike(java.lang.String)
	 */
	public List findAllByIdLike(String id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Menu.class);
		criteria.add(Restrictions.like("menId", id.replace("*", "%") + "%"));

		return criteria.list();
	}
	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#queryPerPageIdLike(java.lang.String, int, int)
	 */
	public List queryPerPageIdLike(String menuId, int intPage, int intPageSize) {
//		Session session = this.getSession();
		Session session=getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Menu.class);

		criteria.add(Restrictions.like("menId", menuId.replace("*", "%") + "%"));
//		criteria.add(Restrictions.eq("menStat", DicDataUtil.DICDATA_000000));//只查询有效的数据
		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);
		criteria.addOrder(Order.asc("menId"));
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MenuDAO#findAllD()
	 */
	public List findAllD() {

		Session session = this.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Menu.class);
		criteria.add(Restrictions.eq("menAtt", "1"));
		criteria.addOrder(Order.asc("menId"));
		return criteria.list();

	}
	
}