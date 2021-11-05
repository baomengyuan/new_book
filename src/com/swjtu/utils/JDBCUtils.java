package com.swjtu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.jms.ConnectionConsumer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author baomengyuan
 * @create 2021-10-06 22:24
 */

public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    //静态代码块
    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream stream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(stream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接池中的连接
    //返回NULL说明失败
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn);//保存到ThreadLocal对象中，供后面jdbc使用
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            //说明之前使用过连接，操作过数据库
            try {
                connection.commit();//提交事务

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错（Tomcat底层使用了线程池技术）
        conns.remove();
    }

    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            //说明之前使用过连接，操作过数据库
            try {
                connection.rollback();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错（Tomcat底层使用了线程池技术）
        conns.remove();
    }
}
