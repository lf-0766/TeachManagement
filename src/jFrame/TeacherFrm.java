/*
 * Created by JFormDesigner on Thu Jun 01 12:04:41 CST 2023
 */

package jFrame;

import java.awt.event.*;

import com.mysql.cj.result.Row;
import com.mysql.cj.xdevapi.Table;
import dao.TeachDao;
import dao.TeacherDao;
import model.Teacher;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.swingx.VerticalLayout;
import util.StringUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static dao.ScoreDao.*;
import static dao.TeachDao.closeCourse;
import static dao.TeachDao.searchCourseByQuery;
import static util.FrameUtil.showErrorMessage;
import static util.FrameUtil.showMessageBox;
import static util.StringUtil.isBlank;
import static util.StringUtil.trim;
import org.apache.poi.xssf.usermodel.*;

/**
 * @author admin
 */
public class TeacherFrm extends JFrame {

    private Teacher nowTeacher;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPanel;
    private JTabbedPane tabbedPane1;
    private JPanel panel7;
    private JTabbedPane tabbedPane2;
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
    private JPanel panel10;
    private JTabbedPane tabbedPane3;
    private JPanel openCoursePanel;
    private JScrollPane scrollPane4;
    private JTable table3;
    private JPanel panel11;
    private JPanel panel12;
    private JLabel label10;
    private JLabel label11;
    private JTextField textField8;
    private JLabel label12;
    private JTextField textField9;
    private JButton button4;
    private JLabel label5;
    private JPanel panel13;
    private JLabel label13;
    private JLabel label14;
    private JTextField textField10;
    private JLabel label15;
    private JTextField textField11;
    private JButton button5;
    private JPanel panel14;
    private JLabel label16;
    private JLabel label18;
    private JTextField textField13;
    private JLabel label20;
    private JTextField textField15;
    private JLabel label21;
    private JTextField textField14;
    private JButton button6;
    private JPanel panel15;
    private JPanel panel17;
    private JTabbedPane tabbedPane4;
    private JPanel enrollScore;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel6;
    private JLabel label6;
    private JTextField textField4;
    private JLabel label7;
    private JTextField textField5;
    private JLabel label8;
    private JTextField textField6;
    private JButton button2;
    private JPanel panel18;
    private JPanel enrollScore2;
    private JScrollPane scrollPane5;
    private JTable table5;
    private JPanel panel19;
    private JLabel label31;
    private JTextField textField22;
    private JLabel label32;
    private JTextField textField23;
    private JLabel label33;
    private JTextField textField24;
    private JTextField textField25;
    private JButton alterScoreAndSubmit;
    private JButton refreshAuditRecord;
    private JPanel searchScore;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JPanel panel9;
    private JLabel label22;
    private JTextField textField17;
    private JLabel label23;
    private JTextField textField18;
    private JLabel label24;
    private JComboBox<String> comboBox1;
    private JLabel label25;
    private JComboBox<String> comboBox2;
    private JButton button8;
    private JButton GradeExportButton;
    private JPanel statistics;
    private JScrollPane scrollPane3;
    private JTable table4;
    private JPanel panel16;
    private JLabel label26;
    private JComboBox<String> comboBox3;
    private JLabel label27;
    private JTextField textField19;
    private JLabel label29;
    private JTextField textField21;
    private JLabel label28;
    private JTextField textField20;
    private JLabel label30;
    private JComboBox<String> comboBox4;
    private JButton button9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public TeacherFrm(Teacher t) {
        nowTeacher = t;
        initComponents();
        setTitle("教师页面    当前用户：" + nowTeacher.getTName());
    }

    public static void main(String[] args) {
        new TeacherFrm(new Teacher("052508", "李华", "123"));
    }

    private void button1MousePressed(MouseEvent e) {
        // TODO add your code here
        String oldPwd = textField1.getText();
        String newPwd = textField2.getText();
        String confirmPwd = textField3.getText();
        if (!StringUtil.isEqual(newPwd, confirmPwd)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
        } else {
            Teacher temp = nowTeacher;
            temp.setPwd(newPwd);
            try {
                TeacherDao.alterTeacherPwd(temp, oldPwd);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "原密码错误！");
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, "修改成功");
        }
    }

    private void table3MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table3, new JTextField[]{textField10, textField11});
    }

    private void button4MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            fillTable(table3, searchCourseByQuery(nowTeacher.getTNo(), textField8.getText(), textField9.getText()));
        } catch (SQLException ex) {
            showMessage("数据库异常");
            throw new RuntimeException(ex);
        }
    }

    private void button5MousePressed(MouseEvent e) {
        // TODO add your code here
        int r = 0;
        try {
            r = closeCourse(nowTeacher.getTNo(), textField10.getText());
        } catch (SQLException ex) {
            showMessage("数据库异常");
            throw new RuntimeException(ex);
        }
        switch (r) {
            case 0:
                showMessage("结课成功");
                break;
            case 1:
                showMessage("不存在课程");
                break;
            case 2:
                showMessage("未知错误");
                break;
        }
        clearTextfield(new JTextField[]{textField10, textField11});
        try {
            fillTable(table3, searchCourseByQuery(nowTeacher.getTNo(), textField8.getText(), textField9.getText()));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void button6MousePressed(MouseEvent e) {
        // TODO add your code here
        int r = TeachDao.openCourse(nowTeacher.getTNo(), textField13.getText(), textField15.getText(), textField14.getText());
        switch (r) {
            case 0:
                showMessage("开课成功！");
                break;
            case 1:
                showMessage("不存在课程！");
                break;
            case 2:
                showMessage("数据库异常！");
                break;
        }
        try {
            fillTable(table3, TeachDao.getTeachByTNo(nowTeacher.getTNo()));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void fillTable(JTable table, ResultSet rs) throws SQLException {
//        DefaultTableModel model = new DefaultTableModel();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
//         添加列名
//        Vector<String> columnNames = new Vector<String>();
//        for (int i = 1; i <= columnCount; i++) {
//            columnNames.add(metaData.getColumnName(i));
//        }
//        model.setColumnIdentifiers(columnNames);

        // 添加行数据
        while (rs.next()) {
            Vector<Object> rowData = new Vector<Object>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.add(rs.getObject(i));
            }
            model.addRow(rowData);
        }

        table.setModel(model);
    }

    public void switchPanel(JPanel p) {
        CardLayout cardLayout = new CardLayout();
        dialogPanel.setLayout(cardLayout);
        dialogPanel.add(p, "a");
        cardLayout.show(dialogPanel, "a");
    }

    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null, m);
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
            String s =  String.valueOf(rowData[i]);
            t[i].setText(s);
        }
//        textField6.setText((String) rowData[0]);
//        textField7.setText((String) rowData[2]);
    }

    public void clearTextfield(JTextField[] t) {
        for (int i = 0; i < t.length; i++) {
            t[i].setText("");
        }
    }


    private void table1MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table1, new JTextField[]{new JTextField(), textField4,textField5});
    }

    private void button2MousePressed(MouseEvent e) {
        // TODO add your code here
        if(isBlank(textField4.getText())){
            showMessage("请选择记录");
        }
        else if (isBlank(textField6.getText())) {
            showMessage("请输入成绩！");

        }else if(Integer.parseInt(textField6.getText()) < 0){
            showMessage("成绩输入不合法！");
        }else {
            try {
                int r = enrollScore(trim(textField4.getText()), trim(textField5.getText()), Double.parseDouble(textField6.getText()));
                if (r != 0) {
                    showMessage("数据库异常！");
                } else {
                    showMessage("登记成功："+textField4.getText()+" "+textField5.getText()+""+textField6.getText());
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    fillTable(table1, searchNullScoreList(nowTeacher.getTNo()));
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void menuItem6MousePressed(MouseEvent e) {
        // TODO add your code here
        new AddScore(nowTeacher);
    }

    private void comboBox3ItemStateChanged(ItemEvent e) {
        // TODO add your code here
        int selectedIndex = comboBox3.getSelectedIndex();  // 获取当前选择的选项的索引

        if(selectedIndex == 0) {
            textField19.setEnabled(true);  // textField19 可用
            textField21.setEnabled(false);  // textField21 不可用
            textField20.setEnabled(false);  // textField20 不可用
            comboBox4.setEnabled(false);  // comboBox4 不可用
        } else if(selectedIndex == 1) {
            textField19.setEnabled(false);  // textField19 不可用
            textField21.setEnabled(true);  // textField21 可用
            textField20.setEnabled(false);  // textField20 不可用
            comboBox4.setEnabled(false);  // comboBox4 不可用
        } else if(selectedIndex == 2) {
            textField19.setEnabled(false);  // textField19 不可用
            textField21.setEnabled(false);  // textField21 不可用
            textField20.setEnabled(true);  // textField20 可用
            comboBox4.setEnabled(true);  // comboBox4 可用
        }
    }

    private void button9MousePressed(MouseEvent e) {
        // TODO add your code here
        ResultSet rs = searchStatisticByQuery(textField19.getText(),textField21.getText(),textField20.getText(), (String) comboBox4.getSelectedItem(), comboBox3.getSelectedIndex()+1);
        fillTableWithHead(table4,rs);
    }

    private void button8MousePressed(MouseEvent e) {
        // TODO add your code here
        ResultSet rs = searchScoreByQuery(textField17.getText(),textField18.getText(), (String) comboBox1.getSelectedItem(),comboBox2.getSelectedIndex());
        try {
            fillTable(table2,rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

//    private void table2MousePressed(MouseEvent e) {
//        // TODO add your code here
//        fillSelect(table2,new JTextField[]{new JTextField(),textField7,new JTextField(),textField12,textField16,new JTextField()});
//    }

//    private void button3MousePressed(MouseEvent e) {
//        // TODO add your code here
//        int r = alterScore(textField7.getText(),textField12.getText(), Double.parseDouble(textField16.getText()));
//        if(r == 0){
//            showMessage("修改失败");
//        }else{
//            showMessage("修改成功");
//            ResultSet rs = searchScoreByQuery(textField17.getText(),textField18.getText(), (String) comboBox1.getSelectedItem(),comboBox2.getSelectedIndex());
//            try {
//                fillTable(table2,rs);
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//
//    }

//    private void button7MousePressed(MouseEvent e) {
//    // TODO add your code here
//        String sname = textField7.getText();
//        String cname = textField12.getText();
//        try {
//            int result = delScore(sname, cname);
//            if (result > 0) {
//                showMessage("删除成功！");
//            } else {
//                showMessage("未找到匹配的记录！");
//            }
//        } catch (SQLException ex) {
//            showMessage("数据库异常：" + ex.getMessage());
//        }
//        ResultSet rs = searchScoreByQuery(textField17.getText(),textField18.getText(), (String) comboBox1.getSelectedItem(),comboBox2.getSelectedIndex());
//        try {
//            fillTable(table2,rs);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    private void accountPanelComponentShown(ComponentEvent e) {
        // TODO add your code here
    }

    private void openCoursePanelComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillTable(table3, TeachDao.getTeachByTNo(nowTeacher.getTNo()));
        } catch (SQLException ex) {
            showMessage("数据库异常！");
            throw new RuntimeException(ex);
        }
    }

    private void enrollScoreComponentShown(ComponentEvent e) {
        // TODO add your code here
        try {
            fillTable(table1, searchNullScoreList(nowTeacher.getTNo()));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void searchScoreComponentShown(ComponentEvent e) {
        // TODO add your code here
        ResultSet rs = searchScoreByQuery(textField17.getText(),textField18.getText(), (String) comboBox1.getSelectedItem(),comboBox2.getSelectedIndex());
        try {
            fillTable(table2,rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void statisticsComponentShown(ComponentEvent e) {
        // TODO add your code here
    }

    private void TeacherPanelAuditRecordShown(ComponentEvent e) {
        // TODO add your code here
        try {
            ResultSet rs = getAuditRecord(nowTeacher.getTNo());
            fillTable(table5,rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void alterScoreAndSubmitMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(isBlank(textField25.getText())){
            showMessage("请选择成绩！");
        } else if (!"审核驳回".equals(textField25.getText())) {
            showMessage("成绩正在审核流程中，无需修改！");
        }else{
            int r  = alterScore(textField22.getText(),textField23.getText(), Double.parseDouble(textField24.getText()));
            if(r != 0 ){
                showMessage("操作成功！");
            }else{
                showMessage("操作失败！");
            }
        }
        clearTextfield(new JTextField[]{textField22, textField23, textField24,textField25});
    }

    private void table2MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void refreshAuditRecordMouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            ResultSet rs = getAuditRecord(nowTeacher.getTNo());
            fillTable(table5,rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        clearTextfield(new JTextField[]{textField22,textField23,textField24,textField25});
    }

    private void table5MousePressed(MouseEvent e) {
        // TODO add your code here
        fillSelect(table5,new JTextField[]{new JTextField(),textField22,textField23,textField24,textField25});
    }

    public static void exportGradeSheet(JTable table, String filePath) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (FileWriter writer = new FileWriter(filePath)) {
            // 写入表头
            for (int column = 0; column < model.getColumnCount(); column++) {
                writer.write(model.getColumnName(column) + "\t");
            }
            writer.write("\n");
            // 写入每行数据
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int column = 0; column < model.getColumnCount(); column++) {
                    writer.write(model.getValueAt(row, column).toString() + "\t");
                }
                writer.write("\n");
            }
            showMessageBox("导出成绩成功！");
        } catch (IOException e) {
            showErrorMessage("导出成绩失败！");
        }
    }
    public static String chooseSaveLocation() {
        String initialFileName = "score.xlsx";
        String initialDirectory = "C://";
        JFileChooser fileChooser = new JFileChooser(initialDirectory);

        // 设置保存文件的过滤器（可选）
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel文件 (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);

        // 设置初始文件名
        fileChooser.setSelectedFile(new File(initialFileName));

        // 显示文件选择对话框
        int result = fileChooser.showSaveDialog(null);
        fileChooser.setVisible(true);
        // 处理用户选择的结果
        if (result == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的文件路径
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            return filePath;
        }

        return null; // 用户取消了选择
    }

    private void GradeExportButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String filePath = chooseSaveLocation();
        exportGradeSheet(table2, filePath);
    }

    public void fillTableWithHead(JTable t, ResultSet rs) {
        try {
            // 获取结果集中的元数据
            ResultSetMetaData metaData = rs.getMetaData();

            // 获取结果集中的列数
            int columnCount = metaData.getColumnCount();

            // 创建表格模型
            DefaultTableModel tableModel = new DefaultTableModel();

            // 设置表格模型的列名
            Vector<String> columnNames = new Vector<>();
            for(int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            tableModel.setColumnIdentifiers(columnNames);

            // 清除上一次的填充
            tableModel.setRowCount(0);

            // 遍历结果集中的所有行数据，将其添加到表格模型中
            while(rs.next()) {
                Vector<Object> rowData = new Vector<>();
                for(int i = 1; i <= columnCount; i++) {
                    rowData.add(rs.getObject(i));
                }
                tableModel.addRow(rowData);
            }

            // 设置表格的数据模型
            t.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel7 = new JPanel();
        tabbedPane2 = new JTabbedPane();
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
        panel10 = new JPanel();
        tabbedPane3 = new JTabbedPane();
        openCoursePanel = new JPanel();
        scrollPane4 = new JScrollPane();
        table3 = new JTable();
        panel11 = new JPanel();
        panel12 = new JPanel();
        label10 = new JLabel();
        label11 = new JLabel();
        textField8 = new JTextField();
        label12 = new JLabel();
        textField9 = new JTextField();
        button4 = new JButton();
        label5 = new JLabel();
        panel13 = new JPanel();
        label13 = new JLabel();
        label14 = new JLabel();
        textField10 = new JTextField();
        label15 = new JLabel();
        textField11 = new JTextField();
        button5 = new JButton();
        panel14 = new JPanel();
        label16 = new JLabel();
        label18 = new JLabel();
        textField13 = new JTextField();
        label20 = new JLabel();
        textField15 = new JTextField();
        label21 = new JLabel();
        textField14 = new JTextField();
        button6 = new JButton();
        panel15 = new JPanel();
        panel17 = new JPanel();
        tabbedPane4 = new JTabbedPane();
        enrollScore = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel6 = new JPanel();
        label6 = new JLabel();
        textField4 = new JTextField();
        label7 = new JLabel();
        textField5 = new JTextField();
        label8 = new JLabel();
        textField6 = new JTextField();
        button2 = new JButton();
        panel18 = new JPanel();
        enrollScore2 = new JPanel();
        scrollPane5 = new JScrollPane();
        table5 = new JTable();
        panel19 = new JPanel();
        label31 = new JLabel();
        textField22 = new JTextField();
        label32 = new JLabel();
        textField23 = new JTextField();
        label33 = new JLabel();
        textField24 = new JTextField();
        textField25 = new JTextField();
        alterScoreAndSubmit = new JButton();
        refreshAuditRecord = new JButton();
        searchScore = new JPanel();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        panel9 = new JPanel();
        label22 = new JLabel();
        textField17 = new JTextField();
        label23 = new JLabel();
        textField18 = new JTextField();
        label24 = new JLabel();
        comboBox1 = new JComboBox<>();
        label25 = new JLabel();
        comboBox2 = new JComboBox<>();
        button8 = new JButton();
        GradeExportButton = new JButton();
        statistics = new JPanel();
        scrollPane3 = new JScrollPane();
        table4 = new JTable();
        panel16 = new JPanel();
        label26 = new JLabel();
        comboBox3 = new JComboBox<>();
        label27 = new JLabel();
        textField19 = new JTextField();
        label29 = new JLabel();
        textField21 = new JTextField();
        label28 = new JLabel();
        textField20 = new JTextField();
        label30 = new JLabel();
        comboBox4 = new JComboBox<>();
        button9 = new JButton();

        //======== this ========
        setTitle("\u6559\u5e08\u7cfb\u7edf");
        setVisible(true);
        setPreferredSize(new Dimension(1000, 600));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPanel.setLayout(new CardLayout());

            //======== tabbedPane1 ========
            {
                tabbedPane1.setTabPlacement(SwingConstants.LEFT);

                //======== panel7 ========
                {
                    panel7.setLayout(new BorderLayout());

                    //======== tabbedPane2 ========
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
                        tabbedPane2.addTab("\u5bc6\u7801\u4fee\u6539", accountPanel);
                    }
                    panel7.add(tabbedPane2, BorderLayout.CENTER);
                }
                tabbedPane1.addTab("\u8d26\u6237\u7ba1\u7406", panel7);

                //======== panel10 ========
                {
                    panel10.setLayout(new BorderLayout());

                    //======== tabbedPane3 ========
                    {

                        //======== openCoursePanel ========
                        {
                            openCoursePanel.setBorder(new TitledBorder("\u5f00\u8bfe\u5217\u8868"));
                            openCoursePanel.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    openCoursePanelComponentShown(e);
                                }
                            });
                            openCoursePanel.setLayout(new BorderLayout());

                            //======== scrollPane4 ========
                            {

                                //---- table3 ----
                                table3.setModel(new DefaultTableModel(
                                    new Object[][] {
                                        {null, null, null, null},
                                    },
                                    new String[] {
                                        "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u5730\u70b9", "\u65f6\u95f4"
                                    }
                                ) {
                                    boolean[] columnEditable = new boolean[] {
                                        false, false, false, false
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
                            openCoursePanel.add(scrollPane4, BorderLayout.CENTER);

                            //======== panel11 ========
                            {
                                panel11.setLayout(new VerticalLayout());

                                //======== panel12 ========
                                {
                                    panel12.setBorder(new EmptyBorder(5, 0, 10, 0));
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
                                panel11.add(label5);

                                //======== panel13 ========
                                {
                                    panel13.setBorder(new EmptyBorder(10, 0, 20, 0));
                                    panel13.setLayout(new FlowLayout());

                                    //---- label13 ----
                                    label13.setText("\u505c\u5f00");
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
                                    button5.setText("\u505c\u5f00");
                                    button5.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button5MousePressed(e);
                                        }
                                    });
                                    panel13.add(button5);
                                }
                                panel11.add(panel13);

                                //======== panel14 ========
                                {
                                    panel14.setLayout(new FlowLayout());

                                    //---- label16 ----
                                    label16.setText("\u5f00\u8bfe");
                                    label16.setForeground(Color.blue);
                                    panel14.add(label16);

                                    //---- label18 ----
                                    label18.setText("\u8bfe\u7a0b\u540d");
                                    panel14.add(label18);

                                    //---- textField13 ----
                                    textField13.setColumns(7);
                                    panel14.add(textField13);

                                    //---- label20 ----
                                    label20.setText("\u5730\u70b9");
                                    panel14.add(label20);

                                    //---- textField15 ----
                                    textField15.setColumns(7);
                                    panel14.add(textField15);

                                    //---- label21 ----
                                    label21.setText("\u65f6\u95f4");
                                    panel14.add(label21);

                                    //---- textField14 ----
                                    textField14.setColumns(7);
                                    panel14.add(textField14);

                                    //---- button6 ----
                                    button6.setText("\u5f00\u8bfe");
                                    button6.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button6MousePressed(e);
                                        }
                                    });
                                    panel14.add(button6);
                                }
                                panel11.add(panel14);

                                //======== panel15 ========
                                {
                                    panel15.setLayout(new FlowLayout());
                                }
                                panel11.add(panel15);
                            }
                            openCoursePanel.add(panel11, BorderLayout.EAST);
                        }
                        tabbedPane3.addTab("\u5f00\u8bfe\u7ba1\u7406", openCoursePanel);
                    }
                    panel10.add(tabbedPane3, BorderLayout.CENTER);
                }
                tabbedPane1.addTab("\u8bfe\u7a0b\u7ba1\u7406", panel10);

                //======== panel17 ========
                {
                    panel17.setLayout(new BorderLayout());

                    //======== tabbedPane4 ========
                    {

                        //======== enrollScore ========
                        {
                            enrollScore.setBorder(new TitledBorder("\u672a\u767b\u8bb0\u5b66\u751f\u5217\u8868"));
                            enrollScore.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    enrollScoreComponentShown(e);
                                }
                            });
                            enrollScore.setLayout(new BorderLayout());

                            //======== scrollPane1 ========
                            {

                                //---- table1 ----
                                table1.setModel(new DefaultTableModel(
                                    new Object[][] {
                                        {null, null, null},
                                    },
                                    new String[] {
                                        "\u5b66\u53f7", "\u59d3\u540d", "\u8bfe\u7a0b\u540d"
                                    }
                                ));
                                table1.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        table1MousePressed(e);
                                    }
                                });
                                scrollPane1.setViewportView(table1);
                            }
                            enrollScore.add(scrollPane1, BorderLayout.CENTER);

                            //======== panel6 ========
                            {
                                panel6.setLayout(new FlowLayout());

                                //---- label6 ----
                                label6.setText("\u59d3\u540d");
                                panel6.add(label6);

                                //---- textField4 ----
                                textField4.setEditable(false);
                                textField4.setColumns(8);
                                panel6.add(textField4);

                                //---- label7 ----
                                label7.setText("\u8bfe\u7a0b");
                                panel6.add(label7);

                                //---- textField5 ----
                                textField5.setEditable(false);
                                textField5.setColumns(8);
                                panel6.add(textField5);

                                //---- label8 ----
                                label8.setText("\u6210\u7ee9");
                                panel6.add(label8);

                                //---- textField6 ----
                                textField6.setColumns(5);
                                panel6.add(textField6);

                                //---- button2 ----
                                button2.setText("\u767b\u8bb0");
                                button2.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button2MousePressed(e);
                                    }
                                });
                                panel6.add(button2);
                            }
                            enrollScore.add(panel6, BorderLayout.SOUTH);
                        }
                        tabbedPane4.addTab("\u6210\u7ee9\u767b\u8bb0", enrollScore);

                        //======== panel18 ========
                        {
                            panel18.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    TeacherPanelAuditRecordShown(e);
                                }
                            });
                            panel18.setLayout(new BorderLayout());

                            //======== enrollScore2 ========
                            {
                                enrollScore2.setBorder(new TitledBorder("\u6210\u7ee9\u5ba1\u6838\u5217\u8868"));
                                enrollScore2.addComponentListener(new ComponentAdapter() {
                                    @Override
                                    public void componentShown(ComponentEvent e) {
                                        enrollScoreComponentShown(e);
                                    }
                                });
                                enrollScore2.setLayout(new BorderLayout());

                                //======== scrollPane5 ========
                                {

                                    //---- table5 ----
                                    table5.setModel(new DefaultTableModel(
                                        new Object[][] {
                                        },
                                        new String[] {
                                            "\u5b66\u53f7", "\u59d3\u540d", "\u8bfe\u7a0b\u540d", "\u6210\u7ee9", "\u5f53\u524d\u72b6\u6001", "\u5ba1\u6838\u5907\u6ce8"
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
                                enrollScore2.add(scrollPane5, BorderLayout.CENTER);

                                //======== panel19 ========
                                {
                                    panel19.setLayout(new FlowLayout());

                                    //---- label31 ----
                                    label31.setText("\u59d3\u540d");
                                    panel19.add(label31);

                                    //---- textField22 ----
                                    textField22.setColumns(8);
                                    textField22.setEditable(false);
                                    panel19.add(textField22);

                                    //---- label32 ----
                                    label32.setText("\u8bfe\u7a0b");
                                    panel19.add(label32);

                                    //---- textField23 ----
                                    textField23.setEditable(false);
                                    textField23.setColumns(8);
                                    panel19.add(textField23);

                                    //---- label33 ----
                                    label33.setText("\u6210\u7ee9");
                                    panel19.add(label33);

                                    //---- textField24 ----
                                    textField24.setColumns(5);
                                    panel19.add(textField24);

                                    //---- textField25 ----
                                    textField25.setColumns(5);
                                    textField25.setVisible(false);
                                    panel19.add(textField25);

                                    //---- alterScoreAndSubmit ----
                                    alterScoreAndSubmit.setText("\u4fee\u6539\u5e76\u63d0\u4ea4");
                                    alterScoreAndSubmit.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            alterScoreAndSubmitMouseClicked(e);
                                        }
                                    });
                                    panel19.add(alterScoreAndSubmit);

                                    //---- refreshAuditRecord ----
                                    refreshAuditRecord.setText("\u5237\u65b0");
                                    refreshAuditRecord.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            refreshAuditRecordMouseClicked(e);
                                        }
                                    });
                                    panel19.add(refreshAuditRecord);
                                }
                                enrollScore2.add(panel19, BorderLayout.SOUTH);
                            }
                            panel18.add(enrollScore2, BorderLayout.CENTER);
                        }
                        tabbedPane4.addTab("\u6210\u7ee9\u5ba1\u6838\u7ba1\u7406", panel18);

                        //======== searchScore ========
                        {
                            searchScore.setBorder(new TitledBorder("\u6210\u7ee9\u67e5\u8be2"));
                            searchScore.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    searchScoreComponentShown(e);
                                }
                            });
                            searchScore.setLayout(new BorderLayout());

                            //======== scrollPane2 ========
                            {

                                //---- table2 ----
                                table2.setModel(new DefaultTableModel(
                                    new Object[][] {
                                    },
                                    new String[] {
                                        "\u5b66\u53f7", "\u59d3\u540d", "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u6210\u7ee9", "\u5b66\u671f"
                                    }
                                ));
                                table2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                                table2.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        table2MousePressed(e);
                                    }
                                });
                                scrollPane2.setViewportView(table2);
                            }
                            searchScore.add(scrollPane2, BorderLayout.CENTER);

                            //======== panel9 ========
                            {
                                panel9.setLayout(new FlowLayout());

                                //---- label22 ----
                                label22.setText("\u5b66\u53f7");
                                panel9.add(label22);

                                //---- textField17 ----
                                textField17.setColumns(8);
                                panel9.add(textField17);

                                //---- label23 ----
                                label23.setText("\u8bfe\u7a0b\u53f7");
                                panel9.add(label23);

                                //---- textField18 ----
                                textField18.setColumns(8);
                                panel9.add(textField18);

                                //---- label24 ----
                                label24.setText("\u5b66\u671f");
                                panel9.add(label24);

                                //---- comboBox1 ----
                                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "\u5168\u90e8\u5b66\u671f",
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8"
                                }));
                                panel9.add(comboBox1);

                                //---- label25 ----
                                label25.setText("\u6392\u5e8f");
                                panel9.add(label25);

                                //---- comboBox2 ----
                                comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "\u9ed8\u8ba4",
                                    "\u5b66\u53f7",
                                    "\u5b66\u671f",
                                    "\u6210\u7ee9",
                                    "\u8bfe\u7a0b\u53f7"
                                }));
                                panel9.add(comboBox2);

                                //---- button8 ----
                                button8.setText("\u67e5\u8be2");
                                button8.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button8MousePressed(e);
                                    }
                                });
                                panel9.add(button8);

                                //---- GradeExportButton ----
                                GradeExportButton.setText("\u5bfc\u51fa");
                                GradeExportButton.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        GradeExportButtonMouseClicked(e);
                                    }
                                });
                                panel9.add(GradeExportButton);
                            }
                            searchScore.add(panel9, BorderLayout.NORTH);
                        }
                        tabbedPane4.addTab("\u6210\u7ee9\u67e5\u8be2", searchScore);

                        //======== statistics ========
                        {
                            statistics.setBorder(new TitledBorder("\u6210\u7ee9\u7edf\u8ba1"));
                            statistics.addComponentListener(new ComponentAdapter() {
                                @Override
                                public void componentShown(ComponentEvent e) {
                                    statisticsComponentShown(e);
                                }
                            });
                            statistics.setLayout(new BorderLayout());

                            //======== scrollPane3 ========
                            {
                                scrollPane3.setViewportView(table4);
                            }
                            statistics.add(scrollPane3, BorderLayout.CENTER);

                            //======== panel16 ========
                            {
                                panel16.setLayout(new FlowLayout());

                                //---- label26 ----
                                label26.setText("\u7edf\u8ba1\u4f9d\u636e");
                                panel16.add(label26);

                                //---- comboBox3 ----
                                comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "\u73ed\u7ea7",
                                    "\u8bfe\u7a0b",
                                    "\u5b66\u751f|\u5b66\u671f"
                                }));
                                comboBox3.addItemListener(e -> comboBox3ItemStateChanged(e));
                                panel16.add(comboBox3);

                                //---- label27 ----
                                label27.setText("\u73ed\u7ea7");
                                panel16.add(label27);

                                //---- textField19 ----
                                textField19.setColumns(5);
                                panel16.add(textField19);

                                //---- label29 ----
                                label29.setText("\u8bfe\u7a0b\u53f7");
                                panel16.add(label29);

                                //---- textField21 ----
                                textField21.setEnabled(false);
                                textField21.setColumns(5);
                                panel16.add(textField21);

                                //---- label28 ----
                                label28.setText("\u5b66\u53f7");
                                panel16.add(label28);

                                //---- textField20 ----
                                textField20.setEnabled(false);
                                textField20.setColumns(5);
                                panel16.add(textField20);

                                //---- label30 ----
                                label30.setText("\u5b66\u671f");
                                panel16.add(label30);

                                //---- comboBox4 ----
                                comboBox4.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8"
                                }));
                                comboBox4.setEnabled(false);
                                panel16.add(comboBox4);

                                //---- button9 ----
                                button9.setText("\u67e5\u8be2");
                                button9.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        button9MousePressed(e);
                                    }
                                });
                                panel16.add(button9);
                            }
                            statistics.add(panel16, BorderLayout.NORTH);
                        }
                        tabbedPane4.addTab("\u6210\u7ee9\u7edf\u8ba1", statistics);
                    }
                    panel17.add(tabbedPane4, BorderLayout.CENTER);
                }
                tabbedPane1.addTab("\u6210\u7ee9\u7ba1\u7406", panel17);
            }
            dialogPanel.add(tabbedPane1, "card8");
        }
        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


}
