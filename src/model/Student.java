package model;

import java.util.Calendar;

public class Student {
    private String SNo;
    private String SName;
    private String SSex;
    private String SPwd;
    private String SClass;
    private String SDept;
    private int enrollYear;

    public Student() {
    }

    public int getEnrollYear() {
        return enrollYear;
    }

    public void setEnrollYear(int enrollYear) {
        this.enrollYear = enrollYear;
    }

    public int getTerm(){

        int yearNow = Calendar.getInstance().get(Calendar.YEAR);
        int monthNow = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int termNum = (yearNow - enrollYear) * 2; // 计算入学年份到当前年份的学期数
        if (monthNow < 9) {
            termNum -= 1; // 如果还没到9月份，当前学期为上学期，需要减1
        }
        if (termNum <= 0) {
            return -1;
        } else if (termNum == 1) {
            return 1;
        } else if (termNum == 2) {
            return 2;
        } else {
            return  (termNum - 1) ; // 计算当前学期的名称
        }

}

    public String getSNo() {
        return SNo;
    }

    public void setSNo(String SNo) {
        this.SNo = SNo;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getSSex() {
        return SSex;
    }

    public void setSSex(String SSex) {
        this.SSex = SSex;
    }

    public Student(String SNo, String SName, String SSex, String SPwd, String SClass, int enrollYear) {
        this.SNo = SNo;
        this.SName = SName;
        this.SSex = SSex;
        this.SPwd = SPwd;
        this.SClass = SClass;
        this.enrollYear = enrollYear;
    }

    public String getSPwd() {
        return SPwd;
    }

    public void setSPwd(String SPwd) {
        this.SPwd = SPwd;
    }

    public String getSClass() {
        return SClass;
    }

    public void setSClass(String SClass) {
        this.SClass = SClass;
    }

    public String getSDept() {
        return SDept;
    }

    public void setSDept(String SDept) {
        this.SDept = SDept;
    }

    public Student(String SNo, String SName, String SSex,  String SPwd, String SClass) {
        this.SNo = SNo;
        this.SName = SName;
        this.SSex = SSex;
        this.SPwd = SPwd;
        this.SClass = SClass;
    }
}
