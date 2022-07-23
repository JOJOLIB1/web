import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * @className: PACKAGE_NAME.CRUDServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-22 10:02
 */
@WebServlet({"/create","/servlet/list","/update","/upGe","/detail","/del"})
public class CRUDServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/create".equals(path)) {
            doCreate(request, response);
        }else if ("/servlet/list".equals(path)) {
            doList(request, response);
        }else if ("/update".equals(path)) {
            doUpdate(request, response);
        }else if ("/upGe".equals(path)) {
            doUpGe(request, response);
        }else if ("/detail".equals(path)) {
            doDetail(request, response);
        }else if ("/del".equals(path)) {
            doDel(request, response);
        }
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String dept_no = request.getParameter("dept_no");
        String dept_name = request.getParameter("dept_name");
        String dept_loc = request.getParameter("dept_loc");
        String path = request.getContextPath();
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
            response.sendRedirect(path + "/servlet/list");
        } else {
            response.sendRedirect( path + "/page/Error.html");
        }
    }
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String path = request.getContextPath();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
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
    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 1.获取待修改数据
        // 命名有问题 - 懒得改
        String id = request.getParameter("id");
        String dept_no = request.getParameter("dept_no");
        String dept_name = request.getParameter("dept_name");
        String dept_loc = request.getParameter("dept_loc");
        String path = request.getContextPath();
        // 2.连接数据库进行修改
        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("UPDATE dept SET dept_no = ?,dept_name = ?,dept_loc = ? WHERE id = ?");
            ps.setString(1,dept_no);
            ps.setString(2,dept_name);
            ps.setString(3,dept_loc);
            ps.setString(4,id);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 3.判断
        if (update == 1) {
            response.sendRedirect(path + "/servlet/list");
        } else {
            response.sendRedirect( path + "/page/Error.html");
        }
    }
    private void doUpGe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
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
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        ResultSet rs = null;
        PreparedStatement ps = null;
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
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        out.print("<br><input type=\"button\" value=\"返回\" onclick='window.history.back()'/>");
        out.print("</body>");
        out.print("</html>");
    }
    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 1.获取待删除的id
        String id = request.getParameter("id");
        // 2.连接收据库,进行删除
        Connection conn = null;
        PreparedStatement ps = null;
        RequestDispatcher rd = null;
        int update = 0;
        String path = request.getContextPath();
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("DELETE FROM dept WHERE id = ?");
            ps.setString(1,id);
            update = ps.executeUpdate();
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
        if (update == 1) {
            response.sendRedirect(path + "/servlet/list");
        } else {
            response.sendRedirect( path + "/page/Error.html");
        }
    }
}
