<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
<SCRIPT type="text/javascript">
	var secs = 5;//倒计时的秒数
	var URL;
	function Load(url) {
		URL = url;
		for(var i = secs; i >= 0; i--) {
			window.setTimeout('doUpdate(' +i+ ')',(secs - i)*1000);
		}
	}
	function doUpdate(num) {
		document.getElementById('ShowDiv').innerHTML = '将在' + num +'秒后自动跳转到登录页面';
		if(num == 0) {
			windows.location = URL;
		}
	}
</SCRIPT>
</head>
<body>
	<center>
		<h1>
			<font color="red" align="center">恭喜您，注册成功！</font>
		</h1>
		<br/>
		<div id="ShowDiv">
			<script type="text/javascript">
				Load("http://localhost:8080/JavaWeb3-1/login.jsp");
			</script>
			<s:a href="/login.jsp">
				<font color="blue">
					<h5>手动登录？</h5>
				</font>
			</s:a>
		</div>
	</center>
</body>
</html>