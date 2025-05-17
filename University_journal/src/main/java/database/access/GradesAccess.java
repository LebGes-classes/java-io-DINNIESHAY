package database.access;

import database.connection.ExcelDataBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.grade.Grade;

import java.util.*;

public class GradesAccess {

    private static Sheet gradesSheet;

    public GradesAccess(Sheet sheet) {
        gradesSheet = sheet;
    }

    public static ArrayList<Grade> getAll() {
        ArrayList<Grade> grades = new ArrayList<>();
        Iterator<Row> iterator = gradesSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell valueCell = currRow.getCell(1);

            if (idCell != null && valueCell != null) {
                try {
                    int gradeId = (int) currRow.getCell(0).getNumericCellValue();
                    String value = currRow.getCell(1).getStringCellValue();
                    int subjectId = (int) currRow.getCell(2).getNumericCellValue();
                    int studentId = (int) currRow.getCell(3).getNumericCellValue();

                    Grade grade = new Grade(gradeId, value, subjectId, studentId);
                    grades.add(grade);
                } catch (Exception e) {
                    System.out.println("Error reading group data at row " + currRow.getRowNum());
                }
            }
        }

        return grades;
    }

    public static Grade getById(int id) {
        Grade grade = new Grade();

        for (int i = 1; i <= gradesSheet.getLastRowNum(); i++) {
            Row row = gradesSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == id) {
                    grade.setId(id);
                    grade.setValue(row.getCell(1).getStringCellValue());
                    grade.setSubjectId((int) row.getCell(2).getNumericCellValue());
                    grade.setStudentId((int)row.getCell(3).getNumericCellValue());
                }
            }
        }

        return grade;
    }

    public static Map<Integer, ArrayList<String>> getAllBySubject(int subjectId) {
        Map<Integer, ArrayList<String>> grades = new HashMap<>();
        Iterator<Row> iterator = gradesSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell valueCell = currRow.getCell(1);
            Cell subjectIdCell = currRow.getCell(2);
            Cell studentIdCell = currRow.getCell(3);

            if (idCell != null && valueCell != null && subjectIdCell != null && studentIdCell != null) {
                if ((int) subjectIdCell.getNumericCellValue() == subjectId) {
                    try {
                        int gradeId = (int) currRow.getCell(0).getNumericCellValue();
                        String value = currRow.getCell(1).getStringCellValue();
                        int studentId = (int) currRow.getCell(3).getNumericCellValue();

                        Grade grade = new Grade(gradeId, value, subjectId, studentId);

                        if (grades.containsKey(studentId)) {
                            grades.get(studentId).add(grade.getValue());
                        } else {
                            grades.put(studentId, new ArrayList<>(List.of(new String[]{grade.getValue()})));
                        }
                    } catch (Exception e) {
                        System.out.println("Error reading group data at row " + currRow.getRowNum());
                    }
                }
            }
        }

        return grades;
    }

    public static Map<Integer, ArrayList<String>> getAllByStudent(int studentId) {
        Map<Integer, ArrayList<String>> grades = new HashMap<>();
        Iterator<Row> iterator = gradesSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell valueCell = currRow.getCell(1);
            Cell subjectIdCell = currRow.getCell(2);
            Cell studentIdCell = currRow.getCell(3);

            if (idCell != null && valueCell != null && subjectIdCell != null && studentIdCell != null) {
                if ((int) studentIdCell.getNumericCellValue() == studentId) {
                    try {
                        int gradeId = (int) currRow.getCell(0).getNumericCellValue();
                        String value = currRow.getCell(1).getStringCellValue();
                        int subjectId = (int) currRow.getCell(2).getNumericCellValue();

                        Grade grade = new Grade(gradeId, value, subjectId, studentId);

                        if (grades.containsKey(subjectId)) {
                            grades.get(subjectId).add(grade.getValue());
                        } else {
                            grades.put(subjectId, new ArrayList<>(List.of(new String[]{grade.getValue()})));
                        }
                    } catch (Exception e) {
                        System.out.println("Error reading group data at row " + currRow.getRowNum());
                    }
                }
            }
        }

        return grades;
    }



    public static void add(Grade grade) {
        int newRowIndex = gradesSheet.getLastRowNum() + 1;
        Row newRow = gradesSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(grade.getValue());
        newRow.createCell(2).setCellValue(grade.getSubjectId());
        newRow.createCell(3).setCellValue(grade.getStudentId());

        ExcelDataBase.saveExcelFile();
    }

    public static void update(Grade grade) {
        int rowIndex = getRowIndex(grade);
        if (rowIndex != -1) {
            Row row = gradesSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(grade.getValue());
            row.getCell(2).setCellValue(grade.getSubjectId());
            row.getCell(3).setCellValue(grade.getStudentId());

            ExcelDataBase.saveExcelFile();
        }
    }

    public static void delete(Grade grade) {
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

    private static int getMaxId() {
        int maxId = 0;
        ArrayList<Grade> grades = getAll();
        if (!grades.isEmpty()) {
            maxId = grades.getLast().getId();
        }

        return maxId;
    }

    private static int getSize() {
        ArrayList<Grade> grades = getAll();
        int size = grades.size();

        return size;
    }

    private static int getRowIndex(Grade grade) {
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
