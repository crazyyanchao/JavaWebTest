<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <form action="admin/update_Announcement" method="post">
    <input type="hidden" name="announcement.announcementId" value="${announcement.announcementId}">
  	<table>
  	  <caption>添加公告</caption>
  	  <tr>
  	    <td>公告：</td>
  	    <td>
			<textarea cols="80" id="editor1" name="announcement.content" rows="10">
				${announcement.content}
			</textarea>
			<input type="submit" value="Submit"/>
		</td>
  	  </tr>
  	</table>
  	</form>
	<ckeditor:replace  replace="editor1" basePath="ckeditor/" />