<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加数据页面</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/dept/add" method="post">
        部门编号:<input type="text" name="dept_no"><br>
        部门名称:<input type="text" name="dept_name"><br>
        部门地址:<input type="text" name="dept_loc"><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="确认添加">
    </form>
</body>
</html>
