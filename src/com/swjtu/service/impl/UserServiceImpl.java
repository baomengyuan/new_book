package com.swjtu.service.impl;

import com.swjtu.dao.UserDao;
import com.swjtu.dao.impl.UserDaoImpl;
import com.swjtu.pojo.User;
import com.swjtu.service.UserService;

/**
 * @author baomengyuan
 * @create 2021-10-07 11:03
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
