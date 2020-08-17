package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//其中的查询方法涉及到泛型方法,直接提取成为泛型类
public abstract class BaseDao <T>{

    // 使用 DbUtils 操作数据库,创建 QueryRunner类对象
    private QueryRunner queryRunner = new QueryRunner();

    /*
    * update() 方法用来执行: Insert\Update\Delete语句
    * @param sql: 执行的sql语句
    * @param args: sql对应的参数值
    */
    public int update(String sql,Object...args){
        // 获取德鲁伊连接
        Connection connection = JdbcUtils.getConnection();
        try {
            // 调用 DBUtils的update()
            int update = queryRunner.update(connection, sql, args);
            // 返回受影响的行数
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    * querySingle() 方法: 查询并返回单个JavaBean对象
    * @param clazz 返回的对象类型
    * @param sql 执行的sql语句
    * @param args sql对应的参数值
    * @param <T> 直接提取成泛型类，不需要泛型方法
    */
    public T querySingle(Class<T> clazz,String sql,Object...args){
        // 获取德鲁伊连接池连接
        Connection connection = JdbcUtils.getConnection();
        // 返回对象
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    * queryMulti() 方法: 查询并返回 List对象数组
     * @param clazz 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 直接提取成泛型类，不需要泛型方法
    */
    public List<T> queryMulti(Class<T> clazz,String sql,Object...args){
        // 获取德鲁伊连接池连接
        Connection connection = JdbcUtils.getConnection();
        // 返回对象数组
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * querySingleValue() 方法用来执行返回一行一列的sql语句
     * @param sql: 执行的sql语句
     * @param args: sql对应的参数值
     */
    public Object querySingleValue(String sql,Object...args){
        // 获取德鲁伊连接池连接
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
