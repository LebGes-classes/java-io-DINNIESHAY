package university.lesson;

import database.access.SubjectsAccess;
import database.access.TeachersAccess;
import university.subject.Subject;
import university.teacher.Teacher;

public class Lesson {

    private int subjectId;
    private int groupId;
    private int teacherId;
    private String time;
    private String dayOfWeek;
    private String classroom;

    public Lesson(int subjectId, int groupId, int teacherId, String time, String dayOfWeek, String classroom) {
        this.subjectId = subjectId;
        this.groupId = groupId;
        this.teacherId = teacherId;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.classroom = classroom;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        Subject subject = SubjectsAccess.getById(subjectId);
        Teacher teacher = TeachersAccess.getById(teacherId);

        return String.format(
                "%-15s | %-15s | %-30s | %-15s | %-50s",
                dayOfWeek, time, subject.getName(), classroom, teacher.getFullName()
        );
    }
}
