<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

    <form action="admin/add_Announcement" method="post">
  	<table>
  	  <caption>我要推荐：</caption>
  	  <tr>
  	    <td>书名：</td>
  	    <td><input name="recommandBook.bookName" type="text"/></td>
  	  </tr>
  	  
  	  <tr>
  	    <td>作者：</td>
  	    <td><input name="recommandBook.bookAuthor" type="text"/></td>
  	  </tr>
  	  
  	  <tr>
  	    <td>出版社：</td>
  	    <td><input name="recommandBook.press" type="text"/></td>
  	  </tr>
  	  
  	  <tr>
  	    <td>出版时间：</td>
  	    <td><input name="recommandBook.publishTime" type="text"/></td>
  	  </tr>
  	  
  	  <tr>
  	    <td>推荐原因：</td>
  	    <td><input name="recommandBook.reason" type="text"/></td>
  	  </tr>
  	  
  	  <tr>
  	    <td> </td>
  	    <td><input type="reset" value="重置"/> <input type="submit" value="提交"/></td>
  	  </tr>
  	</table>
  	</form>
