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
<title>在线考试 | 查询学生成绩</title>
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
<link href="<%=path%>/css/add-ons.min.css" rel="stylesheet" />

<!-- end: CSS file-->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
	body{min-width: 1050px;overflow: scroll;}
</style>
</head>
<body>
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
							<li><a href="home"><i class="icon fa fa-home"></i>主页</a></li>
							<li><a href="javascript:void();"><i class="fa fa-group"></i>学生成绩管理</a></li>
							<li class="active"><i class="fa fa-search"></i>查询学生成绩</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>查询学生成绩</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<!-- <div>
					</div> -->
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
	<script src="<%=path%>/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/skycons.js"></script>

	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	<!-- end: JavaScript-->
</body>
</html>