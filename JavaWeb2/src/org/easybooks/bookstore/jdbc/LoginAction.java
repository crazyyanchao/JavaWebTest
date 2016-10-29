package org.easybooks.bookstore.jdbc;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.ResultSet;

/**
 * Created by root on 16-9-21.
 */
public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    //处理用户请求的execute方法
    public String execute() throws Exception{
        String usr = getUsername();   //获取提交的姓名
        String pwd = getPassword();   //获取提交的密码
        boolean validated = false;       //验证成功标识
        MySQLConnBean MySqlBean = new MySQLConnBean(); //创建连接对象
        //查询user表中的记录
        String sql = "select * from user";
        MySqlBean.OpenConn();//调用MySqlBean中加载JDBC驱动的方法
        ResultSet rs = MySqlBean.executeQuery(sql);//取得结果集

        while(rs.next())
        {
        if((rs.getString("username").compareTo(usr) == 0)
                &&(rs.getString("password").compareTo(pwd) == 0))
        {
            validated = true;             //标识为true表示验证成功通过
        }
        }

    rs.close();
    MySqlBean.closeStmt();
    MySqlBean.closeConn();
    if(validated)
    {
        //验证成功返回字符串“success”
        return "success";
    }
    else
    {
        return "error";
    }
}
public String getUsername(){
    return username;
}
public void setUsername(String username){
    this.username = username;
}
public String getPassword(){
    return  password;
}
public void setPassword(String password){
    this.password = password;
}
}

