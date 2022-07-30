<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>后台页面</title>
  </head>
  <body>
    <H1 ALIGN="center">欢迎使用后台页面</H1>
    <h2 align="center">登录</h2>
    <hr>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
      <center>用户名:<input type="text" name="username"></center><br>
      <center>密码:&nbsp;&nbsp;&nbsp;<input type="password" name="userpwd"></center><br>
      <center><input type="submit" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;是否1分钟免登录<input type="radio" name="pass" value="true"></center>
    </form>
    <hr>
  </body>
</html>
