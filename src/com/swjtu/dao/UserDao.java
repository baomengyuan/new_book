package com.swjtu.dao;

import com.swjtu.pojo.User;

/**
 * @author baomengyuan
 * @create 2021-10-07 10:23
 */

public interface UserDao {
    //根据用户名查询用户信息
    //如果返回NULL，说明没有这个用户
    public User queryUserByUsername(String username);
    //保存用户信息
    public int saveUser(User user);
    //登陆操作,根据用户名和密码查询用户，如果返回NULL，那么说明用户名或者密码错误，那么反之亦然
    public User queryUserByUsernameAndPassword(String username,String password);


}
