package com.atguigu.test;

import java.lang.reflect.Method;

public class UserServletTest {

    public void login(){
        System.out.println("调用了login()方法");
    }
    public void regist(){
        System.out.println("调用了regist()方法");
    }
    public void updateUser(){
        System.out.println("调用了updateUser()方法");
    }
    public void updateUserPassword(){
        System.out.println("调用了updateUserPassword()方法");
    }

    public static void main(String[] args){
        String action = "login";

        try {
            // 获取action业务鉴别字符串，获取对应的业务，方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            // 调用目标业务的方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
