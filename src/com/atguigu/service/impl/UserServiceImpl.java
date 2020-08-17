package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.domain.User;
import com.atguigu.service.UserService;

// 创建 UserServiceImpl接口实现类
public class UserServiceImpl implements UserService {

    // 创建 UserDao对象，调用其中的方法
    private UserDao userDao = new UserDaoImpl();

    //注册用户功能，调用 UserDao的 saveUser() 方法
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    //用户登录功能，调用 UserDao的 queryUserByUsernameandPassword() 方法
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameandPassword(user.getUsername(),user.getPassword());
    }

    //用户名验证功能，调用 UserDao的 queryUserByUsername() 方法
    @Override
    public boolean exisetsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null){
            // 如果返回的 User 为空,说明用户名不存在,可以使用
            return false;
        }
        return true;
    }
}
