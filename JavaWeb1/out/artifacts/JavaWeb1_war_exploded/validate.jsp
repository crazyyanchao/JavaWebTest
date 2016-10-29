<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-9-20
  Time: 上午7:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*"%>
<jsp:useBean id="MySqlBean" scope="page" class="org.easybooks.bookstore.jdbc.MySQLConnBean"/>
<body>
<% String usr=request.getParameter("username");
   String pwd=request.getParameter("password");
   boolean validated=false;//验证成功的标识
   String sql="select * from user";//查询user表中的记录
    //调用MySqlBean中加载JDBC驱动的方法
   MySqlBean.OpenConn();
    //取得结果集
   ResultSet rs=MySqlBean.executeQuery(sql);
   while(rs.next())
   {   if((rs.getString("username").compareTo(usr)==0)&&(rs.getString("password").compareTo(pwd)==0))
       {
          validated=true;//标识为true表示验证成功通过
       }
    }
    rs.close();
    MySqlBean.closeStmt();
    MySqlBean.closeConn();
    if(validated)
{
 %>
   <jsp:forward page="welcome.jsp"/>
 <%
   }
    else
    {
    %>
    <jsp:forward page="error.jsp"/>
    <%
    }
    %>
    </body>

