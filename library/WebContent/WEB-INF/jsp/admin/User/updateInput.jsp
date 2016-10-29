<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<form action="admin/add_User" method="post">
  	  <table>
  	  	<caption>修改用户</caption>
  	  	<tr><td>学号：</td><td><input name="user.userNo" type="text" value="${user.password }"/></td></tr>
  	  	<tr><td>密码：</td><td><input name="user.password" value="${user.password }"/></td></tr>
  	  	<tr><td>密码：</td><td><input name="user.password1" value="${user.password1 }"/></td></tr>
  	  	<tr><td>姓名：</td><td><input name="user.name" value="${user.name }"/></td></tr>
  	  	<tr><td>余额：</td><td><input name="user.balance" value="${user.balance }"/></td></tr>
  	  	<tr><td>权限：</td><td><input name="" value=""/></td></tr>
  	  	<tr><td> </td><td><input value="重置" type="reset" /><input value="提交" type="submit" /></td></tr>
  	  </table>
  	</form>