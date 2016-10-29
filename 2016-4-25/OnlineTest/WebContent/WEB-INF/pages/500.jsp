<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试平台 | 500</title>
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
</head>
<body>
	<!-- Start: Content -->
		<div class="container-fluid content">
			<div class="row">
				<!-- Main Page -->
				<div id="content" class="col-sm-12 full">				
					<div class="row box-error">					
						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="text-center">
								<h1>500</h1>
								<h2>Unauthorized ...</h2>
								<p>You need to login first to see this page.</p>
								<a href="javascript: history.go(-1)">
									<button type="button" class="btn btn-danger"><i class="fa fa-chevron-left"></i> Back</button>
								</a>
								<a href="/">
									<button type="button" class="btn btn-danger"><i class="fa fa-lock"></i> Login</button>
								</a>
							</div>						
						</div>					
					</div>			
				</div>
				<!-- End Main Page -->
			</div>
		</div><!--/container-->
		
		
		<!-- start: JavaScript-->
		
	<!-- Vendor JS-->
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