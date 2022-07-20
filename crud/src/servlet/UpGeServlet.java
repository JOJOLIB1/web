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
 * @className: servlet.UpGeServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-20 12:35
 */
public class UpGeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取待修改的编号
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String path = request.getContextPath();
        // 2.输出页面
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        out.print("<!DOCTYPE html>");
        out.print("<html lang=\"en\">");
        out.print("<head>");
        out.print("<meta charset=\"UTF-8\">");
        out.print("<title>修改页面</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<form action=\""+path+"/update\" method=\"post\">");
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT id,dept_no,dept_name,dept_loc FROM dept WHERE id = ?");
            ps.setString(1,id);
            rs = ps.executeQuery();
            if (rs.next()){
                out.print("<input type='text' name='id' value='"+rs.getString(1)+"' hidden >");
                out.print("部门编号:<input type=\"text\" name=\"dept_no\" value='"+rs.getString(2)+"' ><br>");
                out.print("部门名字:<input type=\"text\" name=\"dept_name\" value='"+rs.getString(3)+"'><br>");
                out.print("部门地址:<input type=\"text\" name=\"dept_loc\"value='"+rs.getString(4)+"'> <br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        out.print("<input type=\"submit\" value=\"修改\">");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }

}
