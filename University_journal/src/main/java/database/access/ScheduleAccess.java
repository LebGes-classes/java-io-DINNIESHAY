package database.access;

import org.apache.poi.ss.usermodel.Sheet;
import university.schedule.Schedule;

import java.util.ArrayList;

public class ScheduleAccess implements ExcelAccess<Schedule> {

    public static Sheet schedulesSheet;

    public ScheduleAccess(Sheet sheet) {
        schedulesSheet = sheet;
    }

    public ArrayList<Schedule> getAll() {
    }

    public void add(Schedule object) {

    }

    public void update(Schedule object) {

    }

    public void delete(Schedule object) {

    }
}
