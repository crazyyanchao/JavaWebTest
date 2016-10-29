<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-9-20
  Time: 上午7:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>成功页面</title>
</head>
<body>
<h2>
<ceter>
<%out.print(request.getParameter("username"));%>
    您好！欢迎光临叮当书店！
</ceter>
    </h2>
</body>
</html>
