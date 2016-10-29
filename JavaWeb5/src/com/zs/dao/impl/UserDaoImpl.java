package com.zs.dao.impl;

import java.util.List;

import com.zs.dao.BaseDaoImpl;
import com.zs.dao.UserDao;
import com.zs.model.User;

//判断用户密码是否存在与数据库中
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public void saveUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> getAll(Class clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User isValidUser(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User isValidAdmin(String username, String password) {
        User user = null;
        List<User> list =super.search("FROM User WHERE grade='admin' AND username ='"+username+"' AND password = '"+password+"'");

        //"select * from users where username=? and password=?";


        if(list!=null && list.size()>0){
            user = list.get(0);
        }
        return user;
    }

}