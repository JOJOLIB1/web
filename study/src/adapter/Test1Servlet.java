package adapter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * @className: PACKAGE_NAME.TestServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-11 21:44
 */
public class Test1Servlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("test1.....");
    }
}
