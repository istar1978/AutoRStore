package com.zhaozhy.autorstore.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.AssRechargeDAO;
import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.AssRecharge;
import com.zhaozhy.autorstore.util.DateUtil;

/**
 * @Title AssRechargeDAOImpl.java
 * @Package com.zhaozhy.autorstore.dao.impl
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-17 下午03:39:34
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class AssRechargeDAOImpl extends BaseDaoImpl<AssRecharge> implements AssRechargeDAO {
	private static final Log	log			= LogFactory.getLog(AssRechargeDAOImpl.class);
	// property constants
	public static final String	ASS_ID		= "assId";
	public static final String	REC_AMOUNT	= "recAmount";
	public static final String	REC_DATE	= "recDate";
	public static final String	REC_TIME	= "recTime";
	public static final String	REC_PRESENT	= "recPresent";
	public static final String	REC_TEXT	= "recText";

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findById(java.lang.String)
	 */
	public AssRecharge findById(String id) {
		log.debug("getting AssRecharge instance with id: " + id);
//			AssRecharge instance = (AssRecharge) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.AssRecharge", id);
		AssRecharge instance=(AssRecharge)this.getSession().get(AssRecharge.class, id);
			return instance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findByAssId(java.lang.Object)
	 */
	public List findByAssId(Object assId) {
		return findByProperty(ASS_ID, assId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findByRecAmount(java.lang.Object)
	 */
	public List findByRecAmount(Object recAmount) {
		return findByProperty(REC_AMOUNT, recAmount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findByRecDate(java.lang.Object)
	 */
	public List findByRecDate(Object recDate) {
		return findByProperty(REC_DATE, recDate);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findByRecTime(java.lang.Object)
	 */
	public List findByRecTime(Object recTime) {
		return findByProperty(REC_TIME, recTime);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findByRecPresent(java.lang.Object)
	 */
	public List findByRecPresent(Object recPresent) {
		return findByProperty(REC_PRESENT, recPresent);
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.AssRechargeDAO#findByRecText(java.lang.Object)
	 */
	public List findByRecText(Object recText) {
		return findByProperty(REC_TEXT, recText);
	}

	public List findUsingChartHisQry(Map<String, String> paramap) {
		Session session=this.getSession();
		Criteria criteria=session.createCriteria(AssRecharge.class);
		String assId=paramap.get("assId");
		if(assId!=null&&!assId.equals("")){
			criteria.add(Restrictions.like("assId", "%"+assId+"%"));
		}
		String beginDate=paramap.get("beginDate");
		String endDate=paramap.get("endDate");
		if(beginDate!=null&&!beginDate.equals("")){
			beginDate=DateUtil.format10To8(beginDate);
			if(endDate!=null&&!endDate.equals("")){
				endDate=DateUtil.format10To8(endDate);
				criteria.add(Restrictions.between("recDate", beginDate, endDate));
			}else{
				//only beginDate
				criteria.add(Restrictions.ge("recDate", beginDate));
			}
		}else{
			if(endDate!=null&&!endDate.equals("")){
				endDate=DateUtil.format10To8(endDate);
				//only endDate
				criteria.add(Restrictions.le("recDate", endDate));
			}else{
				//beginDate && endDate 都没有
			}
		}
		criteria.addOrder(Order.asc("recId"));
		return criteria.list();
	}
}