package com.zhaozhy.autorstore.filter;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

/**
 * 与事务有关的辅助类
 * 
 * @author zhaozy
 * 
 */
public class MyOpenSessionInViewFilter extends OpenSessionInViewFilter {

	@Override
	protected Session getSession(SessionFactory sessionFactory)
			throws DataAccessResourceFailureException {
		// TODO Auto-generated method stub
		this.setFlushMode(FlushMode.AUTO);
		// this.setFlushMode(FlushMode.COMMIT);
		return super.getSession(sessionFactory);
	}

	@Override
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		session.flush();
		super.closeSession(session, sessionFactory);
	}

}
