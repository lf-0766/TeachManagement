/*
 * Created by JFormDesigner on Wed May 31 18:59:33 CST 2023
 */

package jFrame;

import java.awt.event.*;
import javax.swing.border.*;
import dao.ScoreDao;
import dao.StudentDao;
import dao.TeachDao;
import dao.TeacherDao;
import model.Course;
import model.Score;
import model.Student;
import org.jdesktop.swingx.VerticalLayout;
import util.StringUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author admin
 */
public class StudentFrm extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPanel;
    private JTabbedPane tabbedPane5;
    private JPanel panel25;
    private JTabbedPane tabbedPane6;
    private JPanel accountPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField textField1;
    private JPanel panel3;
    private JLabel label2;
    private JTextField textField2;
    private JPanel panel4;
    private JLabel label3;
    private JTextField textField3;
    private JPanel panel5;
    private JButton button1;
    private JPanel panel26;
    private JTabbedPane tabbedPane7;
    private JPanel courseSelectPanel;
    private JPanel panel6;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JPanel panel7;
    private JPanel panel8;
    private JLabel label6;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label4;
    private JTextField textField4;
    private JButton button2;
    private JPanel panel9;
    private JLabel label7;
    private JLabel label8;
    private JTextField textField6;
    private JLabel label9;
    private JTextField textField7;
    private JLabel label16;
    private JTextField textField12;
    private JButton button3;
    private JPanel courseListPanel;
    private JScrollPane scrollPane4;
    private JTable table3;
    private JPanel panel11;
    private JPanel panel12;
    private JLabel label10;
    private JLabel label11;
    private JTextField textField8;
    private JLabel label12;
    private JTextField textField9;
    private JLabel label17;
    private JTextField textField13;
    private JButton button4;
    private JPanel panel13;
    private JLabel label13;
    private JLabel label14;
    private JTextField textField10;
    private JLabel label15;
    private JTextField textField11;
    private JButton button5;
    private JPanel panel27;
    private JTabbedPane tabbedPane8;
    private JPanel scorePanel;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel mainPanel;
    private JLabel label18;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private Student nowStu;

    public StudentFrm(Student stu) {
        nowStu = stu;
        initComponents();
        setTitle("学生页面  当前用户："+nowStu.getSName());
    }

    public static void main(String[] args) {
        new StudentFrm(new Student(
                "219044123",
                "马小燕",
                "女",
                "123",
                "01",
                2021
        ));
    }

    private void button1MousePressed(MouseEvent e) {
        // TODO add your code here
        String oldPwd = textField1.getText();
        String newPwd = textField2.getText();
        String confirmPwd = textField3.getText();
        if (!StringUtil.isEqual(newPwd, confirmPwd)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
        } else {
            Student temp = nowStu;
            temp.setSPwd(newPwd);
            try {
                StudentDao.alterStudentPwd(temp, oldPwd);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "原密码错误！");
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, "修改成功");
        }
    }

    private void button2MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            fillStudentCourseList(new Course(textField5.getText(), textField4.getText()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "数据库异常！");
            throw new RuntimeException(ex);
        }
    }

    private void table2MousePressed(MouseEvent e) {
        // TODO add your code here
        fillCourseSelect(table2, new JTextField[]{textField6, textField7, textField12});
    }

    private void table3MousePressed(MouseEvent e) {
        // TODO add your code here
        fillCourseSelect(table3, new JTextField[]{textField10, textField11});
    }

    private void button5MousePressed(MouseEvent e){
        // TODO add your code here
        if(StringUtil.isBlank(textField10 .getText())){
            showMessage("请选择课程！");
        }else{

        String CNo = textField10.getText();
        try {
            ScoreDao.cancelSelect(new Score(nowStu.getSNo(), CNo));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "数据库异常！");
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "退课成功！");
            try {
                fillSelectedCourse();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            clearTextfield(new JTextField[]{textField10,textField11});
        }
//        fillCourseSelect(table3, new JTextField[]{textField10, textField11});
    }

    private void button3MousePressed(MouseEvent e)  {
        // TODO add your code here
        if(StringUtil.isBlank(textField6.getText())){
            showMessage("请选择课程！");
        }else{
        try {
           String  TNo = TeacherDao.getTNoByTName(textField12.getText());
            ScoreDao.selectCourse(new Score(
                    nowStu.getSNo(),
                    TNo,
                    textField6.getText(),
                    null,
                    nowStu.getTerm()
            ));
        } catch (SQLException ex) {
            showMessage("选课失败！");
            throw new RuntimeException(ex);
        }
        showMessage("选课成功！");
            try {
                fillStudentCourseList(new Course());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        clearTextfield(new JTextField[]{textField6,textField7,textField12});
    }

    private void button4MousePressed(MouseEvent e) {
        // TODO add your code here
            fillSelectedCourseByQuery(nowStu.getSNo(),textField8.getText(),textField9.getText(),textField13.getText());
    }

    private void accountPanelComponentShown(ComponentEvent e) {
        // TODO add your code here
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
    }

    private void courseSelectPanelComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillStudentCourseList(new Course());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "数据库异常！");
        }
    }

    private void courseListPanelComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillSelectedCourse();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "数据库异常！");
            throw new RuntimeException(ex);
        }
    }

    private void scorePanelComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillStudentScoreList();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "查询失败");
            throw new RuntimeException(ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPanel = new JPanel();
        tabbedPane5 = new JTabbedPane();
        panel25 = new JPanel();
        tabbedPane6 = new JTabbedPane();
        accountPanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        textField2 = new JTextField();
        panel4 = new JPanel();
        label3 = new JLabel();
        textField3 = new JTextField();
        panel5 = new JPanel();
        button1 = new JButton();
        panel26 = new JPanel();
        tabbedPane7 = new JTabbedPane();
        courseSelectPanel = new JPanel();
        panel6 = new JPanel();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        panel7 = new JPanel();
        panel8 = new JPanel();
        label6 = new JLabel();
        label5 = new JLabel();
        textField5 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        button2 = new JButton();
        panel9 = new JPanel();
        label7 = new JLabel();
        label8 = new JLabel();
        textField6 = new JTextField();
        label9 = new JLabel();
        textField7 = new JTextField();
        label16 = new JLabel();
        textField12 = new JTextField();
        button3 = new JButton();
        courseListPanel = new JPanel();
        scrollPane4 = new JScrollPane();
        table3 = new JTable();
        panel11 = new JPanel();
        panel12 = new JPanel();
        label10 = new JLabel();
        label11 = new JLabel();
        textField8 = new JTextField();
        label12 = new JLabel();
        textField9 = new JTextField();
        label17 = new JLabel();
        textField13 = new JTextField();
        button4 = new JButton();
        panel13 = new JPanel();
        label13 = new JLabel();
        label14 = new JLabel();
        textField10 = new JTextField();
        label15 = new JLabel();
        textField11 = new JTextField();
        button5 = new JButton();
        panel27 = new JPanel();
        tabbedPane8 = new JTabbedPane();
        scorePanel = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        mainPanel = new JPanel();
        label18 = new JLabel();

        //======== this ========
        setTitle("\u5b66\u751f\u9875\u9762");
        setPreferredSize(new Dimension(1000, 600));
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPanel.setLayout(new CardLayout());

            //======== tabbedPane5 ========
            {
                tabbedPane5.setTabPlacement(SwingConstants.LEFT);

                //======== panel25 ========
                {
                    panel25.setLayout(new BorderLayout());

                    //======== tabbedPane6 ========
                    {

                        //======== accountPanel ========
                        {
                            accountPanel.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    accountPanelComponentShown(e);
                                }
                            });
                            accountPanel.setLayout(new BorderLayout());

                            //======== panel1 ========
                            {
                                panel1.setBorder(new TitledBorder("\u5bc6\u7801\u4fee\u6539"));
                                panel1.setLayout(new VerticalLayout(20));

                                //======== panel2 ========
                                {
                                    panel2.setLayout(new FlowLayout());

                                    //---- label1 ----
                                    label1.setText("\u539f\u5bc6\u7801");
                                    label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 8f));
                                    panel2.add(label1);

                                    //---- textField1 ----
                                    textField1.setColumns(10);
                                    panel2.add(textField1);
                                }
                                panel1.add(panel2);

                                //======== panel3 ========
                                {
                                    panel3.setLayout(new FlowLayout());

                                    //---- label2 ----
                                    label2.setText("\u65b0\u5bc6\u7801");
                                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 8f));
                                    panel3.add(label2);

                                    //---- textField2 ----
                                    textField2.setColumns(10);
                                    panel3.add(textField2);
                                }
                                panel1.add(panel3);

                                //======== panel4 ========
                                {
                                    panel4.setLayout(new FlowLayout());

                                    //---- label3 ----
                                    label3.setText("\u786e\u8ba4\u65b0\u5bc6\u7801");
                                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 8f));
                                    panel4.add(label3);

                                    //---- textField3 ----
                                    textField3.setColumns(10);
                                    panel4.add(textField3);
                                }
                                panel1.add(panel4);

                                //======== panel5 ========
                                {
                                    panel5.setLayout(new FlowLayout());

                                    //---- button1 ----
                                    button1.setText("\u4fee\u6539\u5bc6\u7801");
                                    button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 6f));
                                    button1.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button1MousePressed(e);
                                        }
                                    });
                                    panel5.add(button1);
                                }
                                panel1.add(panel5);
                            }
                            accountPanel.add(panel1, BorderLayout.CENTER);
                        }
                        tabbedPane6.addTab("\u5bc6\u7801\u4fee\u6539", accountPanel);
                    }
                    panel25.add(tabbedPane6, BorderLayout.CENTER);
                }
                tabbedPane5.addTab("\u8d26\u6237\u7ba1\u7406", panel25);

                //======== panel26 ========
                {
                    panel26.setLayout(new BorderLayout());

                    //======== tabbedPane7 ========
                    {

                        //======== courseSelectPanel ========
                        {
                            courseSelectPanel.setBorder(new TitledBorder("\u5b66\u751f\u9009\u8bfe"));
                            courseSelectPanel.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    courseSelectPanelComponentShown(e);
                                }
                            });
                            courseSelectPanel.setLayout(new BorderLayout());

                            //======== panel6 ========
                            {
                                panel6.setLayout(new BorderLayout());

                                //======== scrollPane3 ========
                                {

                                    //---- table2 ----
                                    table2.setModel(new DefaultTableModel(
                                        new Object[][] {
                                        },
                                        new String[] {
                                            "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u6559\u5e08", "\u5730\u70b9", "\u65f6\u95f4"
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
                                    table2.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            table2MousePressed(e);
                                        }
                                    });
                                    scrollPane3.setViewportView(table2);
                                }
                                panel6.add(scrollPane3, BorderLayout.CENTER);

                                //======== panel7 ========
                                {
                                    panel7.setLayout(new VerticalLayout());

                                    //======== panel8 ========
                                    {
                                        panel8.setLayout(new FlowLayout());

                                        //---- label6 ----
                                        label6.setText("\u8bfe\u7a0b\u67e5\u8be2");
                                        label6.setForeground(Color.blue);
                                        panel8.add(label6);

                                        //---- label5 ----
                                        label5.setText("\u8bfe\u7a0b\u53f7");
                                        panel8.add(label5);

                                        //---- textField5 ----
                                        textField5.setColumns(10);
                                        panel8.add(textField5);

                                        //---- label4 ----
                                        label4.setText("\u8bfe\u7a0b\u540d");
                                        panel8.add(label4);

                                        //---- textField4 ----
                                        textField4.setColumns(10);
                                        panel8.add(textField4);

                                        //---- button2 ----
                                        button2.setText("\u67e5\u8be2");
                                        button2.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                button2MousePressed(e);
                                            }
                                        });
                                        panel8.add(button2);
                                    }
                                    panel7.add(panel8);

                                    //======== panel9 ========
                                    {
                                        panel9.setLayout(new FlowLayout());

                                        //---- label7 ----
                                        label7.setText("\u9009\u8bfe");
                                        label7.setForeground(Color.blue);
                                        panel9.add(label7);

                                        //---- label8 ----
                                        label8.setText("\u8bfe\u7a0b\u53f7");
                                        panel9.add(label8);

                                        //---- textField6 ----
                                        textField6.setColumns(10);
                                        textField6.setEditable(false);
                                        panel9.add(textField6);

                                        //---- label9 ----
                                        label9.setText("\u8bfe\u7a0b\u540d");
                                        panel9.add(label9);

                                        //---- textField7 ----
                                        textField7.setColumns(10);
                                        textField7.setEditable(false);
                                        panel9.add(textField7);

                                        //---- label16 ----
                                        label16.setText("\u6559\u5e08");
                                        panel9.add(label16);

                                        //---- textField12 ----
                                        textField12.setEditable(false);
                                        textField12.setColumns(5);
                                        panel9.add(textField12);

                                        //---- button3 ----
                                        button3.setText("\u9009\u8bfe");
                                        button3.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                button3MousePressed(e);
                                            }
                                        });
                                        panel9.add(button3);
                                    }
                                    panel7.add(panel9);
                                }
                                panel6.add(panel7, BorderLayout.EAST);
                            }
                            courseSelectPanel.add(panel6, BorderLayout.CENTER);
                        }
                        tabbedPane7.addTab("\u5b66\u751f\u9009\u8bfe", courseSelectPanel);

                        //======== courseListPanel ========
                        {
                            courseListPanel.setBorder(new TitledBorder("\u9009\u8bfe\u7ed3\u679c"));
                            courseListPanel.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    courseListPanelComponentShown(e);
                                }
                            });
                            courseListPanel.setLayout(new BorderLayout());

                            //======== scrollPane4 ========
                            {

                                //---- table3 ----
                                table3.setModel(new DefaultTableModel(
                                    new Object[][] {
                                    },
                                    new String[] {
                                        "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u6559\u5e08", "\u5730\u70b9", "\u65f6\u95f4"
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
                                table3.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        table3MousePressed(e);
                                    }
                                });
                                scrollPane4.setViewportView(table3);
                            }
                            courseListPanel.add(scrollPane4, BorderLayout.CENTER);

                            //======== panel11 ========
                            {
                                panel11.setLayout(new VerticalLayout());

                                //======== panel12 ========
                                {
                                    panel12.setLayout(new FlowLayout());

                                    //---- label10 ----
                                    label10.setText("\u8bfe\u7a0b\u67e5\u8be2");
                                    label10.setForeground(Color.blue);
                                    panel12.add(label10);

                                    //---- label11 ----
                                    label11.setText("\u8bfe\u7a0b\u53f7");
                                    panel12.add(label11);

                                    //---- textField8 ----
                                    textField8.setColumns(10);
                                    panel12.add(textField8);

                                    //---- label12 ----
                                    label12.setText("\u8bfe\u7a0b\u540d");
                                    panel12.add(label12);

                                    //---- textField9 ----
                                    textField9.setColumns(10);
                                    panel12.add(textField9);

                                    //---- label17 ----
                                    label17.setText("\u6559\u5e08");
                                    panel12.add(label17);

                                    //---- textField13 ----
                                    textField13.setColumns(5);
                                    panel12.add(textField13);

                                    //---- button4 ----
                                    button4.setText("\u67e5\u8be2");
                                    button4.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button4MousePressed(e);
                                        }
                                    });
                                    panel12.add(button4);
                                }
                                panel11.add(panel12);

                                //======== panel13 ========
                                {
                                    panel13.setLayout(new FlowLayout());

                                    //---- label13 ----
                                    label13.setText("\u9009\u8bfe");
                                    label13.setForeground(Color.blue);
                                    panel13.add(label13);

                                    //---- label14 ----
                                    label14.setText("\u8bfe\u7a0b\u53f7");
                                    panel13.add(label14);

                                    //---- textField10 ----
                                    textField10.setColumns(10);
                                    textField10.setEditable(false);
                                    panel13.add(textField10);

                                    //---- label15 ----
                                    label15.setText("\u8bfe\u7a0b\u540d");
                                    panel13.add(label15);

                                    //---- textField11 ----
                                    textField11.setColumns(10);
                                    textField11.setEditable(false);
                                    panel13.add(textField11);

                                    //---- button5 ----
                                    button5.setText("\u9000\u9009");
                                    button5.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button5MousePressed(e);
                                        }
                                    });
                                    panel13.add(button5);
                                }
                                panel11.add(panel13);
                            }
                            courseListPanel.add(panel11, BorderLayout.EAST);
                        }
                        tabbedPane7.addTab("\u9009\u8bfe\u7ed3\u679c", courseListPanel);
                    }
                    panel26.add(tabbedPane7, BorderLayout.CENTER);
                }
                tabbedPane5.addTab("\u9009\u8bfe\u7ba1\u7406", panel26);

                //======== panel27 ========
                {
                    panel27.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentShown(ComponentEvent e) {
                            scorePanelComponentShown(e);
                        }
                    });
                    panel27.setLayout(new BorderLayout());

                    //======== tabbedPane8 ========
                    {

                        //======== scorePanel ========
                        {
                            scorePanel.setBorder(new TitledBorder("\u6210\u7ee9\u5217\u8868"));
                            scorePanel.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    scorePanelComponentShown(e);
                                }
                            });
                            scorePanel.setLayout(new BorderLayout());

                            //======== scrollPane2 ========
                            {
                                scrollPane2.setViewportView(table1);
                            }
                            scorePanel.add(scrollPane2, BorderLayout.CENTER);
                        }
                        tabbedPane8.addTab("\u6210\u7ee9\u67e5\u8be2", scorePanel);
                    }
                    panel27.add(tabbedPane8, BorderLayout.CENTER);
                }
                tabbedPane5.addTab("\u6210\u7ee9\u7ba1\u7406", panel27);
            }
            dialogPanel.add(tabbedPane5, "card6");

            //======== mainPanel ========
            {
                mainPanel.setLayout(new BorderLayout());

                //---- label18 ----
                label18.setText("\u6b22\u8fce\u4f7f\u7528\u6559\u5b66\u7ba1\u7406\u7cfb\u7edf\uff01");
                label18.setHorizontalTextPosition(SwingConstants.CENTER);
                label18.setFont(label18.getFont().deriveFont(label18.getFont().getStyle() | Font.BOLD, label18.getFont().getSize() + 10f));
                label18.setHorizontalAlignment(SwingConstants.CENTER);
                mainPanel.add(label18, BorderLayout.CENTER);
            }
            dialogPanel.add(mainPanel, "card5");
        }
        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void removeAllRow(JTable t) {
        t.setModel(new DefaultTableModel());
    }

    public void fillStudentScoreList() throws SQLException {
        removeAllRow(table1);
        ResultSet rs = ScoreDao.getScoreByStudent(nowStu);
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.addColumn("课程号");
        model.addColumn("课程名");
        model.addColumn("成绩");
        model.addColumn("学期");
        while (rs.next()) {
            Object[] rowData = {
                    rs.getString("CNo"),
                    rs.getString("CName"),
                    rs.getString("grade"),
                    rs.getString("Term"),
            };
            model.addRow(rowData);
        }
    }

    public void fillStudentCourseList(Course course) throws SQLException {
        ResultSet rs = TeachDao.getTeach(course,nowStu.getSNo());
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        while (rs.next()) {
            Object[] rowData = {
                    rs.getString("CNo"),
                    rs.getString("CName"),
                    rs.getString("TName"),
                    rs.getString("CRoom"),
                    rs.getString("CTime"),
            };
            model.addRow(rowData);
        }
    }

    public void fillCourseSelect(JTable tb, JTextField[] t) {
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

    public void fillSelectedCourse() throws SQLException {
        ResultSet rs = ScoreDao.getSelectedCourse(new Score(nowStu.getSNo()));
        DefaultTableModel model = (DefaultTableModel) table3.getModel();
        model.setRowCount(0);
        while (rs.next()) {
            Object[] rowData = {
                    rs.getString("CNo"),
                    rs.getString("CName"),
                    rs.getString("TName"),
                    rs.getString("CRoom"),
                    rs.getString("CTime")
            };
            model.addRow(rowData);
        }
    }

    public void fillSelectedCourseByQuery(String SNo,String CNo,String CName,String TName){
        try {
            ResultSet rs = ScoreDao.getSelectedCourseByQuery(SNo, CNo,CName, TName);
            DefaultTableModel model = (DefaultTableModel) table3.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("CNo"),
                        rs.getString("CName"),
                        rs.getString("TName"),
                        rs.getString("CRoom"),
                        rs.getString("CTime")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null, m);
    }

    public void clearTextfield(JTextField[] t){
        for(int i =0;i<t.length; i++){
            t[i].setText("");
        }
    }
}
