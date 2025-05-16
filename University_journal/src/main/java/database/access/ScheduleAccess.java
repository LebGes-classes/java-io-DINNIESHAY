package database.access;

import org.apache.poi.ss.usermodel.Sheet;

public class ScheduleAccess implements ExcelAccess {

    public static Sheet schedulesSheet;

    public ScheduleAccess(Sheet sheet) {
        schedulesSheet = sheet;
    }
}
