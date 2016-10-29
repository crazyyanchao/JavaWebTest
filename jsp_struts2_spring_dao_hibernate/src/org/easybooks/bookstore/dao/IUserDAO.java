package org.easybooks.bookstore.dao;
import org.easybooks.bookstore.vo.User;
public interface IUserDAO {
	public User validateUser(String username,String password);
}
