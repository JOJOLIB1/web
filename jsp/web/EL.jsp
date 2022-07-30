<%@ page import="jsp.Demo" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/7/30
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL 表达式练习</title>
</head>
<body>
    <%
        request.setAttribute("abc","abc");
    %>
    1.${abc}<br>
    <%
        request.setAttribute("demo",new Demo("abc"));
    %>
    2.${demo.attribute}<br>
    3.${demo["attribute"]}<br>
    <%
        HashMap<String, String> hashMap = new HashMap<>();
        request.setAttribute("map",hashMap);
        hashMap.put("key","value");
    %>
    4.${map.key}<br>
    5.${map["key"]}<br>
    <%
        request.setAttribute("array",new String[] {"string[]"});
        ArrayList<String> strings = new ArrayList<>();
        request.setAttribute("list",strings);
    %>
    6.${list[0]}<br>
    7.${array[0]}<br>
    8:${"写了等于没学"}<br>

    get请求参数第一个:${param.key}
    get请求多个参数:${paramValues.key}
    ${initParam.key}
    ${pageContext.request.contextPath}

</body>
</html>
