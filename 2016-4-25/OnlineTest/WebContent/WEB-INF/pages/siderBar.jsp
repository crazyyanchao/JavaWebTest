<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
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
						<li class="nav-parent"><a> <i class="fa fa-wrench" aria-hidden="true"></i><span>个人信息管理</span></a>
							<ul class="nav nav-children">
								<li><a href="personinfo"><span class="text">个人中心</span></a></li>
								<li><a href="password"><span class="text">修改密码</span></a></li>
							</ul>
						</li>
						<li class="nav-parent"><a> <i class="fa fa-file" aria-hidden="true"></i><span>试卷管理</span></a>
							<ul class="nav nav-children">
								<li><a href="testpaperdesign"><span class="text">试卷设定</span></a></li>
								<li><a href="testpaperlist"><span class="text">试卷列表</span></a></li>
								<li><a href="markpaper"><span class="text">试卷评阅</span></a></li>
							</ul>
						</li>
						<li class="nav-parent"><a><i class="glyphicon glyphicon-book" aria-hidden="true"></i><span>题库管理</span></a>
							<ul class="nav nav-children">
								<li><a href="singlechoose"><span class="text">单选题</span></a></li>
								<li><a href="multichoose"><span class="text">多选题</span></a></li>
								<li><a href="fillblank"><span class="text">填空判断题</span></a></li>
								<li><a href="essayquestion"><span class="text">问答题</span></a></li>
							</ul>
						</li>
						<li class="nav-parent"><a><i class="fa fa-book" aria-hidden="true"></i><span>课程管理</span></a>
							<ul class="nav nav-children">
								<li><a href="coursemanage"><span class="text">全部课程</span></a></li>
								<li><a href="teachercourse"><span class="text">所授课程</span></a></li>
							</ul>
						</li>
						
						<li class="nav-parent"><a> <i class="fa fa-group" aria-hidden="true"></i><span>学生成绩管理</span></a>
							<ul class="nav nav-children">
								<li><a href="scorelist"><span class="text">成绩列表</span></a></li>
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
</body>
</html>