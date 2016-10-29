<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript" src="js/bookType_showAll.js"></script>
<div style="float: left;">
  	<table>
  	  <caption>书籍类型列表</caption>
  	  <tr><th width="400px">书籍类型名称</th></tr>
  	  <c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr>
  	  	  <td><a href="front/showAll_BookType?query.key=parentId&column=parentId&value=${obj.bookTypeId }">${obj.bookTypeName }</a>(<a></a>)</td>
  	  	</tr>
  	  </c:forEach>
  	</table>
  	<a href="front/showAll_BookType?query.key=parentId">返回顶层</a> 
  </div>
  	
