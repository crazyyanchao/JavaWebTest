package org.easybooks.bookstore.dao;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
public class BaseDAO {
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public Session getSession(){
		Session session=sessionFactory.openSession();
		return session;
	}
}
