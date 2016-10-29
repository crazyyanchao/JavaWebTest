<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  	<form action="admin/add_Book" method="post">
  	  <table>
  	  	<caption>添加书籍</caption>
  	  	<tr><td>书籍编号</td><td><input name="book.bookId" type="text"/></td></tr>
  	  	<tr><td>书籍名称</td><td><input name="book.bookName" type="text"/></td></tr>
  	  	<tr><td>出版社</td><td><input name="book.press" type="text"/></td></tr>
  	  	<tr><td>出版日期</td><td><input name="book.publishTime" type="text"/></td></tr>
  	  	<tr><td>库存量</td><td><input name="book.count" type="text"/></td></tr>
  	  	<tr><td> </td><td><input type="submit" value="提交"/></td></tr>
  	  </table>
  	</form>
