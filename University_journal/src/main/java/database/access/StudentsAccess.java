package database.access;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.student.Student;
import database.connection.ExcelDataBase;

import java.util.ArrayList;
import java.util.Iterator;

public class StudentsAccess {

    private static Sheet studentsSheet;

    public StudentsAccess(Sheet sheet) {
        studentsSheet = sheet;
    }

    public static ArrayList<Student> getAll() {
        ArrayList<Student> students = new ArrayList<>();
        Iterator<Row> iterator = studentsSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell nameCell = currRow.getCell(1);

            if (idCell != null && nameCell != null) {
                try {
                    int studentId = (int) currRow.getCell(0).getNumericCellValue();
                    String fullName = currRow.getCell(1).getStringCellValue();
                    int groupId = (int) currRow.getCell(2).getNumericCellValue();
                    String status = currRow.getCell(3).getStringCellValue();

                    Student student = new Student(studentId, fullName, groupId, status);
                    students.add(student);
                } catch (Exception e) {
                    System.out.println("Error reading group data at row " + currRow.getRowNum());
                }
            }
        }

        return students;
    }

    public static Student getById(int id) {
        Student student = new Student();

        for (int i = 1; i <= studentsSheet.getLastRowNum(); i++) {
            Row row = studentsSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == id) {
                    student.setId(id);
                    student.setFullName(row.getCell(1).getStringCellValue());
                    student.setGroupId((int) row.getCell(2).getNumericCellValue());
                    student.setStatus(row.getCell(3).getStringCellValue());
                }
            }
        }

        return student;
    }

    public static void add(Student student) {
        int newRowIndex = studentsSheet.getLastRowNum() + 1;
        Row newRow = studentsSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(student.getFullName());
        newRow.createCell(2).setCellValue(student.getGroupId());
        newRow.createCell(3).setCellValue(student.getStatus());

        ExcelDataBase.saveExcelFile();
    }

    public static void update(Student student) {
        int rowIndex = getRowIndex(student);
        if (rowIndex != -1) {
            Row row = studentsSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(student.getFullName());
            row.getCell(2).setCellValue(student.getGroupId());
            row.getCell(3).setCellValue(student.getStatus());

            ExcelDataBase.saveExcelFile();
        }
    }

    public static void delete(Student student) {
        int rowIndex = getRowIndex(student);
        if (rowIndex != -1) {
            studentsSheet.removeRow(studentsSheet.getRow(rowIndex));

            if (rowIndex < studentsSheet.getLastRowNum()) {
                studentsSheet.shiftRows(rowIndex + 1, studentsSheet.getLastRowNum(), -1);
            }

            ExcelDataBase.saveExcelFile();
        }
    }

    private static int getMaxId() {
        int maxId = 0;
        ArrayList<Student> students = getAll();
        if (!students.isEmpty()) {
            maxId = students.getLast().getId();
        }

        return maxId;
    }

    private static int getRowIndex(Student student) {
        for (int i = 1; i <= studentsSheet.getLastRowNum(); i++) {
            Row row = studentsSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == student.getId()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
