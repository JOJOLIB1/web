<%@ page import="bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Dept dept = (Dept)request.getAttribute("deOne");
%>
<html>
<head>
    <title>详情页面</title>
</head>1
<body>
    <hr>
    编号:&nbsp;&nbsp;<%=dept.getId()%>
    部门编号:<%=dept.getDeptNo()%>
    部门名字:<%=dept.getDeptName()%>
    部门地址:<%=dept.getDeptLoc()%>
    <hr>
    <center><a href="<%=request.getContextPath()%>/dept/list">返回</a></center>
</body>
</html>
