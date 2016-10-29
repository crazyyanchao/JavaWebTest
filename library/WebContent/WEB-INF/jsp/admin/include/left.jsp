<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<style type="text/css">

</style>
</head>

<ul>
  <li>
  	<p><a href="">用户信息管理</a></p>
  	<ul>
  	  <li><a href="admin/addInput_User">添加用户</a></li>
  	  <li><a href="admin/showAll_User">所有用户</a></li>
  	  <li><a href=""></a></li>
  	  <li><a href=""></a></li>
  	</ul>
  </li>
  
  <li>
  	<p><a href="">公告信息管理</a></p>
  	<ul>
  	  <li><a href="admin/addInput_Announcement">添加公告</a></li>
  	  <li><a href="admin/showAll_Announcement?query.key=new">最新公告</a></li>
  	  <li><a href="admin/showAll_Announcement">公告列表</a></li>
  	</ul>
  </li>
  
  <li>
  	<p><a href="">书籍评论管理</a></p>
  	<ul>
  	  <li><a href="admin/showAll_Comment">书评列表</a></li>
  	</ul>
  </li>
  
  <li>
  	<p><a href="">书籍信息管理</a></p>
  	<ul>
  	  <li><a href="admin/addInput_Book">添加书籍</a></li>
  	  <li><a href="admin/showAll_Book?query.key=online">上架书籍列表</a></li>
	  <li><a href="admin/showAll_Book?query.key=outline">下架书籍列表</a></li>
  	</ul>
  </li>
  
  <li>
  	<p><a href="">书籍分类管理</a></p>
  	<ul>
  	  <li><a href="admin/showAll_BookType?query.key=parentId">分类列表</a></li>
  	</ul>
  </li>
  
  <li>
  	<p><a href="">借书记录管理</a></p>
  	<ul>
  	  <li><a href="admin/addInput_BorrowRecord">添加记录</a></li>
  	  <li><a href="admin/showAll_BorrowRecord">记录列表</a></li>
  	</ul>
  </li>
  
  
  <li>
  	<p><a href="">图书推荐管理</a></p>
  	<ul>
  	  <li><a href="admin/addInput_RecommandBook">添加记录</a></li>
  	  <li><a href="admin/showAll_RecommandBook">记录列表</a></li>
  	</ul>
  </li>
	
  
  
  <li>
  	<p><a href="">权限分组管理</a></p>
  	<ul>
		<li><a href="admin/addInput_PermissionGroup">添加权限分组</a></li>
		<li><a href="admin/showAll_PermissionGroup">权限分组列表</a></li>
  	</ul>
  </li>
  
  <li>
  	<p><a href="">权限路径管理</a></p>
  	<ul>
		<li><a href="admin/addInput_PermissionUrl">添加权限路径</a></li>
		<li><a href="admin/showAll_PermissionUrl">权限路径列表</a></li>
  	</ul>
  </li>
  <li>
  	<p><a href="">其他</a></p>
  	<ul>
		<li><a href="">欠款人员列表</a></li>
		<li><a href=""></a></li>
		<li><a href="">图书超期列表</a></li>
		<li><a href=""></a></li>
		<li><a href="">最近一月新进书籍</a></li>
  	</ul>
  </li>	
</ul>