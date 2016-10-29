<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<meta charset="UTF-8" />
<title>在线考试 | 主页</title>
<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- start: CSS file-->
<!-- Vendor CSS-->
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet" />
<!-- Theme CSS -->
<link href="<%=path%>/css/jquery.mmenu.css" rel="stylesheet" />
<!-- Page CSS -->
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<link href="<%=path%>/css/add-ons.min.css" rel="stylesheet" />
<!-- SweetAlert CSS -->
<link href="<%=path%>/css/sweetalert.css" rel="stylesheet" />
<!-- end: CSS file-->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<!-- Start: Header -->
	<jsp:include page="stu_header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">

			<!-- Sidebar -->
			<div class="sidebar">
				<div class="sidebar-collapse">
					<!-- Sidebar Header Logo-->
					<div class="sidebar-header">
						<span style="font-size: 20px;">大学在线考试系统</span>
					</div>
					<!-- Sidebar Menu-->
					<div class="sidebar-menu">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-sidebar">
								<li><a href="home"><i class="fa fa-home" aria-hidden="true"></i><span>主页</span></a></li>
								<li class="nav-parent" id="testPaperManage"><a><i class="fa fa-wrench" aria-hidden="true"></i><span>个人信息管理</span></a>
									<ul class="nav nav-children">
										<li><a href="personinfo"><span class="text">个人信息</span></a></li>
										<li><a href="password"><span class="text">修改密码</span></a></li>
									</ul>
								</li>
								<li class="nav-parent"><a><i class="fa fa-info-circle" aria-hidden="true"></i><span>考试信息管理</span></a>
									<ul class="nav nav-children">
										<li><a href="testlist"><span class="text">考试列表</span></a></li>
										<li><a href="scorelist"><span class="text">查看结果</span></a></li>
									</ul>
								</li>
							</ul>
						</nav>
					</div>
					<!-- End Sidebar Menu-->
				</div>
				<!-- Sidebar Footer-->
				<div class="sidebar-footer">
					<ul class="sidebar-terms">
						<li><a href="javascript:void();">条款</a></li>
						<li><a href="javascript:void();">隐私</a></li>
						<li><a href="javascript:void();">帮助</a></li>
						<li><a href="javascript:void();">关于</a></li>
					</ul>
					<div class="copyright text-center">
						<small>版权所有  © 丁鹏</small>
					</div>
				</div>
				<!-- End Sidebar Footer-->
			</div>
			<!-- End Sidebar -->

			<!-- Main Page -->
			<div class="main ">
				<!-- Page Header -->
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="home"><i class="icon fa fa-home"></i>主页</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>主页</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<h3 style="text-align: center; margin-left: 30%;">欢迎使用大学在线考试系统</h3>
				<div style="text-align: center;">
					<img alt="教育情怀" src="<%=path%>/img/edulove.jpg" height="384px;" width="800px;">
				</div>
				<!--     Page body end      -->
			</div>
			<!-- End Main Page -->
		</div>
	</div>
	<!--/container-->

	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<!-- end: JavaScript-->
	<script type="text/javascript">
	function getCookie(name){var arr = document.cookie.match(new RegExp("(^| )" + name +"=([^;]*)(;|$)") );if(arr != null){return unescape(arr[2]);}else{return null;}}$(document).ready(function(){var msg = getCookie("DenyMsg");document.cookie = "DenyMsg="+";expires=0;path=/";if(msg == "denied"){swal("对不起","您没有访问权限，请求被拒接!","error");}});
	</script>
</body>
</html>