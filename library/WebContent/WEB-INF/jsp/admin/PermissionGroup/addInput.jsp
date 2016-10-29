<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
  	<s:form action="admin/add_PermissionGroup">
  	<caption>添加权限分组</caption>
  	  <s:textfield label="权限分组名称" name="permissionGroup.permissionGroupName"></s:textfield>
  	  <s:textfield label="权限分组描述" name="permissionGroup.description"></s:textfield>
  	  <s:submit value="提交"></s:submit>
  	</s:form>
