package com.atguigu.web;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    // web层只能调用 Service层方法，所有需要创建 UserService对象
    private UserService userService = new UserServiceImpl();

    // 登录时，密码有私密性，使用 doPost() 方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2、调用 UserService对象的 login()方法
        User loginUser = userService.login(new User(null, username, password, null));
        // 对返回的 loginUser判断
        if(loginUser == null){
            // 把错误信息和 回显的表单项username信息，保存到 Request域中
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",username);
            // 如果等于 null,证明数据库不存在该用户，登录失败，调回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            // 否则登录成功，跳转到 login_success.jsp 页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }
}
