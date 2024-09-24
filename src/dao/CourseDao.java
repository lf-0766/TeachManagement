package dao;

import model.Course;
import util.DbUtil;

import java.sql.*;

public class CourseDao {
    public static ResultSet getCourseList() throws SQLException {
        String sql = "select * from course";
        Statement st = (DbUtil.getConnection()).createStatement();
        return st.executeQuery(sql);
    }

    public static Course getCourse(String CNo) {
        Course course = null;
        String sql = "SELECT * FROM Course WHERE CNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, CNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = new Course(rs.getString("CNo"), rs.getString("CName"), rs.getString("CCredit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public static ResultSet searchCourseByQuery(String CNo, String CName) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT CNo as 课程编号, CName as 课程名称 ,CCredit as 学分 FROM course WHERE 1=1";
        if (CNo != null && !CNo.isEmpty()) {
            sql += " AND CNo = ?";
        }
        if (CName != null && !CName.isEmpty()) {
            sql += " AND CName LIKE ?";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        int index = 1;
        if (CNo != null && !CNo.isEmpty()) {
            ps.setString(index++, CNo);
        }
        if (CName != null && !CName.isEmpty()) {
            ps.setString(index++, "%" + CName + "%");
        }
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static int addCourse(String CNo, String CName,String CCredit) {
        // 在Course表中查询是否已存在该课程记录
        String sqlCheck = "SELECT * FROM Course WHERE CNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, CNo);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                // 如果已存在该记录，则不能再添加该课程，返回1表示已存在
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }

        // 在Course表中插入CNo和CName
        String sqlInsert = "INSERT INTO Course (CNo, CName,CCredit) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
            psInsert.setString(1, CNo);
            psInsert.setString(2, CName);
            psInsert.setString(3, CCredit);
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

    public static int deleteCourse(String CNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT * FROM course WHERE CNo=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, CNo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return 1; // 课程不存在
            }
            sql = "DELETE FROM course WHERE CNo=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, CNo);
            int count = ps.executeUpdate();
            return count > 0 ? 0 : 2; // 成功删除，返回0；其他错误，返回2
        } catch (SQLException e) {
            e.printStackTrace();
            return 2; // 其他错误
        } finally {
            DbUtil.closeConnection(conn);
        }
    }

    public static int alterCourse(String CNo, String CName, String credit) throws SQLException {
        // 在Course表中查询是否已经存在该课程记录
        String sqlCheck = "SELECT * FROM Course WHERE CNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, CNo);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                // 如果不存在该记录，则不能修改该课程，返回1表示不存在该课程
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他数据库问题
            return 2;
        }

        // 在Course表中修改课程信息
        String sqlUpdate = "UPDATE Course SET CName = ?, CCredit = ? WHERE CNo = ?";
        Connection conn = DbUtil.getConnection();
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setString(1, CName);
        psUpdate.setString(2, credit);
        psUpdate.setString(3, CNo);
        int count = psUpdate.executeUpdate();
        if (count == 1) {
            // 成功修改，返回0
            return 0;
        }
        return 0;
    }


}
