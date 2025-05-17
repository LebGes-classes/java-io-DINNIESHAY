package database.access;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.subject.Subject;
import database.connection.ExcelDataBase;

import java.util.ArrayList;
import java.util.Iterator;

public class SubjectsAccess {

    private static Sheet subjectsSheet;

    public SubjectsAccess(Sheet sheet) {
        subjectsSheet = sheet;
    }

    public static ArrayList<Subject> getAll() {
        ArrayList<Subject> subjects = new ArrayList<>();
        Iterator<Row> iterator = subjectsSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell nameCell = currRow.getCell(1);

            if (idCell != null && nameCell != null) {
                try {
                    int subjectId = (int) currRow.getCell(0).getNumericCellValue();
                    String subjectName = currRow.getCell(1).getStringCellValue();

                    Subject subject = new Subject(subjectId, subjectName);
                    subjects.add(subject);
                } catch (Exception e) {
                    System.out.println("Error reading group data at row " + currRow.getRowNum());
                }
            }
        }

        return subjects;
    }

    public static void add(Subject subject) {
        int newRowIndex = subjectsSheet.getLastRowNum() + 1;
        Row newRow = subjectsSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(subject.getName());

        ExcelDataBase.saveExcelFile();
    }

    public static void update(Subject subject) {
        int rowIndex = getRowIndex(subject);
        if (rowIndex != -1) {
            Row row = subjectsSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(subject.getName());

            ExcelDataBase.saveExcelFile();
        }
    }

    public static void delete(Subject subject) {
        int rowIndex = getRowIndex(subject);
        if (rowIndex != -1) {
            subjectsSheet.removeRow(subjectsSheet.getRow(rowIndex));

            if (rowIndex < subjectsSheet.getLastRowNum()) {
                subjectsSheet.shiftRows(rowIndex + 1, subjectsSheet.getLastRowNum(), -1);
            }

            ExcelDataBase.saveExcelFile();
        }
    }

    private static int getMaxId() {
        int maxId = 0;
        ArrayList<Subject> subjects = getAll();
        if (!subjects.isEmpty()) {
            maxId = subjects.getLast().getId();
        }

        return maxId;
    }

    private static int getRowIndex(Subject subject) {
        for (int i = 1; i <= subjectsSheet.getLastRowNum(); i++) {
            Row row = subjectsSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == subject.getId()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
