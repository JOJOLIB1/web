package util;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;

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
        DruidDataSourceFactory.createDataSource();
    }
}
