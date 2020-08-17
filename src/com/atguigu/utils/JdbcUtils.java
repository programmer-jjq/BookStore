package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource dataSource;
    // 创建 ThreadLocal对象,存放 Connection连接
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    // 静态代码块
    static {
        Properties properties = new Properties();
        try {
            // 读取 druid.properties配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);

            // 创建数据库连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     *      如果返回null,即获取连接失败
     *      如果返回有值，即获取连接成功
     */
    public static Connection getConnection() {
        //未使用事务之前的获取Connection连接方法
        /*
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
        */

        // 从 ThreadLocal对象中获取Connection连接
        Connection conn = conns.get();
        if(conn == null){
            try {
                // 如果 conn为空，证明第一次获取连接，从数据库连接池中获取一个连接对象
                conn = dataSource.getConnection();
                // 将 conn连接保存到 ThreadLocal对象中
                conns.set(conn);
                // 设置手动提交事务
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    // 提交事务并关闭释放连接
    public static void commitAndClose(){
        Connection connection = conns.get();
        // 如果ThreadLocal对象中的 Connection不等于 null,说明使用过连接操作数据库
        if(connection !=null){
            try {
                // 提交事务
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    // 关闭释放连接
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行 remove操作，否则会出错（Tomcat服务器底层使用了线程池技术）
        conns.remove();
    };

    // 回滚事务并关闭释放连接
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        // 如果ThreadLocal对象中的 Connection不等于 null,说明使用过连接操作数据库
        if(connection !=null){
            try {
                // 回滚事务
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    // 关闭释放连接
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行 remove操作，否则会出错（Tomcat服务器底层使用了线程池技术）
        conns.remove();
    };

    /*
     * 关闭连接，释放连接会数据库连接池
     *      关闭 Result虚拟表
     *      关闭 Statement命令对象流
     *      关闭 Connection连接
    public static void close(Connection connection){
        try {
            if (connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

};




