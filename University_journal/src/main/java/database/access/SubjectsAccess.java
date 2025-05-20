package database.access;

import serialization.JsonDeserializer;
import serialization.JsonSerializer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.grade.Grade;
import university.subject.Subject;
import database.connection.ExcelDataBase;

import java.util.ArrayList;
import java.util.Iterator;

public class SubjectsAccess {

    private SubjectsAccess() {};
    private static Sheet subjectsSheet;
    private static final String jsonFilePath = "University_journal/src/main/java/serialization/jsonfiles/subjects.json";

    public static void init(Sheet sheet) {
        subjectsSheet = sheet;
        writeToJson();
    }

    public static void writeToJson() {
        JsonSerializer.writeToJsonFile(getAll(), jsonFilePath);
    }

    public static void loadFromJson() {
        JsonDeserializer.readFromJsonFile(Subject.class, jsonFilePath);
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

    public static Subject getById(int id) {
        Subject subject = new Subject();

        for (int i = 1; i <= subjectsSheet.getLastRowNum(); i++) {
            Row row = subjectsSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == id) {
                    subject.setId(id);
                    subject.setName(row.getCell(1).getStringCellValue());
                }
            }
        }

        return subject;
    }

    public static Subject getByName(String name) {
        Subject subject = new Subject();

        for (int i = 1; i <= subjectsSheet.getLastRowNum(); i++) {
            Row row = subjectsSheet.getRow(i);
            if (row != null) {
                Cell nameCell = row.getCell(1);
                if (nameCell != null && nameCell.getStringCellValue().equals(name)) {
                    subject.setId((int) row.getCell(0).getNumericCellValue());
                    subject.setName(name);
                }
            }
        }

        return subject;
    }

    public static void add(Subject subject) {
        int newRowIndex = subjectsSheet.getLastRowNum() + 1;
        Row newRow = subjectsSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(subject.getName());

        ExcelDataBase.saveExcelFile();
        writeToJson();
    }

    public static void update(Subject subject) {
        int rowIndex = getRowIndex(subject);
        if (rowIndex != -1) {
            Row row = subjectsSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(subject.getName());

            ExcelDataBase.saveExcelFile();
            writeToJson();
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
            writeToJson();
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
