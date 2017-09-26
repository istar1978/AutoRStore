package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaozhy.autorstore.dao.ItemMateDAO;
import com.zhaozhy.autorstore.entity.ItemMate;
import com.zhaozhy.autorstore.entity.ItemMateId;
/**
 * 
 * @Title				ItemMateDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:52:02
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ItemMateDAOImpl extends BaseDaoImpl<ItemMate> implements ItemMateDAO {
	private static final Log log = LogFactory.getLog(ItemMateDAOImpl.class);
	// property constants
	public static final String REP_ID = "repId";
	public static final String MAT_ID = "matId";
	public static final String IM_UNIT = "imUnit";
	public static final String IM_NUM = "imNum";


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ItemMateDAO#findById(com.zhaozhy.autorstore.entity.ItemMateId)
	 */
	public ItemMate findById(ItemMateId id) {
		log.debug("getting ItemMate instance with id: " + id);
//			ItemMate instance = (ItemMate) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.ItemMate", id);
		ItemMate instance=(ItemMate)this.getSession().get(ItemMate.class, id);
			return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ItemMateDAO#findByRepId(java.lang.Object)
	 */
	public List findByRepId(Object repId) {
		return findByProperty(REP_ID, repId);
	}

}