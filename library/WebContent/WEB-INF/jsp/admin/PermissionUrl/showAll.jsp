<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
  	<table>
  	  <caption>权限路径列表</caption>
  	  <tr><th width="40px">编号</th><th width="200px">路径地址</th><th width="200px">路径描述</th><th width="90px">操作</th></tr>
  	  <c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr>
  	  	  <td>${obj.permissionUrlId}</td>
  	  	  <td>${obj.action}</td>
  	  	  <td>${obj.description}</td>
  	  	  <td><a href="admin/updateInput_PermissionUrl?permissionUrl.permissionUrlId=${obj.permissionUrlId}">修改</a> 
  	  	   | <a href="">删除</a>
  	  	   </td>
  	  	</tr>
  	  </c:forEach>
  	</table>
  	<s:a href="admin/showAll_PermissionUrl?page=%{#pageInfo.first}">首页</s:a>
  	<s:iterator begin="%{#pageInfo.start}" end="%{#pageInfo.end}" var="page">
  	  <s:a href="admin/showAll_PermissionUrl?page=%{#page}"><s:property value="#page"/></s:a>
  	</s:iterator>
  	<s:a href="admin/showAll_PermissionUrl?page=%{#pageInfo.end}">尾页</s:a>
