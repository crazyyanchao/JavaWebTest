<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String role = request.getSession().getAttribute("role").toString();
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
						<li class="nav-parent"><a> <i class="fa fa-hospital-o" aria-hidden="true"></i><span>院系管理</span></a>
							<ul class="nav nav-children">
								<li><a href="academylist"><span class="text">学院管理</span></a></li>
								<li><a href="departmentlist"><span class="text">科系管理</span></a></li>
							</ul>
						</li>
						<li class="nav-parent"><a> <i class="fa fa-users" aria-hidden="true"></i><span>人员管理</span></a>
							<ul class="nav nav-children">
								<li><a href="teacherlist"><span class="text">教师管理</span></a></li>
								<li><a href="classlist"><span class="text">班级管理</span></a></li>
								<li><a href="studentlist"><span class="text">学生管理</span></a></li>
								<%
									if(role.equals("超级管理员")){
										%>
										<li><a href="adminlist"><span class="text">管理员管理</span></a></li>
										<%
									}
								%>
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