<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>在线考试 | 在线阅卷</title>
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
	.search_condiction{margin-left: 10px;}
	#course_chapterSelectArea a{ color: #333}
</style>
</head>
<body>
	<!-- 科目选择弹窗 -->
	<div id="course_chapterSelectArea" style="background-color: #eee;"></div>
	<div id="testPaperShowing" style="display: none"></div>
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
							<li><a><i class="fa fa-file-text"></i>试卷评阅</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>试卷评阅</h2>
					</div>
				</div>
				<!-- End Page Header -->
				<!--     Page body start    -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel" style="min-width: 910px;">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-table red"></i><span class="break" style="font-size: 16px; color: #eee">测试列表</span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<div style="text-align: center;">
<!-- 										<label class="search_condiction">学院</label><select id="markpaper_depart" class="form-control" style="width: 100px;display: inline-block;margin: 0 20px 0 10px"><option value="">请选择学院</option></select>  -->
<!-- 										<label class="search_condiction">班级</label><select id="markpaper_class" onclick="choosedepartfirst()" class="form-control" style="width: 100px;display: inline-block;margin: 0 20px 0 10px;"><option value="">请选择班级</option></select> -->
										<label class="search_condiction">科目</label><input id="markpaper_course" onclick="showCourseSelectPanel()" class="form-control" style="display:inline-block; width:150px;margin: 0 20px 0 10px">
										<label class="search_condiction">学号</label><input id="markpaper_num" class="form-control" style="display:inline-block; width:150px;margin: 0 20px 0 10px">
										<button class="btn btn-default" onclick="search_tests()">筛选</button>
									</div>
									<table class="table table-striped table-bordered bootstrap-datatable datatable" style="min-width: 880px;">
										<thead>
											<tr style="text-align: center;">
												<th width="15%">测试编号</th>
												<th width="30%">科目</th>
												<th width="30%">学号</th>
<!-- 												<th width="">班级</th> -->
												<th width="25%">阅卷</th>
											</tr>
										</thead>
										<tbody id="testslist"></tbody>
									</table>
								</div>
								<div class="btn-toolbar" role="toolbar">
									<h5 style="display: inline-block; margin-top: 15px;">当前页面：</h5>
									<h5 id="currPage" style="display: inline-block; margin-top: 15px;"></h5>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<h5 style="display: inline-block; margin-top: 15px;">总页数：</h5>
									<h5 id="totalPage" style="display: inline-block; margin-top: 15px;"></h5>
									<div class="bk-margin-5 btn-group" style="float: right; right: -5px;">
										<button type="button" class="btn btn-default" onclick="markPaperFirst()">首页</button>
										<button type="button" class="btn btn-default" onclick="markPaperPrev()">上一页</button>
										<button type="button" class="btn btn-default" onclick="markPaperNext()">下一页</button>
										<button type="button" class="btn btn-default" onclick="markPaperLast()">尾页</button>
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
	
	<script type="text/javascript" src="<%=path%>/js/markpaper.js"></script>
	<!-- end: JavaScript-->
</body>
</html>