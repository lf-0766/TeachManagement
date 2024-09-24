package dao;

import model.Student;
import util.DbUtil;

import java.sql.*;

public class StudentDao {

    public static Student studentLogin(String uid, String pwd) {
        String sql = "SELECT * FROM Student WHERE SNo = ? AND pwd = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, uid);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // 如果查询结果集中有记录，则说明验证通过，返回Student对象
                return new Student(rs.getString("SNo"), rs.getString("SName"), rs.getString("SSex"),
                        rs.getString("SClass"), rs.getString("pwd"), rs.getInt("enrollYear"));
            } else {
                // 否则，返回null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 抛出异常，返回null
            return null;
        }
    }
    public static ResultSet getStudentList() throws SQLException {
        String sql = "select * from student";
        Statement st = (DbUtil.getConnection()).createStatement();
        return st.executeQuery(sql);
    }

    public static Student getStudent(String SNo) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT * FROM student WHERE SNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, SNo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // 学号存在，返回学生对象
            Student student = new Student();
            student.setSNo(rs.getString("SNo"));
            student.setSName(rs.getString("SName"));
            student.setSSex(rs.getString("SSex"));
            student.setSClass(rs.getString("SClass"));
            student.setSPwd(rs.getString("pwd"));
            student.setEnrollYear(rs.getInt("enrollYear"));
            return student;
        } else {
            // 学号不存在，抛出异常或返回错误信息
            throw new SQLException("学号不存在");
        }
    }

    public static int addStudent(String SNo, String SName, String SSex, String SClass, String pwd, int enrollYear) {
// 在Student表中查询是否已存在该学生记录
        String sqlCheck = "SELECT * FROM Student WHERE SNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, SNo);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
// 如果已存在该记录，则不能再添加该学生，返回1表示已存在
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
// 抛出异常，返回2表示其他数据库问题
            return 2;
        }

// 在Student表中插入SNo、SName、SSex、SClass、pwd和enrollYear
        String sqlInsert = "INSERT INTO Student (SNo, SName, SSex, SClass, pwd, enrollYear) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
            psInsert.setString(1, SNo);
            psInsert.setString(2, SName);
            psInsert.setString(3, SSex);
            psInsert.setString(4, SClass);
            psInsert.setString(5, pwd);
            psInsert.setInt(6, enrollYear);
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

    public static int alterStudent(String SNo, String SName, String SSex, String SClass, String pwd, int enrollYear) {
// 在Student表中查询是否已经存在该学生记录
        String sqlCheck = "SELECT * FROM Student WHERE SNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, SNo);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
// 如果不存在该记录，则不能修改该学生，返回1表示不存在该学生
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
// 抛出异常，返回2表示其他数据库问题
            return 2;
        }

// 在Student表中修改学生信息
        String sqlUpdate = "UPDATE Student SET SName = ?, SSex = ?, SClass = ?, pwd = ?, enrollYear = ? WHERE SNo = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
            psUpdate.setString(1, SName);
            psUpdate.setString(2, SSex);
            psUpdate.setString(3, SClass);
            psUpdate.setString(4, pwd);
            psUpdate.setInt(5, enrollYear);
            psUpdate.setString(6, SNo);
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

    public static void alterStudentPwd(Student student, String oldPwd) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT * FROM student WHERE SNo = ? AND pwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getSNo());
        ps.setString(2, oldPwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // 验证通过，更新密码
            sql = "UPDATE student SET pwd = ? WHERE SNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getSPwd());
            ps.setString(2, student.getSNo());
            ps.executeUpdate();
        } else {
            // 验证失败，抛出异常或返回错误信息
            throw new SQLException("原密码错误");
        }
    }

    public static String getStudentPwd(String SNo) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT pwd FROM student WHERE SNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, SNo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // 学号存在，返回密码
            return rs.getString("pwd");
        } else {
            // 学号不存在，抛出异常或返回错误信息
            throw new SQLException("学号不存在");
        }
    }

    public static ResultSet searchStudentByQuery(String SNo, String SName) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT SNo as 学号, SName AS 姓名, SSex as 性别, SClass as 班级, enrollYear as 入学年份 FROM student WHERE 1=1";
        if (SNo != null && !SNo.isEmpty()) {
            sql += " AND SNo = ?";
        }
        if (SName != null && !SName.isEmpty()) {
            sql += " AND SName LIKE ?";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        int index = 1;
        if (SNo != null && !SNo.isEmpty()) {
            ps.setString(index++, SNo);
        }
        if (SName != null && !SName.isEmpty()) {
            ps.setString(index++, "%" + SName + "%");
        }
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static int deleteStudent(String SNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT * FROM student WHERE SNo=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, SNo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return 1; // 学生不存在
            }
            sql = "DELETE FROM student WHERE SNo=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, SNo);
            int count = ps.executeUpdate();
            return count > 0 ? 0 : 2; // 成功删除，返回0；其他错误，返回2
        } catch (SQLException e) {
            e.printStackTrace();
            return 2; // 其他错误
        } finally {
            DbUtil.closeConnection(conn);
        }
    }


}
