package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaozhy.autorstore.dao.SerialGenDAO;
import com.zhaozhy.autorstore.entity.SerialGen;
import com.zhaozhy.autorstore.entity.SerialGenId;
import com.zhaozhy.autorstore.util.DateUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
/**
 * 
 * @Title				SerialGenDAOImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:55:52
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class SerialGenDAOImpl extends BaseDaoImpl<SerialGen> implements SerialGenDAO {
	private static final Log log = LogFactory.getLog(SerialGenDAOImpl.class);
	// property constants
	public static final String SER_NO = "serNo";
	public static final String SER_RULE = "serRule";

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.SerialGenDAO#findById(com.zhaozhy.autorstore.entity.SerialGenId)
	 */
	public SerialGen findById(SerialGenId id) {
		log.debug("getting SerialGen instance with id: " + id);
//			SerialGen instance = (SerialGen) getHibernateTemplate().get( "com.zhaozhy.autorstore.entity.SerialGen", id);
		SerialGen instance=(SerialGen)this.getSession().get(SerialGen.class, id);
			return instance;
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.SerialGenDAO#findBySerNo(java.lang.Object)
	 */
	public List findBySerNo(Object serNo) {
		return findByProperty(SER_NO, serNo);
	}

	/* (non-Javadoc)
	 * @see com.zhaozhy.autorstore.dao.impl.SerialGenDAO#findBySerRule(java.lang.Object)
	 */
	public List findBySerRule(Object serRule) {
		return findByProperty(SER_RULE, serRule);
	}

	public String genAssComboId() {
		String serSmall=DicDataUtil.DICDATA_1001;
		
		return this.genId10(serSmall);
	}

	public String genConsumeListId() {
		String serSmall=DicDataUtil.DICDATA_1003;
		
		return this.genId10(serSmall);
	}

	public String genConsumeId() {
		String serSmall=DicDataUtil.DICDATA_1002;
		
		return this.genId20(serSmall);
	}

	public String genRechargeId() {
		String serSmall=DicDataUtil.DICDATA_1000;
		
		return this.genId20(serSmall);
	}

	public String genMaterialMatId() {
		String serSmall=DicDataUtil.DICDATA_1004;
		
		return this.genId10(serSmall);
	}
	
	public String genId20(String serSmall){
		String serLarge=DicDataUtil.DICDATA_10;
		
		SerialGenId sgId=new SerialGenId();
		sgId.setSerLarge(serLarge);
		sgId.setSerSmall(serSmall);
		
		SerialGen sg=this.findById(sgId);
		String rtnNo=new String();
		String sNo=new String();
		String dateStr=DateUtil.getDateString17();
		if(null==sg){
			//说明此前没有取过流水号，生成一条
			String serlNo=StringUtils.leftPad("1", 3, "0");
			sg=new SerialGen();
			sg.setId(sgId);
			 sNo=dateStr+serlNo;
			sg.setSerNo(sNo);
			rtnNo=sNo;
			sg.setSerRule("yyyyMMddHHmmssS+001");
			this.save(sg);
		}else{
			//流水号生成表中有值，+1返回
			 sNo=sg.getSerNo();
			sNo=sNo.substring(17);
			sNo=StringUtils.leftPad(new Integer(Integer.parseInt(sNo)+1).toString(),3,"0");
			sg.setSerNo(dateStr+sNo);
			this.update(sg);
			rtnNo=dateStr+sNo;
		}
//		System.out.println("Id20:"+sg);
		return rtnNo;
	}

	public String genId10(String serSmall) {
		String serLarge=DicDataUtil.DICDATA_10;
		
		String rtnId=new String();
		SerialGenId sgId=new SerialGenId();
		sgId.setSerLarge(serLarge);
		sgId.setSerSmall(serSmall);
		
		SerialGen sg=this.findById(sgId);
		if(null==sg){
			//之前没有取过，从1开始
			String serlNo=StringUtils.leftPad("1", 10, "0");
			sg=new SerialGen();
			sg.setId(sgId);
			sg.setSerNo(serlNo);
			sg.setSerRule("十位数字,不足十位前补零");
			this.save(sg);
			rtnId=serlNo;
		}else{
			//流水号生成表中有值，+1返回
			Integer sno=Integer.parseInt(sg.getSerNo());
			sno++;
			String serStr=StringUtils.leftPad(sno.toString(), 10, "0");
			sg.setSerNo(serStr);
			this.update(sg);
			rtnId=serStr;
		}
		return rtnId;
	}
}