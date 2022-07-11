import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * @className: PACKAGE_NAME.Test2Servlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-11 22:20
 */
public class Test2Servlet extends GenericServlet{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Test2 .....");
    }
}
