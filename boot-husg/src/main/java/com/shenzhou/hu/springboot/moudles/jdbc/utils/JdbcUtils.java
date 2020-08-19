package com.shenzhou.hu.springboot.moudles.jdbc.utils;

import java.sql.*;

public class JdbcUtils {
    //获得连接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            String url ="jdbc:mysql://127.0.0.1:3306/demoboot";
            String username = "root";
            String paaaword = "root";
            connection = (Connection) DriverManager.getConnection(url, username, paaaword);
            return connection;
        }catch (Exception e){
        }

        return null;
    }
    //关闭连接
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //回滚
    public static  void rollback(Connection conn){
        if(conn != null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
