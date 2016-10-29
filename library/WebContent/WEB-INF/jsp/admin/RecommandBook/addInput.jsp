<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

    <form action="admin/add_Announcement" method="post">
  	<table>
  	  <caption>我要推荐：</caption>
  	  <tr>
  	    <td>书名：</td>
  	    <td>
			<textarea cols="80" id="editor1" name="announcement.content" rows="10"></textarea>
			<input type="submit" value="Submit"/>
		</td>
  	  </tr>
  	  
  	  <tr>
  	    <td>作者：</td>
  	    <td>
			<textarea cols="80" id="editor1" name="announcement.content" rows="10"></textarea>
			<input type="submit" value="Submit"/>
		</td>
  	  </tr>
  	  
  	  <tr>
  	    <td>出版社：</td>
  	    <td>
			<textarea cols="80" id="editor1" name="announcement.content" rows="10"></textarea>
			<input type="submit" value="Submit"/>
		</td>
  	  </tr>
  	  
  	  <tr>
  	    <td>出版时间：</td>
  	    <td>
			<textarea cols="80" id="editor1" name="announcement.content" rows="10"></textarea>
			<input type="submit" value="Submit"/>
		</td>
  	  </tr>
  	</table>
  	</form>
