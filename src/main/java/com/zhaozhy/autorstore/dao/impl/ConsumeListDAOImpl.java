package com.zhaozhy.autorstore.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.zhaozhy.autorstore.dao.ConsumeListDAO;
import com.zhaozhy.autorstore.entity.ConsumeList;
import com.zhaozhy.autorstore.util.DateUtil;
/**
 * 
 * @Title				ConsumeListDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:16:44
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ConsumeListDAOImpl extends BaseDaoImpl<ConsumeList> implements ConsumeListDAO {
	private static final Log log = LogFactory.getLog(ConsumeListDAOImpl.class);
	// property constants
	public static final String CON_ID = "conId";
	public static final String REP_ID = "repId";
	public static final String MAT_ID = "matId";
	public static final String LIS_NUM = "lisNum";
	public static final String LIS_MONEY = "lisMoney";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ConsumeListDAO#findById(java.lang.String)
	 */
	public ConsumeList findById(java.lang.String id) {
		log.debug("getting ConsumeList instance with id: " + id);
//			ConsumeList instance = (ConsumeList) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.ConsumeList", id);
		ConsumeList instance=(ConsumeList)this.getSession().get(ConsumeList.class, id);
			return instance;
	}


	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ConsumeListDAO#findByConId(java.lang.Object)
	 */
	public List findByConId(Object conId) {
		return findByProperty(CON_ID, conId);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ConsumeListDAO#findByRepId(java.lang.Object)
	 */
	public List findByRepId(Object repId) {
		return findByProperty(REP_ID, repId);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ConsumeListDAO#findByMatId(java.lang.Object)
	 */
	public List findByMatId(Object matId) {
		return findByProperty(MAT_ID, matId);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ConsumeListDAO#findByLisNum(java.lang.Object)
	 */
	public List findByLisNum(Object lisNum) {
		return findByProperty(LIS_NUM, lisNum);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.ConsumeListDAO#findByLisMoney(java.lang.Object)
	 */
	public List findByLisMoney(Object lisMoney) {
		return findByProperty(LIS_MONEY, lisMoney);
	}


	public List findUsingChartHisQry(Map<String, String> valueMap) {
		Session session=this.getSession();
		String beginDate=valueMap.get("beginDate");
		String endDate=valueMap.get("endDate");
		String repId=valueMap.get("repId");
		String matId=valueMap.get("matId");
		StringBuffer sql=new StringBuffer("");
		/*
		if(repId!=null&&!repId.equals("")){
			sql.append("SELECT c.rep_Id,sum(c.lis_Num) nsum FROM Consume_List c where c.rep_Id is not null and c.rep_Id<>''  ");
		}else if(matId!=null&&!matId.equals("")){
			sql.append(" select c.mat_Id,sum(c.lis_Num) nsum from Consume_List c where c.mat_Id is not null and c.mat_Id<>'' ");
		}else{
			
		}
		*/
		sql.append("select c.rep_id REP_ID ,c.mat_id MAT_ID ,sum(c.lis_num) NSUM from autorstore.consume_list c where 1=1 ");
		if(beginDate!=null&&!beginDate.equals("")){
			beginDate=DateUtil.format10To8(beginDate);
			if(endDate!=null&&!endDate.equals("")){
				endDate=DateUtil.format10To8(endDate);
				sql.append(" and substr(con_Id,1,8) between '"+beginDate+"'  and '"+endDate+"' ");
			}else{
				//only beginDate
				sql.append(" and substr(con_Id,1,8) >= '"+beginDate+"' ");
			}
		}else{
			if(endDate!=null&&!endDate.equals("")){
				//only endDate
				endDate=DateUtil.format10To8(endDate);
				sql.append(" and substr(con_Id,1,8) <= '"+endDate+"' ");
			}else{
				// beginDate && endDate 都没有
			}
		}
		if(repId!=null&&!repId.equals("")){
			sql.append(" and c.rep_Id='"+repId+"' ");
		}else if(matId!=null&&!matId.equals("")){
			sql.append(" and c.mat_Id='"+matId+"' ");
		}else{
			
		}
		sql.append(" group by c.rep_id,c.mat_id ");
//		System.out.println("sql====="+sql.toString());
		 SQLQuery query=session.createSQLQuery(sql.toString());
		//设定结果结果集中的每个对象为Map类型   
		  query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

}