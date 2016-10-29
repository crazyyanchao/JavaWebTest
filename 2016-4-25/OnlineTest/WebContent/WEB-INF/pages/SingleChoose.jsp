<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<meta charset="UTF-8" />
<title>在线考试 | 单选题</title>
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
<!-- 时间、日期 -->
<link href="<%=path%>/css/datepicker3.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap-timepicker.css" rel="stylesheet"/>
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
 	.main{min-width: 1050px;overflow: scroll;}
	a{ cursor: pointer; text-decoration: none;}
	.main i{color: #EEE;}
	.main a{ cursor: pointer; text-decoration: none;}
	#course_chapterSelectArea a{color: #666;} 
</style>
</head>
<body>
	<!-- 缩写显示弹窗 -->
	<div id="table_small"></div>
	<!-- 添加、修改弹窗 -->
	<div id="table_input" style="background-color: #eee;"></div>
	<!-- 科目、章节 选择弹窗 -->
	<div id="course_chapterSelectArea" style="background-color: #eee;"></div>
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
							<li><a href="javascript:void();"><i class="glyphicon glyphicon-book"></i>题库管理</a></li>
							<li class="active"><i class="fa fa-check-circle"></i>单选题</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>单选题管理</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel" style="min-width: 910px;">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-table red"></i><span class="break" style="font-size: 16px; color: #eee">试题列表</span>
								</h6>
								<div class="panel-actions">
									<a href="javascript:void();" onclick="singleChooseReload()" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<div style="text-align: center;">
										<label class="search_condiction">科目</label><input id="singlechoose_course" onclick="courseSelectPanel_search()" class="form-control" style="display:inline-block; width:150px;margin: 0 20px 0 10px">
										<label class="search_condiction">日期</label>
										<div class="input-daterange input-group" data-plugin-datepicker style="width: 300px;display: inline-table;vertical-align: top;margin-left: 10px;margin-right: 20px;">
											<span class="input-group-addon">
												<i class="fa fa-calendar" style="color: #bbb;"></i>
											</span>
											<input type="text" class="form-control" name="start" id="date_start"/>
											<span class="input-group-addon">to</span>
											<input type="text" class="form-control" name="end" id="date_end"/>
										</div>
										<button class="btn btn-default" onclick="searchFilter()" style="vertical-align: top;">筛选</button>
									</div>
									<table class="table table-striped table-bordered bootstrap-datatable datatable" style="min-width: 880px;">
										<thead>
											<tr style="text-align: center;">
												<th width="5%">题号</th>
												<th width="10%">科目</th>
												<th width="8%">章节</th>
												<th width="70px;">难度等级</th>
												<th>题目</th>
												<th width="9%">选项A</th>
												<th width="9%">选项B</th>
												<th width="9%">选项C</th>
												<th width="9%">选项D</th>
												<th width="45px">答案</th>
												<th width="140px;">操作</th>
											</tr>
										</thead>
										<tbody id="singlechoose"></tbody>
									</table>
								</div>
								
								<div class="btn-toolbar" role="toolbar">
									<h5 style="display: inline-block; margin-top: 15px;">当前页面：</h5>
									<h5 id="currPage" style="display: inline-block; margin-top: 15px;"></h5>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<h5 style="display: inline-block; margin-top: 15px;">总页数：</h5>
									<h5 id="totalPage" style="display: inline-block; margin-top: 15px;"></h5>
									<div class="bk-margin-5 btn-group" style="float: right; right: -5px;">
										<button type="button" class="btn btn-default" onclick="singleChooseFirst()">首页</button>
										<button type="button" class="btn btn-default" onclick="singleChoosePrev()">上一页</button>
										<button type="button" class="btn btn-default" onclick="singleChooseNext()">下一页</button>
										<button type="button" class="btn btn-default" onclick="singleChooseLast()">尾页</button>
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
	<!-- 时间、日期 -->
	<script src="<%=path%>/js/bootstrap-datepicker.js"></script>
	<script src="<%=path%>/js/bootstrap-timepicker.js"></script>
	<script src="<%=path%>/js/form-elements.js"></script>
	<!-- Theme JS -->
	<script src="<%=path%>/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/js/core.min.js"></script>
	
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/singlechoose.js"></script>
	<!-- end: JavaScript-->
</body>
</html>