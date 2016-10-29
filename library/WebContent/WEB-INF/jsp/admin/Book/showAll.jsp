<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
  <script type="text/javascript" src="js/book_showAll.js"></script>
  	<div id="book_left" style="float: left;">
  <table>
  	<caption>书籍列表</caption>
  	<tr><th width="70px">书籍编号</th><th width="150px">书籍名称</th><th>出版社</th><th>出版时间</th><th>库存量</th><th>入库时间</th><th>出库时间</th><th>评论</th><th>操作</th></tr>
  	<c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  <tr>
  	    <td class="bookId">${obj.bookId }</td><td>${obj.bookName }</td><td>${obj.press }</td><td>${obj.publishTime }</td>
  	  	<td>${obj.count }</td><td>${obj.importTime }</td><td>${obj.exportTime }</td>
  	  	<td><a class="showAllComments" href="">查看</a> | <a class="addComment" href="#">添加</a></td>
  	  	<td><a href="">下架</a> | <a class="update" href="">修改</a> | <a class="delete" href="">删除</a></td>
  	  </tr>
  	</c:forEach>
  </table>
  <div class="pagination">
  	<c:url value="admin/showAll_Book" var="url"></c:url>
  	<a href="${url}?page=${pageInfo.first}">首页</a>
  	  <c:forEach items="${pageInfo.datas}"  begin="${pageInfo.start}" end="${pageInfo.end}" varStatus="s">
  	  	<a href="${url}?page=${s.index}">${s.index }</a>
  	  </c:forEach>
  	<a href="${url}?page=${pageInfo.end}">尾页</a>
  </div>
  </div>
  <div id="book_right">
  	<div id="comments"></div>
  	<div id="commentInput">
  	  <form action="admin/add_Comment" method="post">
  	  <input type="hidden" id="bookId" name="comment.book.bookId" value="11"/>
  	  	<table style="font-size: 15px">
  	  	  <caption>添加书评</caption>
  	  	  <tr><td width="50px">内容：</td><td><textarea name="comment.content" rows="5" cols="15"></textarea></td></tr>
  	  	  <tr><td> </td>
  	  	  	<td>
  	  	  		<input id="submitComment" type="button" value="提交"/>
  	  	  		<input id="cancelComment" type="button" value="取消"/>
  	  	  	</td>
  	  	  </tr>
  	  	</table>
  	  </form>
  	</div>
  	<div id="updateInput"></div>
  </div>
按书籍剩余量排序   
按书籍借阅量排序
按出版时间排序
按进书时间排序


已下架书籍管理