<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/30
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL</title>
</head>
<body>
    <c:choose>
        <c:when test="${param.key > 10}">
            >10的存在
        </c:when>
        <c:when test="${param.key > 20}">
            >20的存在
        </c:when>
        <c:otherwise>
            其他存在
        </c:otherwise>
    </c:choose>

    <c:if test="${param.key > 10}">
        var scope分别指变量名和该变量存储的域
    </c:if>

    <c:forEach begin="1" end="12" step="3" var="a" varStatus="aS" >
        ${a},${aS.count}<br>
    </c:forEach>

</body>
</html>
