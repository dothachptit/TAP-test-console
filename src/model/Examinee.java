package model;



public class Examinee {
    private int id;
    private int totalScore;
    private int divisionScore;
    Division division = new Division();
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }



    public Examinee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getDivisionScore() {
        return divisionScore;
    }

    public void setDivisionScore(int divisionScore) {
        this.divisionScore = divisionScore;
    }

    @Override
    public String toString() {
        return "Examinee{" +
                "id=" + id +
                ", totalScore=" + totalScore +
                ", divisionScore=" + divisionScore +
                ", division=" + division +
                ", Status='" + Status + '\'' +
                '}';
    }
}
