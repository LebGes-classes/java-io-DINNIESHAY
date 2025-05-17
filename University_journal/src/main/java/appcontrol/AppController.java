package appcontrol;

import appcontrol.manager.*;
import appcontrol.visual.printer.Printer;
import appcontrol.visual.services.Services;
import database.connection.ExcelDataBase;

public class AppController {

    Printer printer = new Printer();

    public void openApp() {

        ExcelDataBase.readExcelFile();

        boolean isRunning = true;

        while(isRunning) {
            printer.printMenu();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    manageStudents();
                    break;
                case "2":
                    manageGroups();
                    break;
                case "3":
                    manageGrades();
                    break;
                case "4":
                    manageTeachers();
                    break;
                case "5":
                    manageSubjects();
                    break;
                case "6":
                    manageSchedule();
                    break;
                case "E", "e", "У", "у":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again.\n");
            }
        }
        System.out.println("Exiting the program...\n");
    }

    private void manageStudents() {
        boolean managing = true;

        while (managing) {
            printer.printStudentManagement();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    StudentsManager.addStudent();
                    break;
                case "2":
                    StudentsManager.transferStudent();
                    break;
                case "3":
                    StudentsManager.deductStudent();
                    break;
                case "4":
                    StudentsManager.sendOnAcademicLeave();
                    break;
                case "5":
                    StudentsManager.changeStudentName();
                    break;
                case "6":
                    StudentsManager.printInfo();
                    break;
                case "B", "b", "И", "и":
                    managing = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again\n");
            }
        }
    }

    private void manageGroups() {
        boolean managing = true;

        while (managing) {
            printer.printGroupManagement();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    GroupsManager.addGroup();
                    break;
                case "2":
                    GroupsManager.printInfo();
                    break;
                case "B", "b", "И", "и":
                    managing = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again\n");
            }
        }
    }

    private void manageGrades() {
        boolean managing = true;

        while (managing) {
            printer.printGradeManagement();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    GradesManager.addGradesForGroup();
                    break;
                case "2":
                    GradesManager.addGradeForStudent();
                    break;
                case "3":
                    GradesManager.printGradesOfSubject();
                    break;
                case "4":
                    GradesManager.printGradesOfStudent();
                    break;
                case "B", "b", "И", "и":
                    managing = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again\n");
            }
        }
    }

    private void manageTeachers() {
        boolean managing = true;

        while (managing) {
            printer.printTeacherManagement();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    TeachersManager.hireTeacher();
                    break;
                case "2":
                    TeachersManager.fireTeacher();
                    break;
                case "3":
                    TeachersManager.changeName();
                    break;
                case "4":
                    TeachersManager.changeSubject();
                    break;
                case "5":
                    TeachersManager.printInfo();
                    break;
                case "B", "b", "И", "и":
                    managing = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again\n");
            }
        }
    }

    private void manageSubjects() {
        boolean managing = true;

        while (managing) {
            printer.printSubjectManagement();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    SubjectsManager.addSubject();
                    break;
                case "2":
                    SubjectsManager.printInfo();
                    break;
                case "B", "b", "И", "и":
                    managing = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again\n");
            }
        }
    }

    private void manageSchedule() {
        boolean managing = true;

        while (managing) {
            printer.printScheduleManagement();
            String choice = Services.getInput();

            switch (choice) {
                case "1":
                    ScheduleManager.changeSchedule();
                    break;
                case "2":
                    ScheduleManager.printScheduleForGroup();
                    break;
                case "3":
                    ScheduleManager.printScheduleForTeacher();
                    break;
                case "B", "b", "И", "и":
                    managing = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again\n");
            }
        }
    }
}
