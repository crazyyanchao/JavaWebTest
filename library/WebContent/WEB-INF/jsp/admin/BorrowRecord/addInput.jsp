<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/borrowRecord_add.js"></script>

  	  <div><label for="bookId">书籍编号：</label><input id="bookId" name="book.bookId" type="text"/>
  	  <input id="search_book" type="button" value="检索">
  	  <div class="warm">该书不存在</div></div>
  	  
  	
  	<table id="bookInfo">
  	  	<tr><td>书籍编号</td><td><input name="book.bookId" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>书籍名称</td><td><input name="book.bookName" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>出版社</td><td><input name="book.press" value="333" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>出版时间</td><td><input name="book.publishTime" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>库存量</td><td><input name="book.count" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>入库时间</td><td><input name="book.importTime" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>出库时间</td><td><input name="book.exportTime" readonly="readonly" type="text"/></td></tr>
  	  	<tr><td>附带光盘</td><td><input name="book.exportTime" readonly="readonly" type="text"/></td></tr>
    </table>
  	<form action="">
  	  <input type="hidden"/>
  	  <div><label>读者编号：</label><input /><input type="submit" value="借书"></div>
  	  
  	</form>
