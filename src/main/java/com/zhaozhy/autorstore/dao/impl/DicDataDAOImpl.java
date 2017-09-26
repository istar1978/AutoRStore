package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.zhaozhy.autorstore.dao.DicDataDAO;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;


public class DicDataDAOImpl extends BaseDaoImpl<DicData> implements DicDataDAO {
	private static final Log log = LogFactory.getLog(DicDataDAOImpl.class);
	// property constants
	public static final String DIC_NAME = "dicName";
	public static final String DIC_TEXT = "dicText";


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DicDataDAO#findById(com.zhaozhy.autorstore.entity.DicDataId)
	 */
	public DicData findById(com.zhaozhy.autorstore.entity.DicDataId id) {
		log.debug("getting DicData instance with id: " + id);
//			DicData instance = (DicData) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.DicData", id);
		DicData instance=(DicData)this.getSession().get(DicData.class, id);
			return instance;
	}


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DicDataDAO#findByDicName(java.lang.Object)
	 */
	public List findByDicName(Object dicName) {
		return findByProperty(DIC_NAME, dicName);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DicDataDAO#findByDicText(java.lang.Object)
	 */
	public List findByDicText(Object dicText) {
		return findByProperty(DIC_TEXT, dicText);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DicDataDAO#findByIdProperty(com.zhaozhy.autorstore.entity.DicDataId)
	 */
	public List findByIdProperty(DicDataId id){
		log.debug("finding DicData instance with id");
		Session session=this.getSession();
		Criteria criteria=session.createCriteria(DicData.class);
		if(id.getDicLarge()!=null){
			criteria.add(Restrictions.like("id.dicLarge", id.getDicLarge().trim().replace("*", "%")+"%"));
		}
		if(id.getDicValue()!=null){
			criteria.add(Restrictions.like("id.dicValue", id.getDicValue().trim().replace("*", "%")+"%"));
		}
		criteria.addOrder(Order.asc("id.dicLarge"));
		criteria.addOrder(Order.asc("id.dicValue"));
		return criteria.list();
		
	}
	
	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DicDataDAO#findPageByIdProperty(com.zhaozhy.autorstore.entity.DicDataId, int, int)
	 */
	public List findPageByIdProperty(DicDataId id,int intPage, int intPageSize){
		log.debug("finding DicData instance with id page");
		Session session=this.getSession();
		Criteria criteria=session.createCriteria(DicData.class);
		
		if(id.getDicLarge()!=null){
			criteria.add(Restrictions.like("id.dicLarge", id.getDicLarge().trim().replace("*", "%")+"%"));
		}
		if(id.getDicValue()!=null){
			criteria.add(Restrictions.like("id.dicValue", id.getDicValue().trim().replace("*", "%")+"%"));
		}
		
		criteria.addOrder(Order.asc("id.dicLarge"));
		criteria.addOrder(Order.asc("id.dicValue"));
		criteria.setFirstResult((intPage - 1) * intPageSize);
		criteria.setMaxResults(intPageSize);

		return criteria.list();
	}
	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.DicDataDAO#findAllByExample(java.lang.String)
	 */
	public List findAllByExample(String dicDataLarge){
		log.debug("find dicdata list with dicdata large:"+dicDataLarge);
		Session session=this.getSession();
		Criteria criterial=session.createCriteria(DicData.class);
		criterial.add(Restrictions.like("id.dicLarge", dicDataLarge));
		criterial.addOrder(Order.asc("id.dicLarge"));
		return criterial.list();
	}
	
}