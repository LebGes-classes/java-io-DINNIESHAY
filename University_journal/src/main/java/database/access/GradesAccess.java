package database.access;

import database.connection.ExcelDataBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.grade.Grade;

import java.util.ArrayList;
import java.util.Iterator;

public class GradesAccess {

    private static Sheet gradesSheet;

    public GradesAccess(Sheet sheet) {
        gradesSheet = sheet;
    }

    public ArrayList<Grade> getAll() {
        ArrayList<Grade> grades = new ArrayList<>();
        Iterator<Row> iterator = gradesSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell nameCell = currRow.getCell(1);

            if (idCell != null && nameCell != null) {
                try {
                    int gradeId = (int) currRow.getCell(0).getNumericCellValue();
                    int subjectId = (int) currRow.getCell(1).getNumericCellValue();
                    int studentId = (int) currRow.getCell(2).getNumericCellValue();

                    Grade grade = new Grade(gradeId, subjectId, studentId);
                    grades.add(grade);
                } catch (Exception e) {
                    System.out.println("Error reading group data at row " + currRow.getRowNum());
                }
            }
        }

        return grades;
    }

    public void add(Grade grade) {
        int newRowIndex = getSize();
        Row newRow = gradesSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(grade.getSubjectId());
        newRow.createCell(2).setCellValue(grade.getStudentId());

        ExcelDataBase.saveExcelFile();
    }

    public void update(Grade grade) {
        int rowIndex = getRowIndex(grade);
        if (rowIndex != -1) {
            Row row = gradesSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(grade.getSubjectId());
            row.getCell(2).setCellValue(grade.getStudentId());

            ExcelDataBase.saveExcelFile();
        }
    }

    public void delete(Grade grade) {
        int rowIndex = getRowIndex(grade);
        if (rowIndex != -1) {
            Row row = gradesSheet.getRow(rowIndex);
            gradesSheet.removeRow(row);

            if (rowIndex < gradesSheet.getLastRowNum()) {
                gradesSheet.shiftRows(rowIndex + 1, gradesSheet.getLastRowNum(), -1);
            }
        }

        ExcelDataBase.saveExcelFile();
    }

    private int getMaxId() {
        int maxId = 0;
        ArrayList<Grade> grades = getAll();
        if (!grades.isEmpty()) {
            maxId = grades.getLast().getId();
        }

        return maxId;
    }

    private int getSize() {
        ArrayList<Grade> grades = getAll();
        int size = grades.size();

        return size;
    }

    private int getRowIndex(Grade grade) {
        int rowIndex = -1;

        for (int i = 1; i <= gradesSheet.getLastRowNum(); i++) {
            Row row = gradesSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == grade.getId()) {
                    rowIndex = i;
                }
            }
        }

        return rowIndex;
    }
}
