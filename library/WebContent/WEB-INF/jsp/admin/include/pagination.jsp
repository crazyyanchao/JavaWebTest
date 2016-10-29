<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="pagination">
  	<c:url value="admin/showAll_Book" var="url"></c:url>
  	<a href="${url}?page=${pageInfo.first}">首页</a>
  	  <c:forEach items="${pageInfo.datas}"  begin="${pageInfo.start}" end="${pageInfo.end}" varStatus="s">
  	  	<a href="${url}?page=${s.index}">${s.index }</a>
  	  </c:forEach>
  	<a href="${url}?page=${pageInfo.end}">尾页</a>
  </div>