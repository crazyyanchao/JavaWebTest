package org.easybooks.bookstore.jdbc;
import java.sql.*;
public class MySQLConnBean {
	private Statement stmt = null;
	private Connection conn = null;
	ResultSet rs = null;
	//构造函数
	public MySQLConnBean(){}
	public void OpenConn()throws Exception
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = "";
			conn = DriverManager.getConnection(url,user,password);
		}
		catch(SQLException e)
		{
			System.err.println("Data.executeQuery: " + e.getMessage());
		}
	}
	//执行查询类的SQL语句，有返回集
	public ResultSet executeQuery(String sql)
	{
		rs = null;
		try
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
		}
		catch(SQLException e)
		{
			System.err.println("Data.executeQuery: " + e.getMessage());
		}
		return rs;
	}
	//关闭对象
	public void closeStmt()
	{
		try
		{
			stmt.close();
		}
		catch(SQLException e)
		{
			System.err.println("Date.executeQuery: " + e.getMessage());
		}
	}
	public void closeConn()
	{
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
