package org.easybooks.bookstore.jdbc;

import java.sql.*;

/**
 * Created by root on 16-9-22.
 */
public class MySQLConnBean {
    private Statement stmt = null;
    private Connection conn = null;
    private ResultSet rs = null;
    public void OpenConn() throws Exception
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1:3306/test";
            String user = "root";
            String password = "";//默认无密码
            conn = DriverManager.getConnection(url,user,password);
        }
        catch(SQLException e)
        {
            System.err.println("Data.executeQuery:" + e.getMessage());
        }
    }
    //执行查询类的SQL语句，有返回值
    public ResultSet executeQuery(String sql) {
        rs = null;
        try
        {
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
        }
        catch(SQLException e)
        {
            System.err.println("Data.executeQuery: " + e.getMessage());
        }
        return rs;
    }
    //关闭对象
    public void closeStmt(){
        try
        {
            stmt.close();
        }
        catch(SQLException e)
        {
            System.err.println("Data.executeQuery: " + e.getMessage());
        }
    }

    public void closeConn() {
        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            System.err.println("Data.executeQuery: " + e.getMessage());
        }
    }
}

