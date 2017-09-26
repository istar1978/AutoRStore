package com.zhaozhy.autorstore.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.regexp.internal.recompile;
import com.zhaozhy.autorstore.dao.AssConsumeDAO;
import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.util.DateUtil;

public class AssConsumeDAOImpl extends BaseDaoImpl<AssConsume> implements AssConsumeDAO {
	private static final Log	log			= LogFactory.getLog(AssConsumeDAOImpl.class);
	// property constants
	public static final String	ASS_ID		= "assId";
	public static final String	CON_DATE	= "conDate";
	public static final String	CON_TIME	= "conTime";
	public static final String	CON_AMOUNT	= "conAmount";
	public static final String	CON_RAMOUNT	= "conRamount";
	public static final String	CON_TYPE	= "conType";
	public static final String	CON_POINT	= "conPoint";
	public static final String	STA_ID		= "staId";
	public static final String	BRA_ID		= "braId";
	public static final String	CON_COMBO	= "conCombo";
	public static final String	COM_ID		= "comId";
	public static final String	CON_DESC	= "conDesc";

	public AssConsume findById(java.lang.String id) {
		log.debug("getting AssConsume instance with id: " + id);
		//			AssConsume instance = (AssConsume) getHibernateTemplate().get("com.zhaozhy.autorstore.entity.AssConsume", id);
		AssConsume instance = (AssConsume) this.getSession().get(AssConsume.class, id);
		return instance;
	}

	public List findByAssId(Object assId) {
		return findByProperty(ASS_ID, assId);
	}

	public List findByConDate(Object conDate) {
		return findByProperty(CON_DATE, conDate);
	}

	public List findByConTime(Object conTime) {
		return findByProperty(CON_TIME, conTime);
	}

	public List findByConAmount(Object conAmount) {
		return findByProperty(CON_AMOUNT, conAmount);
	}

	public List findByConRamount(Object conRamount) {
		return findByProperty(CON_RAMOUNT, conRamount);
	}

	public List findByConType(Object conType) {
		return findByProperty(CON_TYPE, conType);
	}

	public List findByConPoint(Object conPoint) {
		return findByProperty(CON_POINT, conPoint);
	}

	public List findByStaId(Object staId) {
		return findByProperty(STA_ID, staId);
	}

	public List findByBraId(Object braId) {
		return findByProperty(BRA_ID, braId);
	}

	public List findByConCombo(Object conCombo) {
		return findByProperty(CON_COMBO, conCombo);
	}

	public List findByComId(Object comId) {
		return findByProperty(COM_ID, comId);
	}

	public List findByConDesc(Object conDesc) {
		return findByProperty(CON_DESC, conDesc);
	}


	public List findAllTimeMaterial(String fromDate, String endDate, String d_id) throws ParseException {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AssConsume.class);

		DateFormat df = DateFormat.getDateInstance();

		if (fromDate != null && endDate != null) {
			criteria.add(Restrictions.between("id.dealDate", df.parse(fromDate), df.parse(endDate)));
		} else if (fromDate != null && endDate == null) {
			criteria.add(Restrictions.gt("id.dealDate", df.parse(fromDate)));
		} else if (fromDate == null && endDate != null) {
			criteria.add(Restrictions.lt("id.dealDate", df.parse(endDate)));
		}

		if (d_id != null) {
			criteria.add(Restrictions.eq("id.DId", d_id));
		}

		criteria.addOrder(Order.asc("id"));

		return criteria.list();
	}

	public List findAllTimeMaterialPerPage(String fromDate, String endDate, String d_id, int intPage, int intPageSize) throws ParseException {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AssConsume.class);

		DateFormat df = DateFormat.getDateInstance();

		if (fromDate != null && endDate != null) {
			criteria.add(Restrictions.between("id.dealDate", df.parse(fromDate), df.parse(endDate)));
		} else if (fromDate != null && endDate == null) {
			criteria.add(Restrictions.gt("id.dealDate", df.parse(fromDate)));
		} else if (fromDate == null && endDate != null) {
			criteria.add(Restrictions.lt("id.dealDate", df.parse(endDate)));
		}

		if (d_id != null) {
			criteria.add(Restrictions.eq("id.DId", d_id));
		}

		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);

		criteria.addOrder(Order.asc("id"));

		return criteria.list();
	}

	public List findAllTimeAssociator(String fromDate, String endDate, String b_id) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AssConsume.class);

		DateFormat df = DateFormat.getDateInstance();

		try {
			if (fromDate != null && endDate != null) {
				criteria.add(Restrictions.between("id.dealDate", df.parse(fromDate), df.parse(endDate)));
			} else if (fromDate != null && endDate == null) {
				criteria.add(Restrictions.gt("id.dealDate", df.parse(fromDate)));
			} else if (fromDate == null && endDate != null) {
				criteria.add(Restrictions.lt("id.dealDate", df.parse(endDate)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (b_id != null) {
			criteria.add(Restrictions.eq("id.BId", b_id));
		}

		criteria.add(Restrictions.ne("id.AId", "0000000000"));
		return criteria.list();
	}

	public List findAllTimeByBId(String fromDate, String endDate, String b_id) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AssConsume.class);

		DateFormat df = DateFormat.getDateInstance();

		try {
			if (fromDate != null && endDate != null) {
				criteria.add(Restrictions.between("id.dealDate", df.parse(fromDate), df.parse(endDate)));
			} else if (fromDate != null && endDate == null) {
				criteria.add(Restrictions.gt("id.dealDate", df.parse(fromDate)));
			} else if (fromDate == null && endDate != null) {
				criteria.add(Restrictions.lt("id.dealDate", df.parse(endDate)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (b_id != null) {
			criteria.add(Restrictions.eq("id.BId", b_id));
		}

		return criteria.list();
	}

	public List findUsingChartHisQry(Map<String, String> paramap) {
		Session session=this.getSession();
		Criteria criteria=session.createCriteria(AssConsume.class);
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
				criteria.add(Restrictions.between("conDate", beginDate, endDate));
			}else{
				//only beginDate
				criteria.add(Restrictions.ge("conDate", beginDate));
			}
		}else{
			if(endDate!=null&&!endDate.equals("")){
				endDate=DateUtil.format10To8(endDate);
				//only endDate
				criteria.add(Restrictions.le("conDate", endDate));
			}else{
				//beginDate && endDate 都没有
			}
		}
		criteria.addOrder(Order.asc("conId"));
		return criteria.list();
	}

}