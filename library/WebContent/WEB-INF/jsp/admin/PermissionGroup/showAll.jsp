<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <script type="text/javascript" src="js/permissionGroup_showAll.js"></script>
	<div id="permisionGroup" style="float: left;margin-left: 10px">
  	<table>
  	  <caption>权限组列表</caption>
  	  <tr><th width="40px">编号</th><th width="150px">权限组名</th><th width="150px">权限组描述</th><th width="200px">映射路径</th><th width="200px">操作</th></tr>
  	  <c:forEach  items="${permissionGroups}" var="obj" varStatus="s">
  	  	<tr>
  	  	  <td class="groupId">${obj.permissionGroupId}</td>
  	  	  <td>${obj.permissionGroupName}</td>
  	  	  <td>${obj.description}</td>
  	  	  <td>
  	  	  	<select class="group_urls">
  	  	  	  <c:forEach  items="${obj.permissionUrls}" var="url" varStatus="s">
  	  	  		<option>${url.description }</option>
  	  	  	  </c:forEach>
  	  	  	</select>
  	  	  </td>
  	  	  
  	  	  <td><a href="admin/updateInput_PermissionGroup?permissionGroup.permissionGroupId=${obj.permissionGroupId}">修改</a> | <a href="admin/delete_PermissionGroup?permissionGroup.permissionGroupId=${permissionGroup.permissionGroupId}" >删除</a>
  	  	  
  	  	   | <a class="mangage_url" href="">管理权限路径</a></td>
  	  	</tr>
  	  </c:forEach>
  	</table>
  	</div>
  	<div id="permisionUrls" style="width: 200px;float: right;">
  	  <ul class="board_ul">
  	  	<c:forEach items="${permissionUrls }" var="permissionUrl" varStatus="s">
		  <li><input class="urls" type="checkbox"  value="${permissionUrl.permissionUrlId }" />${permissionUrl.description }</li>
		</c:forEach> 
  	  </ul>
  	  <a id="update_groupurls" href="">提交</a>
  	</div>
  