package database.access;

import serialization.JsonDeserializer;
import serialization.JsonSerializer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import university.grade.Grade;
import university.lesson.Lesson;
import university.subject.Subject;
import university.teacher.Teacher;

import java.util.ArrayList;

public class ScheduleAccess {

    private ScheduleAccess() {};
    public static Sheet schedulesSheet;
    private static final String jsonFilePath = "University_journal/src/main/java/serialization/jsonfiles/lessons.json";

    public static void init(Sheet sheet) {
        schedulesSheet = sheet;
        writeToJson();
    }

    public static void writeToJson() {
        JsonSerializer.writeToJsonFile(getAll(), jsonFilePath);
    }

    public static void loadFromJson() {
        JsonDeserializer.readFromJsonFile(Lesson.class, jsonFilePath);
    }

    public static ArrayList<Lesson> getAll() {
        ArrayList<Lesson> lessons = new ArrayList<>();

        if (schedulesSheet == null) {
            return lessons;
        }

        Row headerRow = schedulesSheet.getRow(0);
        for (int columnIndex = 1; columnIndex < headerRow.getLastCellNum(); columnIndex++) {
            for (int rowIndex = 1; rowIndex <= schedulesSheet.getLastRowNum(); rowIndex++) {
                Row row = schedulesSheet.getRow((rowIndex));
                if (row == null) {
                    continue;
                }

                String time = row.getCell(0).getStringCellValue();
                String lessonInfo = null;

                if (row.getCell(columnIndex) != null) {
                    lessonInfo = row.getCell(columnIndex).getStringCellValue();
                }

                if (lessonInfo != null) {
                    String[] parts = lessonInfo.split("\\n");
                    if (parts.length >= 3) {
                        String subjectName = parts[0].trim();
                        String teacherName = parts[1].trim();
                        String classroom = parts[2].trim();

                        Subject subject = SubjectsAccess.getByName(subjectName);
                        int groupId = (int) headerRow.getCell(columnIndex).getNumericCellValue();
                        Teacher teacher = TeachersAccess.getByName(teacherName);
                        String dayOfWeek = getDayOfWeek(rowIndex);

                        Lesson lesson = new Lesson(subject.getId(), groupId, teacher.getId(), time, dayOfWeek, classroom);
                        lessons.add(lesson);
                    }
                }
            }
        }

        return lessons;
    }

    public static ArrayList<Lesson> getLessonsOfGroup(int groupId) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        if (schedulesSheet == null) {
            return lessons;
        }

        int groupColumn = -1;
        Row headerRow = schedulesSheet.getRow(0);
        for (int i = 1; i < headerRow.getLastCellNum() && groupColumn == -1; i++) {
            if (groupId == (int) headerRow.getCell(i).getNumericCellValue()) {
                groupColumn = i;
            }
        }

        if (groupColumn == -1) {
            return lessons;
        }

        for (int rowIndex = 1; rowIndex <= schedulesSheet.getLastRowNum(); rowIndex++) {
            Row row = schedulesSheet.getRow((rowIndex));
            if (row == null) {
                continue;
            }

            String time = row.getCell(0).getStringCellValue();
            String lessonInfo = null;
            if (row.getCell(groupColumn) != null) {
                lessonInfo = row.getCell(groupColumn).getStringCellValue();
            }

            if (lessonInfo != null) {
                String[] parts = lessonInfo.split("\\n");
                if (parts.length >= 3) {
                    String subjectName = parts[0].trim();
                    String teacherName = parts[1].trim();
                    String classroom = parts[2].trim();

                    Subject subject = SubjectsAccess.getByName(subjectName);
                    Teacher teacher = TeachersAccess.getByName(teacherName);
                    String dayOfWeek = getDayOfWeek(rowIndex);

                    Lesson lesson = new Lesson(subject.getId(), groupId, teacher.getId(), time, dayOfWeek, classroom);
                    lessons.add(lesson);
                }
            }
        }

        return lessons;
    }

    public static String getDayOfWeek(int row) {
        String day = null;

        if (row >= 1 && row <= 7) {
            day = "Понедельник";
        } else if (row >= 8 && row <= 14) {
            day = "Вторник";
        } else if (row >= 15 && row <= 21) {
            day = "Среда";
        } else if (row >= 22 && row <= 28) {
            day = "Четверг";
        } else if (row >= 29 && row <= 35) {
            day = "Пятница";
        } else if (row >= 35 && row <= 42) {
            day = "Суббота";
        }

        return day;
    }
}
