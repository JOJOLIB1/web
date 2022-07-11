import jakarta.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @className: PACKAGE_NAME.HelloServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-11 11:00
 */
public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig)
            throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        PrintWriter out = servletResponse.getWriter();
        servletResponse.setContentType("text/html;charset=utf-8");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://43.142.25.180:3306/test","root","Hello_world_123!!~~~");
            PreparedStatement ps = conn.prepareStatement("SELECT name,phone FROM user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.print("<h2>" +rs.getString(1) + "," + rs.getString(2) + "</h2>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
