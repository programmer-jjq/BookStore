package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.domain.User;
import org.junit.Test;

public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        //注册页面，查看数据库是否存在该用户
        if(userDao.queryUserByUsername("admin111") == null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameandPassword() {
        //登录页面，查看数据库是否存在该用户
        User user = userDao.queryUserByUsernameandPassword("admin", "admin1234");
        if(user == null){
            // 如果结果为 null，证明不存在用户，登录失败
            System.out.println("用户名或密码错误，登录失败！");
        }else {
            System.out.println("登录成功！");
        }
    }

    @Test
    public void saveUser() {
        //注册页面，将输入的信息保存为一个用户
        User user = new User(null,"jjq","123456","jjq168@qq.com");
        int update = userDao.saveUser(user);
        System.out.println(update);
    }
}