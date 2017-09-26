package com.zhaozhy.autorstore.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.loader.criteria.CriteriaLoader;

import com.zhaozhy.autorstore.dao.ConsumeCartDAO;
import com.zhaozhy.autorstore.entity.ConsumeCart;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * 
 * @Title ConsumeCartDAOImpl.java
 * @Package com.zhaozhy.autorstore.dao.impl
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-23 上午09:11:56
 * @Desc TODO
 * @Version V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ConsumeCartDAOImpl extends BaseDaoImpl<ConsumeCart> implements
		ConsumeCartDAO {
	private static final Log log = LogFactory.getLog(ConsumeCartDAOImpl.class);
	// property constants
	public static final String ASS_ID = "assId";
	public static final String REP_ID = "repId";
	public static final String MAT_ID = "matId";
	public static final String CART_NUM = "cartNum";
	public static final String CART_PERPRICE = "cartPerprice";
	public static final String CART_MONEY = "cartMoney";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findById(java.lang.Integer
	 * )
	 */
	public ConsumeCart findById(java.lang.Integer id) {
		log.debug("getting ConsumeCart instance with id: " + id);
		ConsumeCart instance = (ConsumeCart) getSession().get(
				ConsumeCart.class, id);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findByAssId(java.lang.
	 * Object)
	 */
	public List findByAssId(Object assId) {
		return findByProperty(ASS_ID, assId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findByRepId(java.lang.
	 * Object)
	 */
	public List findByRepId(Object repId) {
		return findByProperty(REP_ID, repId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findByMatId(java.lang.
	 * Object)
	 */
	public List findByMatId(Object matId) {
		return findByProperty(MAT_ID, matId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findByCartNum(java.lang
	 * .Object)
	 */
	public List findByCartNum(Object cartNum) {
		return findByProperty(CART_NUM, cartNum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findByCartPerprice(java
	 * .lang.Object)
	 */
	public List findByCartPerprice(Object cartPerprice) {
		return findByProperty(CART_PERPRICE, cartPerprice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhaozhy.autorstore.dao.impl.ConsumeCartDAO#findByCartMoney(java.lang
	 * .Object)
	 */
	public List findByCartMoney(Object cartMoney) {
		return findByProperty(CART_MONEY, cartMoney);
	}

	public ConsumeCart ifHasSameItem(ConsumeCart cart) {
		String assId = cart.getAssId();
		String repId = cart.getRepId();
		String matId = cart.getMatId();
		List<ConsumeCart> carts = this.findByAssId(assId);
		ConsumeCart rtnCart = null;
		for (ConsumeCart c : carts) {
			String rep_id = c.getRepId();
			String mat_id = c.getMatId();
			if (repId != null && !repId.equals("")) {
				if (rep_id != null && !rep_id.equals("")) {
					if (rep_id.equals(repId)) {
						rtnCart = c;
					}
				}
			} else if (matId != null && !matId.equals("")) {
				if (mat_id != null && !mat_id.equals("")) {
					if (mat_id.equals(matId)) {
						rtnCart = c;
					}
				}
			}
		}
		return rtnCart;
	}

	public int[] deleteCartItems(List keyList) {
		int[] results = new int[keyList.size()];
		for (int i = 0; i < keyList.size(); i++) {
			Map key = (Map) keyList.get(i);
			String recordKey = (String) key.get(DicDataUtil.RECORDKEY);
			this.delete(this.findById(new Integer(recordKey)));
			results[i] = 1;
		}
		return results;
	}

	public List<ConsumeCart> findByAssIdWithMatIdNotNull(String assId) {
		Session session = this.getSession();
		Criteria criterial = session.createCriteria(ConsumeCart.class);

		List<ConsumeCart> rtnList = criterial
				.add(Restrictions.eq("assId", assId))//
				.add(Restrictions.isNotNull("matId"))//
				.list();
		return rtnList;
	}

}