package appcontrol.manager;

import appcontrol.visual.services.Services;
import database.access.TeachersAccess;
import university.teacher.Teacher;

import java.util.ArrayList;

public class TeachersManager {

    public static void hireTeacher() {
        System.out.println("Enter new teacher's full name:");
        String fullName = Services.getInput();

        System.out.println("Choose teacher's subject");
        if (SubjectsManager.noSubjects()) {
            System.out.println("No subjects");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        SubjectsManager.printSubjects();
        int subjectId = Integer.parseInt(Services.getInput());

        Teacher teacher = new Teacher(fullName, subjectId, "Working");
        TeachersAccess.add(teacher);
    }

    public static void fireTeacher() {
        System.out.println("Choose teacher to fire:");
        if (noTeachers()) {
            System.out.println("No teachers");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }

        printTeachers();

        int teacherId = Integer.parseInt(Services.getInput());
        Teacher teacher = TeachersAccess.getById(teacherId);

        teacher.setStatus("Fired");

        TeachersAccess.update(teacher);
    }

    public static void changeName() {
        System.out.println("Choose teacher to change his/her name:");
        if (noTeachers()) {
            System.out.println("No teachers");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }

        printTeachers();

        int teacherId = Integer.parseInt(Services.getInput());
        Teacher teacher = TeachersAccess.getById(teacherId);

        System.out.println("Enter his/her name:");
        teacher.setFullName(Services.getInput());

        TeachersAccess.update(teacher);
    }

    public static void changeSubject() {
        System.out.println("Choose teacher to change his/her subject:");
        if (noTeachers()) {
            System.out.println("No teachers");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }

        printTeachers();

        int teacherId = Integer.parseInt(Services.getInput());
        Teacher teacher = TeachersAccess.getById(teacherId);

        System.out.println("Choose his/her subject:");
        if (SubjectsManager.noSubjects()) {
            System.out.println("No subjects");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        SubjectsManager.printSubjects();

        teacher.setSubjectId(Integer.parseInt(Services.getInput()));

        TeachersAccess.update(teacher);
    }

    public static void printInfo() {
        if (noTeachers()) {
            System.out.println("No teachers");
        }

        printTeachers();

        System.out.println("\nPress any key to go back");
        Services.getInput();
    }

    public static boolean noTeachers() {
        ArrayList<Teacher> teachers = TeachersAccess.getAll();
        boolean noTeachers = teachers.isEmpty();

        return noTeachers;
    }

    public static void printTeachers() {
        ArrayList<Teacher> teachers = TeachersAccess.getAll();
        for (Teacher teacher : teachers) {
            System.out.print(teacher);
        }
    }
}
