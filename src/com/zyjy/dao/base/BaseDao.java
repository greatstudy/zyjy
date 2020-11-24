package com.zyjy.dao.base;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName BaseDao
 * @Description 只做连接和关闭资源，同时获得表的前缀
 * @Author 清Great
 * @Date 2020/10/13 10:50
 */
public class BaseDao {
    private static BaseDao baseDao = new BaseDao();

    private static ComboPooledDataSource ds = new ComboPooledDataSource();


    public static BaseDao getBaseDao() {
        return baseDao;
    }


    public String getTableFirstName() {
        //表的前缀
        String tableFirstName = "C";
        return tableFirstName;
    }

    /**
     * 获取连接
     * 获取连接并设置成非自动commit
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);

        return connection;
    }


    public static void commit(Connection connection) throws SQLException {
        connection.commit();
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接并设置成非自动commit
     *
     * @return
     */
    public Connection getConnAutoCommit() throws SQLException {
        Connection connection = ds.getConnection();
        connection.setAutoCommit(true);
        return connection;
    }


    /**
     * 关闭资源
     *
     * @param rs
     * @param ps
     */
    public void closeResource(ResultSet rs, PreparedStatement ps) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
    }


    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void dsState() {

        System.out.println("最大连接数:" + ds.getMaxPoolSize());// 最大连接数
        System.out.println("最小连接数:" + ds.getMinPoolSize());// 最小连接数
        try {
            System.out.println("正在使用连接数:" + ds.getNumBusyConnections());// 正在使用连接数
            System.out.println("空闲连接数:" + ds.getNumIdleConnections());// 空闲连接数
            System.out.println("总连接数:" + ds.getNumConnections());// 总连接数
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
