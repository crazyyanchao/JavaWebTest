<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">
</head>

<body>
<center>
<form action="submit.jsp" id="form1" name="form1" method="post">
  <br>�û�����<input name="username" type="text" id="username"/>
  <br>��    ��:<input name="password" type="password" id="password"/>
  <br><input type="submit" name="Submit" value="�ύ" >
</form>
</center>
</body>
</html>