package university.grade;

import java.io.Serializable;

public class Grade implements Serializable {

    private int id;
    private String value;
    private int subjectId;
    private int studentId;

    public Grade() {}

    public Grade(int id, String value, int subjectId, int studentId) {
        this.id = id;
        this.value = value;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public Grade(String value, int subjectId, int studentId) {
        this.value = value;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Grade " +
                "id: " + id +
                ", value: " + value +
                ", subjectId: " + subjectId +
                ", studentId: " + studentId +
                "\n";
    }
}
