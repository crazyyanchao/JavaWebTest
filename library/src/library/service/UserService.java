package library.service;

import library.model.User;

public interface UserService extends BaseService<User> {

	public void checkLogin(User user);

	public User queryByUserNo(String string);
}
