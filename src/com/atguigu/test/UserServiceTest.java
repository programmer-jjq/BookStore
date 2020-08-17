package com.atguigu.test;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        // 调用 UserServiceImpl的registUser()方法
        userService.registUser(new User(null, "admin2", "admin2", "admin2@qq.com"));
        userService.registUser(new User(null, "admin3", "admin3", "admin3@qq.com"));
    }

    @Test
    public void login() {
        //调用 UserServiceImpl的login()方法
        //返回null,证明数据库无该用户信息,登录失败
        //System.out.println(userService.login(new User(null,"jjq111","jjq111",null)));

        // 返回用户信息,证明数据库存在该用户并且比对成功，登录成功
        System.out.println(userService.login(new User(null,"admin","admin",null)));
    }

    @Test
    public void exisetsUsername() {
        // 调用 UserServiceImpl的 existsUsername()方法
        if( userService.exisetsUsername("admin111")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}