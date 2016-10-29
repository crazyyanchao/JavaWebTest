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
<title>在线考试 | 个人中心</title>
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
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
 	.col-md-6 label{margin-top: 8px; font-size: 14px;}
 	.control-label{ width: 60px;}
 	.col-md-6{width: 450px; margin-top: 30px;}
 	.panel{width: 450px; background-color: #eee; margin: 0;}
/* 	.panel-body{width: 400px; height:465px; background-color: #aaa;} */
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
							<li><a><i class="icon fa fa-wrench"></i>个人信息管理</a></li>
							<li><a><i class="icon fa fa-user"></i>个人中心</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>个人中心</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="row">
					<div class="col-md-6" style="margin-left: 30%;margin-top: 10%">
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">学号</label>
								<div class="col-md-9">
									<input type="text" id="User_Id" class="form-control" placeholder="UserId" disabled="disabled"><br>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">姓名</label>
								<div class="col-md-9">
									<input type="text" id="UserName" class="form-control" placeholder="UserName" disabled="disabled"><br>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">手机</label>
								<div class="col-md-9">
									<input type="text" id="Phone" class="form-control" placeholder="Phone"><br>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">邮箱</label>
								<div class="col-md-9">
									<input type="text" id="Email" class="form-control" placeholder="Email"><br>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">QQ</label>
								<div class="col-md-9">
									<input type="text" id="QQ" class="form-control" placeholder="QQ"><br>
								</div>
							</div>
					</div>
				<!--     Page body end      -->	
				</div>
			</div>
			<!-- End Main Page -->
		</div>
	</div>
	<!--/container-->

	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>

	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<!-- end: JavaScript-->
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
		function studentinfoload(){
			var userId = $("#UserId").html();
			$.ajax({
				type : "post",
				url : "../getstudentinfo",
				success : function(data) {
					var resCode = eval("("+data+")").resCode;
					var resMsg = eval("("+data+")").resMsg;
					if(resCode == "000000"){
						var userid = eval("("+data+")").data.user.userId;
						var name = eval("("+data+")").data.user.name;
						var phone = eval("("+data+")").data.user.phone;
						var email = eval("("+data+")").data.user.email;
						var qq = eval("("+data+")").data.user.qq;
						var academy = eval("("+data+")").data.user.academy;
						var profession = eval("("+data+")").data.user.profession;
						var classno = eval("("+data+")").data.user.belongNo;
						$("#User_Id").val(userid);
						$("#UserName").val(name);
						$("#Phone").val(phone);
						$("#Email").val(email);
						$("#QQ").val(qq);
						$("#Academy").val(academy);
						$("#Profession").val(profession);
						$("#Class").val(classno);
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
		$(document).ready( studentinfoload );
		$(document).on("change",".form-control",function(e){
			if(lowerIe()){
				if(confirm("确定修改?"))
				 {
					$.ajax({
						type : "post",
						url : "../modifypersoninfo",
						data:{"phone":$("#Phone").val(),
							  "email":$("#Email").val(),
							  "QQ":$("#QQ").val()},
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
									swal("错误", resMsg, "error");
								}
							}
						}
					});
				 }else{
					 studentinfoload();
					 alert("你取消了修改");
				 }
			}else{
				swal({
					title : "确定修改?",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "是的, 修改!",
					cancelButtonText : "不,暂时不修改!",
					closeOnConfirm : false,
					closeOnCancel : false
				}, function(isConfirm) {
					if (isConfirm) {
						$.ajax({
							type : "post",
							url : "../modifypersoninfo",
							data:{"phone":$("#Phone").val(),
								  "email":$("#Email").val(),
								  "QQ":$("#QQ").val()},
							success : function(data) {
								var resCode = eval("("+data+")").resCode;
								var resMsg = eval("("+data+")").resMsg;
								if(resCode == "000000"){
									swal("成功", resMsg, "success");
								}else{
									swal("错误", resMsg, "error");
								}
							}
						});
					} else {
						studentinfoload();
						swal("取消操作", "你取消了修改", "error");
					}
				});
			}
			e.stopPropagation();
		});
	</script>
</body>
</html>