<%@ page language="java" pageEncoding="gb2312" import="org.springframework.context.*,org.springframework.context.support.*,org.easybooks.bookstore.dao.*,org.easybooks.bookstore.dao.impl.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
	</head>
	<body>
		<%
			String usr=request.getParameter("username");//获取提交的姓名
			String pwd=request.getParameter("password");//获取提交的密码
			boolean validated=false;//验证成功标识
			ApplicationContext context=new FileSystemXmlApplicationContext("/jsp_spring_dao_hibernate/src/applicationContext.xml");
			IUserDAO userDAO=(IUserDAO)context.getBean("userDAO");
			//直接使用持久层封装好了的验证功能						
			if(userDAO.validateUser(usr,pwd)!=null)
			{
				validated=true;//标识为true表示验证成功通过
			}
			if(validated)
			{
				//验证成功跳转到welcome.jsp
		%>
				<jsp:forward page="welcome.jsp"/>
		<%
			}
			else
			{
				//验证失败跳转到error.jsp
		%>
				<jsp:forward page="error.jsp"/>
		<%
			}
		%>
	</body>
</html>


