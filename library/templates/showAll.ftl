<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<table>
  <tr><td>·��id</td><td>·����ַ</td><td>·������</td><td>����</td></tr>
  <s:iterator value="%{#pageInfo.datas}" var="permissionUrl" status="s">
    <tr>
    <td><s:property value="#permissionUrl.permissionUrlId"/></td>
  	<td><s:property value="#permissionUrl.action"/></td>
  	<td><s:property value="#permissionUrl.description"/></td>
  	<td><s:a href="admin/updateInput_PermissionUrl?permissionUrl.permissionUrlId=%{#permissionUrl.permissionUrlId}">�޸�</s:a> | <s:a>ɾ��</s:a></td>
  	</tr>
  </s:iterator>
</table>
<s:a href="admin/showAll_PermissionUrl?page=%{#pageInfo.first}">��ҳ</s:a>
<s:iterator begin="%{#pageInfo.start}" end="%{#pageInfo.end}" var="page">
  <s:a href="admin/showAll_PermissionUrl?page=%{#page}"><s:property value="#page"/></s:a>
</s:iterator>
<s:a href="admin/showAll_PermissionUrl?page=%{#pageInfo.end}">βҳ</s:a>
