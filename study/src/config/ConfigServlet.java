package config;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @className: config.ConfigServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-12 15:40
 */
public class ConfigServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = servletResponse.getWriter();
        ServletConfig config = this.getServletConfig();
        Enumeration<String> names = config.getInitParameterNames();
        while (names.hasMoreElements()) {
            String element = names.nextElement();
            out.print(config.getInitParameter(element));
        }
    }
}
