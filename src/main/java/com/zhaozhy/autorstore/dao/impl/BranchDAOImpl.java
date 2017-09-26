package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.BranchDAO;
import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * @Title BranchDAOImpl.java
 * @Package com.zhaozhy.autorstore.dao
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-17 上午08:35:37
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class BranchDAOImpl extends BaseDaoImpl<Branch> implements BranchDAO {
	private static final Log	log			= LogFactory.getLog(BranchDAOImpl.class);
	// property constants
	public static final String	BRA_NAME	= "braName";
	public static final String	BRA_LEVEL	= "braLevel";
	public static final String	BRA_UPID	= "braUpid";
	public static final String	BRA_STAT	= "braStat";

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findById(java.lang.String)
	 */
	public Branch findById(java.lang.String id) {
		log.debug("getting Branch instance with id: " + id);
		//			Branch instance = (Branch) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.Branch", id);
		Branch instance = (Branch) this.getSession().get(Branch.class, id);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findByBraName(java.lang.Object)
	 */
	public List findByBraName(Object braName) {
		return findByProperty(BRA_NAME, braName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findByBraLevel(java.lang.Object)
	 */
	public List findByBraLevel(Object braLevel) {
		return findByProperty(BRA_LEVEL, braLevel);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findByBraUpid(java.lang.Object)
	 */
	public List findByBraUpid(Object braUpid) {
		return findByProperty(BRA_UPID, braUpid);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findByBraStat(java.lang.Object)
	 */
	public List findByBraStat(Object braStat) {
		return findByProperty(BRA_STAT, braStat);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findAllInUsing()
	 */
	public List findAllInUsing() {
		log.debug("find all Branch in using instance");
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Branch.class);
		criteria.add(Restrictions.eq("braStat", DicDataUtil.DICDATA_000000));
		criteria.addOrder(Order.asc("braId"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findAllOthers(java.lang.String)
	 */
	public List findAllOthers(String branchId) {

		String queryString = "from Branch b where b.id not in('" + branchId + "') order by b.id";
		System.out.println("QueryString :" + queryString);
		return this.getSession().createQuery(queryString).list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#findAllByIdLike(java.lang.String)
	 */
	public List findAllByIdLike(String b_id) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Branch.class);
		criteria.add(Restrictions.like("id", b_id.replace("*", "%") + "%"));
		criteria.addOrder(Order.asc("id"));

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.BranchDAO#queryPageByIdLike(java.lang.String, int, int)
	 */
	public List queryPageByIdLike(String b_id, int intPage, int intPageSize) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Branch.class);
		criteria.add(Restrictions.like("braId", b_id.replace("*", "%") + "%"));
		criteria.addOrder(Order.asc("braId"));

		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);

		return criteria.list();
	}
}