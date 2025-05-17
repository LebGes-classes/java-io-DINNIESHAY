package database.access;

import org.apache.poi.ss.usermodel.Sheet;
import university.schedule.Schedule;

import java.util.ArrayList;

public class ScheduleAccess {

    public static Sheet schedulesSheet;

    public ScheduleAccess(Sheet sheet) {
        schedulesSheet = sheet;
    }
}
