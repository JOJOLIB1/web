package http;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * @className: http.RequestTestServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-16 21:41
 */
public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Map<String, String[]> map = req.getParameterMap();
        for (var item : map.entrySet()) {
            out.print(item.getKey() + Arrays.toString(item.getValue()));
        }
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            out.print(name + Arrays.toString(req.getParameterValues(name)));
        }/*
        while (names.hasMoreElements()) {
            String name  = names.nextElement();
            System.out.println(name + req.getParameter(name));
        }*/
    }
}
