<%@ page language="java" import="java.util.*,member.User,member.MemberDao" pageEncoding="GBK"%>


<%!
    public static String codestring(String s){
        String str=s;
        try{
            byte[] b=str.getBytes("iso-8859-1");
            str=new String(b,"utf-8");
            return str;
        }catch(Exception e)
        {
            return str;
        }
    }
%>
<%
    //取得用户名
    String username=codestring(request.getParameter("username"));
    String password=codestring(request.getParameter("password"));
    MemberDao md=new MemberDao();
    User m=new User();
    //封装
    m.setUsername(username);
    m.setPassword(password);
    //调用方法，保存对象
    md.save(m);
    //从数据库中加载类对象
    out.print("<center>注册成功<br></center>");
    List l=null;
    l=md.findByProperty("username",username);
    Iterator i=l.iterator();
    out.print("<center>从数据库中加载类对象<br></center>");
    if(i.hasNext()){
        m=(User)i.next();
        out.print("<center>用户名："+m.getUsername()+"<br></center>");
        out.print("<center>密码："+m.getPassword()+"<br></center>");
    }else{
        out.print("<center>数据库中不存在该对象</center>");
    }
%>
