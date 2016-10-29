<%@ page language="java" pageEncoding="gb2312" import="org.springframework.context.*,org.springframework.context.support.*,org.easybooks.bookstore.dao.*,org.easybooks.bookstore.dao.impl.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
	</head>
	<body>
		<%
			String usr=request.getParameter("username");//��ȡ�ύ������
			String pwd=request.getParameter("password");//��ȡ�ύ������
			boolean validated=false;//��֤�ɹ���ʶ
			ApplicationContext context=new FileSystemXmlApplicationContext("/jsp_spring_dao_hibernate/src/applicationContext.xml");
			IUserDAO userDAO=(IUserDAO)context.getBean("userDAO");
			//ֱ��ʹ�ó־ò��װ���˵���֤����						
			if(userDAO.validateUser(usr,pwd)!=null)
			{
				validated=true;//��ʶΪtrue��ʾ��֤�ɹ�ͨ��
			}
			if(validated)
			{
				//��֤�ɹ���ת��welcome.jsp
		%>
				<jsp:forward page="welcome.jsp"/>
		<%
			}
			else
			{
				//��֤ʧ����ת��error.jsp
		%>
				<jsp:forward page="error.jsp"/>
		<%
			}
		%>
	</body>
</html>


