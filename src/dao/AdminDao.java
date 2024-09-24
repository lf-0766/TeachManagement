package dao;

import model.Admin;
import util.DbUtil;

import java.sql.*;

public class AdminDao {
    public static int login(Admin admin) throws SQLException {
        String username = admin.getUname();
        String password = admin.getPwd();

        // 查询数据库中是否存在该用户
        String sql = "SELECT * FROM admin WHERE uname = ? AND pwd = ?";
        Connection conn = DbUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // 如果存在该用户，返回身份类型
            return 1;
        }
        return -1;
    }

    public static ResultSet getAllUsers(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
        return rs;
    }
}
