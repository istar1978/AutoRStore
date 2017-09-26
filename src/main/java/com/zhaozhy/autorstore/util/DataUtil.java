package com.zhaozhy.autorstore.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.sysadmin.DictionaryView;
/**
 * 
 * @Title				DataUtil.java
 * @Package		com.zhaozhy.autorstore.util
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-19   下午09:20:22
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
@SuppressWarnings("unchecked")
public class DataUtil {

	public static Map<String, String> transformList2Map(List dataList,Class clazz){
//		for(int i=0;i<dataList.size();i++){
//			(Class)dataList.get(i);
//		}
		return null;
	}
	/**
	 * 
	 * @CreateDate	2017-7-8  下午05:07:30
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					拼装维修材料下拉列表所需map
	 * @param matList
	 * @return
	 */
	public static Map getOptionsEc4Mat(List<Material> matList) {
		Map rtnMap=new HashMap();
		rtnMap.put("", "-------请选择---------");
		for(Material mat:matList){
			rtnMap.put(mat.getId().getMatId(), mat.getId().getMatId()+":"+mat.getMatName());
		}
		return rtnMap;
	}
	/**
	 * 
	 * @CreateDate	2017-7-8  下午05:03:35
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					拼装维修项目下拉列表所需 map
	 * @param repList
	 * @return
	 */
	public static Map getOptionsEc4Rep(List<RepairItem> repList){
		Map rtnMap=new HashMap();
		rtnMap.put("", "-------请选择---------");
		for(RepairItem ri:repList){
			rtnMap.put(ri.getRepId(),ri.getRepId()+":"+ri.getRepName());
		}
		return rtnMap;
	}
	
	/**
	 * 
	 * @CreateDate	2017-6-19  下午09:20:27
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					下拉列表框没有  第一项 “------请选择------”
	 * @param dataMap
	 * @return
	 */
	public static List getSelectListDefault(Map<String,String> dataMap){
		List rtnList=new ArrayList();
		DictionaryView dirView;
		Set<String> skey=dataMap.keySet();
		for(String key:skey){
			dirView=new DictionaryView();
			String svalue=dataMap.get(key);
			dirView.setCode(key);
			dirView.setName(key+":"+svalue);
			rtnList.add(dirView);
		}
		return rtnList;
	}
	/**
	 * 
	 * @CreateDate	2017-6-22  上午07:21:30
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					下拉列表框第一项 “------请选择------”
	 * @param dataMap
	 * @return
	 */
	public static List getSelectList(Map<String,String> dataMap){
		List rtnList=new ArrayList();
		DictionaryView dirView=new DictionaryView();
		dirView.setCode("");
		dirView.setName(DicDataUtil.SELECT_NULLVALUE);
		rtnList.add(dirView);
		Set<String> skey=dataMap.keySet();
		for(String key:skey){
			dirView=new DictionaryView();
			String svalue=dataMap.get(key);
			dirView.setCode(key);
			dirView.setName(key+":"+svalue);
			rtnList.add(dirView);
		}
		return rtnList;
	}
	/**
	 * 
	 * @CreateDate	2017-6-23  下午09:00:44
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					下拉列表框第一项 “------请选择------”,下拉列表值形如：0101010101:机油:100
	 * @param matList
	 * @return
	 */
	public static List getSelectListMat(List<Material> matList){
		List rtnList=new ArrayList();
		DictionaryView dirView=new DictionaryView();
		dirView.setCode("");
		dirView.setName(DicDataUtil.SELECT_NULLVALUE);
		rtnList.add(dirView);
		for(Material mat:matList){
			dirView=new DictionaryView();
			dirView.setCode(mat.getId().getMatId());
			dirView.setName(mat.getId().getMatId()+":"+mat.getMatName()+":"+mat.getMatNum());
			rtnList.add(dirView);
		}
		return rtnList;
	}
	/**
	 * 
	 * @CreateDate	2017-6-20  下午09:14:29
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据传入的key，在sysinit.properties文件中查找对应的值 返回
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getValueByKeyPro(String key) throws IOException{
		Properties prop=new Properties();
		InputStream in=DataUtil.class.getResourceAsStream("/sysinit.properties");
		prop.load(in);
		return prop.getProperty(key);
	}
	
	/**
	 * 
	 * @CreateDate	2017年9月20日  下午12:01:35
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					为增加系统改变数据库的灵活性，增加此方法取得数据库备份文件地址
	 * @return				读取配置文件中的相关参数返回
	 * @throws IOException
	 */
	public static String getDBFilepath4Mail() throws IOException{
//		String filepath=DataUtil.getValueByKeyPro(DicDataUtil.MAIL_FILEPATH);
		String dbtype=DataUtil.getValueByKeyPro(DicDataUtil.DBTYPE);
		return DataUtil.getValueByKeyPro(DicDataUtil.MAIL_FILEPATH+"."+dbtype);
	}
	/**
	 * 
	 * @CreateDate	2017-6-20  下午09:45:46
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					计算本次积分
	 * @param money 金额
	 * @return					返回本次积分
	 * @throws IOException 
	 */
	public static Integer calculatePoint(BigDecimal money) throws IOException{
		//积分规则 从配置文件中取
		String rule=DataUtil.getValueByKeyPro(DicDataUtil.POINT_RULE);
		int ruleInt=Integer.parseInt(rule);
		BigDecimal point=money.divideToIntegralValue(new BigDecimal(ruleInt));
		return point.intValue();
	}
	
	/**
	 * 
	 * @CreateDate	2017-6-21  上午10:13:27
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据传入的积分，判断会员是哪个级别
	 * @param point	积分
	 * @return
	 * @throws IOException
	 */
	public static String getNewAssLevel(Integer point) throws IOException{
		String level01=DataUtil.getValueByKeyPro(DicDataUtil.ASSOCIATOR_LEVEL_01);
		String level02=DataUtil.getValueByKeyPro(DicDataUtil.ASSOCIATOR_LEVEL_02);
		Integer level01Int=Integer.parseInt(level01);
		Integer level02Int=Integer.parseInt(level02);
		
		if(point<level01Int){
			return DicDataUtil.DICDATA_000600;
		}else if(point>=level01Int&&point<level02Int){
			return DicDataUtil.DICDATA_000601;
		}else{
			return DicDataUtil.DICDATA_000602;
		}
	}
	/**
	 * 
	 * @CreateDate	2017-6-24  下午08:48:44
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据金额和会员级别，查询配置文件，返回实收金额
	 * @param moneySrc 金额
	 * @param level			会员级别
	 * @return
	 * @throws IOException
	 */
	public static BigDecimal getDiscountAmount(BigDecimal moneySrc,String level) throws IOException{
		String discount00=DataUtil.getValueByKeyPro(DicDataUtil.ASSOCIATOR_DISCOUNT_00);
		String discount01=DataUtil.getValueByKeyPro(DicDataUtil.ASSOCIATOR_DISCOUNT_01);
		String discount02=DataUtil.getValueByKeyPro(DicDataUtil.ASSOCIATOR_DISCOUNT_02);
		BigDecimal dis00=new BigDecimal(discount00);
		BigDecimal dis01=new BigDecimal(discount01);
		BigDecimal dis02=new BigDecimal(discount02);

		if(level.equals(DicDataUtil.DICDATA_000601)){
			//一级会员
			return moneySrc.multiply(dis01);
		}else if(level.equals(DicDataUtil.DICDATA_000602)){
			//二级会员
			return moneySrc.multiply(dis02);
		}else{
			//其余暂定 普通会员
			return moneySrc.multiply(dis00);
		}
	}
}
