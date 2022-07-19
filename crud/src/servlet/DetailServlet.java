package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: servlet.DetailServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-19 12:39
 */
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        PrintWriter out = resp.getWriter();
        ResultSet rs;
        PreparedStatement ps;
        Connection conn = null;
        out.print("<!DOCTYPE html>");
        out.print("<html lang=\"en\">");
        out.print("<head>");
        out.print("<meta charset=\"UTF-8\">");
        out.print("<title>详情页面</title>");
        out.print("</head>");
        out.print("<body>");
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT dept_no,dept_name,dept_loc FROM dept WHERE id = ?");
            ps.setString(1,id);
            rs = ps.executeQuery();
            if (rs.next()){
                out.print("部门编号:" + rs.getString(1));
                out.print("<br>部门名称:" + rs.getString(2));
                out.print("<br>部门地址:" + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print("<br><input type=\"button\" value=\"返回\" onclick='window.history.back()'/>");
        out.print("</body>");
        out.print("</html>");

    }
}
