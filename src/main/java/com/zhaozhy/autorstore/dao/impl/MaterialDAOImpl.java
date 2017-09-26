package com.zhaozhy.autorstore.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.MaterialDAO;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
/**
 * 
 * @Title				MaterialDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午10:47:38
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class MaterialDAOImpl extends BaseDaoImpl<Material> implements MaterialDAO {
	private static final Log log = LogFactory.getLog(MaterialDAOImpl.class);
	// property constants
	public static final String MAT_NAME = "matName";
	public static final String REP_ID = "repId";
	public static final String MAT_CLASSIFY = "matClassify";
	public static final String MAT_PREPRICE = "matPreprice";
	public static final String MAT_REALPRICE = "matRealprice";
	public static final String MAT_FACTORY = "matFactory";
	public static final String MAT_NUM = "matNum";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findById(com.zhaozhy.autorstore.entity.MaterialId)
	 */
	public Material findById(MaterialId id) {
		log.debug("getting Material instance with id: " + id);
//			Material instance = (Material) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.Material", id);
		Material instance=(Material)this.getSession().get(Material.class,id);
			return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByMatName(java.lang.Object)
	 */
	public List findByMatName(Object matName) {
		return findByProperty(MAT_NAME, matName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByRepId(java.lang.Object)
	 */
	public List findByRepId(Object repId) {
		return findByProperty(REP_ID, repId);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByMatClassify(java.lang.Object)
	 */
	public List findByMatClassify(Object matClassify) {
		return findByProperty(MAT_CLASSIFY, matClassify);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByMatPreprice(java.lang.Object)
	 */
	public List findByMatPreprice(Object matPreprice) {
		return findByProperty(MAT_PREPRICE, matPreprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByMatRealprice(java.lang.Object)
	 */
	public List findByMatRealprice(Object matRealprice) {
		return findByProperty(MAT_REALPRICE, matRealprice);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByMatFactory(java.lang.Object)
	 */
	public List findByMatFactory(Object matFactory) {
		return findByProperty(MAT_FACTORY, matFactory);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findByMatNum(java.lang.Object)
	 */
	public List findByMatNum(Object matNum) {
		return findByProperty(MAT_NUM, matNum);
	}
	
	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findAllByExample(com.zhaozhy.autorstore.entity.Material)
	 */
	public List findAllByExample(Material material) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Material.class);

		MaterialId materialId = material.getId();

		String dr_id = materialId.getMatId();
		String branch_id = materialId.getBraId();
		String dr_stat = materialId.getMatStat();

		String dr_name = material.getMatName();
		String dr_category = material.getMatClassify();
		String dr_factory = material.getMatFactory();
		Date inDate=material.getMatIndate();
		Date proDate=material.getMatProdate();
		BigDecimal prePrice=material.getMatPreprice();
		BigDecimal realPrice=material.getMatRealprice();
		Integer matNum=material.getMatNum();

		if (StringUtils.isNotEmpty(dr_id)) {
			criteria.add(Restrictions.like("id.matId", dr_id.trim().replace("*", "%") + "%"));
		}
		if (StringUtils.isNotEmpty(branch_id)) {
			criteria.add(Restrictions.like("id.braId", branch_id.trim().replace("*", "%") + "%"));
		}
		if (StringUtils.isNotEmpty(dr_stat)) {
			criteria.add(Restrictions.like("id.matStat", dr_stat.trim().replace( "*", "%") + "%"));
		}
		if (StringUtils.isNotEmpty(dr_name)) {
			criteria.add(Restrictions.like("matName", dr_name.trim().replace("*", "%") + "%"));
		}
		if (StringUtils.isNotEmpty(dr_category)) {
			criteria.add(Restrictions.like("matClassify", dr_category.trim() .replace("*", "%") + "%"));
		}
		if (StringUtils.isNotEmpty(dr_factory)) {
			criteria.add(Restrictions.like("matFactory", dr_factory.trim() .replace("*", "%") + "%"));
		}
		if(inDate!=null){
			criteria.add(Restrictions.eq("matIndate", inDate));
		}
		if(proDate!=null){
			criteria.add(Restrictions.eq("matProdate", proDate));
		}
		if(matNum!=null){
			criteria.add(Restrictions.eq("matNum", matNum));
		}

		criteria.addOrder(Order.asc("id.matId"));
		criteria.addOrder(Order.asc("id.braId"));
		criteria.addOrder(Order.asc("id.matStat"));

		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findAllByExamplePerPage(com.zhaozhy.autorstore.entity.Material, int, int)
	 */
	public List findAllByExamplePerPage(Material material, int intPage, int intPageSize) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Material.class);

		MaterialId materialId = material.getId();

		String dr_id = materialId.getMatId();
		String branch_id = materialId.getBraId();
		String dr_stat = materialId.getMatStat();

		String dr_name = material.getMatName();

		String dr_category = material.getMatClassify();
		String dr_factory = material.getMatFactory();

		if (dr_id != null) {
			criteria.add(Restrictions.like("id.matId", dr_id.trim().replace("*",
					"%")
					+ "%"));
		}

		if (branch_id != null) {
			criteria.add(Restrictions.like("id.braId", branch_id.trim()
					.replace("*", "%")
					+ "%"));
		}

		if (dr_stat != null) {
			criteria.add(Restrictions.like("id.matStat", dr_stat.trim().replace(
					"*", "%")
					+ "%"));
		}

		if (dr_name != null) {
			criteria.add(Restrictions.like("matName", dr_name.trim().replace("*",
					"%")
					+ "%"));
		}

		if (dr_category != null) {
			criteria.add(Restrictions.like("matClassify", dr_category.trim()
					.replace("*", "%")
					+ "%"));
		}

		if (dr_factory != null) {
			criteria.add(Restrictions.like("matFactory", dr_factory.trim()
					.replace("*", "%")
					+ "%"));
		}


		criteria.addOrder(Order.asc("id.matId"));
		criteria.addOrder(Order.asc("id.braId"));
		criteria.addOrder(Order.asc("id.matStat"));

		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);

		return criteria.list();
	}
	
	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.MaterialDAO#findAllHas()
	 */
	public List findAllHas() {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Material.class);
		criteria.add(Restrictions.eq("id.stat", "*"));

		criteria.addOrder(Order.asc("id"));

		return criteria.list();
	}

	public List findByBraidStat(String braId, String stat) {
		log.debug("materialdaoimpl.findbybraidstat()");
		Session session=this.getSession();
		Criteria criteria=session.createCriteria(Material.class);
		return criteria.add(Restrictions.eq("id.braId", braId))//
								.add(Restrictions.eq("id.matStat", stat))//
								.addOrder(Order.asc("id.matId"))//
								.list();
	}

	public BigDecimal getPerPriceByMatId(String matId) {
		String hql="SELECT m.matRealprice FROM Material m where m.id.matId=:matId";
		return (BigDecimal)this.getSession().createQuery(hql)//
																			.setString("matId", matId)//
																			.uniqueResult();
	}

}