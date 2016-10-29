<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
  	<table id="bookRecords">
  	  <caption>借还记录</caption>
  	  <tr><th width="40px">编号</th><th width="200px">书籍名称</th><th width="100px">借出时间</th>
  	  <th width="100px">归还时间</th><th width="80px">超期天数</th><th width="80px">续借次数</th><th>操作</th></tr>
  	  <c:forEach items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr><td>${obj.borrowRecordId }</td><td>${obj.book.bookName }</td>
  	  	<td>${obj.borrowTime }</td><td>${obj.returnTime }</td><td>1</td>
  	  	<td><a href="">修改</a> | <a href="">删除</a></td></tr>
  	  </c:forEach>
  	</table>
  	
  	<div id="search">
  		<form action="">
  		<table>
  			<caption>借书记录查询</caption>
  			<tr><td width="80px">借书人</td><td width="200px"><input name="" type="text" /></td></tr>
  			<tr><td>借书时间</td><td><input name="" type="text" /></td></tr>
  			<tr><td>还书时间</td><td><input name="" type="text" /></td></tr>
  			<tr><td></td><td></td></tr>
  			<tr><td></td><td></td></tr>
  			<tr><td></td><td><input type="submit"></td></tr>
  		</table>
  		
  		</form>
  	</div>
  	
  	
  	
  	<ul>
  		<li>某人借书记录</li>
  		<li>某日借书记录</li>
  		<li>某日还书记录</li>
  		<li>某操作员操作记录</li>
  	</ul>