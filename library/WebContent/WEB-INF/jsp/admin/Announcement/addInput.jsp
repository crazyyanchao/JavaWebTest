<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

    <form action="admin/add_Announcement" method="post">
  	<table>
  	  <caption>添加公告</caption>
  	  <tr>
  	    <td>公告：</td>
  	    <td>
			<textarea cols="80" id="editor1" name="announcement.content" rows="10"></textarea>
			<input type="submit" value="Submit"/>
		</td>
  	  </tr>
  	</table>
  	</form>
	<ckeditor:replace  replace="editor1" basePath="ckeditor/" />