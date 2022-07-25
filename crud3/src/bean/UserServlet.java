package bean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: bean.UserServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-25 18:11
 */
@WebServlet({"/user/login","/user/logout"})
public class UserServlet extends HttpServlet {
    // 升级方向
    // 数据表 -> 前端页面(页面流转) -> servlet完成 -> 错误页面 -> session机制 -> 安全退出

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        }else if ("/user/logout".equals(servletPath)) {
            doLogout(request, response);
        }
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect(request.getContextPath());
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.获取信息
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        // 2.连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String path = null;
        boolean login = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND userpwd = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,userpwd);
            rs = ps.executeQuery();
            login = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        // 判断是否登录成功
        if (login) {
            path = "/dept/list";
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
        }else {
            path = "/error.jsp";
        }
        response.sendRedirect(request.getContextPath() + path);
    }
}
