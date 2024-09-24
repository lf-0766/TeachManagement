/*
 * Created by JFormDesigner on Sat Jun 03 08:17:08 CST 2023
 */

package jFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Teacher;
import org.jdesktop.swingx.*;

import static dao.ScoreDao.addScore;


/**
 * @author admin
 */
public class AddScore extends JFrame {
    private Teacher teacher;
    public AddScore(Teacher t) {
        teacher = t;
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

    private void button9MousePressed(MouseEvent e) {
        // TODO add your code here
        int r = addScore(textField19.getText(),textField20.getText(), teacher.getTNo(),textField21.getText(), (String) comboBox2.getSelectedItem());
        if(r != 0) {
            showMessage("添加成功！");
        }else{
            showMessage("添加失败！");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel10 = new JPanel();
        panel16 = new JPanel();
        label24 = new JLabel();
        textField19 = new JTextField();
        panel17 = new JPanel();
        label25 = new JLabel();
        textField20 = new JTextField();
        panel18 = new JPanel();
        label26 = new JLabel();
        textField21 = new JTextField();
        panel19 = new JPanel();
        label27 = new JLabel();
        comboBox2 = new JComboBox<>();
        panel20 = new JPanel();
        panel21 = new JPanel();
        button9 = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u6210\u7ee9");
        var contentPane = getContentPane();
        contentPane.setLayout(new VerticalLayout());

        //======== panel10 ========
        {
            panel10.setLayout(new VerticalLayout());

            //======== panel16 ========
            {
                panel16.setLayout(new FlowLayout());

                //---- label24 ----
                label24.setText("\u5b66\u53f7");
                panel16.add(label24);

                //---- textField19 ----
                textField19.setColumns(10);
                panel16.add(textField19);
            }
            panel10.add(panel16);

            //======== panel17 ========
            {
                panel17.setLayout(new FlowLayout());

                //---- label25 ----
                label25.setText("\u8bfe\u7a0b\u53f7");
                panel17.add(label25);

                //---- textField20 ----
                textField20.setColumns(10);
                panel17.add(textField20);
            }
            panel10.add(panel17);

            //======== panel18 ========
            {
                panel18.setLayout(new FlowLayout());

                //---- label26 ----
                label26.setText("\u6210\u7ee9");
                panel18.add(label26);

                //---- textField21 ----
                textField21.setColumns(10);
                panel18.add(textField21);
            }
            panel10.add(panel18);

            //======== panel19 ========
            {
                panel19.setLayout(new FlowLayout());

                //---- label27 ----
                label27.setText("\u5b66\u671f");
                panel19.add(label27);

                //---- comboBox2 ----
                comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8"
                }));
                panel19.add(comboBox2);
            }
            panel10.add(panel19);

            //======== panel20 ========
            {
                panel20.setLayout(new FlowLayout());

                //======== panel21 ========
                {
                    panel21.setLayout(new FlowLayout());
                }
                panel20.add(panel21);

                //---- button9 ----
                button9.setText("\u6dfb\u52a0");
                button9.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        button9MousePressed(e);
                    }
                });
                panel20.add(button9);
            }
            panel10.add(panel20);
        }
        contentPane.add(panel10);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null, m);
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel10;
    private JPanel panel16;
    private JLabel label24;
    private JTextField textField19;
    private JPanel panel17;
    private JLabel label25;
    private JTextField textField20;
    private JPanel panel18;
    private JLabel label26;
    private JTextField textField21;
    private JPanel panel19;
    private JLabel label27;
    private JComboBox<String> comboBox2;
    private JPanel panel20;
    private JPanel panel21;
    private JButton button9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
