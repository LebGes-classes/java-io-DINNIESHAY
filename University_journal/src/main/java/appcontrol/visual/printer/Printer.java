package appcontrol.visual.printer;

import appcontrol.visual.services.Services;

import java.io.File;

public class Printer {

    public void printMenu() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/menu.txt"));
    }

    public void printGradeManagement() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/grade_management.txt"));
    }

    public void printGroupManagement() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/group_management.txt"));
    }

    public void printScheduleManagement() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/schedule_management.txt"));
    }

    public void printStudentManagement() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/student_management.txt"));
    }

    public void printSubjectManagement() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/subject_management.txt"));
    }

    public void printTeacherManagement() {
        Services.printFile(new File("University_journal/src/main/java/appcontrol/visual/printer/textfiles/teacher_management.txt"));
    }
}
