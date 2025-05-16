package database.access;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.teacher.Teacher;
import database.connection.ExcelDataBase;

import java.util.ArrayList;
import java.util.Iterator;

public class TeachersAccess implements ExcelAccess {

    private static Sheet teachersSheet;

    public TeachersAccess(Sheet sheet) {
        teachersSheet = sheet;
    }

    public ArrayList<Teacher> getAll() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Iterator<Row> iterator = teachersSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next(); // Пропускаем заголовок
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();
            int teacherId = (int) currRow.getCell(0).getNumericCellValue();
            String fullName = currRow.getCell(1).getStringCellValue();
            int subjectId = (int) currRow.getCell(2).getNumericCellValue();
            String status = currRow.getCell(3).getStringCellValue();

            Teacher teacher = new Teacher(teacherId, fullName, subjectId, status);
            teachers.add(teacher);
        }

        return teachers;
    }

    public void add(Teacher teacher) {
        int newRowIndex = teachersSheet.getLastRowNum() + 1;
        Row newRow = teachersSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(teacher.getFullName());
        newRow.createCell(2).setCellValue(teacher.getSubjectId());
        newRow.createCell(3).setCellValue(teacher.getStatus());

        ExcelDataBase.saveExcelFile();
    }

    public void update(Teacher teacher) {
        int rowIndex = getRowIndex(teacher);
        if (rowIndex != -1) {
            Row row = teachersSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(teacher.getFullName());
            row.getCell(2).setCellValue(teacher.getSubjectId());
            row.getCell(3).setCellValue(teacher.getStatus());

            ExcelDataBase.saveExcelFile();
        }
    }

    public void delete(Teacher teacher) {
        int rowIndex = getRowIndex(teacher);
        if (rowIndex != -1) {
            teachersSheet.removeRow(teachersSheet.getRow(rowIndex));

            if (rowIndex < teachersSheet.getLastRowNum()) {
                teachersSheet.shiftRows(rowIndex + 1, teachersSheet.getLastRowNum(), -1);
            }

            ExcelDataBase.saveExcelFile();
        }
    }

    private int getMaxId() {
        if (teachersSheet.getLastRowNum() < 1) return 0;
        Row lastRow = teachersSheet.getRow(teachersSheet.getLastRowNum());
        return (int) lastRow.getCell(0).getNumericCellValue();
    }

    private int getRowIndex(Teacher teacher) {
        for (int i = 1; i <= teachersSheet.getLastRowNum(); i++) {
            Row row = teachersSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == teacher.getId()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
