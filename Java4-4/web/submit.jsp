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
    //ȡ���û���
    String username=codestring(request.getParameter("username"));
    String password=codestring(request.getParameter("password"));
    MemberDao md=new MemberDao();
    User m=new User();
    //��װ
    m.setUsername(username);
    m.setPassword(password);
    //���÷������������
    md.save(m);
    //�����ݿ��м��������
    out.print("<center>ע��ɹ�<br></center>");
    List l=null;
    l=md.findByProperty("username",username);
    Iterator i=l.iterator();
    out.print("<center>�����ݿ��м��������<br></center>");
    if(i.hasNext()){
        m=(User)i.next();
        out.print("<center>�û�����"+m.getUsername()+"<br></center>");
        out.print("<center>���룺"+m.getPassword()+"<br></center>");
    }else{
        out.print("<center>���ݿ��в����ڸö���</center>");
    }
%>
