<%@ page import="bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改页面</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/dept/modify" method="post">
        <input type="text" name="id" value="${deOne.id}" hidden>
        部门编号:<input type="text" name="dept_no" value="${deOne.deptNo}" readonly><br>
        部门名称:<input type="text" name="dept_name" value="${deOne.deptName}"><br>
        部门地址:<input type="text" name="dept_loc" value="${deOne.deptLoc}"><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="确认修改">
    </form>
</body>
</html>
