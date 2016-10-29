<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  	<form action="admin/add_Comment" method="post"><%--
  	  <input type="hidden" name="comment.book.bookId" value="${bookId }" />
  	  --%><table>
  	  	<tr><td>内容：</td><td><input name="comment.content" type="text"/></td></tr>
  	  	<tr><td> </td><td><input id="submitComment" type="submit" value="提交"/></td></tr>
  	  </table>
  	</form>
