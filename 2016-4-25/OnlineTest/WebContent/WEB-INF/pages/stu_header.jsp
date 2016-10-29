<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String TheUserName = "游客，你好";
	String UserRole = "教师";
	String UserId = "";
	if( session.getAttribute("userName") != null){
	   TheUserName = session.getAttribute("userName").toString();
	}
	if( session.getAttribute("role") != null ){
		UserRole = session.getAttribute("role").toString();
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="navbar" role="navigation">
		<div class="container-fluid container-nav">
			<!-- Navbar Action -->
			<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><a href="javascript:void();"
					id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
				<li class="visible-xs visible-sm"><a href="javascript:void();"
					id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>
			</ul>
			<div style="height: 100%; display: inline-block;margin: 0 auto; margin-left: 23%; position: fixed; top: 10px;">
				<!-- 动态显示时间 -->
				<h4 id="dynamicTime"></h4>
			</div>
			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Userbox -->
				<div class="userbox">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<div class="profile-info">
							<span class="name"><%=TheUserName%></span>
							<span class="role"><%=UserRole%></span>
						</div> <i class="fa custom-caret"></i>
					</a>
					<div class="dropdown-menu">
						<ul class="list-unstyled">
							<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">
								<a href="personinfo"><i class="fa fa-user"></i>个人中心</a>
							</li>
							<li><a id="A_logout" href="../logout"><i class="fa fa-power-off"></i>退出</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- End Userbox -->
			</div>
			<!-- End Navbar Right -->
		</div>
	</div>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript">
		function disptime() {
			var time = new Date(); //获得当前时间
			var year = time.getFullYear();//获得年、月、日
			var month = time.getMonth() + 1;
			var day = time.getDate();
			var hour = time.getHours(); //获得小时、分钟、秒 
			var minute = time.getMinutes();
			var second = time.getSeconds();
			var apm = "AM"; //默认显示上午: AM 
			if (hour > 12) //按12小时制显示 
			{
				hour = hour - 12;
				apm = "PM";
			}
			if (minute < 10) //如果分钟只有1位，补0显示 
				minute = "0" + minute;
			if (second < 10) //如果秒数只有1位，补0显示 
				second = "0" + second;
			/*设置文本框的内容为当前时间*/
			document.getElementById("dynamicTime").innerHTML = year + "年"
					+ month + "月" + day + "日 " + hour + ":" + minute + ":"
					+ second + " " + apm;
			/*设置定时器每隔1秒（1000毫秒），调用函数disptime()执行，刷新时钟显示*/
			setTimeout("disptime()", 1000);
		}
		$(document).ready(disptime);
	</script>
</body>
</html>