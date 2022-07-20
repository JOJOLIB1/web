package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @className: servlet.CreateServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-20 12:21
 */
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.取到待添加的数据
        String dept_no = request.getParameter("dept_no");
        String dept_name = request.getParameter("dept_name");
        String dept_loc = request.getParameter("dept_loc");
        // 2.连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("INSERT INTO dept(dept_no,dept_name,dept_loc) VALUES(?,?,?)");
            ps.setString(1,dept_no);
            ps.setString(2,dept_name);
            ps.setString(3,dept_loc);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps);
        }
        // 3.判断是否成功
        if (update == 1) {
            request.getRequestDispatcher("/servlet/list").forward(request,response);
        }else {
            request.getRequestDispatcher("/page/Error.html").forward(request,response);
        }
    }
}
