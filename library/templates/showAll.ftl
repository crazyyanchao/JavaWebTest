<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<table>
  <tr><td>路径id</td><td>路径地址</td><td>路径描述</td><td>操作</td></tr>
  <s:iterator value="%{#pageInfo.datas}" var="permissionUrl" status="s">
    <tr>
    <td><s:property value="#permissionUrl.permissionUrlId"/></td>
  	<td><s:property value="#permissionUrl.action"/></td>
  	<td><s:property value="#permissionUrl.description"/></td>
  	<td><s:a href="admin/updateInput_PermissionUrl?permissionUrl.permissionUrlId=%{#permissionUrl.permissionUrlId}">修改</s:a> | <s:a>删除</s:a></td>
  	</tr>
  </s:iterator>
</table>
<s:a href="admin/showAll_PermissionUrl?page=%{#pageInfo.first}">首页</s:a>
<s:iterator begin="%{#pageInfo.start}" end="%{#pageInfo.end}" var="page">
  <s:a href="admin/showAll_PermissionUrl?page=%{#page}"><s:property value="#page"/></s:a>
</s:iterator>
<s:a href="admin/showAll_PermissionUrl?page=%{#pageInfo.end}">尾页</s:a>
