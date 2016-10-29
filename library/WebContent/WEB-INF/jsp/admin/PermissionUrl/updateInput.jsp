<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
    <s:form action="admin/update_PermissionUrl">
      <s:hidden name="permissionUrl.permissionUrlId" value="%{#permissionUrl.permissionUrlId}"></s:hidden>
  	  <s:property value="%{#permissionUrl.permissionUrlId}"/>
  	  <s:textfield label="控制路径" name="permissionUrl.action" value="%{#permissionUrl.action}"></s:textfield>
  	  <s:textfield label="权限描述" name="permissionUrl.description" value="%{#permissionUrl.description}"></s:textfield>
  	  <s:submit value="提交"></s:submit>
  	</s:form>
