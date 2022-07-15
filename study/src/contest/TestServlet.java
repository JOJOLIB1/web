package contest;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @className: contest.TestServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-12 22:01
 */
public class TestServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = servletResponse.getWriter();
        ServletContext application = this.getServletContext();
        Enumeration<String> names = application.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            out.print(name + "=" + application.getInitParameter(name) + "<br>");
        }
        String parameter = this.getInitParameter("key00");
        out.print(parameter);
    }
}
