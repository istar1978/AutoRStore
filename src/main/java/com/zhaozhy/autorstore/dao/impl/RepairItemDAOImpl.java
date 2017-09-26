package com.zhaozhy.autorstore.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.RepairItemDAO;
import com.zhaozhy.autorstore.entity.RepairItem;
/**
 * 
 * @Title				RepairItemDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:16:29
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class RepairItemDAOImpl extends BaseDaoImpl<RepairItem> implements RepairItemDAO {
	private static final Log log = LogFactory.getLog(RepairItemDAOImpl.class);
	// property constants
	public static final String REP_NAME = "repName";
	public static final String REP_CLASSIFY = "repClassify";
	public static final String REP_MONEY = "repMoney";
	public static final String REP_STAT = "repStat";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.RepairItemDAO#findById(java.lang.String)
	 */
	public RepairItem findById(String id) {
		log.debug("getting RepairItem instance with id: " + id);
//			RepairItem instance = (RepairItem) getHibernateTemplate()//
//			.get("com.zhaozhy.autorstore.entity.RepairItem", id);
		RepairItem instance=(RepairItem)this.getSession().get(RepairItem.class, id);
			return instance;
	}


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.RepairItemDAO#findByRepName(java.lang.Object)
	 */
	public List findByRepName(Object repName) {
		return findByProperty(REP_NAME, repName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.RepairItemDAO#findByRepClassify(java.lang.Object)
	 */
	public List findByRepClassify(Object repClassify) {
		return findByProperty(REP_CLASSIFY, repClassify);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.RepairItemDAO#findByRepMoney(java.lang.Object)
	 */
	public List findByRepMoney(Object repMoney) {
		return findByProperty(REP_MONEY, repMoney);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.RepairItemDAO#findByRepStat(java.lang.Object)
	 */
	public List findByRepStat(Object repStat) {
		return findByProperty(REP_STAT, repStat);
	}

	public BigDecimal getPerPriceByRepId(String repId) {
		String hql="SELECT r.repMoney FROM RepairItem r where r.repId=:repId";
		return (BigDecimal)this.getSession().createQuery(hql)//
																			.setString("repId", repId)//
																			.uniqueResult();
	}


	public List findAllByExample(RepairItem ri) {
		Session session=this.getSession();
		Criteria criteria=session.createCriteria(RepairItem.class);
		String repId=ri.getRepId();
		String repName=ri.getRepName();
		String repClassify=ri.getRepClassify();
		BigDecimal repMoney=ri.getRepMoney();
		String repStat=ri.getRepStat();
		
		if(StringUtils.isNotEmpty(repId)){
			criteria.add(Restrictions.like("repId", repId.replace("*", "%")+"%"));
		}
		if(StringUtils.isNotEmpty(repName)){
			criteria.add(Restrictions.like("repName", repName.replace("*", "%")+"%"));
		}
		if(StringUtils.isNotEmpty(repClassify)){
			criteria.add(Restrictions.like("repClassify", repClassify.replace("*", "%")+"%"));
		}
		if(repMoney!=null){
			criteria.add(Restrictions.eq("repMoney", repMoney));
		}
		if(StringUtils.isNotEmpty("repStat")){
			criteria.add(Restrictions.like("repStat", repStat.replace("*", "%")+"%"));
		}
		criteria.addOrder(Order.asc("repId"));
		
		return criteria.list();
	}

}