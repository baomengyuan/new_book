package com.swjtu.service;

import com.swjtu.pojo.User;

/**业务层
 * @author baomengyuan
 * @create 2021-10-07 11:00
 */
public interface UserService {
    //注册用户
    public void registerUser(User user);
    //登录
    public User login(User user);
    //检查用户名是否可用
    //返回true表示用户名已经存在，返回false表示用户名可用
    public boolean existUsername(String usernmae);
}
