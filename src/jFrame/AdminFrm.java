/*
 * Created by JFormDesigner on Sat May 27 14:36:30 CST 2023
 */

package jFrame;

import java.awt.event.*;

import dao.*;
import model.Course;
import model.Student;
import model.Teacher;
import org.jdesktop.swingx.VerticalLayout;
import util.DbUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static dao.CourseDao.*;
import static dao.StudentDao.deleteStudent;
import static dao.TeacherDao.*;
import static util.FrameUtil.showErrorMessage;
import static util.FrameUtil.showMessageBox;
import static util.StringUtil.isBlank;

/**
 * @author admin
 */
public class AdminFrm extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPanel;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane tabbedPane2;
    private JPanel userListPanel;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel teacherList;
    private JScrollPane scrollPane5;
    private JTable table5;
    private JPanel panel12;
    private JPanel panel13;
    private JLabel label13;
    private JTextField textField20;
    private JLabel label23;
    private JTextField textField21;
    private JButton button14;
    private JButton button15;
    private JPanel panel14;
    private JLabel label24;
    private JTextField textField22;
    private JLabel label25;
    private JTextField textField23;
    private JButton button16;
    private JButton button17;
    private JButton addTeacherButton;
    private JPanel studentList;
    private JScrollPane scrollPane6;
    private JTable table6;
    private JPanel panel7;
    private JPanel panel8;
    private JLabel label12;
    private JTextField textField13;
    private JLabel label17;
    private JTextField textField14;
    private JButton button10;
    private JButton button12;
    private JPanel panel9;
    private JLabel label18;
    private JTextField textField15;
    private JLabel label19;
    private JTextField textField16;
    private JButton button11;
    private JButton button13;
    private JButton addStudentButton;
    private JPanel panel2;
    private JPanel courseList;
    private JScrollPane scrollPane7;
    private JTable table7;
    private JPanel panel10;
    private JPanel panel11;
    private JLabel label20;
    private JTextField textField17;
    private JLabel label21;
    private JTextField textField18;
    private JButton button18;
    private JButton button19;
    private JPanel panel17;
    private JLabel label22;
    private JTextField textField19;
    private JLabel label30;
    private JTextField textField27;
    private JButton button20;
    private JButton button21;
    private JButton AddCourse;
    private JPanel panel6;
    private JPanel panel15;
    private JLabel label4;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JPanel panel16;
    private JTextField textField3;
    private JTextField textField2;
    private JTextField textField1;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label1;
    private JButton BackupDB;
    private JPanel panel5;
    private JLabel label2;
    private JButton RestoreDB;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenu menu4;
    private JMenuItem menuItem8;
    private JMenuItem menuItem12;
    private JMenu menu3;
    private JMenuItem menuItem7;
    private JMenuItem menuItem2;
    private JMenu menu5;
    private JMenuItem menuItem10;
    private JMenuItem menuItem3;
    private JMenu menu2;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JTextPane textPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public AdminFrm() throws SQLException {

        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(
                (screenSize.width - 800) / 2,
                (screenSize.height - 600) / 2,
                800,
                600
        );
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new AdminFrm();


    }

    private void menuItem6MouseClicked(MouseEvent e) {
        // TODO add your code here
        JOptionPane.showMessageDialog(null, "退出");
    }

    private void menuItem1MousePressed(MouseEvent e) {
        // TODO add your code here
        CardLayout cardLayout = new CardLayout();
        dialogPanel.setLayout(cardLayout);
        dialogPanel.add(userListPanel, "userListPanel");
        cardLayout.show(dialogPanel, "userListPanel");
        removeAllRow(table1);
        try {
            fillUserTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void menuItem8MousePressed(MouseEvent e) {
        // TODO add your code here
        CardLayout cardLayout = new CardLayout();
        dialogPanel.setLayout(cardLayout);
        dialogPanel.add(teacherList, "teacherList");
        cardLayout.show(dialogPanel, "teacherList");
        try {
            fillTeacherTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void menuItem7MousePressed(MouseEvent e) {
        // TODO add your code here
        CardLayout cardLayout = new CardLayout();
        dialogPanel.setLayout(cardLayout);
        dialogPanel.add(studentList, "studentList");
        cardLayout.show(dialogPanel, "studentList");
        try {
            fillStudentTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void courseListPressed(MouseEvent e) {
        // TODO add your code here
        CardLayout cardLayout = new CardLayout();
        dialogPanel.setLayout(cardLayout);
        dialogPanel.add(courseList, "courseList");
        cardLayout.show(dialogPanel, "courseList");
        try {
            fillCourseList();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void menuItem2MousePressed(MouseEvent e) {
        // TODO add your code here
        new AddStudent();
    }

    private void scrollPane6MousePressed(MouseEvent e) {
        // TODO add your code here

    }

    private void table6MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table6, new JTextField[]{textField15, textField16});

    }

    private void button13MousePressed(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField15.getText())) {
            showMessage("请选择学生");
        } else {
            Student s = null;
            try {
                s = getSelectedStudent(table6);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new AlterStudent(s);
        }
    }

    private void button10MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            fillTable(table6, StudentDao.searchStudentByQuery(textField13.getText(), textField14.getText()));
        } catch (SQLException ex) {
            showMessage("查询失败");
            throw new RuntimeException(ex);
        }

    }

    private void button12MousePressed(MouseEvent e) {
        // TODO add your code here
        clearTextfield(new JTextField[]{textField13, textField14, textField15, textField16});
        try {
            fillStudentTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //删除学生
    private void button11MousePressed(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField15.getText())) {
            showMessage("请选择学生");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "确定要删除该学生吗？", "确认删除", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                deleteStudent(textField15.getText());
                try {
                    this.fillStudentTable();
                    clearTextfield(new JTextField[]{textField15, textField16});
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void menuItem3MousePressed(MouseEvent e) {
        // TODO add your code here

    }

    private void button19MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            fillCourseList();
            clearTextfield(new JTextField[]{textField18, textField17, textField19, textField27});
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void button20MousePressed(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField19.getText())) {
            showMessage("请选择课程");
        } else {

            int option = JOptionPane.showConfirmDialog(this, "确定要删除该课程吗？", "确认删除", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                deleteCourse(textField19.getText());
                try {
                    this.fillCourseList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        clearTextfield(new JTextField[]{textField19, textField27});
    }

    private void table7MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table7, new JTextField[]{textField19, textField27});
    }

    private void button18MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            fillTable(table7, searchCourseByQuery(textField17.getText(), textField18.getText()));
        } catch (SQLException ex) {
            showMessage("查询失败");
            throw new RuntimeException(ex);
        }
    }

    private void button14MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            fillTable(table5, searchTeacherByQuery(textField20.getText(), textField21.getText()));
        } catch (SQLException ex) {
            showMessage("查询失败");
            throw new RuntimeException(ex);
        }

    }

    private void button15MousePressed(MouseEvent e) {
        // TODO add your code here
        clearTextfield(new JTextField[]{textField20, textField21});
        try {
            fillTeacherTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void button16MousePressed(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField22.getText())) {
            showMessage("请选择教师");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "确定要删除该教师吗？", "确认删除", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                deleteTeacher(textField22.getText());
                try {
                    this.fillTeacherTable();
                    clearTextfield(new JTextField[]{textField22, textField23});
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }

    private void button17MousePressed(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField22.getText())) {
            showMessage("请选择教师");
        } else {

            Teacher t = null;
            try {
                t = getSelectedTeacher(table5);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new AlterTeacher(t);
        }
    }

    private void menuItem12MousePressed(MouseEvent e) {
        // TODO add your code here
        new AddTeacher();
    }

    private void button21MousePressed(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField19.getText())) {
            showMessage("请选择课程");
        } else {
            Course c = null;
            c = getSelectedCourse(table7);
            new AlterCourse(c);
        }
    }

    private void table5MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table5, new JTextField[]{textField22, textField23});
    }

    private void RestoreDBMouseClicked(MouseEvent e) {
        // TODO add your code here
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 设置文件过滤器，只允许选择.sql文件
        fileChooser.setFileFilter(new FileNameExtensionFilter("SQL Files", "sql"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的备份文件路径
            File selectedFile = fileChooser.getSelectedFile();
            String restorePath = selectedFile.getAbsolutePath();
            try {
                // 从资源文件夹中读取mysql可执行文件，并将其保存到临时文件夹中
                InputStream is = getClass().getResourceAsStream("/resources/mysql.exe");
                File outputFile = new File(System.getProperty("java.io.tmpdir"), "mysql");
                OutputStream os = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                os.close();
                is.close();
                // 启动mysql命令恢复数据库
                ProcessBuilder pb = new ProcessBuilder(outputFile.getAbsolutePath(),"-h",DbUtil.getUrl().split(":")[2].split("//")[1],"-P",DbUtil.getUrl().split(":")[3].split("/")[0], "-u", DbUtil.getDbUser(), "-p" + DbUtil.getDbPwd(), DbUtil.getDbName(),"--force");
                pb.redirectInput(selectedFile); // 指定备份文件
                pb.redirectErrorStream(true); // 合并错误流到输出流
//                System.out.println("Command: " + pb.command());
                Process p = pb.start();
                // 将进程的输出流输出到控制台中
//                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
                int exitCode = p.waitFor(); // 等待进程结束
                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(null, "恢复成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "恢复失败！");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "恢复失败：" + ex.getMessage());
            }
        }
    }

    private void BackupDBMouseClicked(MouseEvent e) {
        // TODO add your code here
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的备份文件保存路径
            File selectedDirectory = fileChooser.getSelectedFile();
            String backupPath = selectedDirectory.getAbsolutePath() + File.separator + "Backup.sql";
            try {
                // 从资源文件夹中读取mysqldump可执行文件，并将其保存到临时文件夹中
                InputStream is = getClass().getResourceAsStream("/resources/mysqldump.exe");
                File outputFile = new File(System.getProperty("java.io.tmpdir"), "mysqldump");
                OutputStream os = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                os.close();
                is.close();

                // 启动mysqldump命令备份数据库
                ProcessBuilder pb = new ProcessBuilder(outputFile.getAbsolutePath(),"-h",DbUtil.getUrl().split(":")[2].split("//")[1],"-P",DbUtil.getUrl().split(":")[3].split("/")[0],DbUtil.getDbName(), "-u", DbUtil.getDbUser(), "-p" + DbUtil.getDbPwd(), "-r", backupPath);
                pb.start();
                JOptionPane.showMessageDialog(null, "备份成功！");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "备份失败：" + ex.getMessage());
            }
        }

    }

    private void AddCourseMouseClicked(MouseEvent e) {
        // TODO add your code here
        new AddCourse();
    }

    private void menuItem5MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void menuItem4MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void panel2ComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillCourseList();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void panel1ComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillUserTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void teacherListComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillTeacherTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void studentListComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillStudentTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void addTeacherButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        new AddCourse();

    }

    private void addStudentButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        new AddStudent();
    }

    private void panel6ComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillAuditTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void table2MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table2,new JTextField[]{textField1,new JTextField(),textField2,new JTextField(),textField3});
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField1.getText())) {
            showMessage("请选择成绩");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "确定审核通过吗？", "审核确认", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    ScoreDao.AuditPass(textField1.getText(),textField2.getText(),textField3.getText());
                    clearTextfield(new JTextField[]{textField1, textField2,textField3});
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            fillAuditTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        if (isBlank(textField1.getText())) {
            showMessage("请选择成绩");
        } else {
//            int option = JOptionPane.showConfirmDialog(this, "确定审核驳回吗？", "审核确认", JOptionPane.YES_NO_OPTION);
//            if (option == JOptionPane.YES_OPTION) {
//                try {
//                    ScoreDao.AuditFail(textField1.getText(),textField2.getText(),textField3.getText());
//                    clearTextfield(new JTextField[]{textField1, textField2,textField3});
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
            JTextField  commentField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new JLabel("审核备注:"));
            panel.add(commentField);

            int option = JOptionPane.showOptionDialog(
                    this,
                    panel,
                    "审核驳回",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"确定", "取消"},
                    "确定"
            );

            if (option == JOptionPane.YES_OPTION) {
                String comment = commentField.getText();
                // 执行审核驳回的操作
                try {
                    ScoreDao.AuditFail(textField1.getText(),textField2.getText(),textField3.getText(),comment);
                    clearTextfield(new JTextField[]{textField1, textField2,textField3});
                    showMessageBox("操作成功！");
                } catch (SQLException ex) {
                    showErrorMessage("操作失败！");
                    throw new RuntimeException(ex);
                }
            }
        }

        try {
            fillAuditTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            fillAuditTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        clearTextfield(new JTextField[]{textField1,textField2,textField3});
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        tabbedPane2 = new JTabbedPane();
        userListPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        teacherList = new JPanel();
        scrollPane5 = new JScrollPane();
        table5 = new JTable();
        panel12 = new JPanel();
        panel13 = new JPanel();
        label13 = new JLabel();
        textField20 = new JTextField();
        label23 = new JLabel();
        textField21 = new JTextField();
        button14 = new JButton();
        button15 = new JButton();
        panel14 = new JPanel();
        label24 = new JLabel();
        textField22 = new JTextField();
        label25 = new JLabel();
        textField23 = new JTextField();
        button16 = new JButton();
        button17 = new JButton();
        addTeacherButton = new JButton();
        studentList = new JPanel();
        scrollPane6 = new JScrollPane();
        table6 = new JTable();
        panel7 = new JPanel();
        panel8 = new JPanel();
        label12 = new JLabel();
        textField13 = new JTextField();
        label17 = new JLabel();
        textField14 = new JTextField();
        button10 = new JButton();
        button12 = new JButton();
        panel9 = new JPanel();
        label18 = new JLabel();
        textField15 = new JTextField();
        label19 = new JLabel();
        textField16 = new JTextField();
        button11 = new JButton();
        button13 = new JButton();
        addStudentButton = new JButton();
        panel2 = new JPanel();
        courseList = new JPanel();
        scrollPane7 = new JScrollPane();
        table7 = new JTable();
        panel10 = new JPanel();
        panel11 = new JPanel();
        label20 = new JLabel();
        textField17 = new JTextField();
        label21 = new JLabel();
        textField18 = new JTextField();
        button18 = new JButton();
        button19 = new JButton();
        panel17 = new JPanel();
        label22 = new JLabel();
        textField19 = new JTextField();
        label30 = new JLabel();
        textField27 = new JTextField();
        button20 = new JButton();
        button21 = new JButton();
        AddCourse = new JButton();
        panel6 = new JPanel();
        panel15 = new JPanel();
        label4 = new JLabel();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        panel16 = new JPanel();
        textField3 = new JTextField();
        textField2 = new JTextField();
        textField1 = new JTextField();
        button3 = new JButton();
        button2 = new JButton();
        button4 = new JButton();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label1 = new JLabel();
        BackupDB = new JButton();
        panel5 = new JPanel();
        label2 = new JLabel();
        RestoreDB = new JButton();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menu4 = new JMenu();
        menuItem8 = new JMenuItem();
        menuItem12 = new JMenuItem();
        menu3 = new JMenu();
        menuItem7 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu5 = new JMenu();
        menuItem10 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menu2 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        textPane1 = new JTextPane();

        //======== this ========
        setTitle("\u7ba1\u7406\u9875\u9762");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPanel.setBorder(null);
            dialogPanel.setLayout(new CardLayout());

            //======== tabbedPane1 ========
            {
                tabbedPane1.setTabPlacement(SwingConstants.LEFT);

                //======== panel1 ========
                {
                    panel1.setLayout(new BorderLayout());

                    //======== tabbedPane2 ========
                    {

                        //======== userListPanel ========
                        {
                            userListPanel.setBorder(new TitledBorder("\u7ba1\u7406\u5458\u5217\u8868"));
                            userListPanel.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    panel1ComponentShown(e);
                                }
                            });
                            userListPanel.setLayout(new BorderLayout());

                            //======== scrollPane1 ========
                            {

                                //---- table1 ----
                                table1.setModel(new DefaultTableModel(
                                    new Object[][] {
                                    },
                                    new String[] {
                                        "Id", "\u7528\u6237\u540d", "\u5bc6\u7801"
                                    }
                                ));
                                scrollPane1.setViewportView(table1);
                            }
                            userListPanel.add(scrollPane1, BorderLayout.CENTER);
                        }
                        tabbedPane2.addTab("\u7ba1\u7406\u5458\u7528\u6237\u7ba1\u7406", userListPanel);

                        //======== teacherList ========
                        {
                            teacherList.setBorder(new TitledBorder("\u6559\u5e08\u5217\u8868"));
                            teacherList.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    teacherListComponentShown(e);
                                }
                            });
                            teacherList.setLayout(new BorderLayout());

                            //======== scrollPane5 ========
                            {

                                //---- table5 ----
                                table5.setModel(new DefaultTableModel(
                                    new Object[][] {
                                    },
                                    new String[] {
                                        "\u5de5\u53f7", "\u59d3\u540d"
                                    }
                                ));
                                table5.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        table5MousePressed(e);
                                    }
                                });
                                scrollPane5.setViewportView(table5);
                            }
                            teacherList.add(scrollPane5, BorderLayout.CENTER);

                            //======== panel12 ========
                            {
                                panel12.setBorder(new TitledBorder("\u64cd\u4f5c\u533a"));
                                panel12.setLayout(new VerticalLayout());

                                //======== panel13 ========
                                {
                                    panel13.setBorder(new TitledBorder("\u67e5\u8be2"));
                                    panel13.setLayout(new FlowLayout());

                                    //---- label13 ----
                                    label13.setText("\u5de5\u53f7");
                                    panel13.add(label13);

                                    //---- textField20 ----
                                    textField20.setColumns(10);
                                    panel13.add(textField20);

                                    //---- label23 ----
                                    label23.setText("\u59d3\u540d");
                                    panel13.add(label23);

                                    //---- textField21 ----
                                    textField21.setColumns(10);
                                    panel13.add(textField21);

                                    //---- button14 ----
                                    button14.setText("\u67e5\u8be2");
                                    button14.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button14MousePressed(e);
                                        }
                                    });
                                    panel13.add(button14);

                                    //---- button15 ----
                                    button15.setText("\u91cd\u7f6e|\u5237\u65b0");
                                    button15.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button15MousePressed(e);
                                        }
                                    });
                                    panel13.add(button15);
                                }
                                panel12.add(panel13);

                                //======== panel14 ========
                                {
                                    panel14.setBorder(new TitledBorder("\u4fee\u6539"));
                                    panel14.setLayout(new FlowLayout());

                                    //---- label24 ----
                                    label24.setText("\u5de5\u53f7");
                                    panel14.add(label24);

                                    //---- textField22 ----
                                    textField22.setColumns(10);
                                    textField22.setEditable(false);
                                    panel14.add(textField22);

                                    //---- label25 ----
                                    label25.setText("\u59d3\u540d");
                                    panel14.add(label25);

                                    //---- textField23 ----
                                    textField23.setColumns(10);
                                    textField23.setEditable(false);
                                    panel14.add(textField23);

                                    //---- button16 ----
                                    button16.setText("\u5220\u9664");
                                    button16.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button16MousePressed(e);
                                        }
                                    });
                                    panel14.add(button16);

                                    //---- button17 ----
                                    button17.setText("\u4fee\u6539");
                                    button17.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button17MousePressed(e);
                                        }
                                    });
                                    panel14.add(button17);

                                    //---- addTeacherButton ----
                                    addTeacherButton.setText("\u6dfb\u52a0");
                                    addTeacherButton.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            addTeacherButtonMouseClicked(e);
                                        }
                                    });
                                    panel14.add(addTeacherButton);
                                }
                                panel12.add(panel14);
                            }
                            teacherList.add(panel12, BorderLayout.SOUTH);
                        }
                        tabbedPane2.addTab("\u6559\u5e08\u7528\u6237\u7ba1\u7406", teacherList);

                        //======== studentList ========
                        {
                            studentList.setBorder(new TitledBorder("\u5b66\u751f\u5217\u8868"));
                            studentList.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    studentListComponentShown(e);
                                }
                            });
                            studentList.setLayout(new BorderLayout());

                            //======== scrollPane6 ========
                            {
                                scrollPane6.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        scrollPane6MousePressed(e);
                                    }
                                });

                                //---- table6 ----
                                table6.setModel(new DefaultTableModel(
                                    new Object[][] {
                                    },
                                    new String[] {
                                        "\u5b66\u53f7", "\u59d3\u540d", "\u6027\u522b", "\u73ed\u7ea7", "\u5165\u5b66\u5e74\u4efd"
                                    }
                                ) {
                                    boolean[] columnEditable = new boolean[] {
                                        false, false, false, false, false
                                    };
                                    @Override
                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return columnEditable[columnIndex];
                                    }
                                });
                                table6.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        table6MousePressed(e);
                                    }
                                });
                                scrollPane6.setViewportView(table6);
                            }
                            studentList.add(scrollPane6, BorderLayout.CENTER);

                            //======== panel7 ========
                            {
                                panel7.setBorder(new TitledBorder("\u64cd\u4f5c\u533a"));
                                panel7.setLayout(new VerticalLayout());

                                //======== panel8 ========
                                {
                                    panel8.setBorder(new TitledBorder("\u67e5\u8be2"));
                                    panel8.setLayout(new FlowLayout());

                                    //---- label12 ----
                                    label12.setText("\u5b66\u53f7");
                                    panel8.add(label12);

                                    //---- textField13 ----
                                    textField13.setColumns(10);
                                    panel8.add(textField13);

                                    //---- label17 ----
                                    label17.setText("\u59d3\u540d");
                                    panel8.add(label17);

                                    //---- textField14 ----
                                    textField14.setColumns(10);
                                    panel8.add(textField14);

                                    //---- button10 ----
                                    button10.setText("\u67e5\u8be2");
                                    button10.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button10MousePressed(e);
                                        }
                                    });
                                    panel8.add(button10);

                                    //---- button12 ----
                                    button12.setText("\u91cd\u7f6e|\u5237\u65b0");
                                    button12.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button12MousePressed(e);
                                        }
                                    });
                                    panel8.add(button12);
                                }
                                panel7.add(panel8);

                                //======== panel9 ========
                                {
                                    panel9.setBorder(new TitledBorder("\u4fee\u6539"));
                                    panel9.setLayout(new FlowLayout());

                                    //---- label18 ----
                                    label18.setText("\u5b66\u53f7");
                                    panel9.add(label18);

                                    //---- textField15 ----
                                    textField15.setColumns(10);
                                    textField15.setEditable(false);
                                    panel9.add(textField15);

                                    //---- label19 ----
                                    label19.setText("\u59d3\u540d");
                                    panel9.add(label19);

                                    //---- textField16 ----
                                    textField16.setColumns(10);
                                    textField16.setEditable(false);
                                    panel9.add(textField16);

                                    //---- button11 ----
                                    button11.setText("\u5220\u9664");
                                    button11.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button11MousePressed(e);
                                        }
                                    });
                                    panel9.add(button11);

                                    //---- button13 ----
                                    button13.setText("\u4fee\u6539");
                                    button13.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button13MousePressed(e);
                                        }
                                    });
                                    panel9.add(button13);

                                    //---- addStudentButton ----
                                    addStudentButton.setText("\u6dfb\u52a0");
                                    addStudentButton.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            addStudentButtonMouseClicked(e);
                                        }
                                    });
                                    panel9.add(addStudentButton);
                                }
                                panel7.add(panel9);
                            }
                            studentList.add(panel7, BorderLayout.SOUTH);
                        }
                        tabbedPane2.addTab("\u5b66\u751f\u7528\u6237\u7ba1\u7406", studentList);
                    }
                    panel1.add(tabbedPane2, BorderLayout.CENTER);
                }
                tabbedPane1.addTab("\u7528\u6237\u7ba1\u7406", panel1);

                //======== panel2 ========
                {
                    panel2.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentShown(ComponentEvent e) {
                            panel2ComponentShown(e);
                        }
                    });
                    panel2.setLayout(new BorderLayout());

                    //======== courseList ========
                    {
                        courseList.setBorder(new TitledBorder("\u8bfe\u7a0b\u5217\u8868"));
                        courseList.setLayout(new BorderLayout());

                        //======== scrollPane7 ========
                        {

                            //---- table7 ----
                            table7.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                    "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u5b66\u5206"
                                }
                            ));
                            table7.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    table7MousePressed(e);
                                }
                            });
                            scrollPane7.setViewportView(table7);
                        }
                        courseList.add(scrollPane7, BorderLayout.CENTER);

                        //======== panel10 ========
                        {
                            panel10.setBorder(new TitledBorder("\u64cd\u4f5c\u533a"));
                            panel10.setLayout(new VerticalLayout());

                            //======== panel11 ========
                            {
                                panel11.setBorder(new TitledBorder("\u67e5\u8be2"));
                                panel11.setLayout(new FlowLayout());

                                //---- label20 ----
                                label20.setText("\u8bfe\u7a0b\u53f7");
                                panel11.add(label20);

                                //---- textField17 ----
                                textField17.setColumns(10);
                                panel11.add(textField17);

                                //---- label21 ----
                                label21.setText("\u8bfe\u7a0b\u540d");
                                panel11.add(label21);

                                //---- textField18 ----
                                textField18.setColumns(10);
                                panel11.add(textField18);

                                //---- button18 ----
                                button18.setText("\u67e5\u8be2");
                                button18.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button18MousePressed(e);
                                    }
                                });
                                panel11.add(button18);

                                //---- button19 ----
                                button19.setText("\u91cd\u7f6e|\u5237\u65b0");
                                button19.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button19MousePressed(e);
                                    }
                                });
                                panel11.add(button19);
                            }
                            panel10.add(panel11);

                            //======== panel17 ========
                            {
                                panel17.setBorder(new TitledBorder("\u4fee\u6539"));
                                panel17.setLayout(new FlowLayout());

                                //---- label22 ----
                                label22.setText("\u8bfe\u7a0b\u53f7");
                                panel17.add(label22);

                                //---- textField19 ----
                                textField19.setColumns(10);
                                textField19.setEditable(false);
                                panel17.add(textField19);

                                //---- label30 ----
                                label30.setText("\u8bfe\u7a0b\u540d");
                                panel17.add(label30);

                                //---- textField27 ----
                                textField27.setColumns(10);
                                textField27.setEditable(false);
                                panel17.add(textField27);

                                //---- button20 ----
                                button20.setText("\u5220\u9664");
                                button20.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button20MousePressed(e);
                                    }
                                });
                                panel17.add(button20);

                                //---- button21 ----
                                button21.setText("\u4fee\u6539");
                                button21.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button21MousePressed(e);
                                    }
                                });
                                panel17.add(button21);

                                //---- AddCourse ----
                                AddCourse.setText("\u6dfb\u52a0\u8bfe\u7a0b");
                                AddCourse.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        AddCourseMouseClicked(e);
                                    }
                                });
                                panel17.add(AddCourse);
                            }
                            panel10.add(panel17);
                        }
                        courseList.add(panel10, BorderLayout.SOUTH);
                    }
                    panel2.add(courseList, BorderLayout.CENTER);
                }
                tabbedPane1.addTab("\u8bfe\u7a0b\u7ba1\u7406", panel2);

                //======== panel6 ========
                {
                    panel6.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentShown(ComponentEvent e) {
                            panel6ComponentShown(e);
                        }
                    });
                    panel6.setLayout(new BorderLayout());

                    //======== panel15 ========
                    {
                        panel15.setLayout(new FlowLayout());
                        panel15.add(label4);
                    }
                    panel6.add(panel15, BorderLayout.NORTH);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setBorder(new TitledBorder("\u6210\u7ee9\u5217\u8868"));

                        //---- table2 ----
                        table2.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "\u5b66\u53f7", "\u5b66\u751f\u59d3\u540d", "\u5de5\u53f7", "\u64cd\u4f5c\u6559\u5e08", "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u6210\u7ee9"
                            }
                        ));
                        table2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                table2MousePressed(e);
                            }
                        });
                        scrollPane2.setViewportView(table2);
                    }
                    panel6.add(scrollPane2, BorderLayout.CENTER);

                    //======== panel16 ========
                    {
                        panel16.setLayout(new FlowLayout());

                        //---- textField3 ----
                        textField3.setVisible(false);
                        panel16.add(textField3);

                        //---- textField2 ----
                        textField2.setVisible(false);
                        panel16.add(textField2);

                        //---- textField1 ----
                        textField1.setVisible(false);
                        panel16.add(textField1);

                        //---- button3 ----
                        button3.setText("\u5ba1\u6838\u9a73\u56de");
                        button3.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button3MouseClicked(e);
                            }
                        });
                        panel16.add(button3);

                        //---- button2 ----
                        button2.setText("\u5ba1\u6838\u901a\u8fc7");
                        button2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button2MouseClicked(e);
                            }
                        });
                        panel16.add(button2);

                        //---- button4 ----
                        button4.setText("\u5237\u65b0\u5217\u8868");
                        button4.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        panel16.add(button4);
                    }
                    panel6.add(panel16, BorderLayout.SOUTH);
                }
                tabbedPane1.addTab("\u6210\u7ee9\u5ba1\u6838", panel6);

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(2, 0));

                    //======== panel4 ========
                    {
                        panel4.setBorder(new TitledBorder("\u6570\u636e\u5e93\u5907\u4efd"));
                        panel4.setLayout(new FlowLayout());

                        //---- label1 ----
                        label1.setText("\u70b9\u51fb\u6309\u94ae\u5907\u4efd\u6570\u636e\u5e93\uff1a");
                        panel4.add(label1);

                        //---- BackupDB ----
                        BackupDB.setText("\u5907\u4efd\u6570\u636e\u5e93");
                        BackupDB.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                BackupDBMouseClicked(e);
                            }
                        });
                        panel4.add(BackupDB);
                    }
                    panel3.add(panel4);

                    //======== panel5 ========
                    {
                        panel5.setBorder(new TitledBorder("\u4ece\u5907\u4efd\u6062\u590d\u6570\u636e\u5e93"));
                        panel5.setLayout(new FlowLayout());

                        //---- label2 ----
                        label2.setText("\u70b9\u51fb\u6309\u94ae\u6062\u590d\u6570\u636e\u5e93\uff1a");
                        panel5.add(label2);

                        //---- RestoreDB ----
                        RestoreDB.setText("\u6062\u590d\u6570\u636e\u5e93");
                        RestoreDB.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                RestoreDBMouseClicked(e);
                            }
                        });
                        panel5.add(RestoreDB);
                    }
                    panel3.add(panel5);
                }
                tabbedPane1.addTab("\u6570\u636e\u5e93\u5907\u4efd", panel3);
            }
            dialogPanel.add(tabbedPane1, "card5");
        }
        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== menuBar1 ========
        {
            menuBar1.setVisible(false);

            //======== menu1 ========
            {
                menu1.setText("\u7528\u6237\u7ba1\u7406");

                //---- menuItem1 ----
                menuItem1.setText("\u7ba1\u7406\u5458\u5217\u8868");
                menuItem1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem1MousePressed(e);
                    }
                });
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);

            //======== menu4 ========
            {
                menu4.setText("\u6559\u5e08\u7ba1\u7406");

                //---- menuItem8 ----
                menuItem8.setText("\u6559\u5e08\u5217\u8868");
                menuItem8.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem8MousePressed(e);
                    }
                });
                menu4.add(menuItem8);

                //---- menuItem12 ----
                menuItem12.setText("\u6dfb\u52a0\u6559\u5e08");
                menuItem12.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem12MousePressed(e);
                    }
                });
                menu4.add(menuItem12);
            }
            menuBar1.add(menu4);

            //======== menu3 ========
            {
                menu3.setText("\u5b66\u751f\u7ba1\u7406");

                //---- menuItem7 ----
                menuItem7.setText("\u5b66\u751f\u5217\u8868");
                menuItem7.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem7MousePressed(e);
                    }
                });
                menu3.add(menuItem7);

                //---- menuItem2 ----
                menuItem2.setText("\u5b66\u751f\u6dfb\u52a0");
                menuItem2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem2MousePressed(e);
                    }
                });
                menu3.add(menuItem2);
            }
            menuBar1.add(menu3);

            //======== menu5 ========
            {
                menu5.setText("\u8bfe\u7a0b\u7ba1\u7406");

                //---- menuItem10 ----
                menuItem10.setText("\u8bfe\u7a0b\u5217\u8868");
                menuItem10.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        courseListPressed(e);
                    }
                });
                menu5.add(menuItem10);

                //---- menuItem3 ----
                menuItem3.setText("\u8bfe\u7a0b\u6dfb\u52a0");
                menuItem3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem3MousePressed(e);
                    }
                });
                menu5.add(menuItem3);
            }
            menuBar1.add(menu5);

            //======== menu2 ========
            {
                menu2.setText("\u6570\u636e\u5e93\u5907\u4efd");

                //---- menuItem4 ----
                menuItem4.setText("\u5907\u4efd\u6570\u636e\u5e93");
                menuItem4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem4MousePressed(e);
                    }
                });
                menu2.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("\u6062\u590d\u6570\u636e\u5e93");
                menuItem5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        menuItem5MousePressed(e);
                    }
                });
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void fillUserTable() throws SQLException {
        removeAllRow(table1);
        DefaultTableModel model_1 = (DefaultTableModel) table1.getModel();
        model_1.setRowCount(0);
        model_1.addColumn("Id");
        model_1.addColumn("用户名");
        model_1.addColumn("密码");
        ResultSet rs = AdminDao.getAllUsers(DbUtil.getConnection());
        while (rs.next()) {
            String[] row = new String[5];
            row[0] = rs.getString("id");
            row[1] = rs.getString("uname");
            row[2] = rs.getString("pwd");

            model_1.addRow(row);
        }
    }

    public void fillTeacherTable() throws SQLException {
        removeAllRow(table5);
        ResultSet rs = TeacherDao.getTeacherList();
        DefaultTableModel model = (DefaultTableModel) table5.getModel();
        model.setRowCount(0);
        model.addColumn("工号");
        model.addColumn("姓名");
        while (rs.next()) {
            Object[] rowData = {rs.getString("TNo"), rs.getString("TName")}; // 每一行数据的值
            model.addRow(rowData);
        }
    }

    public void fillStudentTable() throws SQLException {
        removeAllRow(table6);
        ResultSet rs = StudentDao.getStudentList();
        DefaultTableModel model = (DefaultTableModel) table6.getModel();
        model.setRowCount(0);
        model.addColumn("学号");
        model.addColumn("姓名");
        model.addColumn("性别");
        model.addColumn("班级");
        model.addColumn("入学年份");
        while (rs.next()) {
            Object[] rowData = {
                    rs.getString("SNo"),
                    rs.getString("SName"),
                    rs.getString("SSex"),
                    rs.getString("SClass"),
                    rs.getString("enrollYear"),
            };
            model.addRow(rowData);
        }
    }

    public void fillCourseList() throws SQLException {
//        removeAllRow(table7);
        ResultSet rs = CourseDao.getCourseList();
        DefaultTableModel model = (DefaultTableModel) table7.getModel();
        model.setRowCount(0);
//        model.addColumn("课程号");
//        model.addColumn("课程名");
//        model.addColumn("学分");
        while (rs.next()) {
            Object[] rowData = {
                    rs.getString("CNo"),
                    rs.getString("CName"),
                    rs.getString("CCredit"),
            };
            model.addRow(rowData);
        }
    }

    private void fillAuditTable() throws SQLException {
        ResultSet rs = ScoreDao.getAuditScore();
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        while (rs.next()) {
            Object[] rowData = {
                rs.getString("SNo"),
                rs.getString("SName"),
                rs.getString("TNo"),
                rs.getString("TName"),
                rs.getString("CNo"),
                rs.getString("CName"),
                rs.getString("grade")
            };
            model.addRow(rowData);
        }
    }

    public void fillTable(JTable t, ResultSet rs) throws SQLException {
        // 清空表格内容
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        model.setRowCount(0);
        // 获取结果集的列数和列名
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
//        String[] columnNames = new String[columnCount];
//        for (int i = 0; i < columnCount; i++) {
//            columnNames[i] = md.getColumnName(i + 1);
//        }
        // 填充表格内容
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }
        // 设置表格列名
//        model.setColumnIdentifiers(columnNames);
        // 关闭结果集
        rs.close();
    }

    public void fillSelect(JTable tb, JTextField[] t) {
//        int rowIndex = table2.getSelectedRow(); // 获取选中的行号
//        TableModel model = table2.getModel(); // 获取表格模型
        int rowIndex = tb.getSelectedRow(); // 获取选中的行号
        TableModel model = tb.getModel(); // 获取表格模型
        Object[] rowData = new Object[model.getColumnCount()]; // 创建一个数组，用于存储该行的数据
        for (int i = 0; i < model.getColumnCount(); i++) {
            rowData[i] = model.getValueAt(rowIndex, i); // 将该行的每个单元格的值存入数组中
        }
        for (int i = 0; i < t.length; i++) {
            t[i].setText((String) rowData[i]);
        }
//        textField6.setText((String) rowData[0]);
//        textField7.setText((String) rowData[2]);
    }

    public Student getSelectedStudent(JTable t) throws SQLException {
        int selectedRow = t.getSelectedRow();
        String fieldValue = t.getValueAt(selectedRow, 0).toString();
        return StudentDao.getStudent(fieldValue);
    }

    public Teacher getSelectedTeacher(JTable t) throws SQLException {
        int selectedRow = t.getSelectedRow();
        String fieldValue = t.getValueAt(selectedRow, 0).toString();
        return getTeacher(fieldValue);
    }

    public Course getSelectedCourse(JTable t) {
        int selectedRow = t.getSelectedRow();
        String fieldValue = t.getValueAt(selectedRow, 0).toString();
        return getCourse(fieldValue);
    }

    public void removeAllRow(JTable t) {
        t.setModel(new DefaultTableModel());
    }

    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null, m);
    }
    public void clearTextfield(JTextField[] t) {
        for (int i = 0; i < t.length; i++) {
            t[i].setText("");
        }
    }
}
