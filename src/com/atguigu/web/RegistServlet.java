package com.atguigu.web;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    // web层只能调用 Service层方法，所有需要创建 UserService对象
    private UserService userService = new UserServiceImpl();

    // 密码有隐秘性,使用 doPost()
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2、检查验证码是否正确 === 先写死 要求验证码为 abcde
        if("abcde".equalsIgnoreCase(code)){
            //验证码正确时
            // 3、检查 用户名是否可用
            if(userService.exisetsUsername(username)){
                // 如果返回 true,证明该用户已存在，用户名不可用，跳转到注册页面
                System.out.println("用户名["+username+"]已存在!");
                // 把回显信息保存到 Request域中
                req.setAttribute("msg","用户名已存在!!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                // 如果返回 false，证明用户名可用,调用 registUser()方法：将用户信息保存到数据库
                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //验证码不正确时，跳回到注册页面
            // 把回显信息保存到 Request域中
            req.setAttribute("msg","验证码错误!!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            System.out.println("验证码["+code+"]错误!");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
