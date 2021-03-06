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
<title>在线考试 | 修改密码</title>
<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- start: CSS file-->
<!-- Vendor CSS-->
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path%>/css/skycons.css" rel="stylesheet" />
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet" />
<!-- Theme CSS -->
<link href="<%=path%>/css/jquery.mmenu.css" rel="stylesheet" />
<!-- Page CSS -->
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<!-- SweetAlert CSS -->
<link href="<%=path%>/css/sweetalert.css" rel="stylesheet" />
<!-- end: CSS file-->
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->		
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
	label{ font-size: 16px; font-weight: normal;}
	.main_password{width: 300px; height: 150px; margin: 10% 30%;}
</style>
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
						<small>版权说有  © 丁鹏</small>
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
							<li><a><i class="fa fa-wrench"></i>个人信息管理</a></li>
							<li><a><i class="icon fa fa-lock"></i>修改密码</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>修改密码</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="main_password">
					<label class="form-control-static">原始密码</label><input id="initpassword" class="form-control" type="password">
					<label class="form-control-static">新密码</label><input id="newpassword" class="form-control" type="password">
					<label class="form-control-static">确认密码</label><input id="newpasswordconfirm" class="form-control" type="password">
					<br>
					<button type="button" class="btn btn-danger btn-lg btn-block" onclick="changepassword()">确认</button>
				</div>
				<!--     Page body end      -->
			</div>
			<!-- End Main Page -->
		</div>
	</div>
	<!--/container-->

	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/jquery-2.1.1.min.js"></script>
	<script src="<%=path%>/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/skycons.js"></script>

	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<script type="text/javascript">
		function lowerIe(){
			var UA = navigator.userAgent;
			var ieV = "";
			if(/msie/i.test(UA)){
				ieV = UA.match(/msie (\d+\.\d+)/i)[1];
			}
			if(parseInt(ieV) <= 8){
				return true;
			}
			return false;
		}
		function changepassword(){
			if( $("#newpassword").val() != $("#newpasswordconfirm").val()){
				swal("错误","两次输入的密码不一致","error");
				return false;
			}
			$.ajax({
				type : "post",
				url : "../changepassword",
				data:{"initalpassword":$("#initpassword").val(),
					  "newpassword":$("#newpassword").val() },
				success : function(data) {
					var resCode = eval("("+data+")").resCode;
					var resMsg = eval("("+data+")").resMsg;
					if(resCode == "000000"){
						if(lowerIe()){
							alert(resMsg);
						}else{
							swal("成功",resMsg,"success");
						}
					}else{
						if(lowerIe()){
							alert(resMsg);
						}else{
							swal("错误",resMsg,"error");
						}
					}
				}
			});
		}
	</script>
</body>
</html>