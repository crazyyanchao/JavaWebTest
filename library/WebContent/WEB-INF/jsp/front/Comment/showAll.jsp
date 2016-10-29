<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
<!--

$().ready(function(){
	$("#search").click(function(){
		var bookId=$("#bookId").val();
		location.href="admin/showAll_Comment?query=bookId&value="+bookId;
	});
	
});

//-->
</script>
	<div ><label for="bookId">书籍编号:</label><input id="bookId" /><input id="search" type="button" value="搜索评论"/></div>
  	<div id="comments">
  	<table>
  	  <caption>书评列表</caption>
  	  <tr><th width="40px">编号</th><th width="200px">书籍名称</th><th width="300px">评论内容</th>
  	      <th width="80px">评论人</th><th width="80px">评论时间</th><th width="40px">操作</th></tr>
  	  <c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr>
  	  		<td>${obj.commentId }</td>
  	  		<td><a href="admin/show_Book?book.bookId=${obj.book.bookId }">${obj.book.bookName}</a></td>
  	  		<td><textarea rows="1" cols="50" readonly="readonly">${obj.content }</textarea></td>
  	  		<td> </td>
  	  		<td>${obj.commentTime }</td>
  	  		<td><a href="admin/delete_Comment?comment.commentId=${obj.commentId }">删除</a></td>
  	  	</tr>
  	  </c:forEach>
  	</table>
  	</div>
<ul>
<li>某人评论记录</li>
<li>某书评论记录</li>
<li>好评</li>
<li>中评</li>
<li>差评</li>
<li></li>
</ul>