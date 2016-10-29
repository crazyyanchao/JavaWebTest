<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript" src="js/bookType_showAll.js"></script>
<div style="float: left;">
  	<table>
  	  <caption>书籍类型列表</caption>
  	  <tr><th width="80px">类型编号</th><th width="400px">书籍类型名称</th><th>创建子类型</th><th width="100px">操作</th></tr>
  	  <c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr>
  	  	  <td class="pid">${obj.bookTypeId }</td>
  	  	  <td><a href="admin/showAll_BookType?query=parentId&value=${obj.bookTypeId }">${obj.bookTypeName }</a>(<a></a>)</td>
  	  	  <td class="addChild"><a href="">创建</a></td>
  	  	  <td><a href="">修改</a> | <a href="">删除</a></td>
  	  	</tr>
  	  </c:forEach>
  	</table>
  	<a class="addChild" href="">创建顶层类型</a> | <a class="addType" href="">创建类型</a> | 
  	<a href="admin/showAll_BookType?query=parentId">返回顶层</a> 
  </div>
  	
	<div id="addInput">
	  <form action="admin/add_BookType" method="post">
	  	<input id="parentId" name="bookType.parent.bookTypeId" type="hidden" value="${parentId }"/>
	  	  <table>
	  		<caption>创建书籍类型</caption>
	  		<tr><td>书籍类型名称：</td><td><input id="=name" name="bookType.bookTypeName" /></td></tr>
  	  		<tr><td></td><td><input type="submit" value="提交"/></td></tr>
  	 	  </table>
  	  </form>
  	</div>
