package util;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @className: util.DBUtil
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-17 23:12
 */
public class DBUtil {
    public static DataSource dataSource = null;
    static {
        // 配置差一个数据库
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public static void close(Connection conn, Statement statement) {
        try {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn, Statement statement, ResultSet resultSet) {
        try {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
