package util;

import javax.sql.DataSource;
import java.sql.*;


/**
 * @className: util.DBUtil
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-17 23:12
 */
public class DBUtil {
    public static DataSource dataSource = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://43.142.25.180:3306/dep_db","root","Hello_world_123!!~~~");
    }
    public static void close(Connection conn, Statement statement) {
        try {
            if (statement != null)
                statement.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn, Statement statement, ResultSet resultSet) {
        try {
            // 先关小的
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
