package library.dao;

import library.model.User;

public interface UserDao extends BaseDao<User>{

	public User queryByUserNo(String userNo);
}
