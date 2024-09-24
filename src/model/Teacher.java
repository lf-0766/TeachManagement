package model;

public class Teacher {
    String TNo;
    String TName;
    String pwd;



    public Teacher(String TNo, String TName, String pwd) {
        this.TNo = TNo;
        this.TName = TName;
        this.pwd = pwd;
    }

    public String getTNo() {
        return TNo;
    }

    public void setTNo(String TNo) {
        this.TNo = TNo;
    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
