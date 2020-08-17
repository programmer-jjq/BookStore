package com.atguigu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        req.setCharacterEncoding("UTF-8");
        // 解决响应的中文乱码
        resp.setContentType("text/html;charset=UTF-8");

        // 获取 login.jsp 和 regist.jsp 页面传递的 action参数
        String action = req.getParameter("action");

        //对 action的值进行判断
        try {
            // 获取action业务鉴别字符串，获取对应的业务，方法反射对象
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            // 调用目标业务的方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);  // 把异常抛给 filter过滤器
        }
    }
}
