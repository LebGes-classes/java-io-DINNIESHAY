package appcontrol.manager;

import appcontrol.visual.services.Services;
import database.access.GradesAccess;
import database.access.StudentsAccess;
import database.access.SubjectsAccess;
import university.grade.Grade;
import university.student.Student;
import university.subject.Subject;

import java.util.ArrayList;
import java.util.Map;

public class GradesManager {

    public static void addGradesForGroup() {
        System.out.println("Choose subject to add grades:");
        if (SubjectsManager.noSubjects()) {
            System.out.println("No subjects");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        SubjectsManager.printSubjects();
        int subjectId = Integer.parseInt(Services.getInput());

        System.out.println("Choose group to add grades:");
        if (GroupsManager.noGroups()) {
            System.out.println("No groups");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        GroupsManager.printGroups();
        int groupId = Integer.parseInt(Services.getInput());

        ArrayList<Student> students = StudentsAccess.getAllInGroup(groupId);
        System.out.println("Enter grades:\n");
        for (Student student : students) {
            System.out.print(student.getFullName() + ": ");

            String[] values = Services.getInput().split("\\s+");
            for (String value : values) {
                Grade grade = new Grade(value, subjectId, student.getId());
                GradesAccess.add(grade);
            }
        }
    }


    public static void addGradeForStudent() {
        System.out.println("Choose student to add grades:");
        ArrayList<Student> students = StudentsAccess.getAllStudying();
        if (students.isEmpty()) {
            System.out.println("No students");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        for (Student student : students) {
            System.out.print(student);
        }
        int studentId = Integer.parseInt(Services.getInput());
        Student student = StudentsAccess.getById(studentId);

        System.out.println("Enter grades for " + student.getFullName() + ":");

        if (SubjectsManager.noSubjects()) {
            System.out.println("No subjects");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        ArrayList<Subject> subjects = SubjectsAccess.getAll();

        for (Subject subject : subjects) {
            System.out.print(subject.getName() + ": ");

            String[] values = Services.getInput().split("\\s+");
            for (String value : values) {
                if (!value.isEmpty()) {
                    Grade grade = new Grade(value, subject.getId(), student.getId());
                    GradesAccess.add(grade);
                }
            }
        }
    }

    public static void printGradesOfSubject() {
        System.out.println("Choose group to see grades:");
        if (GroupsManager.noGroups()) {
            System.out.println("No groups");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        GroupsManager.printGroups();
        int groupId = Integer.parseInt(Services.getInput());

        System.out.println("Choose subject to see grades:");
        if (SubjectsManager.noSubjects()) {
            System.out.println("No subjects");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        SubjectsManager.printSubjects();
        int subjectId = Integer.parseInt(Services.getInput());

        Map<Integer, ArrayList<String>> gradesOfSubject = GradesAccess.getAllBySubject(subjectId);

        for (Map.Entry<Integer, ArrayList<String>> entry : gradesOfSubject.entrySet()) {
            Student student = StudentsAccess.getById(entry.getKey());
            if (student.getGroupId() == groupId) {
                System.out.println(student.getFullName() + ": " + entry.getValue());
            }
        }
    }

    public static void printGradesOfStudent() {
        System.out.println("Choose student to see grades:");
        ArrayList<Student> students = StudentsAccess.getAllStudying();
        if (students.isEmpty()) {
            System.out.println("No students");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        for (Student student : students) {
            System.out.print(student);
        }
        int studentId = Integer.parseInt(Services.getInput());

        Map<Integer, ArrayList<String>> gradesOfStudent = GradesAccess.getAllByStudent(studentId);

        for (Map.Entry<Integer, ArrayList<String>> entry : gradesOfStudent.entrySet()) {
            Subject subject = SubjectsAccess.getById(entry.getKey());
            System.out.println(subject.getName() + ": " + entry.getValue());
        }
    }
}
