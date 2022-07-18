package http;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @className: http.RequestTest02
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-18 11:36
 */
public class RequestTest02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 2
        req.setAttribute("key","value");
        req.setAttribute("alin","bok");
        req.removeAttribute("alin");
        req.getRequestDispatcher("/t3").forward(req,resp);
    }
}
