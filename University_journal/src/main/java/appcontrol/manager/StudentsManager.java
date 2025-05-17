package appcontrol.manager;

import appcontrol.visual.services.Services;
import database.access.GroupsAccess;
import database.access.StudentsAccess;
import university.group.Group;
import university.student.Student;

import java.util.ArrayList;

public class StudentsManager {

    public static void addStudent() {
        System.out.println("Enter student's full name:");
        String fullName = Services.getInput();
        System.out.println("Choose student's group:");
        if (GroupsManager.noGroups()) {
            System.out.println("No groups");
            System.out.println("\nPress any key to go back");
            return;
        }

        GroupsManager.printGroups();
        int groupId = Integer.parseInt(Services.getInput());

        Student student = new Student(fullName, groupId, "Studying");
        StudentsAccess.add(student);
    }

    public static void transferStudent() {
        System.out.println("Choose student for transferring:");
        if (noStudents()) {
            System.out.println("No students");
            System.out.println("\nPress any key to go back");
            return;
        }

        printStudents();

        int studentId = Integer.parseInt(Services.getInput());
        Student student = StudentsAccess.getById(studentId);

        System.out.println("Choose his/her new group:");
        if (GroupsManager.noGroups()) {
            System.out.println("No groups");
            System.out.println("\nPress any key to go back");
            return;
        }

        GroupsManager.printGroups();

        int groupId = Integer.parseInt(Services.getInput());
        student.setGroupId(groupId);

        StudentsAccess.update(student);
    }

    public static void deductStudent() {
        System.out.println("Choose student to deduct:");
        if (noStudents()) {
            System.out.println("No students");
            System.out.println("\nPress any key to go back");
            return;
        }

        printStudents();

        int studentId = Integer.parseInt(Services.getInput());
        Student student = StudentsAccess.getById(studentId);
        student.setStatus("Expelled");

        StudentsAccess.update(student);
    }

    public static void sendOnAcademicLeave() {
        System.out.println("Choose student to send on academic leave:");
        if (noStudents()) {
            System.out.println("No students");
            System.out.println("\nPress any key to go back");
            return;
        }

        printStudents();

        int studentId = Integer.parseInt(Services.getInput());
        Student student = StudentsAccess.getById(studentId);

        student.setStatus("On academic leave");

        StudentsAccess.update(student);
    }

    public static void changeStudentName() {
        System.out.println("Choose student to change information about him/her:");
        if (noStudents()) {
            System.out.println("No students");
            System.out.println("\nPress any key to go back");
            return;
        }

        printStudents();

        int studentId = Integer.parseInt(Services.getInput());
        Student student = StudentsAccess.getById(studentId);

        System.out.println("Enter his/her name:");
        student.setFullName(Services.getInput());

        StudentsAccess.update(student);
    }

    public static void printInfo() {
        if (noStudents()) {
            System.out.println("No students");
        } else {
            printStudents();
        }

        System.out.println("\nPress any key to go back");
        Services.getInput();
    }

    private static boolean noStudents() {
        boolean noStudents;

        ArrayList<Student> students = StudentsAccess.getAll();
        noStudents = students.isEmpty();

        return noStudents;
    }

    public static void printStudents() {
        ArrayList<Student> students = StudentsAccess.getAll();
        for (Student student : students) {
            System.out.print(student);
        }
    }
}
