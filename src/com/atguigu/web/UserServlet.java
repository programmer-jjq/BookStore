package com.atguigu.web;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    // web层只能调用 Service层方法，所有需要创建 UserService对象
    private UserService userService = new UserServiceImpl();

    // 处理登录的功能
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            // 在 Session域中保存用户登录的信息
            req.getSession().setAttribute("user",loginUser);
            // 否则登录成功，跳转到 login_success.jsp 页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }

    }

    // 处理注销的功能
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁 Session 中用户登录的信息（或销毁 Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    // 处理注册的功能
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //使用 WebUtils的 copyParamToBean() 方法为 user对象注入参数，并返回user对象
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());;

        // 2、检查验证码是否正确 === 使用谷歌 Kaptcha 验证码进行验证
        if(token!=null && code.equalsIgnoreCase(code)){
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
                userService.registUser(user);
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

    // Ajax请求验证用户名是否可用
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、获取请求的参数username
        String username = req.getParameter("username");
        // 2、调用 UserService对象的 existsUsername方法
        boolean exisetsUsername = userService.exisetsUsername(username);
        // 3。把返回的结果封装成为 map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",exisetsUsername);

        Gson gson = new Gson();
        // 将 resultMap集合转换为 JSON字符串
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
