<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
    <s:form action="admin/add_PermissionUrl">
  	  <s:select label="控制路径" name="permissionUrl.action" list="urls"></s:select>
  	  <s:textfield label="权限描述" name="permissionUrl.description"></s:textfield>
  	  <s:submit value="提交"></s:submit>
  	</s:form>
  	<s:form action="admin/add_PermissionUrl">
  	  <s:textfield label="控制路径" name="permissionUrl.action"></s:textfield>
  	  <s:textfield label="权限描述" name="permissionUrl.description"></s:textfield>
  	  <s:submit value="提交"></s:submit>
  	
  	
  	  <s:checkboxlist name="sds"  list="#{1:'一辉',2:'撒卡',3:'童虎',4:'加隆'}" label="请选择你所喜欢的圣斗士" listValue="" value="{1,2}">
   </s:checkboxlist>
   
  	  <s:checkboxlist name="sds"  list="{'一辉','撒卡','童虎','加隆'}" label="请选择你所喜欢的圣斗士" listValue="" value="{'一辉'}">
   </s:checkboxlist>
     <s:submit value="submit"></s:submit>
  	  
  	</s:form>
