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
<title>在线考试 | 管理员管理</title>
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
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
 	.main i{color: #eee;}
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<!-- 添加弹窗 -->
	<div id="table_input" style="background-color: #eee;"></div>
	<!-- Start: Header -->
	<jsp:include page="admin_header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">

			<!-- Sidebar -->
			<jsp:include page="admin_siderbar.jsp"></jsp:include>
			<!-- End Sidebar -->

			<!-- Main Page -->
			<div class="main ">
				<!-- Page Header -->
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="home"><i class="icon fa fa-home"></i>主页</a></li>
							<li><a href="javascript:void();"><i class="fa fa-users"></i>人员管理</a></li>
							<li class="active"><i class="fa fa-th-list"></i>管理员管理</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>管理员管理</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel" style="min-width: 910px;">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-table red"></i><span class="break" id="CMtitle" style="font-size: 16px; color: #eee">管理员列表</span>
								</h6>
								<div class="panel-actions" id="ReturnToCourse">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered bootstrap-datatable datatable" style="min-width: 880px;">
										<thead>
											<tr style="text-align: center;">
												<th width="25%">编号</th>
												<th>姓名</th>
												<th width="25%">性别</th>
												<th width="25%">操作</th>
											</tr>
										</thead>
										<tbody id="adminlist"></tbody>
									</table>
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
	<script type="text/javascript" src="<%=path%>/js/adminlist.js"></script>
	<!-- end: JavaScript-->
</body>
</html>