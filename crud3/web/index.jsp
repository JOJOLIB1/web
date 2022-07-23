<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String contextPath = request.getContextPath();
%>
<html>
  <head>
    <title>后台页面</title>
  </head>
  <body>
    <H1 ALIGN="center">欢迎使用后台页面</H1>
    <hr>
    <center><a href="<%=contextPath%>/dept/list">查看列表</a></center>
    <hr>
  </body>
</html>
