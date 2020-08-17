package com.atguigu.dao;

import com.atguigu.domain.User;

public interface UserDao {

    /*
     * 功能描述: 注册页面，根据用户名查询用户信息，是否已存在该用户
     * @Param: [username]  用户名
     * @Return: 如果返回 Null,说明没有该用户，反之亦然
     */
    public User queryUserByUsername(String username);

    /*
     * @Description :登录页面，根据用户名和密码查询用户信息，是否存在该用户
     * @param username 用户名
	 * @param password 密码
     * @returns : 如果返回null,说明用户名或密码错误，反之登录成功
    */
    public User queryUserByUsernameandPassword(String username,String password);

    /*
     * 功能描述: 保存用户信息，将注册的信息保存到数据库中
     * @Param: [user] 用户名
     * @Return: 如果返回1，说明注册保存成功，反之注册失败
     */
    public int saveUser(User user);

}
