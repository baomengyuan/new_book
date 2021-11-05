//package com.swjtu.test;
//
//import com.swjtu.utils.JDBCUtils;
//import org.junit.Test;
//
//import java.sql.Connection;
//
///**
// * @author baomengyuan
// * @create 2021-10-07 9:35
// */
//public class JDBCUtilsTest {
//    @Test
//    public void testJDBCUtils(){
//        for(int i=0;i<100;i++){
//            Connection conn=JDBCUtils.getConnection();
//            System.out.println(conn);
//            JDBCUtils.close(conn);
//        }
//    }
//}
