package library.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.dao.UserDao;
import library.model.User;
import library.util.PageInfo;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User queryByUserNo(String userNo) {
		String condition = "from User user where user.userNo=:userNo";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userNo", userNo);
		PageInfo<User> pageInfo = queryAll(0, condition, parameter);
		List<User> users = pageInfo.getDatas();
		if (users.size() != 1) {
			
		}
		return users.get(0);
	}

}