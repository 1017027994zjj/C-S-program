package Toolclass;

/**
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator
 *
 */
public class DBUtil {
    // 定义数据库连接参数,连接至云服务器数据库中
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://47.95.200.90:3306/takeaway_platform?serverTimezone=GMT";
    public static final String USERNAME = "takeaway_platform";
    public static final String PASSWORD = "311214";

    // 注册数据库驱动
    static {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("注册失败！");
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // 关闭连接
    public static void closeConn(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
                e.printStackTrace();
            }
        }
    }

    //测试
    public static void main(String[] args) throws SQLException {
        System.out.println(DBUtil.getConn());
    }

}