<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
    <s:form action="admin/update_PermissionGroup">
      <s:hidden name="permissionGroup.permissionGroupId" value="%{#permissionGroup.permissionGroupId}"></s:hidden>
      <s:textfield label="权限组名称" name="permissionGroup.permissionGroupName" value="%{#permissionGroup.permissionGroupName}"></s:textfield>
      <s:textfield label="权限组描述" name="permissionGroup.description" value="%{#permissionGroup.description}"></s:textfield>
      <s:checkboxlist cssStyle="text-align:left" label="地址" name="urls" list="permissionUrls" title="description" listKey="permissionUrlId" listValue="description" value="%{#urlIds}"  ></s:checkboxlist>
      <s:submit value="提交"></s:submit>
    </s:form>
