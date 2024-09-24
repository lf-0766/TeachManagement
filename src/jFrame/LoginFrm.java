/*
 * Created by JFormDesigner on Thu Jun 01 22:21:35 CST 2023
 */

package jFrame;

import model.Student;
import model.Teacher;
import model.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import static dao.StudentDao.studentLogin;
import static dao.TeacherDao.teacherLogin;
import static dao.AdminDao.login;

/**
 * @author admin
 */
public class LoginFrm extends JFrame {

    public LoginFrm() {
        initComponents();
    }

    public static void main(String[] args) {
        new LoginFrm();

    }

    private void button2MousePressed(MouseEvent e) {
        // TODO add your code here
        String role = (String) comboBox1.getSelectedItem();
        switch (role) {
            case "教师":
                loginAsTeacher();
                break;
            case "学生":
                loginAsStudent();
                break;
            case "管理员":
                loginAsAdmin();
                break;
            default:
                showMessage("请选者用户身份！");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label4 = new JLabel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        button2 = new JButton();

        //======== this ========
        setTitle("\u767b\u5f55\u9875");
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 76, 111, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 135, 53, 70, 37, 67, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 1.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};

        //---- label4 ----
        label4.setText("\u6559\u5b66\u7ba1\u7406\u7cfb\u7edf");
        label4.setFont(new Font("\u534e\u6587\u4e2d\u5b8b", Font.BOLD, 48));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label4, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 8f));
        contentPane.add(label1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- textField1 ----
        textField1.setColumns(10);
        textField1.setFont(textField1.getFont().deriveFont(textField1.getFont().getStyle() | Font.BOLD, textField1.getFont().getSize() + 8f));
        contentPane.add(textField1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 8f));
        contentPane.add(label2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- textField2 ----
        textField2.setColumns(10);
        textField2.setFont(textField2.getFont().deriveFont(textField2.getFont().getStyle() | Font.BOLD, textField2.getFont().getSize() + 8f));
        contentPane.add(textField2, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- label3 ----
        label3.setText("\u8eab\u4efd");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 8f));
        contentPane.add(label3, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u6559\u5e08",
            "\u5b66\u751f",
            "\u7ba1\u7406\u5458"
        }));
        comboBox1.setFont(comboBox1.getFont().deriveFont(comboBox1.getFont().getStyle() | Font.BOLD, comboBox1.getFont().getSize() + 5f));
        contentPane.add(comboBox1, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- button2 ----
        button2.setText("\u767b\u5f55");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 2f));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button2MousePressed(e);
            }
        });
        contentPane.add(button2, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void loginAsStudent() {
        String uname = textField1.getText();
        String pwd = textField2.getText();
        try {
            Student s = studentLogin(uname, pwd);
            if (s != null) {
                new StudentFrm(s);
                dispose();
            } else {
                showMessage("用户名或密码错误");
            }
        } catch (Exception ex) {
            showMessage("数据库异常：" + ex.getMessage());
        }
    }

    private void loginAsTeacher() {
        String uname = textField1.getText();
        String pwd = textField2.getText();
        try {
            Teacher t = teacherLogin(uname, pwd);
            if (t != null) {
                new TeacherFrm(t);
                dispose();
            } else {
                showMessage("用户名或密码错误");
            }
        } catch (Exception ex) {
            showMessage("数据库异常：" + ex.getMessage());
        }
    }

    private void loginAsAdmin() {
        String uname = textField1.getText();
        String pwd = textField2.getText();

        int rs = 0;
        try {
            rs = login(new Admin(uname, pwd));
            if (rs != -1) {
                new AdminFrm();
                dispose();
            } else
                showMessage("用户名或密码错误");
        } catch (SQLException ex) {
            showMessage("数据库异常：" + ex.getMessage());
        }
    }

    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null, m);
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label4;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
