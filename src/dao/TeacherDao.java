package dao;

import model.Student;
import model.Teacher;
import util.DbUtil;

import java.sql.*;

public class TeacherDao {
    public static Teacher teacherLogin(String uid, String pwd) throws SQLException {
        String sql = "SELECT * FROM Teacher WHERE TNo = ? AND pwd = ?";
       Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql) ;
            ps.setString(1, uid);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // 如果查询结果集中有记录，则说明验证通过，返回Teacher对象
                return new Teacher(rs.getString("TNo"), rs.getString("TName"), rs.getString("pwd"));
            } else {
                // 否则，返回null
                return null;
            }

    }

    public static ResultSet getTeacherList() throws SQLException {
        String sql = "select * from teacher";
        Statement st = (DbUtil.getConnection()).createStatement();
        return st.executeQuery(sql);
    }

    public static Teacher getTeacher(String TNo) {
        Teacher teacher = null;
        String sql = "SELECT * FROM Teacher WHERE TNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, TNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(rs.getString("TNo"), rs.getString("TName"), rs.getString("pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public static String getTNoByTName(String TName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String TNo = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT TNo FROM Teacher WHERE TName=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, TName);
            rs = ps.executeQuery();
            if (rs.next()) {
                TNo = rs.getString("TNo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeConnection(conn);
        }
        return TNo;
    }

    public static void alterTeacherPwd(Teacher Teacher, String oldPwd) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT * FROM Teacher WHERE TNo = ? AND pwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Teacher.getTNo());
        ps.setString(2, oldPwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // 验证通过，更新密码
            sql = "UPDATE Teacher SET pwd = ? WHERE TNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Teacher.getPwd());
            ps.setString(2, Teacher.getTNo());
            ps.executeUpdate();
        } else {
            // 验证失败，抛出异常或返回错误信息
            throw new SQLException("原密码错误");
        }
    }

    public static int addTeacher(String TNo, String TName, String pwd) {
        // 在Teacher表中查询是否已存在该教师记录
        String sqlCheck = "SELECT * FROM Teacher WHERE TNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, TNo);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                // 如果已存在该记录，则不能再添加该教师，返回1表示已存在
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }

        // 在Teacher表中插入TNo、TName和pwd
        String sqlInsert = "INSERT INTO Teacher (TNo, TName, pwd) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
            psInsert.setString(1, TNo);
            psInsert.setString(2, TName);
            psInsert.setString(3, pwd);
            int count = psInsert.executeUpdate();
            if (count == 1) {
                // 成功插入，返回0
                return 0;
            } else {
                // 插入失败，返回2表示其他数据库问题
                return 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }
    }
    public static int alterTeacher(String TNo, String TName, String pwd) {
        // 在Teacher表中查询是否已经存在该教师记录
        String sqlCheck = "SELECT * FROM Teacher WHERE TNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, TNo);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                // 如果不存在该记录，则不能修改该教师，返回1表示不存在该教师
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }

        // 在Teacher表中修改教师信息
        String sqlUpdate = "UPDATE Teacher SET TName = ?, pwd = ? WHERE TNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
            psUpdate.setString(1, TName);
            psUpdate.setString(2, pwd);
            psUpdate.setString(3, TNo);
            int count = psUpdate.executeUpdate();
            if (count == 1) {
                // 成功修改，返回0
                return 0;
            } else {
                // 修改失败，返回2表示其他数据库问题
                return 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }
    }
    public static int deleteTeacher(String TNo) {
        // 在Teacher表中查询是否已经存在该教师记录
        String sqlCheck = "SELECT * FROM Teacher WHERE TNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, TNo);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                // 如果不存在该记录，则不能删除该教师，返回1表示不存在该教师
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }

        // 在Teacher表中删除教师信息
        String sqlDelete = "DELETE FROM Teacher WHERE TNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psDelete = conn.prepareStatement(sqlDelete)) {
            psDelete.setString(1, TNo);
            int count = psDelete.executeUpdate();
            if (count == 1) {
                // 成功删除，返回0
                return 0;
            } else {
                // 删除失败，返回2表示其他数据库问题
                return 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }
    }
    public static ResultSet searchTeacherByQuery(String TNo, String TName) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT TNo as 工号, TName AS 姓名 FROM Teacher WHERE 1=1";
        if (TNo != null && !TNo.isEmpty()) {
            sql += " AND TNo = ?";
        }
        if (TName != null && !TName.isEmpty()) {
            sql += " AND TName LIKE ?";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        int index = 1;
        if (TNo != null && !TNo.isEmpty()) {
            ps.setString(index++, TNo);
        }
        if (TName != null && !TName.isEmpty()) {
            ps.setString(index++, "%" + TName + "%");
        }
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
