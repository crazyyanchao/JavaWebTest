<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
form
  	<form action="">
  	  <div>账号：<input /></div>
  	  <div>密码：<input /></div>
  	  <input type="submit" value="提交">
  	</form>
table  	
  	<table>
  	  <caption></caption>
  	  <tr><th></th></tr>
  	  <tr><td></td></tr>
  	</table>
select
	<s:select list="">
	</s:select>
input	
	<input name="" value="" type="text"/>
a
	<a href=""></a>
	
ul
	<ul>
	  <li></li>
	</ul>
  </body>
  
</html>
