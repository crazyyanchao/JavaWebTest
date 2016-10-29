<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试 | 用户登录</title>
<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet" />
<link href="<%=path%>/css/signin.css" rel="stylesheet" />
<link type="text/css" href="<%=path%>/css/sweetalert.css" rel="stylesheet" />
<style type="text/css">
	#login_panel a{ cursor: pointer;}
	#login_panel a:hover{text-decoration: none; color: red;}
	.fa{font-size: 14px;}
.file {
    position: relative;
    display: inline-block;
    background: #428BCA;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 6px 14px;
    overflow: hidden;
    color: #FFF;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  	<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif] -->
</head>
<body>
	<div id="login-wrapper">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div id="logo-login" style="padding: 15px 0 15px 0;">
					<p style="font-size: 25px; margin: 0; text-align: center;">
						大学生在线考试系统
					</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="account-box" id="login_panel">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input type="text" id="loguserId" class="form-control" name="loguserId" placeholder="用户编号 /  邮箱" aria-describedby="basic-addon1">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" id="logpassword" class="form-control" name="logpassword" placeholder="密码" aria-describedby="basic-addon1">
						</div>
					</div>
					<div class="checkbox pull-left" id="logpage-checkbox">
						<label class="form_lable"><input type="checkbox" id="logcheckbox" value="rememberMe"> 记住我 </label>
					</div>
					<button class="btn btn btn-primary pull-right" onclick="login()">登录</button>
					<div class="row-block">
						<div class="row"></div>
					</div>
					<div>
					  <p class="form_lable" style="text-align: center;">还没有账号？立即<a onclick="changeToRegester()">注册</a>！</p>
					</div>
				</div>
				<p>&nbsp;</p>
	       		<div style="text-align:center;margin:0 auto;">
	            <h6 style="color:#fff;">版权所有  © 丁鹏 </h6>
	        	</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<!-- AjaxFileUpload js -->
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/js/login.js"></script>
</body>
</html>