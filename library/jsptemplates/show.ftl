<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  	  <table>
  	  	<caption></caption>
  	  	<#list fields as field >
  	  	<tr><td width=""></td><td width="">$ {"${variable}.${field} }</td></tr>
  	  	</#list>
  	  </table>
