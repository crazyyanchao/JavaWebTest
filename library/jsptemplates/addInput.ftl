<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <form action="admin/add_${className}" method="post">
  		<table>
  	  		<caption></caption>
  	  		<#list fields as field >
  	  		<tr><td></td><td><input id="${field}" name="${variable}.${field}" /></td></tr>
  	  		</#list>
  	  		<tr><td> </td><td><input type="submit" value="Ìá½»"/></td></tr>
  		</table>
  	</form>
