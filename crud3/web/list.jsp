<%@ page import="bean.Dept" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/23
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表页面</title>
</head>
<body>
    <script type="text/javascript">
        del = function (id) {
            if (window.confirm("亲,真的要删除嘛,删除不可恢复哦!")) {
                window.location.href = "<%=request.getContextPath()%>/dept/del?id=" + id;
            }
        }
    </script>
    <table align="center">
        <tr>
            <th>记录编号</th>
            <th>部门地址</th>
            <th>操作</th>
        </tr>
        <%
            ArrayList<Dept> depts = (ArrayList<Dept>)request.getAttribute("depts");
            for (Dept dept : depts) {
        %>
        <tr>
            <td><%=dept.getId()%></td>
            <td><%=dept.getDeptLoc()%></td>
            <td>
                <a href="javascript:void(0)" onclick="del(<%=dept.getId()%>)">删除数据</a>&nbsp;&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/dept/detail?id=<%=dept.getId()%>&f=modify">修改数据</a>&nbsp;&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/dept/detail?id=<%=dept.getId()%>&f=detail">数据详情</a>&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <center><a href="/crudp/add.jsp">添加数据</a></center>
</body>
</html>
