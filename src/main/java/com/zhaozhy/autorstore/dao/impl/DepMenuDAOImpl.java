package com.zhaozhy.autorstore.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.DepMenuDAO;
import com.zhaozhy.autorstore.entity.DepMenu;
/**
 * 
 * @Title				DepMenuDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:42:53
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class DepMenuDAOImpl extends BaseDaoImpl<DepMenu> implements DepMenuDAO {
	private static final Log log = LogFactory.getLog(DepMenuDAOImpl.class);
	// property constants
	public static final String DM_STAT = "dmStat";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepMenuDAO#findById(com.zhaozhy.autorstore.entity.DepMenuId)
	 */
	public DepMenu findById(com.zhaozhy.autorstore.entity.DepMenuId id) {
		log.debug("getting DepMenu instance with id: " + id);
		DepMenu instance=(DepMenu)this.getSession().get(DepMenu.class, id);
			return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepMenuDAO#findByDmStat(java.lang.Object)
	 */
	public List findByDmStat(Object dmStat) {
		return findByProperty(DM_STAT, dmStat);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepMenuDAO#findByDId(java.lang.String)
	 */
	public List findByDId(String dId) {
		Criteria criteria=this.getSession().createCriteria(DepMenu.class);
		criteria.add(Restrictions.eq("id.depId", dId))//
						.addOrder(Order.asc("id.menId"));
		return criteria.list();
	}
	
}