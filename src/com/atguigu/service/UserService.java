package com.atguigu.service;

import com.atguigu.domain.User;

public interface UserService {


    //注册用户
    public void registUser(User user);

    /*
     * @Description : 用户登录
     * @returns : 如果返回 null,说明登录失败，返回有值，说明登录成功
    */
    public User login(User user);

    /*
     * @Description : 检查用户名是否可用
     * @returns : 返回true，表示用户名已存在，返回false,表示用户名可用
    */
    public boolean exisetsUsername(String username);

}
