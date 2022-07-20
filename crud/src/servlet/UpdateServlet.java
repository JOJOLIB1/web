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
 * @className: servlet.UpdateServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-20 12:50
 */
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.获取待修改数据
        // 命名有问题 - 懒得改
        String id = request.getParameter("id");
        String dept_no = request.getParameter("dept_no");
        String dept_name = request.getParameter("dept_name");
        String dept_loc = request.getParameter("dept_loc");
        // 2.连接数据库进行修改
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("UPDATE dept SET dept_no = ?,dept_name = ?,dept_loc = ? WHERE id = ?");
            ps.setString(1,dept_no);
            ps.setString(2,dept_name);
            ps.setString(3,dept_loc);
            ps.setString(4,id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 3.判断
        if (count == 1) {
            request.getRequestDispatcher("/servlet/list").forward(request,response);
        }else {
            request.getRequestDispatcher("/page/Error.html").forward(request,response);
        }
    }
}
