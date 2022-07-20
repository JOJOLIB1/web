package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @className: servlet.List
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-19 11:14
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getContextPath();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html lang=\"en\">");
        out.print("<head>");
        out.print("<script>");
        out.print("del = function (delNo) {");
        out.print("if (window.confirm('吊毛,你真的想删嘛?')) {");
        out.print("window.location.href = '"+path+"/del?id='+delNo;}");
        out.print("}");
        out.print("</script>");
        out.print("<meta charset=\"UTF-8\">");
        out.print("<title>列表</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<table align='center' border=\"1px\" width=\"50%\">");
        out.print("<tr>");
        out.print("<th>编号</th>");
        out.print("<th>部门名字</th>");
        out.print("<th>操作</th>");
        out.print("</tr>");
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            System.out.println(conn);
            ps = conn.prepareStatement("SELECT id,dept_name FROM dept");
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String dName = rs.getString(2);
                out.print("<tr>");
                out.print("<td>"+id+"</td>");
                out.print("<td>"+dName+"</td>");
                out.print("<td>");
                out.print("<a href='javascript:void(0)' onclick='del("+id+")'>删除</a>\n");
                out.print(" <a href=\""+path+"/upGe?id="+id+"\">修改</a>");
                out.print(" <a href=\""+path+"/detail?id="+id+"\">详情</a>");
                out.print("</td>");
                out.print("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        out.print("</table>");
        out.print("<a href=\""+path+"/page/create.html\">添加页面</a>");
        out.print("</body>");
        out.print("</html>");
    }
}
