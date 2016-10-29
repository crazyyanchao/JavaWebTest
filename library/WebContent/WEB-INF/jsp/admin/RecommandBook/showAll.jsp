<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
  	<table>
  	  <caption>公告列表</caption>
  	  <tr><th width="80px">公告编号</th><th width="500px">公告内容</th><th width="100px">操作</th></tr>
  	  <c:forEach  items="${pageInfo.datas}" var="obj" varStatus="s">
  	  	<tr><td>${obj.announcementId }</td><td>${obj.content }</td>
  	  	<td><a href="admin/updateInput_Announcement?announcement.announcementId=${obj.announcementId }">修改</a> 
  	  	| <a href="admin/delete_Announcement?announcement.announcementId=${obj.announcementId }">删除</a></td></tr>
  	  </c:forEach>
  	</table>
