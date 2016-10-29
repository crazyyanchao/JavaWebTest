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
<title>在线考试 | 试卷列表</title>
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
<%-- <link href="<%=path%>/css/add-ons.min.css" rel="stylesheet" /> --%>
<!-- SweetAlert CSS -->
<link href="<%=path%>/css/sweetalert.css" rel="stylesheet" />
<!-- end: CSS file-->
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
	.main i{color: #EEE;}
</style>
</head>
<body>
	<!-- 缩写显示弹窗 -->
	<div id="table_small"></div>
	<!-- Start: Header -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<!-- Sidebar -->
			<jsp:include page="siderBar.jsp"></jsp:include>
			<!-- End Sidebar -->

			<!-- Main Page -->
			<div class="main ">
				<!-- Page Header -->
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="home"><i class="fa fa-home"></i>主页</a></li>
							<li><a><i class="fa fa-info-circle"></i>试卷管理</a></li>
							<li><a><i class="fa fa-file-text"></i>试卷列表</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>试卷列表</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel" style="min-width: 910px;">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-table red"></i><span class="break" style="font-size: 16px; color: #eee">试卷列表</span>
								</h6>
								<div class="panel-actions">
									<a href="javascript:void();" onclick="paperlistReload()" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered bootstrap-datatable datatable" style="min-width: 880px;">
										<thead>
											<tr style="text-align: center;">
												<th width="90px;">试卷编号</th>
												<th width="150px;">科目</th>
												<th width="100px">考试日期</th>
												<th width="100px;">开始时间</th>
												<th width="100px;">结束时间</th>
<!-- 												<th>参与班级</th> -->
												<th width="140px;">操作</th>
											</tr>
										</thead>
										<tbody id="testpaperlist"></tbody>
									</table>
								</div>
								<div class="btn-toolbar" role="toolbar">
									<h5 style="display: inline-block; margin-top: 15px;">当前页面：</h5>
									<h5 id="currPage" style="display: inline-block; margin-top: 15px;"></h5>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<h5 style="display: inline-block; margin-top: 15px;">总页数：</h5>
									<h5 id="totalPage" style="display: inline-block; margin-top: 15px;"></h5>
									<div class="bk-margin-5 btn-group" style="float: right; right: -5px;">
										<button type="button" class="btn btn-default" onclick="paperlistFirst()">首页</button>
										<button type="button" class="btn btn-default" onclick="paperlistPrev()">上一页</button>
										<button type="button" class="btn btn-default" onclick="paperlistNext()">下一页</button>
										<button type="button" class="btn btn-default" onclick="paperlistLast()">尾页</button>
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
	<script src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/jquery-2.1.1.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/testpaperlist.js"></script>
	<!-- end: JavaScript-->
</body>
</html>