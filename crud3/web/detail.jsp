<%@ page import="bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情页面</title>
</head>
<body>
    <hr>
    编号:&nbsp;&nbsp;${deOne.id}
    部门编号:${deOne.deptNo}
    部门名字:${deOne.deptName}
    部门地址:${deOne.deptLoc}
    <hr>
    <center><a href="${pageContext.request.contextPath}/dept/list">返回</a></center>
</body>
</html>
