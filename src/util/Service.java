package util;

import model.Division;
import model.Examinee;
import model.ExamineeSubject;
import model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Service {
    Scanner sc = new Scanner(System.in);
    List<Subject> subjectList = new ArrayList<>();
    List<Division> divisionList = new ArrayList<>();
    List<Examinee> examineeList= new ArrayList<>();
    List<ExamineeSubject> examineeSubjectList = new ArrayList<>();
    public void getSubjectInput() {
        System.out.println("Enter subjects: ");
        System.out.print("Number of subjects = ");
        int numOfSubject = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numOfSubject; i++) {
            Subject subject = new Subject();
            subject.setId(i+1);
            System.out.print("Name of subject " + (i + 1) + ": ");
            subject.setName(sc.nextLine());
            subject.setDivision(new Division());
            subjectList.add(subject);
        }
        System.out.println("Done");
        viewListSubject();
    }
    public void getDivisionInput(){
        System.out.println("Enter Division: ");
        System.out.print("Number of division = ");
        int numOfGroup = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numOfGroup; i++) {
          Division division =new Division();
            division.setId((i + 1));
            System.out.print("Name of division " + (i + 1) + ": ");
            division.setName(sc.nextLine());
            viewListSubject();
            while (true) {
                System.out.println("Enter subject id to add division (enter 0 to stop): ");
                Integer subjectId = Integer.parseInt(sc.nextLine());
                if (subjectId == 0) break;
                Subject subject = subjectList.stream()
                        .filter(e -> e.getId() == subjectId)
                        .findFirst().orElse(null);
                if (subject == null && subjectId!=0) {
                    System.out.println( "Subject ID " + subjectId + " is not exist");
                    continue;
                }
                division.getSubjectList().add(subject);
                subject.setDivision(division);
            }
           System.out.println("Min division score to pass " + (i + 1) + ": ");
           division.setMinDivisionScore(Integer.parseInt(sc.nextLine()));
           division.setSubjectList(subjectList);
           divisionList.add(division);

        }
        System.out.println("Done");
        viewListDivision();

    }
    public void getInputExaminee(){
        System.out.println("Enter Examinee: ");
        System.out.print("Number of Examinee = ");
        int numOfStudent = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numOfStudent; i++) {
            Examinee examinee = new Examinee();
            examinee.setId( (i + 1));
            System.out.println("Enter for " + examinee.getId());
            viewListDivision();
            while (true) {
                System.out.println("Enter division ID of Exminee: ");
                Integer divisionId = Integer.parseInt(sc.nextLine());
                Division division= divisionList.stream()
                        .filter(e -> e.getId() == divisionId)
                        .findFirst().orElse(null);
                if (division == null) {
                    System.out.println(divisionId+ " is not exist");
                    continue;
                }
                examinee.setDivision(division);
                break;
            }
            examineeList.add(examinee);
        }
        System.out.println("Done");

    }
    public void getInputExamineeSubject(){
        System.out.println("Enter examinee subjects: ");
        for (Examinee examinee : examineeList) {
            System.out.println(examinee.getDivision().getName());
            for (Subject subject : subjectList) {
                ExamineeSubject examineeSubject = new ExamineeSubject();
                examineeSubject.setId(examinee.getId()+"-"+subject.getName());
               examineeSubject.setExaminee(examinee);
                examineeSubject.setSubject(subject);
                System.out.print(subject.getName() + " score: ");
               examineeSubject.setScore(Integer.parseInt(sc.nextLine()));
                examineeSubjectList.add(examineeSubject);
            }

        }
        System.out.println("enter min total score: ");
        int minTotalScore = Integer.parseInt(sc.nextLine());
        for (Examinee examinee: examineeList) {
           Division division = examinee.getDivision();

            List<ExamineeSubject> subjectOfExaminee = examineeSubjectList.stream()
                    .filter(e -> e.getExaminee().getId() == examinee.getId())
                    .collect(Collectors.toList());

            subjectOfExaminee.forEach(e ->
                    examinee.setTotalScore(examinee.getTotalScore() + e.getScore()));

            subjectOfExaminee.stream()
                    .filter(e -> e.getSubject().getDivision().getId() == division.getId())
                    .forEach(e -> examinee.setDivisionScore(examinee.getDivisionScore() + e.getScore()));

            examinee.setStatus(examinee.getTotalScore() >= minTotalScore &&
                    examinee.getDivisionScore() >= division.getMinDivisionScore() ?
                    "PASS" : "FAIL");
        }
        viewListExaminee();

    }

    private void viewListSubject(){
        System.out.println("\n Subject: ");

        System.out.println("+-------------------------------+");
        System.out.format("|%-15s|%-15s|\n", "ID", "Name");
        System.out.println("+-------------------------------+");
        for (Subject subject:subjectList)
            System.out.format("|%-15s|%-15s|\n", subject.getId(),
                    subject.getName(), subject.getDivision());
        System.out.println("+-------------------------------+\n");
    }

    private void viewListDivision(){
        System.out.println("\n Division: ");

        System.out.println("+--------------------------------------------------+");
        System.out.format("|%-15s|%-15s|%-17s|\n", "ID", "Name", "min Division Score");
        System.out.println("+--------------------------------------------------+");
        for (Division division:divisionList)
            System.out.format("|%-15s|%-15s|%-17s|\n", division.getId(),
                    division.getName(), division.getMinDivisionScore());
        System.out.println("+----------------------------------------------------+\n");

    }
    private void viewListExaminee(){
        System.out.println("\n Examinee List: ");

        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-17s|%-15s|\n", "Id", "Division", "Division Score", "Total Score", "Status");
        System.out.println("+---------------------------------------------------------------------------------+");
        for (Examinee examinee:examineeList)
            System.out.format("|%-15s|%-15s|%-15s|%-17s|%-15s|\n", examinee.getId(),
                    examinee.getDivision().getName(), examinee.getDivisionScore(),
                    examinee.getTotalScore(), examinee.getStatus());
        System.out.println("+---------------------------------------------------------------------------------+\n");

    }






}
