package model;

public class Score {
    private String SNo;
    private String TNo;
    private String CNo;
    private String grade;
    private int term;

    public Score(String SNo) {
        this.SNo = SNo;
    }

    public Score(String SNo, String CNo) {
        this.SNo = SNo;
        this.CNo = CNo;
    }

    public Score(String SNo, String TNo, String CNo) {
        this.SNo = SNo;
        this.TNo = TNo;
        this.CNo = CNo;
    }


    public Score(String SNo, String TNo, String CNo, String grade, int term) {
        this.SNo = SNo;
        this.TNo = TNo;
        this.CNo = CNo;
        this.grade = grade;
        this.term = term;
    }

    public String getSNo() {
        return SNo;
    }

    public void setSNo(String SNo) {
        this.SNo = SNo;
    }

    public String getTNo() {
        return TNo;
    }

    public void setTNo(String TNo) {
        this.TNo = TNo;
    }

    public String getCNo() {
        return CNo;
    }

    public void setCNo(String CNo) {
        this.CNo = CNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
