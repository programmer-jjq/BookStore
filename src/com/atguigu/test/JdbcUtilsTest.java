package com.atguigu.test;

import org.junit.Test;

public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        // 循环获取连接池的连接，每次获取后要释放
//        for (int i = 0; i < 100; i++) {
//            Connection connection = JdbcUtils.getConnection();
//            System.out.println(connection);
//            JdbcUtils.close(connection);
//        }
    }
}
