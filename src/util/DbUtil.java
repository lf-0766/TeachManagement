package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    // MySQL 数据库连接参数
    static String dbName = "teach_manage";

    private static final String URL = "jdbc:mysql://localhost:3306/"+dbName
            +"?serverTimezone=GMT%2B8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
//    private static final String URL = "jdbc:mysql://rm-cn-uax38ws1x000l6lo.rwlb.rds.aliyuncs.com:3306/"+dbName
//            +"?serverTimezone=GMT%2B8&useSSL=false";
//    private static final String USER = "jukspa";
//    private static final String PASSWORD = "K8u6M5e3";
    public static  String  getDbName(){
        return dbName;
    }
    public static  String  getDbUser(){
        return USER;
    }
    public static  String  getDbPwd(){
        return PASSWORD;
    }
    public static String getUrl(){return URL;}
    // 加载 MySQL 数据库驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取 MySQL 数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 关闭 MySQL 数据库连接
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}