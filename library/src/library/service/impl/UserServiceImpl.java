package library.service.impl;

import java.util.List;

import javax.annotation.Resource;

import library.dao.UserDao;
import library.model.User;
import library.query.LibraryQuery;
import library.service.UserService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void add(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User queryById(int id) {
		return userDao.queryById(id);
	}

	@Override
	public List<User> queryAll() {
		return userDao.queryAll();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public PageInfo<User> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		PageInfo<User> pageInfo = new PageInfo<User>(0, 0, 0);
		switch (query.getKey()) {
		case " cvcv":
			break;
		default:
			pageInfo = userDao.queryAll(page);
			break;
		}
		return pageInfo;
	}

	@Override
	public void checkLogin(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User queryByUserNo(String userNo) {
		return userDao.queryByUserNo(userNo);
	}

}
