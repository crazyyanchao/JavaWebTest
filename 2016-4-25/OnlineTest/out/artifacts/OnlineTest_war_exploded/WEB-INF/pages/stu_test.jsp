<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String TheUserName = "";
	String UserRole = "";
	if( session.getAttribute("userName") != null){
	   TheUserName = session.getAttribute("userName").toString();
	}
	if( session.getAttribute("role") != null ){
		UserRole = session.getAttribute("role").toString();
	}
%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<meta charset="UTF-8" /> 
<title>在线考试 | 考试</title>
<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!-- Import google fonts -->
<style type="text/css">
	*{margin: 0; padding: 0;}
</style>
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<!-- SweetAlert CSS -->
<link href="<%=path%>/css/sweetalert.css" rel="stylesheet" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif] -->
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
	header{width: 100%; height: 62px; background-color: #222;}
	.header_time{margin-left: 35%;width: 330px; display: inline-block; color: #eee; margin-top: 10px;}
	.header_resttime{margin-left: 10%; width: 200px; display: inline-block; color: #ccc;}
	.test_body{margin: 25px 130px; color: #333; font-size: 16px;}
	#handin{ float: right; right: 150px; position: relative;}
	footer{width: 100%; height: 60px; background-color: #ddd; color: #222;}
</style>
</head>
<body>
	<header style="margin: 0; position: fixed; top: 0;">
		<div class="header_time">
			<!-- 动态显示时间 -->
			<h4 id="dynamicTime"></h4>
		</div>
		<div class="header_resttime">
			<h5>距离考试结束还有<strong><span id="rest_time" style="font-weight: bolder; font-size: 20px;"></span></strong>分钟</h5>
		</div>
		<span id="testpaperstyle" style="display: none"></span>
	</header>
	<section style="margin-top: 80px;">
		<div class="test_body" id="test_body">
			
		</div>
		<div style="width: 100%; height: 44px;">
			<button id="handin" class="bk-margin-5 btn btn-default" onclick="finishTest()">交卷</button>
		</div>
	</section>
	<footer>
		<h5 style="display: inline-block;margin-left: 43%;">诚信考试</h5>
	</footer>

	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/jquery-2.1.1.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- 禁止使用右键复制 -->
	<script type="text/javascript" src="<%=path%>/js/bancoopy.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/test.js"></script>
</body>
</html>