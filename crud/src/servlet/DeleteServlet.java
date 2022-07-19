package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: servlet.DeleteServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-19 20:38
 */
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取待删除的id
        String id = req.getParameter("id");
        // 2.连接收据库,进行删除
        Connection conn = null;
        PreparedStatement ps = null;
        RequestDispatcher rd = null;
        int reflectCount = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("DELETE FROM dept WHERE id = ?");
            ps.setString(1,id);
            reflectCount = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.close(conn,ps);
        }
        // 3.其他判断
        if (reflectCount == 1) {
            rd = req.getRequestDispatcher("/servlet/list");
        }else {
            rd = req.getRequestDispatcher("/page/Error.html");
        }
        rd.forward(req,resp);
    }
}
