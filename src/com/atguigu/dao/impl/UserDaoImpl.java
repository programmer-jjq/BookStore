package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    // 让 UserDaoImpl实现类继承BaseDao类，实现UserDao接口,重写接口内的方法
    @Override
    public User queryUserByUsername(String username) {
        // sql语句
        String sql = "select id,username,password,email from t_user where username = ? ";
        // 调用 BaseDao的 querySingle() 方法
        return querySingle(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameandPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return querySingle(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
