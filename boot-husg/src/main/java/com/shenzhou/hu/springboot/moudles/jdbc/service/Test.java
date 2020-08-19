package com.shenzhou.hu.springboot.moudles.jdbc.service;


import com.shenzhou.hu.springboot.moudles.jdbc.utils.JdbcUtils;

import java.sql.*;

/**
 * JDBC练习  单表查询，单表插入，批量修改和插入123
 * 1 加载驱动
 * 2 获取连接
 * 3 获取statement，preparedStatement
 * 4 设置参数，从1开始  如果查询条件有两个则第二个parameterIndex写2
 * 5 执行查询,获取结果集
 * 6 处理结果集
 */
public class Test {

    public static void main(String[] args) {
        //单表查询
       // select();
        //单表插入
        //int num = insertSingle();
        //System.out.println("单表插入 --------");
       //System.out.println("单表插入: "+num);

        //批量插入
        batchInsert();

    }


    //单表查询
    public static void  select(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //获得连接
        connection = JdbcUtils.getConnection();
        //获取statement，preparedStatement
        String sql = "select * from user where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，从1开始  如果查询条件有两个则第二个parameterIndex写2
            preparedStatement.setString(1,"1");
            //执行查询,获取结果集
            resultSet = preparedStatement.executeQuery();
            //处理结果集
            while (resultSet.next()){
                //参数可以写下标也可以写列名(表中的字段)
                System.out.println("------------------");
                System.out.println("单表查询");
                System.out.println( "name:  " + resultSet.getString("uname"));
                System.out.println( "id:  " + resultSet.getInt("id"));
                System.out.println("------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JdbcUtils.rollback(connection);
        }finally {
            JdbcUtils.closeAll(connection,preparedStatement,resultSet);
        }
    }


    //单表插入
    public static int  insertSingle(){
        //
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        String sql = "insert user(id,uname) values(?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,3);
            ps.setString(2,"hsg");
            int num = ps.executeUpdate();
            return  num;
        } catch (SQLException e) {
            JdbcUtils.rollback(conn);
        }finally {
            JdbcUtils.closeAll(conn,ps,rs);
        }
        return 0;
    }

    //批量插入
    public static  int batchInsert(){
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JdbcUtils.getConnection();
        String sql = "insert user(id,uname,password) values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 10; i < 16; i++){
                ps.setInt(1,i);
                ps.setString(2,"hsg"+i);
                ps.setString(3,"aaa"+i);
                ps.addBatch();
            }
            int[] num = ps.executeBatch();
            for (int i = 0; i <num.length ; i++) {
                System.out.println("Batch   " + num[i]);
            }

            for(int i = 20; i < 24; i++){
                ps.setInt(1,i);
                ps.setString(2,"hsg"+i);
                ps.setString(3,"aaa"+i);
                ps.addBatch();
            }
            int[] num1 = ps.executeBatch();
            for (int i = 0; i <num1.length ; i++) {
                System.out.println("Batch1   " + num1[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeAll(conn,ps,null);
        }

        return 0;
    }




}



