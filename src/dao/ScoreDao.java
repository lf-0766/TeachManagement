package dao;

import model.Score;
import model.Student;
import util.DbUtil;

import java.sql.*;

import static util.DbUtil.getConnection;
import static util.StringUtil.isBlank;

public class ScoreDao {

    public static ResultSet getScoreByClass() throws SQLException {
        String sql = "SELECT si.SClass, AVG(s.grade) AS 平均成绩, \n" + "    MAX(s.grade) AS 最高成绩, \n" + "    MIN(s.grade) AS 最低成绩,\n" + "    SUM(CASE WHEN s.grade >= 60 THEN 1 ELSE 0 END) \n" + "    / COUNT(*) AS 及格率 \n" + "    FROM student si INNER JOIN score s ON si.SNo = s.SNo\n" + "    GROUP BY si.SClass;";
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public static ResultSet getScoreByCourse() throws SQLException {
        String sql = " SELECT s.Cno, c.CName, AVG(s.grade) AS 平均成绩, \n" + "    MAX(s.grade) AS 最高成绩, \n" + "    MIN(s.grade) AS 最低成绩,\n" + "    SUM(CASE WHEN s.grade >= 60 THEN 1 ELSE 0 END) \n" + "    / COUNT(*) AS 及格率 \n" + "    FROM student si INNER JOIN score s ON si.SNo = s.SNo\n" + "    INNER JOIN course c ON s.CNo = c.CNo\n" + "    GROUP BY s.CNo, c.CName;";
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public static ResultSet getScoreByStudent(Student student) throws SQLException {
        String sql = "SELECT c.CNo, c.CName, s.grade ,Term FROM score s INNER JOIN course c ON s.CNo = c.CNo WHERE s.SNo = " + student.getSNo() + " AND s.grade is not null and s.status = '审核通过' order by Term ASC";
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public static ResultSet getSelectedCourse(Score score) throws SQLException {
        String SNo = score.getSNo();
        Connection conn = getConnection();
        String sql = "SELECT s.CNo,c.CName, t.TName, tc.CRoom, tc.CTime " +
                "FROM score s " + "INNER JOIN course c ON s.CNo = c.CNo " +
                "INNER JOIN teach tc ON s.CNo = tc.CNo AND s.TNo = tc.TNo " +
                "INNER JOIN teacher t ON s.TNo = t.TNo " + "WHERE s.SNo = ? AND s.grade IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, SNo);
        return ps.executeQuery();
    }

    public static ResultSet getSelectedCourseByQuery(String SNo, String CNo, String CName, String TName) {
        try {
            // 连接数据库
            Connection conn = getConnection();

            // 构建查询 SQL
            String sql = "SELECT s.CNo,c.CName, t.TName, tc.CRoom, tc.CTime " +
                    "FROM score s " + "INNER JOIN course c ON s.CNo = c.CNo " +
                    "INNER JOIN teach tc ON s.CNo = tc.CNo AND s.TNo = tc.TNo " +
                    "INNER JOIN teacher t ON s.TNo = t.TNo " + "WHERE s.SNo = " + SNo + " AND s.grade IS NULL";


            if (!isBlank(CNo)) {
                sql += " AND c.CNo = ?";
            }
            if (!isBlank(CName)) {
                sql += " AND c.CName LIKE ?";
            }
            if (!isBlank(TName)) {
                sql += " AND t.TName LIKE ?";
            }

            // 创建 PreparedStatement 对象
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 设置参数
            int paramIndex = 1;

            if (!isBlank(CNo)) {
                pstmt.setString(paramIndex++, CNo);
            }
            if (!isBlank(CName)) {
                pstmt.setString(paramIndex++, "%" + CName + "%");
            }
            if (!isBlank(TName)) {
                pstmt.setString(paramIndex++, "%" + TName + "%");
            }

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 返回查询结果
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void cancelSelect(Score score) throws SQLException {
        String SNo = score.getSNo();
        String CNo = score.getCNo();
        Connection conn = getConnection();
        String sql = "DELETE FROM score WHERE SNo= ? AND CNo= ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, SNo);
        ps.setString(2, CNo);
        ps.executeUpdate();
    }

    public static void selectCourse(Score score) throws SQLException {
        String SNo = score.getSNo();
        String CNo = score.getCNo();
        String TNo = score.getTNo();
        int Term = score.getTerm();
        String grade = score.getGrade();
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO score(SNo, CNo, TNo, grade, term) VALUES(?, ?,?, ?, ?)";
        conn = getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, SNo);
        ps.setString(2, CNo);
        ps.setString(3, TNo);
        ps.setString(4, grade);
        ps.setInt(5, Term);
        ps.executeUpdate();
    }

    public static ResultSet searchNullScoreList(String TNo) throws SQLException {
        // Create a SQL query to search for null grades for a given student ID
        String query = "SELECT score.SNo, student.SName, course.CName " +
                "FROM score " +
                "INNER JOIN student ON score.SNo = student.SNo " +
                "INNER JOIN course ON score.CNo = course.CNo " +
                "WHERE score.TNo = ? AND score.grade IS NULL";

        // Create a prepared statement with the query and the teacher ID parameter
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, TNo);

        // Execute the query and return the result set
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static int enrollScore(String sname, String cname, double grade) throws SQLException {
        Connection conn = getConnection();
        String sql1 = "SELECT SNo FROM student WHERE SName = ?";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ps1.setString(1, sname);
        ResultSet rs1 = ps1.executeQuery();
        String sno = null;
        if (rs1.next()) {
            sno = rs1.getString("SNo");
        } else {
            return 1;
        }

        String sql2 = "SELECT CNo FROM course WHERE CName = ?";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setString(1, cname);
        ResultSet rs2 = ps2.executeQuery();
        String cno = null;
        if (rs2.next()) {
            cno = rs2.getString("CNo");
        } else {
            return 1;
        }

        String sql3 = "UPDATE score SET grade = ? WHERE SNo = ? AND CNo = ?";
        PreparedStatement ps3 = conn.prepareStatement(sql3);
        ps3.setDouble(1, grade);
        ps3.setString(2, sno);
        ps3.setString(3, cno);
        int result = ps3.executeUpdate();
        if (result > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static ResultSet searchStatisticByQuery(String SClass, String CNo, String SNo, String Term, int index) {
        try {
            // 连接数据库
            Connection conn = getConnection();

            // 构建查询 SQL
            String sql = "";
            if (index == 1) {
                sql = "SELECT AVG(grade) AS 平均成绩, MAX(grade) AS 最高成绩, MIN(grade) AS 最低成绩, SUM(CASE WHEN grade >= 60 THEN 1 ELSE 0 END) / COUNT(*) AS 及格率 FROM score, student WHERE score.SNo = student.SNo AND score.status = '审核通过' AND student.SClass = ?";
            } else if (index == 2) {
                sql = "SELECT AVG(grade) AS 平均成绩, MAX(grade) AS 最高成绩, MIN(grade) AS 最低成绩, SUM(CASE WHEN grade >= 60 THEN 1 ELSE 0 END) / COUNT(*) AS 及格率 FROM score WHERE CNo = ?";
            } else if (index == 3) {
                sql = "SELECT AVG(grade) AS 学期平均学分绩 FROM score WHERE SNo = ? AND term = ?";
            }

            // 创建 PreparedStatement 对象
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 设置参数
            if (index == 1) {
                pstmt.setString(1, SClass);
            } else if (index == 2) {
                pstmt.setString(1, CNo);
            } else if (index == 3) {
                pstmt.setString(1, SNo);
                pstmt.setString(2, Term);
            }

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 返回查询结果
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int addScore(String SNo, String CNo, String TNo, String grade, String Term) {
        try {
            // 连接数据库
            Connection conn = getConnection();

            // 构建插入 SQL
            String sql = "INSERT INTO score(SNo, CNo, TNo, grade, term) VALUES (?, ?, ?, ?, ?)";

            // 创建 PreparedStatement 对象
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 设置参数
            pstmt.setString(1, SNo);
            pstmt.setString(2, CNo);
            pstmt.setString(3, TNo);
            pstmt.setString(4, grade);
            pstmt.setString(5, Term);

            // 执行插入操作
            int rows = pstmt.executeUpdate();

            // 返回插入的记录数
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static ResultSet searchScoreByQuery(String SNo, String CNo, String Term, int index) {
        try {
            // 连接数据库
            Connection conn = getConnection();

            // 构建查询 SQL
            String sql = "SELECT score.SNo, student.SName, score.CNo, course.CName, score.grade, score.term FROM score JOIN student ON score.SNo = student.SNo JOIN course ON score.CNo = course.CNo  WHERE score.status = '审核通过' AND score.grade is not null ";
            if (!isBlank(SNo)) {
                sql += " AND score.SNo = ?";
            }
            if (!isBlank(CNo)) {
                sql += " AND score.CNo = ?";
            }
            if (Term != "全部学期") {
                sql += " AND score.term = ?";
            }
            if (index == 1) {
                sql += " ORDER BY score.SNo";
            } else if (index == 2) {
                sql += " ORDER BY score.term";
            } else if (index == 3) {
                sql += " ORDER BY score.grade DESC";
            } else if (index == 4) {
                sql += " ORDER BY score.CNo";
            }

            // 创建 PreparedStatement 对象
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 设置参数
            int paramIndex = 1;
            if (!isBlank(SNo)) {
                pstmt.setString(paramIndex++, SNo);
            }
            if (!isBlank(CNo)) {
                pstmt.setString(paramIndex++, CNo);
            }
            if (Term != "全部学期") {
                pstmt.setString(paramIndex++, Term);
            }

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 返回查询结果
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int alterScore(String SName, String CName, double newScore) {
        try {
            // 连接数据库
            Connection conn = getConnection();

            // 构建查询 SQL
            String sql = "UPDATE score SET grade=?,status = '待审核',comment=NULL WHERE SNo IN (SELECT SNo FROM student WHERE SName=?) AND CNo IN (SELECT CNo FROM course WHERE CName=?)";

            // 创建 PreparedStatement 对象
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 设置参数
            pstmt.setDouble(1, newScore);
            pstmt.setString(2, SName);
            pstmt.setString(3, CName);

            // 执行更新操作
            int rows = pstmt.executeUpdate();

            // 返回更新的行数
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delScore(String Sname, String CName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM score WHERE SNo IN (SELECT SNo FROM Student WHERE SName=?) AND CNo IN (SELECT CNo FROM Course WHERE CName=?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Sname);
            stmt.setString(2, CName);
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DbUtil.closeConnection(conn);
        }
        return result;
    }

    public static ResultSet getAuditScore() throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT s.SNo,s.SName,t.TNo,t.TName,c.CNo,c.CName,sc.grade,sc.status FROM student s,teacher t,score sc,course c WHERE s.sno = sc.sno AND t.tno =sc.tno AND c.cno = sc.cno AND sc.grade IS NOT NULL AND sc.status = '待审核';";
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public static int  AuditPass(String SNo,String TNO,String CNo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = DbUtil.getConnection();
            String sql = "update score set status = '审核通过' where SNo = ? and TNo = ? and CNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SNo);
            stmt.setString(2, TNO);
            stmt.setString(3, CNo);
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DbUtil.closeConnection(conn);
        }
        return result;
    }

    public static int  AuditFail(String SNo, String TNO, String CNo, String comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = DbUtil.getConnection();
            String sql = "update score set status = '审核驳回',comment = ? where SNo = ? and TNo = ? and CNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,comment);
            stmt.setString(2, SNo);
            stmt.setString(3, TNO);
            stmt.setString(4, CNo);
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DbUtil.closeConnection(conn);
        }
        return result;
    }

    public static ResultSet getAuditRecord(String TNo) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "select s.SNo,s.SName,c.CName,sc.grade,sc.status,sc.comment from student s,teacher t,score sc,course c where s.sno = sc.sno and t.tno =sc.tno and c.cno = sc.cno and sc.TNo = ? and sc.grade is not null and sc.status <> '审核通过';";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,TNo);
        return stmt.executeQuery();
    }
}

