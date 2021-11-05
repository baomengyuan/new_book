package com.swjtu.dao.impl;

import com.swjtu.utils.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**被其他类继承的，用于复用代码的
 * @author baomengyuan
 * @create 2021-10-07 9:59
 */

public abstract class BaseDao {
    //使用JDBCUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    //用来执行Update delete insert
    //返回-1表示执行失败，否则返回受影响的行数
    public int update(String sql,Object ...args){
        Connection connection= JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //查询返回一个javaBean的SQL语句
    //type 返回的数据类型
    //sql 执行的sql语句
    //args sql执行的参数
    //<T> 返回的类型的泛型
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //查询返回多个JavaBean
    public <T>List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //返回一行的数据
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn=new JDBCUtils().getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
