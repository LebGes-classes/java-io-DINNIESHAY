package database.access;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.group.Group;
import database.connection.ExcelDataBase;

import java.util.ArrayList;
import java.util.Iterator;

public class GroupsAccess {

    private static Sheet groupsSheet;

    public GroupsAccess(Sheet sheet) {
        groupsSheet = sheet;
    }

    public static ArrayList<Group> getAll() {
        ArrayList<Group> groups = new ArrayList<>();
        Iterator<Row> iterator = groupsSheet.iterator();

        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Row currRow = iterator.next();

            Cell idCell = currRow.getCell(0);
            Cell nameCell = currRow.getCell(1);

            if (idCell != null && nameCell != null) {
                try {
                    int groupId = (int) idCell.getNumericCellValue();
                    String groupName = nameCell.getStringCellValue();

                    Group group = new Group(groupId, groupName);
                    groups.add(group);
                } catch (Exception e) {
                    System.out.println("Error reading group data at row " + currRow.getRowNum());
                }
            }
        }

        return groups;
    }

    public static void add(Group group) {
        int newRowIndex = groupsSheet.getLastRowNum() + 1;
        Row newRow = groupsSheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(getMaxId() + 1);
        newRow.createCell(1).setCellValue(group.getName());

        ExcelDataBase.saveExcelFile();
    }

    public void update(Group group) {
        int rowIndex = getRowIndex(group);
        if (rowIndex != -1) {
            Row row = groupsSheet.getRow(rowIndex);
            row.getCell(1).setCellValue(group.getName());

            ExcelDataBase.saveExcelFile();
        }
    }

    public void delete(Group group) {
        int rowIndex = getRowIndex(group);
        if (rowIndex != -1) {
            groupsSheet.removeRow(groupsSheet.getRow(rowIndex));

            if (rowIndex < groupsSheet.getLastRowNum()) {
                groupsSheet.shiftRows(rowIndex + 1, groupsSheet.getLastRowNum(), -1);
            }

            ExcelDataBase.saveExcelFile();
        }
    }

    private static int getMaxId() {
        int maxId = 0;
        ArrayList<Group> groups = getAll();
        if (!groups.isEmpty()) {
            maxId = groups.getLast().getId();
        }

        return maxId;
    }

    private int getSize() {
        ArrayList<Group> groups = getAll();
        int size = groups.size();

        return size;
    }

    private int getRowIndex(Group group) {
        int rowIndex = -1;

        for (int i = 1; i <= groupsSheet.getLastRowNum(); i++) {
            Row row = groupsSheet.getRow(i);
            if (row != null) {
                Cell idCell = row.getCell(0);
                if (idCell != null && (int) idCell.getNumericCellValue() == group.getId()) {
                    rowIndex = i;
                }
            }
        }

        return rowIndex;
    }
}
