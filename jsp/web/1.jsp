<%@ page import="jsp.Test" %>
<%@page contentType="text/html" pageEncoding="utf-8" %>
<%
    Test test = new Test(1);
    request.setAttribute("abc",test);
%>
${abc.t1}