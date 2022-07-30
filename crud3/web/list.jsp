<%@ page import="bean.Dept" %>
<%@ page import="java.util.ArrayList" %><%--

  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>列表页面</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
    <script type="text/javascript">
        del = function (id) {
            if (window.confirm("亲,真的要删除嘛,删除不可恢复哦!")) {
                window.location.href = "${pageContext.request.contextPath}/dept/del?id=" + id;
            }
        }
    </script>
    <h3 align="center">欢迎${username}回来,当前在线人数:${count}</h3>
    <table align="center">
        <tr>
            <th>记录编号</th>
            <th>部门地址</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${depts}" var="dept">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.deptLoc}</td>
                <td>
                    <a href="javascript:void(0)" onclick="del(${dept.id})">删除数据</a>&nbsp;&nbsp;&nbsp;
                    <a href="dept/detail?id=${dept.id}&f=modify">修改数据</a>&nbsp;&nbsp;&nbsp;
                    <a href="dept/detail?id=${dept.id}&f=detail">数据详情</a>&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
    <center><a href="add.jsp">添加数据</a></center>
    <center><a href="user/logout">安全退出</a></center>
</body>
</html>
