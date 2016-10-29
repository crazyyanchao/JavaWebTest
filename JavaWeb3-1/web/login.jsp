<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<center>
	<div align="left">
		<s:a href="register.jsp">
			<font color="blue">
				<center><h5>还未注册？点击注册</h5></center>
			</font>
		</s:a>
		
	</div>
	<div>
		<s:form action="login" method="post">
			<s:textfield name="username" label="账号"></s:textfield>
			<s:password name="password" label="密码"></s:password>
			<s:submit value="登录"></s:submit>
		</s:form>
	</div>
</center>
</body>
</html>