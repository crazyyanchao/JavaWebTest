<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  	  <table>
  	  	<caption>书籍详细信息</caption>
  	  	<tr><td width="80px">书籍编号</td><td width="200px"><input name="book.bookId" type="text" value="${book.bookId }" readonly="readonly"/></td></tr>
  	  	<tr><td>书籍名称</td><td><input name="book.bookName" type="text" value="${book.bookName }" readonly="readonly"/></td></tr>
  	  	<tr><td>出版社</td><td><input name="book.press" type="text" value="${book.press }" readonly="readonly"/></td></tr>
  	  	<tr><td>出版时间</td><td><input name="book.publishTime" type="text" value="${book.publishTime }" readonly="readonly"/></td></tr>
  	  	<tr><td>库存量</td><td><input name="book.count" type="text" value="${book.count }" readonly="readonly"/></td></tr>
  	  	<tr><td>入库时间</td><td><input name="book.importTime" type="text" value="${book.importTime }" readonly="readonly"/></td></tr>
  	  	<tr><td>出库时间</td><td><input name="book.exportTime" type="text" value="${book.exportTime }" readonly="readonly"/></td></tr>
  	  </table>
