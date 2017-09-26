package com.zhaozhy.autorstore.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaozhy.autorstore.dao.AssComboDAO;
import com.zhaozhy.autorstore.dao.AssConsumeDAO;
import com.zhaozhy.autorstore.dao.AssRechargeDAO;
import com.zhaozhy.autorstore.dao.AssociatorDAO;
import com.zhaozhy.autorstore.dao.BaseDAO;
import com.zhaozhy.autorstore.dao.BranchDAO;
import com.zhaozhy.autorstore.dao.ConsumeCartDAO;
import com.zhaozhy.autorstore.dao.ConsumeListDAO;
import com.zhaozhy.autorstore.dao.DepMenuDAO;
import com.zhaozhy.autorstore.dao.DepartmentDAO;
import com.zhaozhy.autorstore.dao.DicDataDAO;
import com.zhaozhy.autorstore.dao.ItemMateDAO;
import com.zhaozhy.autorstore.dao.MaterialDAO;
import com.zhaozhy.autorstore.dao.MenuDAO;
import com.zhaozhy.autorstore.dao.RepairItemDAO;
import com.zhaozhy.autorstore.dao.SerialGenDAO;
import com.zhaozhy.autorstore.dao.StafferDAO;
import com.zhaozhy.autorstore.dao.Static2DAO;
import com.zhaozhy.autorstore.dao.Static3DAO;
import com.zhaozhy.autorstore.dao.Static4DAO;
import com.zhaozhy.autorstore.service.BaseService;
import com.zhaozhy.autorstore.util.GenericsUtils;
/**
 * 
 * @Title				BaseServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-19   下午01:14:05
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	private static final Log log=LogFactory.getLog(BaseServiceImpl.class);
	private Class clazz; // clazz中存储了子类当前操作实体类型
	protected BaseDAO baseDAO;
	
	protected AssComboDAO assComboDAO;
	protected AssConsumeDAO assConsumeDAO;
	protected AssociatorDAO associatorDAO;
	protected AssRechargeDAO assRechargeDAO;
	protected BranchDAO branchDAO;
	protected ConsumeCartDAO consumeCartDAO;
	protected ConsumeListDAO consumeListDAO;
	protected DepartmentDAO departmentDAO;
	protected DepMenuDAO depMenuDAO;
	protected DicDataDAO dicDataDAO;
	protected ItemMateDAO itemMateDAO;
	protected MaterialDAO materialDAO;
	protected MenuDAO menuDAO;
	protected RepairItemDAO repairItemDAO;
	protected SerialGenDAO serialGenDAO;
	protected StafferDAO stafferDAO;
	protected Static2DAO static2DAO;
	protected Static3DAO static3DAO;
	protected Static4DAO static4DAO;
	
	
	public BaseServiceImpl() {
		// 如果子类调用当前构造方法,this代表的是子类对象
		log.info(this);
		log.info("获取父类信息:" + this.getClass().getSuperclass());
		log.info("获取父类信息包括泛型信息:"
				+ this.getClass().getGenericSuperclass());
//		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//		clazz = (Class) type.getActualTypeArguments()[0];
		clazz=GenericsUtils.getSuperClassGenricType(this.getClass());
		log.info("service clazz:" + clazz);
		// 此处 dao还有没有实例化, 不能实现给baseDao的赋值操作
//		System.out.println("baseDao:" + baseDao);
//		System.out.println("categoryDao:" + categoryDao);
	}
	
	public void init() throws Exception{
		// 1: 根据具体的泛型, 获取相应的Field字段, categoryDao
				String clazzName=clazz.getSimpleName();
				String clazzDaoName=clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "DAO";
				Field clazzField=this.getClass().getSuperclass().getDeclaredField(clazzDaoName);
				// 2: 获取baseDao Filed字段
				Field baseField=this.getClass().getSuperclass().getDeclaredField("baseDAO");
				// 3: 把categoryDao的值赋值给baseDao
				baseField.set(this,clazzField.get(this));
				log.info("baseDao:" + baseDAO);
	}
	
	public void delete(String id) {
		this.baseDAO.delete(id);
	}

	public T get(String id) {
		return (T)this.baseDAO.get(id);
	}

	public List<T> query() {
		return this.baseDAO.query();
	}

	public void save(T t) {
		this.baseDAO.save(t);
	}

	public void update(T t) {
		this.baseDAO.update(t);
	}

	public AssComboDAO getAssComboDAO() {
		return assComboDAO;
	}

	public void setAssComboDAO(AssComboDAO assComboDAO) {
		this.assComboDAO = assComboDAO;
	}

	public AssConsumeDAO getAssConsumeDAO() {
		return assConsumeDAO;
	}

	public void setAssConsumeDAO(AssConsumeDAO assConsumeDAO) {
		this.assConsumeDAO = assConsumeDAO;
	}

	public AssociatorDAO getAssociatorDAO() {
		return associatorDAO;
	}

	public void setAssociatorDAO(AssociatorDAO associatorDAO) {
		this.associatorDAO = associatorDAO;
	}


	public AssRechargeDAO getAssRechargeDAO() {
		return assRechargeDAO;
	}

	public void setAssRechargeDAO(AssRechargeDAO assRechargeDAO) {
		this.assRechargeDAO = assRechargeDAO;
	}

	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public ConsumeCartDAO getConsumeCartDAO() {
		return consumeCartDAO;
	}

	public void setConsumeCartDAO(ConsumeCartDAO consumeCartDAO) {
		this.consumeCartDAO = consumeCartDAO;
	}

	public ConsumeListDAO getConsumeListDAO() {
		return consumeListDAO;
	}

	public void setConsumeListDAO(ConsumeListDAO consumeListDAO) {
		this.consumeListDAO = consumeListDAO;
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public DepMenuDAO getDepMenuDAO() {
		return depMenuDAO;
	}

	public void setDepMenuDAO(DepMenuDAO depMenuDAO) {
		this.depMenuDAO = depMenuDAO;
	}

	public DicDataDAO getDicDataDAO() {
		return dicDataDAO;
	}

	public void setDicDataDAO(DicDataDAO dicDataDAO) {
		this.dicDataDAO = dicDataDAO;
	}

	public ItemMateDAO getItemMateDAO() {
		return itemMateDAO;
	}

	public void setItemMateDAO(ItemMateDAO itemMateDAO) {
		this.itemMateDAO = itemMateDAO;
	}

	public MaterialDAO getMaterialDAO() {
		return materialDAO;
	}

	public void setMaterialDAO(MaterialDAO materialDAO) {
		this.materialDAO = materialDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public RepairItemDAO getRepairItemDAO() {
		return repairItemDAO;
	}

	public void setRepairItemDAO(RepairItemDAO repairItemDAO) {
		this.repairItemDAO = repairItemDAO;
	}

	public SerialGenDAO getSerialGenDAO() {
		return serialGenDAO;
	}

	public void setSerialGenDAO(SerialGenDAO serialGenDAO) {
		this.serialGenDAO = serialGenDAO;
	}

	public StafferDAO getStafferDAO() {
		return stafferDAO;
	}

	public void setStafferDAO(StafferDAO stafferDAO) {
		this.stafferDAO = stafferDAO;
	}

	public Static2DAO getStatic2DAO() {
		return static2DAO;
	}

	public void setStatic2DAO(Static2DAO static2DAO) {
		this.static2DAO = static2DAO;
	}

	public Static3DAO getStatic3DAO() {
		return static3DAO;
	}

	public void setStatic3DAO(Static3DAO static3DAO) {
		this.static3DAO = static3DAO;
	}

	public Static4DAO getStatic4DAO() {
		return static4DAO;
	}

	public void setStatic4DAO(Static4DAO static4DAO) {
		this.static4DAO = static4DAO;
	}

	public void delete(T t) {
		this.baseDAO.delete(t);
	}

	public void deleteAll() {
		this.baseDAO.deleteAll();
	}

	public List<T> findAll() {
		return this.baseDAO.findAll();
	}

	public List<T> findByExample(T t) {
		return this.baseDAO.findByExample(t);
	}

	public List findByProperty(String propertyName, Object value) {
		return this.baseDAO.findByProperty(propertyName, value);
	}

	
}
