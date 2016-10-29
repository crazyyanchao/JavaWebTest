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
<title>在线考试 | 试卷设定</title>
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
<link href="<%=path%>/css/add-ons.min.css" rel="stylesheet"/>
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
	body{min-width: 1050px;overflow: scroll;}
	table tr td a{cursor: pointer; color: #888;}
	#course_chapterSelectArea a{color: #555;}
	.main i{color: #EEE;}
	.AvailableCount{float: right; display: none;}
	.must_chooce{color: red;}
	.tips{color: #f0ad4e;}
	.tipsfont{font-size: 15px;}
</style>
</head>
<body>
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
							<li><a><i class="fa fa-file"></i>试卷管理</a></li>
							<li class="active"><i class="fa fa-edit"></i>试卷设定</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>试卷设定</h2>
					</div>
				</div>
				<!-- End Page Header -->

				<!--     Page body start    -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel bk-bg-white">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-pencil red"></i><span class="break" style="font-size: 16px; color: #eee">基础信息</span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped form-horizontal form-bordered" style="clear: both">
									<tbody id="base_info">
										<tr>
											<td width="35%"><strong>科目</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><a onclick="showCourseSelectPanel(this)">点击选择科目</a></td>
										</tr>
										<tr>
											<td><strong>章节</strong></td>
											<td width="65%"><a onclick="showChapterSelectPanel()">点击选择章节</a></td>
										</tr>
										<tr>
											<td><strong>考试日期</strong><span class="must_chooce">✲</span></td>
											<td width="65%" class="input-daterange"><input type="text" data-plugin-datepicker="" class="form-control"></td>
										</tr>
										<tr>
											<td><strong>开始时间</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="text" data-plugin-timepicker="" class="form-control" data-plugin-options="{ &quot;showMeridian&quot;: false }"></td>
										</tr>
										<tr>
											<td><strong>结束时间</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="text" data-plugin-timepicker="" class="form-control" data-plugin-options="{ &quot;showMeridian&quot;: false }"></td>
										</tr>
										<tr>
											<td><strong>试卷类型</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><select class="form-control" onclick="checkTestTime()"><option value="">请选择试卷类型</option><option value="4001">固定试题</option><option value="4002">不固定试题</option></select></td>
										</tr>
										<tr>
											<td><strong>试卷总分</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input class="form-control" type="number" min="0"></td>
										</tr>
										<tr>
											<td><strong>阅卷人</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><a onclick="chooseMarkers()">点击选择阅卷人</a></td>
										</tr>
										<tr>
											<td><strong>参与班级</strong></td>
											<td width="65%"><a onclick="chooseDepartment()">点击选择参与班级</a></td>
										</tr>
									</tbody>
								</table>
								<p class="tipsfont"><span class="must_chooce">提示</span>：<span class="must_chooce">✲</span>内容为必选项</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel bk-bg-white">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-pencil red"></i><span class="break" style="font-size: 16px; color: #eee">单选题</span>
									<span class="AvailableCount" style="margin-left: 180px;font-size: 16px; color: #eee">当前剩余分值:<span id="RestScore_single" class="text-danger" style="font-weight: bolder;"></span></span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a> 
									<a class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped" style="clear: both">
									<tbody id="SCchoose">
										<tr>
											<td><strong>数量</strong><span class="must_chooce">✲</span> <span class="AvailableCount">现有<span id="singleChooseCount" class="text-danger"></span>题&nbsp;易<span id="singleEasy" class="text-danger"></span>中<span id="singleNormal" class="text-danger"></span>难<span id="singleHard" class="text-danger"></span></span></td>
											<td width="65%"><input type="number" min="0" class="form-control" onmousedown="showSingleCount()"></td>
										</tr>
										<tr>
											<td><strong>每道题的分值</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="number" min="0" class="form-control" onchange="checkRestScore_single()"></td>
										</tr>
										<tr>
											<td><strong>难度</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><select id="singleChooseLevel" class="form-control"><option  value ="">请选择难度</option><option value="2001">简单</option><option value="2002">一般</option><option value="2003">难</option><option value="0">自定义</option></select></td>
										</tr>
									</tbody>
								</table>
								<p class="tipsfont"><span class="must_chooce">提示</span>：单选题为试卷的出题可选项，如果选择单选题，则<span class="must_chooce">✲</span>内容为必选项，反之，则忽略</p>
							</div>
						</div>
					</div>
				</div>
				<!-- end 单选题 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel bk-bg-white">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-pencil red"></i><span class="break" style="font-size: 16px; color: #eee">多选题</span>
									<span class="AvailableCount" style="margin-left: 180px;font-size: 16px; color: #eee">当前剩余分值:<span id="RestScore_multi" class="text-danger" style="font-weight: bolder;"></span></span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a> 
									<a class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped" style="clear: both">
									<tbody id="MCchoose">
										<tr>
											<td><strong>数量</strong><span class="must_chooce">✲</span><span class="AvailableCount">现有<span id="multiChooseCount" class="text-danger"></span>题&nbsp;易<span id="multiEasy" class="text-danger"></span>中<span id="multiNormal" class="text-danger"></span>难<span id="multiHard" class="text-danger"></span></span></td>
											<td width="65%"><input type="number" min="0" onmousedown="showMultiCount()" class="form-control"></td>
										</tr>
										<tr>
											<td><strong>每道题的分值</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="number" min="0" class="form-control" onchange="checkRestScore_multi()"></td>
										</tr>
										<tr>
											<td><strong>难度</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><select id="multiChooseLevel" class="form-control"><option  value ="">请选择难度</option><option value="2001">简单</option><option value="2002">一般</option><option value="2003">难</option><option value="0">自定义</option></select></td>
										</tr>
									</tbody>
								</table>
								<p class="tipsfont"><span class="must_chooce">提示</span>：多选题为试卷的出题可选项，如果选择多算题，则<span class="must_chooce">✲</span>内容为必选项，反之，则忽略</p>
							</div>
						</div>
					</div>
				</div>
				<!-- end 多选题 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel bk-bg-white">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-pencil red"></i><span class="break" style="font-size: 16px; color: #eee">判断题</span>
									<span class="AvailableCount" style="margin-left: 180px;font-size: 16px; color: #eee">当前剩余分值:<span id="RestScore_tf" class="text-danger" style="font-weight: bolder;"></span></span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a> 
									<a class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped" style="clear: both">
									<tbody id="TFchoose">
										<tr>
											<td><strong>数量</strong><span class="must_chooce">✲</span><span class="AvailableCount">现有<span id="tfCount" class="text-danger"></span>题&nbsp;易<span id="tfEasy" class="text-danger"></span>中<span id="tfNormal" class="text-danger"></span>难<span id="tfHard" class="text-danger"></span></span></td>
											<td width="65%"><input type="number" min="0" onmousedown="showTFCount()" class="form-control"></td>
										</tr>
										<tr>
											<td><strong>每道题的分值</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="number" min="0" class="form-control" onchange="checkRestScore_tf()"></td>
										</tr>
										<tr>
											<td><strong>难度</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><select id="tfLevel" class="form-control"><option  value ="">请选择难度</option><option value="2001">简单</option><option value="2002">一般</option><option value="2003">难</option><option value="0">自定义</option></select></td>
										</tr>
									</tbody>
								</table>
								<p class="tipsfont"><span class="must_chooce">提示</span>：判断题为试卷的出题可选项，如果选择判断题，则<span class="must_chooce">✲</span>内容为必选项，反之，则忽略</p>
							</div>
						</div>
					</div>
				</div>
				<!-- end 判断题 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel bk-bg-white">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-pencil red"></i><span class="break" style="font-size: 16px; color: #eee">填空题</span>
									<span class="AvailableCount" style="margin-left: 180px;font-size: 16px; color: #eee">当前剩余分值:<span id="RestScore_fill" class="text-danger" style="font-weight: bolder;"></span></span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a> 
									<a class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped" style="clear: both">
									<tbody id="FBchoose">
										<tr>
											<td><strong>数量</strong><span class="must_chooce">✲</span><span class="AvailableCount">现有<span id="fillBlankCount" class="text-danger"></span>题&nbsp;易<span id="fillEasy" class="text-danger"></span>中<span id="fillNormal" class="text-danger"></span>难<span id="fillHard" class="text-danger"></span></span></td>
											<td width="65%"><input type="number" min="0" onmousedown="showFillCount()" class="form-control"></td>
										</tr>
										<tr>
											<td><strong>每道题的分值</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="number" min="0" class="form-control" onchange="checkRestScore_fill()"></td>
										</tr>
										<tr>
											<td><strong>难度</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><select id="fillBlankLevel" class="form-control"><option  value ="">请选择难度</option><option value="2001">简单</option><option value="2002">一般</option><option value="2003">难</option><option value="0">自定义</option></select></td>
										</tr>
									</tbody>
								</table>
								<p class="tipsfont"><span class="must_chooce">提示</span>：填空题为试卷的出题可选项，如果选择填空题，则<span class="must_chooce">✲</span>内容为必选项，反之，则忽略</p>
							</div>
						</div>
					</div>
				</div>
				<!-- end 填空题 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel bk-bg-white">
							<div class="panel-heading" style="background-color: #34495E">
								<h6>
									<i class="fa fa-pencil red"></i><span class="break" style="font-size: 16px; color: #eee">问答题</span>
									<span class="AvailableCount" style="margin-left: 180px;font-size: 16px; color: #eee">当前剩余分值:<span id="RestScore_essay" class="text-danger" style="font-weight: bolder;"></span></span>
								</h6>
								<div class="panel-actions">
									<a class="btn-minimize"><i class="fa fa-chevron-up"></i></a> 
									<a class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped" style="clear: both">
									<tbody id="EQchoose">
										<tr>
											<td><strong>数量</strong><span class="must_chooce">✲</span><span class="AvailableCount">现有<span id="essayQuestionCount" class="text-danger"></span>题&nbsp;易<span id="essayEasy" class="text-danger"></span>中<span id="essayNormal" class="text-danger"></span>难<span id="essayHard" class="text-danger"></span></span></td>
											<td width="65%"><input type="number" min="0" onmousedown="showEssayCount()" class="form-control"></td>
										</tr>
										<tr>
											<td><strong>每道题的分值</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><input type="number" min="0" class="form-control" onchange="checkRestScore_essay()"></td>
										</tr>
										<tr>
											<td><strong>难度</strong><span class="must_chooce">✲</span></td>
											<td width="65%"><select id="essayQuestionLevel" class="form-control"><option value ="">请选择难度</option><option value="2001">简单</option><option value="2002">一般</option><option value="2003">难</option><option value="0">自定义</option></select></td>
										</tr>
									</tbody>
								</table>
								<p class="tipsfont"><span class="must_chooce">提示</span>：问答题为试卷的出题可选项，如果选择问答题，则<span class="must_chooce">✲</span>内容为必选项，反之，则忽略</p>
							</div>
						</div>
					</div>
				</div>
				<!-- end 问答题 -->
				<!--     Page body end      -->
				<button class="form-control btn btn-danger" onclick="designComfirm()">确定</button>
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
	<!-- 时间、日期 -->
	<script src="<%=path%>/js/bootstrap-datepicker.js"></script>
	<script src="<%=path%>/js/bootstrap-timepicker.js"></script>
	<script src="<%=path%>/js/form-elements.js"></script>
	<!-- SweetAlert JavaScript -->
	<script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/testpaperdesign.js"></script>
	<!-- end: JavaScript-->
</body>
</html>