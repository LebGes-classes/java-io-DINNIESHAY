package appcontrol.manager;

import appcontrol.visual.services.Services;
import database.access.SubjectsAccess;
import university.subject.Subject;

import java.util.ArrayList;

public class SubjectsManager {

    public static void addSubject() {
        System.out.println("Enter new subject name:");
        Subject subject = new Subject(Services.getInput());
        SubjectsAccess.add(subject);
    }

    public static void printInfo() {
        if (noSubjects()) {
            System.out.println("No subjects");
        } else {
            printSubjects();
        }

        System.out.println("\nPress any key to go back");
        Services.getInput();
    }

    public static boolean noSubjects() {
        ArrayList<Subject> subjects = SubjectsAccess.getAll();
        boolean noSubjects = subjects.isEmpty();

        return noSubjects;
    }

    public static void printSubjects() {
        ArrayList<Subject> subjects = SubjectsAccess.getAll();
        for (Subject subject : subjects) {
            System.out.print(subject);
        }
    }
}
