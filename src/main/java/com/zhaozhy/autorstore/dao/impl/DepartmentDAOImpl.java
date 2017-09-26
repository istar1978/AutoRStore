package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.DepartmentDAO;
import com.zhaozhy.autorstore.entity.Department;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * 
 * @Title				DepartmentDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:37:45
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class DepartmentDAOImpl extends BaseDaoImpl<Department> implements DepartmentDAO {
	private static final Log log = LogFactory.getLog(DepartmentDAOImpl.class);
	// property constants
	public static final String DEP_NAME = "depName";
	public static final String DEP_TYPE = "depType";
	public static final String DEP_STAT = "depStat";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findById(java.lang.String)
	 */
	public Department findById(java.lang.String id) {
		log.debug("getting Department instance with id: " + id);
		try {
//			Department instance = (Department) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.Department", id);
			Department instance=(Department)this.getSession().get(Department.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findByDepName(java.lang.Object)
	 */
	public List findByDepName(Object depName) {
		return findByProperty(DEP_NAME, depName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findByDepType(java.lang.Object)
	 */
	public List findByDepType(Object depType) {
		return findByProperty(DEP_TYPE, depType);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findByDepStat(java.lang.Object)
	 */
	public List findByDepStat(Object depStat) {
		return findByProperty(DEP_STAT, depStat);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findAllInUsing()
	 */
	public List findAllInUsing() {
		log.debug("finding all Department in using instances");
		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("stat", "0"));
			// criteria.add(Restrictions.ne("id", "00000000"));
			criteria.addOrder(Order.asc("id"));
			return criteria.list();
		} catch (RuntimeException re) {
			log.error("find all in using failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findAllInUsingN()
	 */
	public List findAllInUsingN() {
		log.debug("finding all Department in using instances");
		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("depStat",DicDataUtil.DICDATA_000000));
//			criteria.add(Restrictions.ne("depId", "0000000000"));
			criteria.addOrder(Order.asc("depId"));
			return criteria.list();
		} catch (RuntimeException re) {
			log.error("find all in using failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#queryPerPageByIdLike(java.lang.String, int, int)
	 */
	public List queryPerPageByIdLike(String depId, int intPage, int intPageSize) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Department.class);

		criteria.add(Restrictions.like("id", depId.replace("*", "%") + "%"));

		criteria.setFirstResult((intPage - 1) * intPageSize);

		criteria.setMaxResults(intPageSize);

		criteria.addOrder(Order.asc("id"));

		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DepartmentDAO#findALlByIdLike(java.lang.String)
	 */
	public List findAllByIdLike(String depId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Department.class);
		criteria.add(Restrictions.like("depId", depId.replace("*", "%") + "%"));
		criteria.addOrder(Order.asc("depId"));
		return criteria.list();
	}
}