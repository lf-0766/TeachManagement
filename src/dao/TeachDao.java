package dao;

import model.Course;
import util.DbUtil;

import java.sql.*;

public class TeachDao {
    public static ResultSet getTeach(Course course,String SNo) throws SQLException {
        String CName = course.getCName();
        String CNo = course.getCNo();
        String sql = "SELECT c.CNo, c.CName, t.TName, tc.CRoom, tc.CTime " +
                "FROM course c INNER JOIN teach tc ON c.CNo = tc.CNo " +
                "INNER JOIN teacher t ON tc.TNo = t.TNo WHERE NOT EXISTS (\n" +
                "SELECT 1 FROM score s WHERE s.CNo = c.CNo and SNo = "+SNo+"\n" +
                ")";
        if (CName != null && !CName.equals("")) {
            sql += "AND c.CName = ? ";
        } else if (CNo != null && !CNo.equals("")) {
            sql += "AND c.CNo = ? ";
        }
        Connection conn = DbUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (CName != null && !CName.equals("")) {
            ps.setString(1, CName);
        } else if (CNo != null && !CNo.equals("")) {
            ps.setString(1, CNo);
        }
        return ps.executeQuery();
    }

    public static ResultSet getTeachByTNo(String TNo) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT c.CNo, c.CName, tc.CRoom, tc.CTime " +
                "FROM teach tc " +
                "INNER JOIN course c ON c.CNo = tc.CNo " +
                "INNER JOIN teacher t ON t.TNo = tc.TNo " +
                "WHERE t.TNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, TNo);
        return ps.executeQuery();
    }

    public static ResultSet searchCourseByQuery(String TNo, String CNo, String CName) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT c.CNo, c.CName, tc.CRoom, tc.CTime " +
                "FROM teach tc " +
                "INNER JOIN course c ON c.CNo = tc.CNo " +
                "INNER JOIN teacher t ON t.TNo = tc.TNo " +
                "WHERE 1=1";
        if (TNo != null && !TNo.trim().isEmpty()) {
            sql += " AND t.TNo = '" + TNo + "'";
        }
        if (CNo != null && !CNo.trim().isEmpty()) {
            sql += " AND c.CNo = '" + CNo + "'";
        }
        if (CName != null && !CName.trim().isEmpty()) {
            sql += " AND c.CName LIKE '%" + CName + "%'";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    public static int openCourse(String TNo, String CName, String CRoom, String CTime) {
        // 在Course表中查找是否有对应课程
        String sqlSelect = "SELECT CNo FROM Course WHERE CName = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
            psSelect.setString(1, CName);
            ResultSet rs = psSelect.executeQuery();
            if (!rs.next()) {
                // 如果没有对应课程，则无法开课，返回1
                return 1;
            }
            String CNo = rs.getString("CNo");
            rs.close();

            // 在Teach表中插入TNo、CNo、CRoom和CTime
            String sqlInsert = "INSERT INTO Teach (TNo,  CNo, CRoom, CTime) VALUES ( ?, ?, ?, ?)";
            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                psInsert.setString(1, TNo);
                psInsert.setString(2, CNo);
                psInsert.setString(3, CRoom);
                psInsert.setString(4, CTime);
                int count = psInsert.executeUpdate();
                if (count == 1) {
                    // 成功插入，返回0                return 0;
                } else {
                    // 插入失败，返回2表示其他错误
                    return 2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回2表示其他错误
            return 2;
        }
        return 0;
    }

    public static int closeCourse(String TNo, String Cno) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "DELETE FROM teach WHERE TNo = ? AND CNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, TNo);
        ps.setString(2, Cno);
        int result = ps.executeUpdate();
        if (result == 0) {
            return 2;
        } else {
            return 0;
        }
    }


}
