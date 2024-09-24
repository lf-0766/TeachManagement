/*
 * Created by JFormDesigner on Thu Jun 01 20:55:42 CST 2023
 */

package jFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jdesktop.swingx.*;

import static dao.TeacherDao.addTeacher;


/**
 * @author admin
 */
public class AddTeacher extends JFrame {
    public AddTeacher() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(
                (screenSize.width - 300) / 2,
                (screenSize.height - 300) / 2,
                300,
                300
        );
        setVisible(true);
    }


    private void button7MousePressed(MouseEvent e) {
        // TODO add your code here
        int r = addTeacher(textField1.getText(),textField2.getText(),textField3.getText());
        switch (r){
            case 0:
                showMessage("添加成功");
                break;
            case 1:
                showMessage("该课程已经存在");
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
        panel5 = new JPanel();
        label4 = new JLabel();
        textField3 = new JTextField();
        panel8 = new JPanel();
        button7 = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u6559\u5e08");
        var contentPane = getContentPane();
        contentPane.setLayout(new VerticalLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());

            //---- label1 ----
            label1.setText("\u5de5\u53f7");
            panel1.add(label1);

            //---- textField1 ----
            textField1.setColumns(10);
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

        //======== panel5 ========
        {
            panel5.setLayout(new FlowLayout());

            //---- label4 ----
            label4.setText("\u5bc6\u7801");
            panel5.add(label4);

            //---- textField3 ----
            textField3.setColumns(10);
            panel5.add(textField3);
        }
        contentPane.add(panel5);

        //======== panel8 ========
        {
            panel8.setLayout(new FlowLayout());

            //---- button7 ----
            button7.setText("\u6dfb\u52a0");
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
    private JPanel panel5;
    private JLabel label4;
    private JTextField textField3;
    private JPanel panel8;
    private JButton button7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
