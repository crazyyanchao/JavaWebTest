<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<div ><label for="bookId">用户编号:</label><input id="bookId" /><input id="search" type="button" value="搜索评论"/></div>
  	<div id="comments">
  	<table>
  	  <caption>用户列表</caption>
  	  <tr><th width="40px">编号</th><th width="80px">用户编号</th><th width="80px">用户密码</th>
  	      <th width="80px">姓名</th><th width="40px">余额</th><th width="40px">操作</th></tr>
  	  <c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr>
  	  		<td>${obj.userId }</td>
  	  		<td>${obj.userNo }</td>
  	  		<td>${obj.password }</td>
  	  		<td>${obj.name }</td>
  	  		<td>${obj.balance }</td>
  	  		<td><a href="">删除</a></td>
  	  	</tr>
  	  </c:forEach>
  	</table>
  	</div>