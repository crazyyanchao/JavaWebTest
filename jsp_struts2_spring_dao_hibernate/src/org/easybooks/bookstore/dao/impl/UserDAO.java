package org.easybooks.bookstore.dao.impl;
import java.util.List;
import org.easybooks.bookstore.dao.*;
import org.easybooks.bookstore.vo.User;
import org.hibernate.*;
public class UserDAO extends BaseDAO implements IUserDAO{
	public User validateUser(String username,String password){		
		String sql="from User u where u.username=? and u.password=?";
		Session session=getSession();
		Query query=session.createQuery(sql);
		query.setParameter(0,username);
		query.setParameter(1,password);
		List users=query.list();
		if(users.size()!=0)
		{
			User user=(User)users.get(0);
			return user;
		}
		session.close();
		return null;
	}
}
