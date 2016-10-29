<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'header.jsp' starting page</title>
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/admin_index.js"></script>
	<link rel="stylesheet" type="text/css" href="css/admin_layout.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
</head>

  <body>
	<div id="container">
  	  <div id="header"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
 	  <div id="navigation"><tiles:insertAttribute name="navigation"></tiles:insertAttribute></div>
  	  <div id="center">
  		<div id="left"><tiles:insertAttribute name="left"></tiles:insertAttribute></div>
 		<div id="main"><tiles:insertAttribute name="main"></tiles:insertAttribute></div>
  	  </div>
  	  <div id="footer"><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>
	</div>
  </body>
</html>
