<%--
  Created by IntelliJ IDEA.
  User: AJG
  Date: 2019/2/15
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello SpringBoot !!!</h1>
${pageContext.request.contextPath}
<hr>
<%--<img src="${pageContext.request.contextPath}/1.jpg">--%>
<img src="${pageContext.request.contextPath}/images/2.jpg">
<img src="${pageContext.request.contextPath}/images/3.jpg">
<a href="${pageContext.request.contextPath}/add">点点我</a>
</body>
</html>
