<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
$().ready(function(){
	$(".modify").click(function(){
		$("this").value="提交";
		var parent=$(this).parent();
		var node=parent.children(".first").not("input");
		var text=node.html();
		node.html("<input value="+text+">");
		node=parent.children(".second");
		text=node.html();
		node.html("<input value="+text+">");
	});
});
</script>
</head>
<body>
<table>
<tr>
<td class="first">aaaaaaa</td>
<td class="second">bbb</td>
<td class="modify"><input type="button" value="修改"/></td>
</tr>
<tr>
<td>aaaaaaa</td>
<td>aaaaaaa</td>
<td class="modify"><input type="button" value="修改"/></td>
</tr>
<tr>
<td>aaaaaaa</td>
<td>aaaaaaa</td>
<td class="modify">aaaaaaa</td>
</tr>
</table>
</body>
</html>