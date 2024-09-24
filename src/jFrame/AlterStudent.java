/*
 * Created by JFormDesigner on Thu Jun 01 15:47:28 CST 2023
 */

package jFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.StudentDao;
import model.Student;
import org.jdesktop.swingx.*;

/**
 * @author admin
 */
public class AlterStudent extends JFrame {
    private Student s;
    public AlterStudent(Student st) {
        s = st;
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(
                (screenSize.width - 300) / 2,
                (screenSize.height - 300) / 2,
                300,
                300
        );
        textField1.setText(s.getSNo());
        textField2.setText(s.getSName());
        textField3.setText(s.getSClass());
        textField4.setText(s.getSPwd());
        comboBox2.setSelectedItem(s.getEnrollYear());
        comboBox1.setSelectedItem(s.getSSex());
        setVisible(true);
    }

    private void button7MousePressed(MouseEvent e) {
        // TODO add your code here
        int r = StudentDao.alterStudent(textField1.getText(), textField2.getText(), (String) comboBox1.getSelectedItem(), textField3.getText(), textField4.getText(), Integer.parseInt((String) comboBox2.getSelectedItem()));
        switch (r) {
            case 0:
                showMessage("修改成功");
                break;
            case 2:
                showMessage("未知错误！");
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        textField2 = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        panel5 = new JPanel();
        label4 = new JLabel();
        textField3 = new JTextField();
        panel6 = new JPanel();
        label5 = new JLabel();
        textField4 = new JTextField();
        panel7 = new JPanel();
        label6 = new JLabel();
        comboBox2 = new JComboBox<>();
        panel8 = new JPanel();
        button7 = new JButton();

        //======== this ========
        setTitle("\u5b66\u751f\u4fe1\u606f\u4fee\u6539");
        var contentPane = getContentPane();
        contentPane.setLayout(new VerticalLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());

            //---- label1 ----
            label1.setText("\u5b66\u53f7");
            panel1.add(label1);

            //---- textField1 ----
            textField1.setColumns(10);
            textField1.setEditable(false);
            panel1.add(textField1);
        }
        contentPane.add(panel1);

        //======== panel2 ========
        {
            panel2.setLayout(new FlowLayout());

            //---- label2 ----
            label2.setText("\u59d3\u540d");
            panel2.add(label2);

            //---- textField2 ----
            textField2.setColumns(10);
            panel2.add(textField2);
        }
        contentPane.add(panel2);

        //======== panel3 ========
        {
            panel3.setLayout(new FlowLayout());

            //---- label3 ----
            label3.setText("\u6027\u522b");
            panel3.add(label3);

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u7537",
                "\u5973"
            }));
            panel3.add(comboBox1);
        }
        contentPane.add(panel3);

        //======== panel5 ========
        {
            panel5.setLayout(new FlowLayout());

            //---- label4 ----
            label4.setText("\u73ed\u7ea7");
            panel5.add(label4);

            //---- textField3 ----
            textField3.setColumns(10);
            panel5.add(textField3);
        }
        contentPane.add(panel5);

        //======== panel6 ========
        {
            panel6.setLayout(new FlowLayout());

            //---- label5 ----
            label5.setText("\u5bc6\u7801");
            panel6.add(label5);

            //---- textField4 ----
            textField4.setColumns(10);
            panel6.add(textField4);
        }
        contentPane.add(panel6);

        //======== panel7 ========
        {
            panel7.setLayout(new FlowLayout());

            //---- label6 ----
            label6.setText("\u5165\u5b66\u5e74\u4efd");
            panel7.add(label6);

            //---- comboBox2 ----
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                "2018",
                "2019",
                "2020",
                "2021",
                "2022",
                "2023",
                "2024",
                "2025",
                "2026"
            }));
            comboBox2.setSelectedIndex(3);
            panel7.add(comboBox2);
        }
        contentPane.add(panel7);

        //======== panel8 ========
        {
            panel8.setLayout(new FlowLayout());

            //---- button7 ----
            button7.setText("\u4fee\u6539");
            button7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    button7MousePressed(e);
                }
            });
            panel8.add(button7);
        }
        contentPane.add(panel8);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null, m);
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textField2;
    private JPanel panel3;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JPanel panel5;
    private JLabel label4;
    private JTextField textField3;
    private JPanel panel6;
    private JLabel label5;
    private JTextField textField4;
    private JPanel panel7;
    private JLabel label6;
    private JComboBox<String> comboBox2;
    private JPanel panel8;
    private JButton button7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
