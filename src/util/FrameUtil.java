package util;

import javax.swing.*;

public class FrameUtil {
    //显示提示信息
    public static void showMessageBox(String message) {
        JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    //显示错误信息
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "错误", JOptionPane.ERROR_MESSAGE);
    }

}
