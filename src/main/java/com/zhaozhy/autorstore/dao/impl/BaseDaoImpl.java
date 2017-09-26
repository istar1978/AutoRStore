package com.zhaozhy.autorstore.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.zhaozhy.autorstore.dao.BaseDAO;
import com.zhaozhy.autorstore.util.GenericsUtils;
/**
 * 
 * @Title				BaseDaoImpl.java
 * @Package		com.zhaozhy.autorstore.dao.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-19   下午01:18:12
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T>  implements BaseDAO<T> {
	private static final Log log=LogFactory.getLog(BaseDaoImpl.class);
	private Class clazz; // clazz中存储了子类当前操作实体类型
	public BaseDaoImpl(){
		// 如果子类调用当前构造方法,this代表的是子类对象
		log.info(this);
		log.info("获取父类信息:" + this.getClass().getSuperclass());
		log.info("获取父类信息包括泛型信息:" + this.getClass().getGenericSuperclass());
//		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
//		clazz=(Class)type.getActualTypeArguments()[0];
		clazz=GenericsUtils.getSuperClassGenricType(this.getClass());
		log.info("dao clazz:" + clazz);
	}	
	
	protected SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public void delete(String id) {
		this.getSession().delete(this.get(id));
	}

	
	public T get(String id) {
		return (T)getSession().get(clazz, id);
	}

	public List<T> query() {
		String hql = "FROM " + clazz.getSimpleName();
		return getSession().createQuery(hql) //
				.list();
	}

	public void save(T t) {
		this.getSession().save(t);
	}

	public void update(T t) {
		this.getSession().update(t);
	}

	public List<T> findByExample(T t){
		return this.getSession().createCriteria(clazz).add(Example.create(t)).list();
	}

	public void delete(T t) {
		this.getSession().delete(t);
	}

	public List<T> findAll() {
		return this.query();
	}

	public List findByProperty(String propertyName, Object value) {
		String queryString = "from "+clazz.getSimpleName()+" as model where model." + propertyName + "=:proName ";
//		return getHibernateTemplate().find(queryString, value);
		return this.getSession().createQuery(queryString).setParameter("proName", value).list();
	}

	public void deleteAll() {
		String hql="delete from "+clazz.getSimpleName();
		this.getSession().createQuery(hql).executeUpdate();
	}
}
