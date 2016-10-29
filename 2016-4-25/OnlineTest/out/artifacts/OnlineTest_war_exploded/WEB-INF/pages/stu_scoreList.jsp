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
<title>在线考试 | 考试结果</title>
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
<!-- SweetAlert CSS  end -->
<!-- end: CSS file-->
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif] -->
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
	button:hover{cursor: pointer;}
	.main i{color: #eee;}
	.main a{cursor: pointer; color: #888;}
	#testPaperShowing{color:#333;}
</style>
</head>
<body>
	<div id="testPaperShowing" style="display: none"></div>
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
						<small>版权所有 © 丁鹏</small>
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
							<li><a href="home"><i class="fa fa-home"></i>主页</a></li>
							<li><a><i class="fa fa-info-circle"></i>考试信息管理</a></li>
							<li><a><i class="fa fa-file-text"></i>考试结果</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>考试结果</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel" style="min-width: 910px;">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-table red"></i><span class="break" style="font-size: 16px; color: #eee">试卷信息列表</span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered bootstrap-datatable datatable" style="min-width: 880px;">
										<thead>
											<tr style="text-align: center;">
												<th width="">测试编号</th>
												<th width="">科目</th>
												<th width="">开始时间</th>
												<th width="">结束时间</th>
												<th width="">成绩</th>
											</tr>
										</thead>
										<tbody id="scorelist"></tbody>
									</table>
								</div>
								<div class="btn-toolbar" role="toolbar">
									<h5 style="display: inline-block; margin-top: 15px;">当前页面：</h5>
									<h5 id="currPage" style="display: inline-block; margin-top: 15px;"></h5>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<h5 style="display: inline-block; margin-top: 15px;">总页数：</h5>
									<h5 id="totalPage" style="display: inline-block; margin-top: 15px;"></h5>
									<div class="bk-margin-5 btn-group" style="float: right; right: -5px;">
										<button type="button" class="btn btn-default" onclick="stuScoreListFirst()">首页</button>
										<button type="button" class="btn btn-default" onclick="stuScoreListPrev()">上一页</button>
										<button type="button" class="btn btn-default" onclick="stuScoreListNext()">下一页</button>
										<button type="button" class="btn btn-default" onclick="stuScoreListLast()">尾页</button>
									</div>
								</div>
							</div>
						</div>
					</div>
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
	<script src="<%=path%>/js/bootstrap.min.js"></script>

	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/stu_scorelist.js"></script>
	<!-- end: JavaScript-->
</body>
</html>