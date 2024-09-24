package model;

public class Course {
    private String CNo;
    private String CName;
    private String CCredit;
    public Course() {
        this.CNo = null;
        this.CName = null;
        this.CCredit = null;
    }
    public Course(String CNo, String CName) {
        this.CNo = CNo;
        this.CName = CName;
    }

    public Course(String CNo, String CName, String CCredit) {
        this.CNo = CNo;
        this.CName = CName;
        this.CCredit = CCredit;
    }

    public String getCNo() {
        return CNo;
    }

    public void setCNo(String CNo) {
        this.CNo = CNo;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCCredit() {
        return CCredit;
    }

    public void setCCredit(String CCredit) {
        this.CCredit = CCredit;
    }
}
