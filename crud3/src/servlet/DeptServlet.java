package servlet;

import bean.Dept;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: servlet.DeptServlet
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-23 20:37
 */
@WebServlet({"/dept/list", "/dept/detail", "/dept/modify", "/dept/add", "/dept/del"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestServletPath = request.getServletPath();
        if ("/dept/list".equals(requestServletPath)) {
            doList(request, response);
        }else if ("/dept/detail".equals(requestServletPath)) {
            doDetail(request, response);
        }else if ("/dept/modify".equals(requestServletPath)) {
            doModify(request, response);
        }else if ("/dept/add".equals(requestServletPath)) {
            doAdd(request, response);
        }else if ("/dept/del".equals(requestServletPath)) {
            doDel(request, response);
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM dept WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps);
        }
        if (update == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deptName = request.getParameter("dept_name");
        String deptNo = request.getParameter("dept_no");
        String deptLoc = request.getParameter("dept_loc");
        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO dept(dept_name,dept_no,dept_loc) VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptName);
            ps.setString(2,deptNo);
            ps.setString(3,deptLoc);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps);
        }
        if (update == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String id = request.getParameter("id");
        String deptName = request.getParameter("dept_name");
        String deptNo = request.getParameter("dept_no");
        String deptLoc = request.getParameter("dept_loc");
        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE dept SET dept_name = ?, dept_no = ?, dept_loc = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptName);
            ps.setString(2,deptNo);
            ps.setString(3,deptLoc);
            ps.setString(4,id);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps);
        }
        if (update == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dept dept = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT dept_no,dept_name,dept_loc FROM dept WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int deptNo = rs.getInt(1);
                String deptName = rs.getString(2);
                String deptLoc = rs.getString(3);
                dept = new Dept(Integer.parseInt(id), deptNo, deptName, deptLoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("deOne",dept);
        request.getRequestDispatcher("/" + request.getParameter("f") + ".jsp").forward(request, response);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dept> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT id,dept_loc FROM dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String deptLoc = rs.getString(2);
                Dept dept = new Dept(id, deptLoc);
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("depts",list);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
