package database.connection;

import database.access.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDataBase {

    private static final String FILE_PATH = "University_journal/University_journal.xlsx";
    public static FileInputStream file;
    public static Workbook workbook;

    public static void readExcelFile() {
        try {
            file = new FileInputStream(FILE_PATH);
            workbook = new XSSFWorkbook(file);
            GradesAccess gradesReader = new GradesAccess(workbook.getSheet("Grades"));
            GroupsAccess groupsReader = new GroupsAccess(workbook.getSheet("Groups"));
            ScheduleAccess scheduleReader = new ScheduleAccess(workbook.getSheet("Schedule"));
            StudentsAccess studentsReader = new StudentsAccess(workbook.getSheet("Students"));
            SubjectsAccess subjectsReader = new SubjectsAccess(workbook.getSheet("Subjects"));
            TeachersAccess teachersReader = new TeachersAccess(workbook.getSheet("Teachers"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + e.getMessage());
        }
    }

    public static void closeExcelFile() {
        try {
            file.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveExcelFile() {
        try {
            if (file != null) {
                file.close();
            }

            FileOutputStream outputFile = new FileOutputStream(FILE_PATH);
            workbook.write(outputFile);
            outputFile.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage());
        }
    }
}
