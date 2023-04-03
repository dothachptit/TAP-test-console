package model;

import java.util.ArrayList;
import java.util.List;

public class Division {
    private int id;
    private String name;
    List<Subject> subjectList  = new ArrayList<>();

    private int minDivisionScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public int getMinDivisionScore() {
        return minDivisionScore;
    }

    public void setMinDivisionScore(int minDivisionScore) {
        this.minDivisionScore = minDivisionScore;
    }

    public Division() {
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjectList=" + subjectList +
                ", minDivisionScore=" + minDivisionScore +
                '}';
    }
}
