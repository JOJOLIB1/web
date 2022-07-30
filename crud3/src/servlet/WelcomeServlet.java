package servlet;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: bean.WelcomeServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-25 21:49
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String userpwd = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }else if ("userpwd".equals(cookie.getName())) {
                    userpwd = cookie.getValue();
                }
            }
        }
        if (username != null && userpwd != null) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean login = false;
            try {
                conn = DBUtil.getConnection();
                String sql = "SELECT * FROM user WHERE username = ? AND userpwd = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, userpwd);
                rs = ps.executeQuery();
                login = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn, ps, rs);
            }
            if (login) {
                HttpSession session = request.getSession();
                User user = new User(username,userpwd);
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
